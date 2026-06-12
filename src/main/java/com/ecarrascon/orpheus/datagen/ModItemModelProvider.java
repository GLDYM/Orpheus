package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Orpheus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(BlocksRegistry.EPIPHANY_TABLE.get());
        simpleBlockItem(BlocksRegistry.MOLY_HERB.get());
        simpleBlockItem(BlocksRegistry.MYTHOS_BLOCK.get());
        simpleBlockItem(BlocksRegistry.PEGASUS_FEATHERS_BLOCK.get());
        simpleBlockItem(BlocksRegistry.TEARS_OF_HADES_BLOCK.get());
        simpleBlockItem(BlocksRegistry.TEARS_OF_HADES_ORE.get());
        simpleBlockItem(BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get());

        basic(
                ItemsRegistry.APOLLOS_SON.get(),
                ItemsRegistry.ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT.get(),
                ItemsRegistry.BROTOI_NECTAR.get(),
                ItemsRegistry.BROTOI_PALLADIUM.get(),
                ItemsRegistry.CALLIOPES_LOVE.get(),
                ItemsRegistry.CALLIOPE_POEM_FRAGMENT.get(),
                ItemsRegistry.CLEAN_COW_GUT.get(),
                ItemsRegistry.COOKED_COW_GUT.get(),
                ItemsRegistry.COW_GUT.get(),
                ItemsRegistry.HELLENIC_CODEX.get(),
                ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get(),
                ItemsRegistry.HEPHAESTUS_BOOTS.get(),
                ItemsRegistry.HEPHAESTUS_CHESTPLATE.get(),
                ItemsRegistry.HEPHAESTUS_HELMET.get(),
                ItemsRegistry.HEPHAESTUS_LEGGINGS.get(),
                ItemsRegistry.HERACLITIAN_FLUX_FRAGMENT.get(),
                ItemsRegistry.HERACLITIAN_FLUX_POTION.get(),
                ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get(),
                ItemsRegistry.HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT.get(),
                ItemsRegistry.LYRE.get(),
                ItemsRegistry.NECTAR_SEED.get(),
                ItemsRegistry.ORPHEUS_LYRE.get(),
                ItemsRegistry.PALLADIUM_WOODEN_FRAGMENT.get(),
                ItemsRegistry.PANDORAS_PITHOS.get(),
                ItemsRegistry.PEGASUS_FEATHER.get(),
                ItemsRegistry.PLAIN_STRING.get(),
                ItemsRegistry.PLATOS_REPUBLIC_SCROLL_FRAGMENT.get(),
                ItemsRegistry.STOIC_MEDITATIVE_STONE.get(),
                ItemsRegistry.TEARS_OF_HADES.get(),
                ItemsRegistry.THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT.get()
        );

        bowModel();
    }

    @SafeVarargs
    private void basic(Item... items) {
        for (Item item : items) {
            basicItem(item);
        }
    }

    private void bowModel() {
        ModelFile pulling0 = getBuilder("tears_bow_pulling_0")
                .parent(new ModelFile.UncheckedModelFile(modLoc("item/tears_bow")))
                .texture("layer0", modLoc("item/tears_bow_pulling_0"));
        ModelFile pulling1 = getBuilder("tears_bow_pulling_1")
                .parent(new ModelFile.UncheckedModelFile(modLoc("item/tears_bow")))
                .texture("layer0", modLoc("item/tears_bow_pulling_1"));
        ModelFile pulling2 = getBuilder("tears_bow_pulling_2")
                .parent(new ModelFile.UncheckedModelFile(modLoc("item/tears_bow")))
                .texture("layer0", modLoc("item/tears_bow_pulling_2"));

        getBuilder("tears_bow")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", itemTexture("tears_bow"))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(-80, 260, -40)
                .translation(-1.0F, -2.0F, 2.5F)
                .scale(0.9F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(-80, -280, 40)
                .translation(-1.0F, -2.0F, 2.5F)
                .scale(0.9F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, -90, 25)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 90, -25)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1F).model(pulling0).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1F).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.65F).model(pulling1).end()
                .override().predicate(ResourceLocation.withDefaultNamespace("pulling"), 1F).predicate(ResourceLocation.withDefaultNamespace("pull"), 0.9F).model(pulling2).end();
    }

    private ResourceLocation itemTexture(String path) {
        return modLoc("item/" + path);
    }

}
