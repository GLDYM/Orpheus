package com.ecarrascon.orpheus.worldgen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_SMALL_KEY = registerKey("tears_of_hades_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_LARGE_KEY = registerKey("tears_of_hades_ore_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_BURIED_KEY = registerKey("tears_of_hades_ore_buried");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldTearsOfHadesOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlocksRegistry.TEARS_OF_HADES_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get().defaultBlockState()));

        register(context, TEARS_OF_HADES_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldTearsOfHadesOres, 4,0.5f));
        register(context, TEARS_OF_HADES_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(overworldTearsOfHadesOres, 12, 0.7f));
        register(context, TEARS_OF_HADES_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldTearsOfHadesOres, 8, 1f));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
