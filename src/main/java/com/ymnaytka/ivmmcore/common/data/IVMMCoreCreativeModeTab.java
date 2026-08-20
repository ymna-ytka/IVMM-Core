package com.ymnaytka.ivmmcore.common.data;

import com.ymnaytka.ivmmcore.IVMMCore;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTItems;

import net.minecraft.world.item.CreativeModeTab;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration.REGISTRATE;

public class IVMMCoreCreativeModeTab {

    public static RegistryEntry<CreativeModeTab> IVMM_CORE = REGISTRATE.defaultCreativeTab(IVMMCore.MOD_ID,
            builder -> builder
                    .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(IVMMCore.MOD_ID, REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", IVMMCore.id("creative_tab"), "IVMM Core"))
                    .icon(GTItems.ULTIMATE_BATTERY::asStack)
                    .build())
            .register();

    public static void init() {}
}
