package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import com.ecarrascon.orpheus.registry.EntitiesRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;


@EventBusSubscriber(modid = Orpheus.MOD_ID)
public class BusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitiesRegistry.VIPER.get(), ViperEntity.createAttributes().build());
    }
}
