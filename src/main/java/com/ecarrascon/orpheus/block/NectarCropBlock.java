package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class NectarCropBlock extends CropBlock {
    public NectarCropBlock(Properties pProperties) {
        super(pProperties);
    }

    protected ItemLike getBaseSeedId() {
        return ItemsRegistry.NECTAR_SEED.get();
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player && isReadyForNectar(state, world, player, player.getMainHandItem())) {
            collectNectar(world, pos, state, player);
        }

        super.stepOn(world, pos, state, entity);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (isReadyForNectar(state, world, player, player.getMainHandItem())) {
            collectNectar(world, pos, state, player);
            return false;
        }

        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (isReadyForNectar(state, world, player, player.getMainHandItem())) {
            collectNectar(world, pos, state, player);
        }

        return super.useWithoutItem(state, world, pos, player, hitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {

        if (isReadyForNectar(state, world, player, stack)) {
            collectNectar(world, pos, state, player);
            return ItemInteractionResult.sidedSuccess(world.isClientSide());
        }

        return super.useItemOn(stack, state, world, pos, player, pHand, pHit);
    }

    private static boolean isReadyForNectar(BlockState state, Level world, Player player, ItemStack stack) {
        return isCropFullyGrown(state) && !world.isClientSide() && !player.isSpectator() && stack.is(Items.HONEY_BOTTLE);
    }

    private static boolean isCropFullyGrown(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    private void collectNectar(Level world, BlockPos pos, BlockState state, Player player) {
        PlayerUtils.decrementHeldItem(player, Items.HONEY_BOTTLE);

        ItemStack brotoiNectar = ItemsRegistry.BROTOI_NECTAR.get().getDefaultInstance();
        world.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1f, 1f);
        if (!player.addItem(brotoiNectar)) {
            player.drop(brotoiNectar, false);
        }

        world.setBlock(pos, state.setValue(AGE, 0), 2);
    }
}
