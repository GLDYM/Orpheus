package com.ecarrascon.orpheus.compat.jei;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, "jei_plugin");

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(List.of(new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get())), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.orpheus_lyre.line1"),
                Component.translatable("jei.orpheus.orpheus_lyre.line2"));

        registration.addIngredientInfo(List.of(new ItemStack(ItemsRegistry.CALLIOPES_LOVE.get())), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.calliopes_love.line1"),
                Component.translatable("jei.orpheus.calliopes_love.line2"));

        registration.addIngredientInfo(List.of(new ItemStack(ItemsRegistry.BROTOI_NECTAR.get())), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.brotoi_nectar.line1"));

        registration.addIngredientInfo(List.of(new ItemStack(ItemsRegistry.COW_GUT.get())), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.cow_gut.line1"));

        registration.addIngredientInfo(List.of(new ItemStack(ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get())), VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.hephaestus_armor_fragment.line1"),
                Component.translatable("jei.orpheus.hephaestus_armor_fragment.line2"));

        registration.addIngredientInfo(List.of(
                        new ItemStack(ItemsRegistry.HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.PLATOS_REPUBLIC_SCROLL_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.CALLIOPE_POEM_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.STOIC_MEDITATIVE_STONE.get())),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.philosopher_chest.line1"),
                Component.translatable("jei.orpheus.philosopher_chest.line2"));

        registration.addIngredientInfo(List.of(
                        new ItemStack(ItemsRegistry.MOLY_HERB.get()),
                        new ItemStack(ItemsRegistry.PEGASUS_FEATHER.get()),
                        new ItemStack(ItemsRegistry.PALLADIUM_WOODEN_FRAGMENT.get()),
                        new ItemStack(ItemsRegistry.NECTAR_SEED.get()),
                        new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_POTION.get()),
                        new ItemStack(ItemsRegistry.PANDORAS_PITHOS.get()),
                        new ItemStack(ItemsRegistry.TEARS_OF_HADES.get())),
                VanillaTypes.ITEM_STACK,
                Component.translatable("jei.orpheus.philosopher_trade.line1"),
                Component.translatable("jei.orpheus.philosopher_trade.line2"));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }
}
