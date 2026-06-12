package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.datagen.advancement.OrpheusGuideAdvancements;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrpheusAdvancementProvider extends AdvancementProvider {
    public OrpheusAdvancementProvider(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, existingFileHelper, List.of(new OrpheusGuideAdvancements()));
    }
}
