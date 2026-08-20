package com.ymnaytka.ivmmcore.common.data.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class IVMMCoreMaterialsLang {

    public static void init(RegistrateLangProvider provider) {}

    private static void addMaterialLang(RegistrateLangProvider provider, String id, String name) {
        provider.add("material.ivmmcore." + id, name);
    }

    public static void multiLang(RegistrateLangProvider provider, String key, String... values) {
        for (var i = 0; i < values.length; i++) {
            provider.add(getSubKey(key, i), values[i]);
        }
    }

    protected static void multilineLang(RegistrateLangProvider provider, String key, String multiline) {
        var lines = multiline.split("\n");
        multiLang(provider, key, lines);
    }

    protected static String getSubKey(String key, int index) {
        return key + "." + index;
    }
}
