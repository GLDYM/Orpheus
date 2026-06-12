package com.ecarrascon.orpheus.mixin;

import com.ecarrascon.orpheus.util.OrpheusLyreHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityExperienceMixin {

    @Inject(method = "getExperienceReward", at = @At("HEAD"), cancellable = true)
    private void noEnterTheIfToNotDropTheXp(ServerLevel level, @Nullable Entity killer, CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof Player player
                && !player.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)
                && OrpheusLyreHelper.shouldKeepInventory(player)) {
            cir.setReturnValue(0);
        }
    }
}
