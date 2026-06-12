package com.ecarrascon.orpheus.mixin;

import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.util.OrpheusLyreHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerCopyFromMixin {

    // KeepInventory when Orpheus Lyre in inventory, "PlayerEntityDropInventoryMixin" makes that items not being dropped
	@Redirect(method = "restoreFrom", at = @At(value = "INVOKE", target = "net/minecraft/world/level/GameRules.getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z", ordinal = 0))
    private boolean redirectCopyFromCondition(GameRules instance, GameRules.Key<GameRules.BooleanValue> rule, ServerPlayer oldPlayer, boolean alive) {
        return ((ServerPlayer) (Object) this).serverLevel().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)
                || OrpheusLyreHelper.shouldKeepInventory(oldPlayer);

    }

    @Inject(method = "restoreFrom(Lnet/minecraft/server/level/ServerPlayer;Z)V", at = @At("TAIL"))
    private void onRestoreFrom(ServerPlayer oldPlayer, boolean keepEverything, CallbackInfo info) {
        ServerPlayer self = (ServerPlayer) (Object) this;

        boolean vanillaKeep = self.serverLevel().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY);
        boolean lyreKeep = ConfigDataCommon.ONE_TIME_USE.get()
                && OrpheusLyreHelper.shouldKeepInventory(oldPlayer);

        if (!vanillaKeep && lyreKeep) {
            OrpheusLyreHelper.consumeOneLyre(self);
        }
    }

}
