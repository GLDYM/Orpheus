package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.block.MythosBlock;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Orpheus.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleCubeWithItem(BlocksRegistry.PEGASUS_FEATHERS_BLOCK.get());
        simpleCubeWithItem(BlocksRegistry.TEARS_OF_HADES_BLOCK.get());
        simpleCubeWithItem(BlocksRegistry.TEARS_OF_HADES_ORE.get());
        simpleCubeWithItem(BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get());

        simpleBlockWithItem(BlocksRegistry.EPIPHANY_TABLE.get(), models().getExistingFile(modLoc("block/epiphany_table")));

        ModelFile molyHerbModel = models().cross(name(BlocksRegistry.MOLY_HERB.get()), blockTexture(BlocksRegistry.MOLY_HERB.get()))
                .renderType("cutout");
        simpleBlock(BlocksRegistry.MOLY_HERB.get(), molyHerbModel);
        simpleBlockItem(BlocksRegistry.MOLY_HERB.get(), molyHerbModel);

        simpleBlock(BlocksRegistry.POTTED_MOLY_HERB.get(),
                models().singleTexture(name(BlocksRegistry.POTTED_MOLY_HERB.get()), mcLoc("block/flower_pot_cross"), "plant",
                                blockTexture(BlocksRegistry.MOLY_HERB.get()))
                        .renderType("cutout"));

        getVariantBuilder(BlocksRegistry.NECTAR_CROP.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(models().crop("nectar_crop_stage" + state.getValue(net.minecraft.world.level.block.CropBlock.AGE),
                                modLoc("block/nectar_crop_stage" + state.getValue(net.minecraft.world.level.block.CropBlock.AGE)))
                        .renderType("cutout"))
                .build());

        getVariantBuilder(BlocksRegistry.MYTHOS_BLOCK.get()).forAllStates(state -> {
            String suffix = switch (state.getValue(MythosBlock.DIMENSION)) {
                case OVERWORLD -> "mythos_block";
                case OVERWORLD_ACTIVE -> "mythos_block_overworld";
                case NETHER -> "mythos_block_nether";
                case NETHER_ACTIVE -> "mythos_block_nether_active";
            };
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/" + suffix)))
                    .build();
        });
        simpleBlockItem(BlocksRegistry.MYTHOS_BLOCK.get(), models().getExistingFile(modLoc("block/mythos_block")));
    }

    private void simpleCubeWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
