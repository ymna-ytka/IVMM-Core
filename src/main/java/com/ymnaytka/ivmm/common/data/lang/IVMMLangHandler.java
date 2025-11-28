package com.ymnaytka.ivmm.common.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class IVMMLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        provider.add("ivmmcore.stage_processed.1_stage", "Stage: 1/2");
        provider.add("ivmmcore.stage_processed.2_stage", "Stage: 2/2");
    }
}
