package com.ymnaytka.ivmm.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.ymnaytka.ivmm.IVMMCore;

import static com.ymnaytka.ivmm.common.registry.IVMMRegistration.REGISTRATE;

public class IVMMCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> IVMMCORE = REGISTRATE.defaultCreativeTab(IVMMCore.MOD_ID,
            builder -> builder
                    .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(IVMMCore.MOD_ID, REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", IVMMCore.id("creative_tab"), "IVMMCore"))
                    .icon(IVMMItems.FIBER::asStack)
                    .build())
            .register();

    public static void init() {}
}
