package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MyhtosBlock extends Block {
    public MyhtosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof Player player) {
            // Get Calliope's Love
            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get())
                    && player.isShiftKeyDown() && WorldUtils.isSurroundedByBlocksTag(pLevel, pPos, BlockTags.SMALL_FLOWERS, 1)) {
                WorldUtils.summonLightning(pLevel, player);
                PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
                giveCalliopesLoveItem(player, pLevel);
                pLevel.destroyBlock(pPos, false);
                pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);

            }

            // Get Orpheus Lyre
            if (pLevel.dimension().equals(Level.NETHER) && player.isHolding(ItemsRegistry.APOLLOS_SON.get())
                    && player.isShiftKeyDown() && WorldUtils.isSurroundedByBlocks(pLevel, pPos, Blocks.MAGMA_BLOCK, 0)) {
                WorldUtils.summonLightning(pLevel, player);
                PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
                giveOrpheusLyreItem(player, pLevel);
                pLevel.destroyBlock(pPos, false);
                pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void giveCalliopesLoveItem(Player player, Level world) {
        ItemStack calliopesLove = ItemsRegistry.CALLIOPES_LOVE.get().getDefaultInstance();
        if (!player.getInventory().add(calliopesLove)) {
            Block.dropResources(world.getBlockState(player.blockPosition()), world, player.blockPosition(), null, null, calliopesLove);
        }
    }

    private void giveOrpheusLyreItem(Player player, Level world) {
        ItemStack orpheusLyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance();
        if (!player.getInventory().add(orpheusLyre)) {
            Block.dropResources(world.getBlockState(player.blockPosition()), world, player.blockPosition(), null, null, orpheusLyre);
        }
    }

}
