package com.ecarrascon.orpheus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import java.util.concurrent.CompletableFuture;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModBiomeTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new OrpheusAdvancementProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModCuriosProvider(packOutput, existingFileHelper, lookupProvider));

        generator.addProvider(event.includeServer(), new OreGenProvider(packOutput, lookupProvider));


    }
}
