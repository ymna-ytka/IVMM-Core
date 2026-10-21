package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import com.ymnaytka.ivmmcore.IVMMCore;
import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;
import java.util.Set;

/**
 * Strips TFC ore features from TFC biomes. Runs once per biome at datapack/registry load (REMOVE phase),
 * so chunk generation never evaluates the removed features.
 *
 * <p>Why this is enough: {@code TFCChunkGenerator#applyBiomeDecoration} reads features through
 * {@code BiomeExtension#getFlattenedFeatures}, which returns {@code biome.getGenerationSettings().features()}
 * (the list Forge biome modifiers edit).
 *
 * <p>Matching is by placed-feature id (ResourceKey) only, no tag lookup:
 * <ul>
 *   <li>{@code tfc:vein/*} (all ore veins, which also create the indicators), except ids in {@code keptFeatures};</li>
 *   <li>{@code tfc:*_deposit} and {@code tfc:*_deep_deposit} when {@code removeDeposits} is on.</li>
 * </ul>
 */
public record TfcOreStripModifier() implements BiomeModifier {

    public static final Codec<TfcOreStripModifier> CODEC = Codec.unit(TfcOreStripModifier::new);

    private static final String TFC = "tfc";

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.REMOVE || !IVMMCoreConfig.disableTfcOres() || !isTfcBiome(biome)) return;

        final Set<ResourceLocation> kept = IVMMCoreConfig.keptFeatureIds();
        final boolean removeDeposits = IVMMCoreConfig.removeDeposits();
        final BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();

        int removed = 0;
        for (GenerationStep.Decoration step : GenerationStep.Decoration.values()) {
            final List<Holder<PlacedFeature>> features = generation.getFeatures(step);
            final int before = features.size();
            features.removeIf(holder -> isTfcOreFeature(holder, kept, removeDeposits));
            removed += before - features.size();
        }

        if (removed > 0) {
            IVMMCore.LOGGER.debug("[IVMMCore] {}: removed {} TFC ore features", biome.unwrapKey().orElse(null), removed);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return IVMMCoreBiomeModifiers.STRIP_TFC_ORES.get();
    }

    private static boolean isTfcBiome(Holder<Biome> biome) {
        return biome.unwrapKey()
                .map(ResourceKey::location)
                .filter(id -> TFC.equals(id.getNamespace()))
                .isPresent();
    }

    private static boolean isTfcOreFeature(Holder<PlacedFeature> holder, Set<ResourceLocation> kept,
                                           boolean removeDeposits) {
        final ResourceLocation id = holder.unwrapKey().map(ResourceKey::location).orElse(null);
        if (id == null || !TFC.equals(id.getNamespace()) || kept.contains(id)) return false;

        final String path = id.getPath();
        if (path.startsWith("vein/")) return true;
        return removeDeposits && (path.endsWith("_deposit") || path.endsWith("_deep_deposit"));
    }
}
