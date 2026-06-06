package com.ymnaytka.ivmmcore.common.data;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.*;

public class IVMMCoreRecipeTypes {

    public final static GTRecipeType PRIMITIVE_EARTH_FURNACE_RECIPES = register("primitive_earth_furnace", MULTIBLOCK)
            .setMaxIOSize(3, 3, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.FIRE);

    public static void init() {}
}
