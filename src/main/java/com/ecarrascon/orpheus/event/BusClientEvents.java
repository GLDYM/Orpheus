package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.item.setting.BowProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class BusClientEvents {
    private BusClientEvents() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(BusClientEvents::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(BowProperties::addCustomBowProperties);
    }
}
