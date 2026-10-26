package com.ymnaytka.ivmmcore.mixin.gtceu;

import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;


@Mixin(value = GTOreDefinition.class, remap = false)
public abstract class GTOreDefinitionMixin {

    @Shadow
    @Nullable
    private Supplier<HolderSet<Biome>> biomes;

    @Inject(method = "isForBiome", at = @At("RETURN"), cancellable = true)
    private void ivmmcore$acceptTfcBiomes(Holder<Biome> biome, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ() || !IVMMCoreConfig.gtOresInTfc()) return;

        final boolean tfcBiome = biome.unwrapKey()
                .filter(key -> "tfc".equals(key.location().getNamespace()))
                .isPresent();
        if (!tfcBiome) return;

        if (biomes == null) return;

        if (biomes.get().unwrap().left().filter(BiomeTags.IS_OVERWORLD::equals).isPresent()) {
            cir.setReturnValue(true);
        }
    }
}
