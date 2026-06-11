package com.ecarrascon.orpheus.mixin;

import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityDropInventoryMixin {

    @Inject(method = "dropEquipment", at = @At("HEAD"), cancellable = true)
    private void noEnterTheIfToNotDropTheItems(CallbackInfo info) {
        if (!((Player) (Object) this).level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)
                && ((Player) (Object) this).getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance())
                && ConfigDataCommon.ORPHEUS_LYRE_POWER.get().matches(".*(?:keep|both).*")) {
            info.cancel();
        }
    }
}
