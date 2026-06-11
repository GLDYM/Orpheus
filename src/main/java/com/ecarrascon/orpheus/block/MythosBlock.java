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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class MythosBlock extends Block {
    public static final EnumProperty<MythosStateEnum> DIMENSION = EnumProperty.create("dimension", MythosStateEnum.class);

    public MythosBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DIMENSION, MythosStateEnum.OVERWORLD));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        MythosStateEnum dimensionState = context.getLevel().dimension().equals(Level.NETHER)
                ? MythosStateEnum.NETHER
                : MythosStateEnum.OVERWORLD;
        return this.defaultBlockState().setValue(DIMENSION, dimensionState);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            updateBlockStateBasedOnDimension(level, pos, state);
            tryGodsCall(level, pos, state, player);
        }
        super.stepOn(level, pos, state, entity);
    }

    private void updateBlockStateBasedOnDimension(Level level, BlockPos pos, BlockState state) {
        if (level.dimension().equals(Level.NETHER)) {
            level.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.NETHER), Block.UPDATE_ALL);
        } else {
            level.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.OVERWORLD), Block.UPDATE_ALL);
        }
    }

    private void tryGodsCall(Level level, BlockPos pos, BlockState state, Player player) {
        if (level.dimension().equals(Level.NETHER)) {
            tryOrpheusCall(level, pos, state, player);
        } else {
            tryCalliopeCall(level, pos, state, player);
        }
    }

    private void tryCalliopeCall(Level level, BlockPos pos, BlockState state, Player player) {
        if (WorldUtils.isSurroundedByBlocksTag(level, pos, BlockTags.FLOWERS, 1)) {
            level.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.OVERWORLD_ACTIVE), Block.UPDATE_ALL);

            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get()) && player.isShiftKeyDown()) {
                activateCalliopeCall(level, pos, player);
            }
        }
    }

    private void tryOrpheusCall(Level level, BlockPos pos, BlockState state, Player player) {
        if (WorldUtils.isSurroundedByBlocks(level, pos, Blocks.MAGMA_BLOCK, 0)) {
            level.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.NETHER_ACTIVE), Block.UPDATE_ALL);

            if (player.isHolding(ItemsRegistry.APOLLOS_SON.get()) && player.isShiftKeyDown()) {
                activateOrpheusCall(level, pos, player);
            }
        }
    }

    private void activateCalliopeCall(Level level, BlockPos pos, Player player) {
        WorldUtils.summonLightning(level, player);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
        giveItemOrDrop(player, level, ItemsRegistry.CALLIOPES_LOVE.get().getDefaultInstance());
        level.destroyBlock(pos, false);
        level.playSound(null, pos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
    }

    private void activateOrpheusCall(Level level, BlockPos pos, Player player) {
        WorldUtils.summonLightning(level, player);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
        giveItemOrDrop(player, level, ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance());
        level.destroyBlock(pos, false);
        level.playSound(null, pos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
    }

    private void giveItemOrDrop(Player player, Level level, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            Block.popResource(level, player.blockPosition(), stack);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIMENSION);
    }
}
