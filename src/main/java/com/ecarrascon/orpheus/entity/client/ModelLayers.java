package com.ecarrascon.orpheus.entity.client;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelLayers {
    public static final ModelLayerLocation VIPER_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, "viper_layer"), "main");
}
