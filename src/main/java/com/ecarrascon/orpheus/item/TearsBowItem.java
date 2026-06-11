package com.ecarrascon.orpheus.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class TearsBowItem extends BowItem {

    public TearsBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            ItemStack ammo = player.getProjectile(stack);
            if (!ammo.isEmpty()) {
                int charge = this.getUseDuration(stack, entityLiving) - timeLeft;
                charge = net.neoforged.neoforge.event.EventHooks.onArrowLoose(stack, level, player, charge, true);
                if (charge < 0) {
                    return;
                }

                float power = getPowerForTime(charge);
                if (!((double) power < 0.1)) {
                    List<ItemStack> drawnProjectiles = draw(stack, ammo, player);
                    if (level instanceof ServerLevel serverLevel && !drawnProjectiles.isEmpty()) {
                        this.shoot(serverLevel, player, player.getUsedItemHand(), stack, drawnProjectiles, power * 5.5F, 0.25F, power == 1.0F, null);
                    }

                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
}
