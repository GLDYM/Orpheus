package com.ecarrascon.orpheus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public ModBiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "orpheus", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(tag("orpheus", "has_structure/philosopher_pillager_outpost"))
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_TAIGA)
                .add(Biomes.DESERT, Biomes.PLAINS, Biomes.SNOWY_PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA);
    }

    private static TagKey<Biome> tag(String namespace, String path) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
