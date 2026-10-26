package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import com.ymnaytka.ivmmcore.IVMMCore;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class IVMMCoreRuleTests {

    public static final DeferredRegister<RuleTestType<?>> RULE_TESTS = DeferredRegister.create(Registries.RULE_TEST,
            IVMMCore.MOD_ID);

    public static final RegistryObject<RuleTestType<TfcRawRockTest>> TFC_RAW_ROCK = RULE_TESTS.register("tfc_raw_rock",
            () -> () -> TfcRawRockTest.CODEC);

    private IVMMCoreRuleTests() {}
}
