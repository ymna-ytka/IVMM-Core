package com.ymnaytka.ivmmcore.common.data.materials;

import com.ymnaytka.ivmmcore.IVMMCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class IVMMCoreMaterials {

    public static Material BlackCopperOxide;
    public static Material Sand;

    public static void register() {
        BlackCopperOxide = new Material.Builder(IVMMCore.id("black_copper_oxide"))
                .ingot()
                .color(0x1b1b1b).iconSet(MaterialIconSet.METALLIC)
                .formula("CuO")
                .langValue("Copper(II) Oxide")
                .buildAndRegister();
        Sand = new Material.Builder(IVMMCore.id("sand"))
                .dust(1)
                .color(0xC2B280).iconSet(MaterialIconSet.SAND)
                .flags(NO_SMELTING, NO_SMASHING)
                .langValue("Sand")
                .buildAndRegister();
    }

}
