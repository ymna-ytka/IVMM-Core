package com.ymnaytka.ivmmcore.common.data.machine.multiblock;

import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;
import com.ymnaytka.ivmmcore.common.data.IVMMCoreCreativeModeTab;

import static com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration.REGISTRATE;

public class IVMMCoreMultiblock_A {

    static {
        IVMMCoreRegistration.REGISTRATE.creativeModeTab(() -> IVMMCoreCreativeModeTab.IVMM_CORE);
    }

    public static void init() {}
}
