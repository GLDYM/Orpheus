package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.block.MyhtosBlock;
import com.ecarrascon.orpheus.block.NectarCropBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlocksRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Orpheus.MOD_ID);

    public static final DeferredBlock<Block> DEEPSLATE_TEARS_OF_HADES_ORE = BLOCKS.register("deepslate_tears_of_hades_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
            ));

    public static final DeferredBlock<Block> NECTAR_CROP = BLOCKS.register("nectar_crop",
            () -> new NectarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).noCollission().noOcclusion()
            ));
    public static final DeferredBlock<Block> EPIPHANY_TABLE = BLOCKS.register("epiphany_table",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> PEGASUS_FEATHERS_BLOCK = BLOCKS.register("pegasus_feathers_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .strength(0.8f)
            ));
    public static final DeferredBlock<Block> MYTHOS_BLOCK = BLOCKS.register("mythos_block",
            () -> new MyhtosBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                    .strength(0.8f)
            ));

    public static final DeferredBlock<Block> TEARS_OF_HADES_ORE = BLOCKS.register("tears_of_hades_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
            ));

    public static final DeferredBlock<Block>TEARS_OF_HADES_BLOCK = BLOCKS.register("tears_of_hades_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
            ));




    public static final DeferredBlock<Block> MOLY_HERB = BLOCKS.register("moly_herb",
            () -> new FlowerBlock(MobEffects.SLOW_FALLING, 50,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OXEYE_DAISY).noOcclusion().noCollission()));
    public static final DeferredBlock<Block> POTTED_MOLY_HERB = BLOCKS.register("potted_moly_herb",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BlocksRegistry.MOLY_HERB,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OXEYE_DAISY).noOcclusion()));
}
