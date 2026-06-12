package com.ecarrascon.orpheus.datagen.advancement;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class OrpheusGuideAdvancements implements AdvancementProvider.AdvancementGenerator {
    private static final ResourceLocation ROOT_BACKGROUND = ResourceLocation.withDefaultNamespace(
            "textures/gui/advancements/backgrounds/adventure.png");

    @Override
    public void generate(
            HolderLookup.Provider provider,
            Consumer<AdvancementHolder> consumer,
            ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ItemsRegistry.LYRE.get(),
                        Component.translatable("advancements.orpheus.obtain_fragment.title"),
                        Component.translatable("advancements.orpheus.obtain_fragment.description"),
                        ROOT_BACKGROUND,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(consumer, rl("guide/root"), existingFileHelper);

        AdvancementHolder codex = chapter(root, consumer, existingFileHelper, "guide/obtain_codex",
                ItemsRegistry.HELLENIC_CODEX.get(), "advancements.orpheus.obtain_codex");
        AdvancementHolder calliopes = chapter(codex, consumer, existingFileHelper, "guide/obtain_calliopes",
                ItemsRegistry.CALLIOPES_LOVE.get(), "advancements.orpheus.obtain_calliopes");
        AdvancementHolder son = chapter(calliopes, consumer, existingFileHelper, "guide/obtain_son",
                ItemsRegistry.APOLLOS_SON.get(), "advancements.orpheus.obtain_son");
        chapter(son, consumer, existingFileHelper, "guide/obtain_lyre",
                ItemsRegistry.ORPHEUS_LYRE.get(), "advancements.orpheus.obtain_lyre");

        chapter(root, consumer, existingFileHelper, "guide/obtain_gut",
                ItemsRegistry.COW_GUT.get(), "advancements.orpheus.obtain_gut");
        chapter(root, consumer, existingFileHelper, "guide/obtain_mythos",
                ItemsRegistry.MYTHOS_BLOCK.get(), "advancements.orpheus.obtain_mythos");
        chapter(root, consumer, existingFileHelper, "guide/obtain_tear",
                ItemsRegistry.TEARS_OF_HADES.get(), "advancements.orpheus.obtain_tear");
    }

    private static AdvancementHolder chapter(
            AdvancementHolder parent,
            Consumer<AdvancementHolder> consumer,
            ExistingFileHelper existingFileHelper,
            String path,
            ItemLike criterionItem,
            String translationBase) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        criterionItem,
                        Component.translatable(translationBase + ".title"),
                        Component.translatable(translationBase + ".description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, rl(path), existingFileHelper);
    }

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, path);
    }
}
