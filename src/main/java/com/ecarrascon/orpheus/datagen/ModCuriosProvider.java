package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class ModCuriosProvider extends CuriosDataProvider {
    public ModCuriosProvider(PackOutput output, ExistingFileHelper fileHelper,
                             CompletableFuture<HolderLookup.Provider> registries) {
        super(Orpheus.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        createSlot("charm")
                .replace(false)
                .order(200)
                .size(1)
                .operation("SET")
                .useNativeGui(true)
                .addCosmetic(false)
                .renderToggle(true)
                .dropRule(ICurio.DropRule.DEFAULT)
                .icon(ResourceLocation.fromNamespaceAndPath("curios", "slot/empty_charm_slot"));

        createEntities("player")
                .replace(false)
                .addEntities(EntityType.PLAYER)
                .addSlots("charm");
    }
}
