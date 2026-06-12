package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.compat.curios.CuriosCompat;
import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.datagen.DataGenerators;
import com.ecarrascon.orpheus.event.BusClientEvents;
import com.ecarrascon.orpheus.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Orpheus.MOD_ID)
public class Orpheus {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "orpheus";

    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogUtils.getLogger();

    public Orpheus(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigDataCommon.SPEC, "orpheus-config.toml");
        TabRegistry.CREATIVE_MODE_TAB.register(modEventBus);
        BlocksRegistry.BLOCKS.register(modEventBus);
        ItemsRegistry.ITEMS.register(modEventBus);
        VillagersRegistry.register(modEventBus);
        LootRegistry.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::gatherData);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            BusClientEvents.register(modEventBus);
        }
        CuriosCompat.registerEventHandlers();

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlocksRegistry.MOLY_HERB.getId(), BlocksRegistry.POTTED_MOLY_HERB);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
