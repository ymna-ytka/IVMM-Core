package com.ymnaytka.ivmmcore;

import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;
import com.ymnaytka.ivmmcore.common.data.worldgen.IVMMCoreWorldgen;
import com.ymnaytka.ivmmcore.common.data.worldgen.tfc.TfcRawRockTest;
import com.ymnaytka.ivmmcore.common.data.worldgen.tfc.TfcRockOres;
import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class IVMMCoreGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return IVMMCoreRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return IVMMCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        TfcRockOres.init();
    }

    @Override
    public void registerWorldgenLayers() {
        if (!IVMMCoreConfig.gtOresInTfc()) return;
        WorldGenLayers.STONE.setTarget(new TfcRawRockTest(WorldGenLayers.STONE.getTarget()));
        WorldGenLayers.DEEPSLATE.setTarget(new TfcRawRockTest(WorldGenLayers.DEEPSLATE.getTarget()));
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {}

    @Override
    public void registerElements() {}

    @Override
    public void registerOreVeins() {
        IVMMCoreWorldgen.lateInit();
    }
}
