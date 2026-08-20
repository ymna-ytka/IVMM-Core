package com.ymnaytka.ivmmcore.common.data.datagen.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class IVMMCoreLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        provider.add("item.ivmmcore.flask_rabbit.filled", "Filled Waterskin (Rabbit)");
        provider.add("item.ivmmcore.flask_sheep.filled", "Filled Waterskin (Sheep)");
    }
}
