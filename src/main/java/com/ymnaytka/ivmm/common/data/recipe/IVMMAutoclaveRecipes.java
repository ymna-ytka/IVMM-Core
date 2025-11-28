package com.ymnaytka.ivmm.common.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import com.ymnaytka.ivmm.common.data.IVMMItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.AUTOCLAVE_RECIPES;

public class IVMMAutoclaveRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        AUTOCLAVE_RECIPES.recipeBuilder("treated_leather_autoclave")
                .inputItems(IVMMItems.PROCESSED_LEATHER)
                .inputFluids(Creosote.getFluid(250))
                .outputItems(IVMMItems.TREATED_LEATHER)
                .duration(300).EUt(32).save(provider);
    }
}
