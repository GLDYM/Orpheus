package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.entity.client.ViperRenderer;
import com.ecarrascon.orpheus.item.setting.BowProperties;
import com.ecarrascon.orpheus.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public Orpheus(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigDataCommon.SPEC, "orpheus-config.toml");
        TabRegistry.CREATIVE_MODE_TAB.register(modEventBus);
        BlocksRegistry.BLOCKS.register(modEventBus);
        ItemsRegistry.ITEMS.register(modEventBus);
        VillagersRegistry.register(modEventBus);
        LootRegistry.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        EntitiesRegistry.ENTITY_TYPES.register(modEventBus);
        SoundsRegistry.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

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

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent.
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntitiesRegistry.VIPER.get(), ViperRenderer::new);

            BowProperties.addCustomBowProperties();
        }
    }
}
