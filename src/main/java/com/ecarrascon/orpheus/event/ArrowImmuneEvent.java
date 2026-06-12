package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.util.OrpheusLyreHelper;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;


@EventBusSubscriber(modid = Orpheus.MOD_ID)
public class ArrowImmuneEvent {
    @SubscribeEvent
    public static void allowDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player
                && event.getSource().is(DamageTypes.ARROW)
                && OrpheusLyreHelper.shouldProtectFromArrows(player)) {
            event.setAmount(0);
        }
    }
}
