package com.ymnaytka.ivmm.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;

import com.ymnaytka.ivmm.IVMMCore;
import com.ymnaytka.ivmm.common.data.IVMMMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class MIMaterials {

    public static void register() {
        IVMMMaterials.Caminite = new Material.Builder(IVMMCore.id("caminite"))
                .ingot()
                .color(0xc2a85e).secondaryColor(0xb09856).iconSet(ROUGH)
                .flags(MORTAR_GRINDABLE, GENERATE_PLATE)
                .toolStats(ToolProperty.Builder.of(1.0F, 0.5F, 75, 1)
                        .enchantability(5).addTypes(GTToolType.MORTAR).build())
                .buildAndRegister();

        IVMMMaterials.Ember = new Material.Builder(IVMMCore.id("ember"))
                .gem()
                .ore()
                .formula("🜂C🜄")
                .color(0xdb510d).secondaryColor(0x792906)
                .buildAndRegister();
    }
}
