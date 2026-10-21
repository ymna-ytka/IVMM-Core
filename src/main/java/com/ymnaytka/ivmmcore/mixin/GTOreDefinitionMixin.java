package com.ymnaytka.ivmmcore.mixin;

import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * GT veins are filtered by biome, and almost all overworld veins (including IVMM Core's own) use
 * {@code #minecraft:is_overworld}. TFC does not put its biomes in that tag, so without this every such vein is
 * rejected in TFC worlds. We accept TFC biomes ONLY for veins whose biome filter is exactly that tag; veins limited
 * to specific biomes or other tags (jungle, mountains, ...) are left alone.
 *
 * <p>Result is cached per biome by GT (WorldOreVeinCache), so this is not on a hot path.
 */
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
