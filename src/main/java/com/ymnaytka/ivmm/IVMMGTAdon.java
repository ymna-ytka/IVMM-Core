package com.ymnaytka.ivmm;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import com.ymnaytka.ivmm.common.data.IVMMOres;
import com.ymnaytka.ivmm.common.data.IVMMRecipeInit;
import com.ymnaytka.ivmm.common.data.IVMMRecipeTypes;
import com.ymnaytka.ivmm.common.registry.IVMMRegistration;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class IVMMGTAdon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return IVMMRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return IVMMCore.MOD_ID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        IVMMRecipeTypes.init();
        IVMMRecipeInit.init(provider);
    }

    @Override
    public void registerOreVeins() {
        IVMMOres.init();
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.
    /*
     * public static final ContentJS<Double> PRESSURE_IN = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * GregitasRecipeCapabilities.PRESSURE, false);
     * public static final ContentJS<Double> PRESSURE_OUT = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * GregitasRecipeCapabilities.PRESSURE, true);
     * 
     * @Override
     * public void registerRecipeKeys(KJSRecipeKeyEvent event) {
     * event.registerKey(CustomRecipeCapabilities.PRESSURE, Pair.of(PRESSURE_IN, PRESSURE_OUT));
     * }
     */
}
