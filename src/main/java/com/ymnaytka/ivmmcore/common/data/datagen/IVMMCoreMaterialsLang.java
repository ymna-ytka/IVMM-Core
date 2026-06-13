package com.ymnaytka.ivmmcore.common.data.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class IVMMCoreMaterialsLang {

    public static void init(RegistrateLangProvider provider) {
        provider.add("material.ivmmcore.black_copper_oxide", "Copper(II) Oxide");
        provider.add("material.ivmmcore.sand", "Sand");
    }

    private static void addMaterialLang(RegistrateLangProvider provider, String id, String name) {
        provider.add("material.ivmmcore." + id, name);
    }
}
