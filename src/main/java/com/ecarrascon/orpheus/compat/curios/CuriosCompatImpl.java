package com.ecarrascon.orpheus.compat.curios;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.OrpheusLyreHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.event.DropRulesEvent;
import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

final class CuriosCompatImpl {
    private CuriosCompatImpl() {
    }

    static boolean isEquipped(Player player, Item item) {
        return CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.isEquipped(item))
                .orElse(false);
    }

    static ItemStack findFirstEquippedStack(Player player, Item item) {
        return CuriosApi.getCuriosInventory(player)
                .flatMap(handler -> handler.findFirstCurio(item))
                .map(SlotResult::stack)
                .orElse(ItemStack.EMPTY);
    }

    static boolean removeFirstEquipped(Player player, Item item) {
        return CuriosApi.getCuriosInventory(player)
                .flatMap(handler -> handler.findFirstCurio(item).map(result -> remove(handler, result)))
                .orElse(false);
    }

    static void registerEventHandlers() {
        NeoForge.EVENT_BUS.addListener(CuriosCompatImpl::onDropRules);
    }

    private static boolean remove(ICuriosItemHandler handler, SlotResult result) {
        if (result.stack().isEmpty()) {
            return false;
        }
        handler.setEquippedCurio(result.slotContext().identifier(), result.slotContext().index(), ItemStack.EMPTY);
        return true;
    }

    private static void onDropRules(DropRulesEvent event) {
        if (event.getEntity() instanceof Player player && OrpheusLyreHelper.shouldKeepInventory(player)) {
            event.addOverride(stack -> true, DropRule.ALWAYS_KEEP);
        }
    }
}
