package com.ymnaytka.ivmm.common.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import com.ymnaytka.ivmm.common.data.IVMMItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.COMPRESSOR_RECIPES;

public class IVMMCompressorRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        COMPRESSOR_RECIPES.recipeBuilder("seared_stone_plate")
                .inputItems(IVMMItems.SEARED_STONE_DUST)
                .outputItems(IVMMItems.SEARED_STONE_PLATE)
                .duration(200).EUt(2).save(provider);
    }
}
