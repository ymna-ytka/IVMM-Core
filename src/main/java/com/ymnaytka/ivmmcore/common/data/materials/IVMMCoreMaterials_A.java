package com.ymnaytka.ivmmcore.common.data.materials;

import com.ymnaytka.ivmmcore.IVMMCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class IVMMCoreMaterials_A {

    public static Material BlackCopperOxide;
    public static Material Sand;
    public static Material Azurite;
    public static Material Limestone;

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
        Azurite = new Material.Builder(IVMMCore.id("azurite"))
                .ore()
                .color(0x284bcc).secondaryColor(0x172263).iconSet(MaterialIconSet.BRIGHT)
                .formula("Cu₃(CO₃)₂(OH)₂")
                .flags(DISABLE_DECOMPOSITION)
                .langValue("Azurite")
                .buildAndRegister();
        Limestone = new Material.Builder(IVMMCore.id("limestone"))
                .dust()
                .color(0xDCD8C7).secondaryColor(0x989A98)
                .flags(NO_SMASHING, DECOMPOSITION_BY_CENTRIFUGING)
                //WIP.components()
                .buildAndRegister();
    }
}
