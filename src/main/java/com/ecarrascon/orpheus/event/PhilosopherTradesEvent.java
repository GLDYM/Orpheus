package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.registry.VillagersRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;


import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Orpheus.MOD_ID)
@ParametersAreNonnullByDefault
public class PhilosopherTradesEvent
{
	@SubscribeEvent
	public static void addCustomTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagersRegistry.PHILOSOPHER.get()) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			// Level 1
			addTrade(trades, 1, new ItemStack(ItemsRegistry.HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT.get(), 1),
					new ItemStack(ItemsRegistry.MOLY_HERB.get(), 1), 14, 2, 0f);

			addTrade(trades, 1, new ItemStack(ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get(), 1),
					new ItemStack(ItemsRegistry.PEGASUS_FEATHER.get(), 8), 16, 2, 0f);

			// Level 2
			addTrade(trades, 2, new ItemStack(ItemsRegistry.ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT.get(), 1),
					new ItemStack(ItemsRegistry.PALLADIUM_WOODEN_FRAGMENT.get(), 1), 9, 4, 0f);

			addTrade(trades, 2, new ItemStack(ItemsRegistry.PLATOS_REPUBLIC_SCROLL_FRAGMENT.get(), 1),
					new ItemStack(ItemsRegistry.NECTAR_SEED.get(), 1), 5, 4, 0f);

			// Level 3
			addTrade(trades, 3, new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_FRAGMENT.get(), 9),
					new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_POTION.get(), 1), 16, 8, 0f);

			addTrade(trades, 3, new ItemStack(ItemsRegistry.THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT.get(), 1),
					new ItemStack(ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get(), 1), 24, 8, 0f);

			// Level 4
			addTrade(trades, 4, new ItemStack(ItemsRegistry.STOIC_MEDITATIVE_STONE.get(), 1),
					new ItemStack(ItemsRegistry.PANDORAS_PITHOS.get(), 1), 2, 27, 0f);

			// Level 5
			addTrade(trades, 5, new ItemStack(ItemsRegistry.HELLENIC_CODEX.get(), 1),
					new ItemStack(ItemsRegistry.TEARS_OF_HADES.get(), 3), 5, 2, 0f);
		}
	}

	private static void addTrade(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, int level, ItemStack input1,
								 ItemStack output, int maxUses, int xp, float priceMultiplier) {
		trades.computeIfAbsent(level, k -> new ArrayList<>()).add((pTrader, pRandom) ->
				new MerchantOffer(new ItemCost(input1.getItem(), input1.getCount()), output, maxUses, xp, priceMultiplier));
	}
}
