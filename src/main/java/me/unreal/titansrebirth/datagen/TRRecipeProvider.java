package me.unreal.titansrebirth.datagen;

import me.unreal.titansrebirth.block.TRBasicBlocks;
import me.unreal.titansrebirth.item.TRBasicItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TRRecipeProvider extends FabricRecipeProvider {
    public TRRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, TRBasicItems.DAWNWOOD_STICK, 4)
                .pattern("#")
                .pattern("#")
                .input('#', TRBasicBlocks.DAWNWOOD_PLANKS)
                .criterion(hasItem(TRBasicBlocks.DAWNWOOD_PLANKS), conditionsFromItem(TRBasicBlocks.DAWNWOOD_PLANKS))
                .offerTo(recipeExporter);
    }
}
