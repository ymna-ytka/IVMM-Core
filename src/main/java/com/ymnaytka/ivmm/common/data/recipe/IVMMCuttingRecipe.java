package com.ymnaytka.ivmm.common.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import com.ymnaytka.ivmm.common.data.IVMMItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CUTTER_RECIPES;

public class IVMMCuttingRecipe {

    public static void init(Consumer<FinishedRecipe> provider) {
        CUTTER_RECIPES.recipeBuilder("tanned_leather_cutter")
                .inputItems(IVMMItems.TANNED_LEATHER)
                .outputItems(IVMMItems.PROCESSED_LEATHER)
                .duration(300).EUt(8).save(provider);
    }
}
