package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import com.ymnaytka.ivmmcore.IVMMCore;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.mojang.serialization.Codec;

public final class IVMMCoreBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, IVMMCore.MOD_ID);

    public static final RegistryObject<Codec<? extends BiomeModifier>> STRIP_TFC_ORES = SERIALIZERS
            .register("strip_tfc_ores", () -> TfcOreStripModifier.CODEC);

    private IVMMCoreBiomeModifiers() {}
}
