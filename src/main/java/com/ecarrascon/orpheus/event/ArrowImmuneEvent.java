package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;


@EventBusSubscriber(modid = Orpheus.MOD_ID)
public class ArrowImmuneEvent {
    @SubscribeEvent
    public static void allowDamage(LivingIncomingDamageEvent event) {
        if (ConfigDataCommon.ORPHEUS_LYRE_POWER.get().matches(".*(?:protect|both).*")
                && event.getEntity() instanceof Player player
                && event.getSource().is(DamageTypes.ARROW)
                && player.getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance())) {
            event.setAmount(0);
        }
    }
}
