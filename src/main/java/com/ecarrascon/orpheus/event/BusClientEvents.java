package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.client.ViperRenderer;
import com.ecarrascon.orpheus.entity.client.ModelLayers;
import com.ecarrascon.orpheus.entity.client.ViperModel;
import com.ecarrascon.orpheus.item.setting.BowProperties;
import com.ecarrascon.orpheus.registry.EntitiesRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@EventBusSubscriber(modid = Orpheus.MOD_ID, value = Dist.CLIENT)
public class BusClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(BowProperties::addCustomBowProperties);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.VIPER_LAYER, ViperModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntitiesRegistry.VIPER.get(), ViperRenderer::new);
    }
}
