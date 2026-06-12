package com.ecarrascon.orpheus.util;

import com.ecarrascon.orpheus.compat.curios.CuriosCompat;
import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class OrpheusLyreHelper {
    private OrpheusLyreHelper() {
    }

    public static boolean hasLyre(Player player) {
        ItemStack lyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance();
        return player.getInventory().contains(lyre) || CuriosCompat.isEquipped(player, ItemsRegistry.ORPHEUS_LYRE.get());
    }

    public static boolean shouldKeepInventory(Player player) {
        return hasLyre(player) && ConfigDataCommon.ORPHEUS_LYRE_POWER.get().matches(".*(?:keep|both).*");
    }

    public static boolean shouldProtectFromArrows(Player player) {
        return hasLyre(player) && ConfigDataCommon.ORPHEUS_LYRE_POWER.get().matches(".*(?:protect|both).*");
    }

    public static boolean consumeOneLyre(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack lyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance();

        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack inSlot = inventory.getItem(slot);
            if (ItemStack.isSameItem(inSlot, lyre)) {
                inventory.removeItem(slot, 1);
                player.containerMenu.broadcastChanges();
                return true;
            }
        }
        return CuriosCompat.removeFirstEquipped(player, ItemsRegistry.ORPHEUS_LYRE.get());
    }
}
