package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.registry.loot.AddItemModifier;
import com.ecarrascon.orpheus.registry.loot.AddSusSandItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Orpheus.MOD_ID);
    }

    @Override
    protected void start() {
        add("cow_gut_from_cow", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/cow")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()},
                ItemsRegistry.COW_GUT.get()));

        add("scute_from_turtle", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/turtle")).build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()},
                Items.TURTLE_SCUTE));

        add("armor_fragment_from_suspicious_sand", new AddSusSandItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid")).build()},
                ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get()));
    }
}
