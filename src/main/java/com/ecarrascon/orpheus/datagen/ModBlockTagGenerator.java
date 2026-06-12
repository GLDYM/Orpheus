package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Orpheus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(tag("orpheus", "tears_of_hades_ores"))
                .add(BlocksRegistry.TEARS_OF_HADES_ORE.get(), BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get());

        tag(tag("c", "ores"))
                .addTag(tag("orpheus", "tears_of_hades_ores"));

        tag(BlockTags.FLOWERS).add(BlocksRegistry.MOLY_HERB.get());
        tag(BlockTags.FLOWER_POTS).add(BlocksRegistry.POTTED_MOLY_HERB.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlocksRegistry.TEARS_OF_HADES_BLOCK.get(), BlocksRegistry.TEARS_OF_HADES_ORE.get(), BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlocksRegistry.TEARS_OF_HADES_BLOCK.get(), BlocksRegistry.TEARS_OF_HADES_ORE.get(), BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get(), BlocksRegistry.EPIPHANY_TABLE.get());
    }

    static TagKey<Block> tag(String namespace, String path) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
