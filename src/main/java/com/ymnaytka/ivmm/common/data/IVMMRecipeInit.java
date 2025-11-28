package com.ymnaytka.ivmm.common.data;

import net.minecraft.data.recipes.FinishedRecipe;

import com.ymnaytka.ivmm.common.data.recipe.*;

import java.util.function.Consumer;

public class IVMMRecipeInit {

    public static void init(Consumer<FinishedRecipe> provider) {
        IVMMShapelessRecipes.init(provider);
        IVMMShapedRecipes.init(provider);
        IVMMCuttingRecipe.init(provider);
        IVMMAutoclaveRecipes.init(provider);
        IVMMCompressorRecipes.init(provider);
    }
}
