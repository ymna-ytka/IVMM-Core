package com.ymnaytka.ivmmcore.common.data.worldgen;

import com.ymnaytka.ivmmcore.common.data.materials.IVMMCoreMaterials;

import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.NoopVeinGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.worldgen.BiomeWeightModifier.EMPTY;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTOres.OVERWORLD_RULES;

@SuppressWarnings("all")
public class IVMMCoreOreVeins_A {

    public static void init() {
        create("azurite_vein", vein -> vein
                .clusterSize(UniformInt.of(30, 45)).density(0.15f).weight(60)
                .layer(WorldGenLayers.STONE)
                .heightRangeUniform(30, 60)
                .biomes(BiomeTags.IS_OVERWORLD)
                .layeredVeinGenerator(generator -> generator
                        .withLayerPattern(() -> GTLayerPattern.builder(OVERWORLD_RULES)
                                .layer(l -> l.weight(2).mat(Limonite).size(1, 1))
                                .layer(l -> l.weight(2).mat(IVMMCoreMaterials.Azurite).size(1, 1))
                                .layer(l -> l.weight(1).mat(Malachite).size(1, 1))
                                .layer(l -> l.weight(1).mat(Bornite).size(1, 1))
                                .build()))
                .surfaceIndicatorGenerator(indicator -> indicator
                        .surfaceRock(IVMMCoreMaterials.Azurite)
                        .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));
    }

    private static GTOreDefinition blank() {
        return new GTOreDefinition(
                ConstantInt.ZERO, 0, 0, IWorldGenLayer.NOWHERE, Set.of(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(0)),
                0, HolderSet::direct, EMPTY, NoopVeinGenerator.INSTANCE,
                new ArrayList<>());
    }

    private static void create(String name, Consumer<GTOreDefinition> config) {
        GTOreDefinition def = blank();
        config.accept(def);
        ResourceLocation id = new ResourceLocation("ivmmcore", name);
        def.register(id);
        GTRegistries.ORE_VEINS.registerOrOverride(id, def);
    }
}
