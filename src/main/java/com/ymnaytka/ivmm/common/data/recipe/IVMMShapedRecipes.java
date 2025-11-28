package com.ymnaytka.ivmm.common.data.recipe;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidContainerIngredient;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.rekindled.embers.RegistryManager;
import com.ymnaytka.ivmm.common.data.IVMMItems;
import elucent.rootsclassic.registry.RootsRegistry;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.rawOre;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class IVMMShapedRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // 1-stone_stage
        VanillaRecipeHelper.addShapedRecipe(provider, "strong_rope_shaped", new ItemStack(IVMMItems.STRONG_ROPE), "zzz",
                " l ", 'z', IVMMItems.ROPE, 'l', IVMMItems.CHUNK_LEATHER);
        VanillaRecipeHelper.addShapedRecipe(provider, "wooden_block_form_shaped",
                new ItemStack(IVMMItems.WOODEN_BLOCK_FORM), "kX ", 'X', WOODEN_FORM_EMPTY.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider, "wooden_bucket_form_shaped",
                new ItemStack(IVMMItems.WOODEN_BUCKET_FORM), " Xk", 'X', WOODEN_FORM_EMPTY.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider, "compressed_caminite_clay_shaped",
                new ItemStack(IVMMItems.COMPRESSED_CAMINITE_CLAY, 8), "BBB", "BFB", "BBB", 'B',
                RegistryManager.CAMINITE_BLEND.get(), 'F', WOODEN_FORM_BRICK.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider, "wreath_shaped", new ItemStack(IVMMItems.WREATH), "FLF", "LIL",
                "FLF", 'F', IVMMItems.FIBER, 'L', ItemTags.FLOWERS, 'I', RootsRegistry.SPELL_POWDER.get());
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_blend_shaped",
                new ItemStack(RegistryManager.CAMINITE_BLEND.get(), 8), "SAC", " m ", 'S', Blocks.SAND, 'A',
                ChemicalHelper.get(dust, Ash), 'C', Blocks.CLAY);
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_bricks_shaped",
                new ItemStack(RegistryManager.CAMINITE_BRICKS.get(), 2), "BBB", "BFB", "BBB", 'B',
                RegistryManager.CAMINITE_BRICK.get(), 'F', new FluidContainerIngredient(Water.getFluidTag(), 1000));
        VanillaRecipeHelper.addShapedRecipe(provider, "clay_shaped", new ItemStack(Blocks.CLAY), " B ", "BFB", " B ",
                'B', Items.CLAY_BALL, 'F', IVMMItems.WOODEN_BLOCK_FORM.asStack());
        VanillaRecipeHelper.addShapedRecipe(provider, "crafting_table_shape", new ItemStack(Items.CRAFTING_TABLE),
                " ZR", " ZF", 'Z', ItemTags.PLANKS, 'R', IVMMItems.STRONG_ROPE, 'F', Items.FLINT);
        VanillaRecipeHelper.addShapedRecipe(provider, "furnace_shape", new ItemStack(Items.FURNACE), "CCC", "FOF",
                "CCC", 'C', RegistryManager.CAMINITE_BRICKS.get(), 'F', Items.FLINT, 'O',
                ChemicalHelper.get(rawOre, Coal));
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_hammer_shape",
                new ItemStack(IVMMItems.PRIMITIVE_CAMINITE_HAMMER),
                "BS ", "BBT", "BS",
                'B', RegistryManager.CAMINITE_BRICK, 'S', IVMMItems.STRONG_ROPE, 'T', Items.STICK);
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_knife_shape",
                new ItemStack(IVMMItems.PRIMITIVE_CAMINITE_KNIFE),
                "fBh", "STS",
                'B', RegistryManager.CAMINITE_BRICK, 'S', IVMMItems.STRONG_ROPE, 'T', Items.STICK);
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_file_shape",
                new ItemStack(IVMMItems.PRIMITIVE_CAMINITE_FILE),
                " B ", "SBS", " T ",
                'B', RegistryManager.CAMINITE_BRICK, 'S', IVMMItems.STRONG_ROPE, 'T', Items.STICK);
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_saw_shape",
                new ItemStack(IVMMItems.PRIMITIVE_CAMINITE_SAW),
                "BBS", "fhT",
                'B', RegistryManager.CAMINITE_BRICK, 'S', IVMMItems.STRONG_ROPE, 'T', Items.STICK);
        VanillaRecipeHelper.addShapedRecipe(provider, "caminite_mortar_shape",
                new ItemStack(IVMMItems.PRIMITIVE_CAMINITE_MORTAR),
                "SBS", "OBO", "OOO",
                'B', RegistryManager.CAMINITE_BRICK, 'S', IVMMItems.STRONG_ROPE, 'T', Items.STICK, 'O', Blocks.STONE);
    }
}
