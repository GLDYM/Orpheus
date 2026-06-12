package com.ecarrascon.orpheus.datagen;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemsRegistry.APOLLOS_SON.get())
                .requires(ItemsRegistry.TEARS_BOW.get())
                .requires(ItemsRegistry.LYRE.get())
                .requires(ItemsRegistry.CALLIOPES_LOVE.get())
                .unlockedBy("has_tears_bow", has(ItemsRegistry.TEARS_BOW.get()))
                .save(output);

        nineBlockStorage(output, ItemsRegistry.PALLADIUM_WOODEN_FRAGMENT.get(), ItemsRegistry.BROTOI_PALLADIUM.get(), "brotoi_palladium");
        nineBlockStorage(output, ItemsRegistry.PEGASUS_FEATHER.get(), ItemsRegistry.PEGASUS_FEATHERS_BLOCK.get(), "pegasus_feathers_block");
        nineBlockStorage(output, ItemsRegistry.TEARS_OF_HADES.get(), ItemsRegistry.TEARS_OF_HADES_BLOCK.get(), "tears_of_hades_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemsRegistry.MYTHOS_BLOCK.get())
                .pattern("###")
                .pattern("#M#")
                .pattern("###")
                .define('#', ItemsRegistry.PEGASUS_FEATHER.get())
                .define('M', ItemsRegistry.MOLY_HERB.get())
                .unlockedBy("has_pegasus_feather", has(ItemsRegistry.PEGASUS_FEATHER.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemsRegistry.CLEAN_COW_GUT.get())
                .requires(Items.WATER_BUCKET)
                .requires(ItemsRegistry.COW_GUT.get())
                .unlockedBy("has_cow_gut", has(ItemsRegistry.COW_GUT.get()))
                .save(output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemsRegistry.CLEAN_COW_GUT.get()), RecipeCategory.FOOD, ItemsRegistry.COOKED_COW_GUT.get(), 0.35F, 200)
                .unlockedBy("has_clean_cow_gut", has(ItemsRegistry.CLEAN_COW_GUT.get()))
                .save(output);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ItemsRegistry.CLEAN_COW_GUT.get()), RecipeCategory.FOOD, ItemsRegistry.COOKED_COW_GUT.get(), 0.35F, 600)
                .unlockedBy("has_clean_cow_gut", has(ItemsRegistry.CLEAN_COW_GUT.get()))
                .save(output, recipeId("cooked_cow_gut_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ItemsRegistry.CLEAN_COW_GUT.get()), RecipeCategory.FOOD, ItemsRegistry.COOKED_COW_GUT.get(), 0.35F, 100)
                .unlockedBy("has_clean_cow_gut", has(ItemsRegistry.CLEAN_COW_GUT.get()))
                .save(output, recipeId("cooked_cow_gut_from_smoking"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.CROSSBOW)
                .pattern("#&#")
                .pattern("~$~")
                .pattern(" # ")
                .define('#', Items.STICK)
                .define('$', Items.TRIPWIRE_HOOK)
                .define('&', Items.IRON_INGOT)
                .define('~', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("crossbow_from_plain_string"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.FISHING_ROD)
                .pattern("  #")
                .pattern(" #X")
                .pattern("# X")
                .define('#', Items.STICK)
                .define('X', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("fishing_rod_from_plain_string"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemsRegistry.HELLENIC_CODEX.get())
                .requires(ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get())
                .requires(ItemsRegistry.HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT.get())
                .requires(ItemsRegistry.PLATOS_REPUBLIC_SCROLL_FRAGMENT.get())
                .requires(ItemsRegistry.ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT.get())
                .requires(ItemsRegistry.THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT.get())
                .requires(ItemsRegistry.CALLIOPE_POEM_FRAGMENT.get())
                .unlockedBy("has_scroll_fragment", has(ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get()))
                .save(output);

        armor(output, ItemsRegistry.HEPHAESTUS_HELMET.get(), "XXX", "XTX");
        armor(output, ItemsRegistry.HEPHAESTUS_CHESTPLATE.get(), "XTX", "XXX", "XXX");
        armor(output, ItemsRegistry.HEPHAESTUS_LEGGINGS.get(), "XXX", "XTX", "X X");
        armor(output, ItemsRegistry.HEPHAESTUS_BOOTS.get(), "T T", "X X", "X X");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.LEAD, 2)
                .pattern("~~ ")
                .pattern("~O ")
                .pattern("  ~")
                .define('O', Items.SLIME_BALL)
                .define('~', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("lead_from_plain_string"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.LOOM)
                .pattern("@@")
                .pattern("##")
                .define('#', ItemTags.PLANKS)
                .define('@', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("loom_from_plain_string"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemsRegistry.LYRE.get())
                .pattern("###")
                .pattern("SSS")
                .pattern("HKH")
                .define('#', Items.BAMBOO)
                .define('S', ItemsRegistry.PLAIN_STRING.get())
                .define('H', Items.HONEYCOMB)
                .define('K', Items.TURTLE_SCUTE)
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.WHITE_WOOL), RecipeCategory.MISC, Items.STRING, 4)
                .unlockedBy("has_white_wool", has(Items.WHITE_WOOL))
                .save(output, recipeId("string_from_wool_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ItemsRegistry.CLEAN_COW_GUT.get()), RecipeCategory.MISC, ItemsRegistry.PLAIN_STRING.get(), 3)
                .unlockedBy("has_clean_cow_gut", has(ItemsRegistry.CLEAN_COW_GUT.get()))
                .save(output, recipeId("plain_string_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.WHITE_WOOL), RecipeCategory.MISC, ItemsRegistry.PLAIN_STRING.get(), 4)
                .unlockedBy("has_white_wool", has(Items.WHITE_WOOL))
                .save(output, recipeId("plain_string_from_wool_stonecutting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.SCAFFOLDING, 6)
                .pattern("I~I")
                .pattern("I I")
                .pattern("I I")
                .define('I', Items.BAMBOO)
                .define('~', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("scaffolding_from_plain_string"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemsRegistry.TEARS_BOW.get())
                .pattern(" #X")
                .pattern("# X")
                .pattern(" #X")
                .define('#', ItemsRegistry.TEARS_OF_HADES.get())
                .define('X', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_tears_of_hades", has(ItemsRegistry.TEARS_OF_HADES.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.WHITE_WOOL)
                .pattern("##")
                .pattern("##")
                .define('#', ItemsRegistry.PLAIN_STRING.get())
                .unlockedBy("has_plain_string", has(ItemsRegistry.PLAIN_STRING.get()))
                .save(output, recipeId("white_wool_from_plain_string"));
    }

    private void nineBlockStorage(RecipeOutput output, Item input, Item result, String recipeName) {
        String inputName = BuiltInRegistries.ITEM.getKey(input).getPath();
        String resultName = BuiltInRegistries.ITEM.getKey(result).getPath();

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + inputName, has(input))
                .save(output, recipeId(recipeName));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, input, 9)
                .requires(result)
                .unlockedBy("has_" + resultName, has(result))
                .save(output, recipeId(inputName + "_from_block"));
    }

    private void armor(RecipeOutput output, Item result, String... pattern) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('X', ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get())
                .define('T', ItemsRegistry.TEARS_OF_HADES.get())
                .unlockedBy("has_hephaestus_armor_fragment", has(ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get()));
        for (String row : pattern) {
            builder.pattern(row);
        }
        builder.save(output);
    }

    private static ResourceLocation recipeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, path);
    }
}
