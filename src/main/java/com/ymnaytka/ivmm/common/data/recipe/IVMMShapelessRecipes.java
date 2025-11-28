package com.ymnaytka.ivmm.common.data.recipe;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.ymnaytka.ivmm.common.data.IVMMItems;

import java.util.function.Consumer;

public class IVMMShapelessRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapelessRecipe(provider, "rope_shapeless", new ItemStack(IVMMItems.ROPE),
                IVMMItems.DRIED_FIBER, IVMMItems.DRIED_FIBER, IVMMItems.DRIED_FIBER);
        VanillaRecipeHelper.addShapelessRecipe(provider, "wooden_shovel_shape", new ItemStack(Items.WOODEN_SHOVEL),
                ItemTags.PLANKS, Items.STICK, Items.STICK);
        VanillaRecipeHelper.addShapelessRecipe(provider, "wooden_axe_shape", new ItemStack(Items.WOODEN_AXE),
                ItemTags.PLANKS, ItemTags.PLANKS, ItemTags.PLANKS, Items.STICK);
    }
}
