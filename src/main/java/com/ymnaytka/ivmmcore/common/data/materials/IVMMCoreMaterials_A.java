package com.ymnaytka.ivmmcore.common.data.materials;

import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.ymnaytka.ivmmcore.IVMMCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class IVMMCoreMaterials_A {

    public static Material Sand;
    public static Material Azurite;
    public static Material CastIron;

    public static void register() {
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
        CastIron = new Material.Builder(IVMMCore.id("cast_iron"))
                .ingot()                                          // ingot, plate, rod и т.д. по флагам
                .liquid(new FluidBuilder().temperature(1473))     // жидкий чугун (~1200 °C)
                .color(0x5A5A62).secondaryColor(0x2B2B30)
                .iconSet(MaterialIconSet.METALLIC)
                .formula("Fe₃C")
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .langValue("Cast Iron")
                .buildAndRegister();
    }
}
