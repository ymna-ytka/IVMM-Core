package com.ymnaytka.ivmm.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import com.ymnaytka.ivmm.IVMMCore;
import com.ymnaytka.ivmm.common.data.IVMMMaterials;

public class MIMaterials {

    public static void init() {
        IVMMMaterials.Ember = new Material.Builder(IVMMCore.id("ember"))
                .gem()
                .ore()
                .formula("🜂C🜄")
                .color(0xdb510d).secondaryColor(0x792906)
                .buildAndRegister();
    }
}
