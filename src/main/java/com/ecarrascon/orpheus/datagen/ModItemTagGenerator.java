package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, "orpheus", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(tag("c", "bows")).add(ItemsRegistry.TEARS_BOW.get());
        tag(tag("c", "seeds")).add(ItemsRegistry.NECTAR_SEED.get());
        tag(tag("c", "string")).add(ItemsRegistry.PLAIN_STRING.get());
        tag(tag("c", "strings")).add(ItemsRegistry.PLAIN_STRING.get());
        tag(ItemTags.SMALL_FLOWERS).add(BlocksRegistry.MOLY_HERB.asItem());
    }

    private static TagKey<Item> tag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}
