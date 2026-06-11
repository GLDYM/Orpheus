package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class OrpheusArmorMaterials {
    public static final Holder<ArmorMaterial> HEPHAESTUS = Holder.direct(
            new ArmorMaterial(
                    createDefenseMap(3, 6, 8, 3),
                    25,
                    SoundEvents.ARMOR_EQUIP_NETHERITE,
                    () -> Ingredient.of(ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get()),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, "hephaestus"))),
                    3.5f,
                    0.0f
            )
    );

    private OrpheusArmorMaterials() {
    }

    private static Map<ArmorItem.Type, Integer> createDefenseMap(int boots, int leggings, int chestplate, int helmet) {
        Map<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
        defense.put(ArmorItem.Type.BOOTS, boots);
        defense.put(ArmorItem.Type.LEGGINGS, leggings);
        defense.put(ArmorItem.Type.CHESTPLATE, chestplate);
        defense.put(ArmorItem.Type.HELMET, helmet);
        defense.put(ArmorItem.Type.BODY, 0);
        return defense;
    }
}
