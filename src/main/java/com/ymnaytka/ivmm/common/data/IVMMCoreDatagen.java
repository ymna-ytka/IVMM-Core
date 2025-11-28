package com.ymnaytka.ivmm.common.data;

import com.tterrag.registrate.providers.ProviderType;
import com.ymnaytka.ivmm.common.data.lang.IVMMLangHandler;
import com.ymnaytka.ivmm.common.registry.IVMMRegistration;

public class IVMMCoreDatagen {

    public static void init() {
        IVMMRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, IVMMLangHandler::init);
    }
}
