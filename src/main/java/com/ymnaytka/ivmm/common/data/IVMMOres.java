package com.ymnaytka.ivmm.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.*;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.common.data.GTOres;

import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class IVMMOres {

    public static void init() {}

    public static final GTOreDefinition EMBERS_VEIN = create("embers_vein", vein -> vein
            .clusterSize(UniformInt.of(40, 50)).density(0.45f).weight(60)
            .layer(WorldGenLayers.STONE)
            .heightRangeUniform(10, 50)
            .biomes(BiomeTags.IS_OVERWORLD)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(config -> config
                            .layer(l -> l.weight(3).mat(IVMMMaterials.Ember).size(2, 4))
                            .layer(l -> l.weight(2).mat(IVMMMaterials.Ember).size(1, 1))
                            .layer(l -> l.weight(1).mat(Coal).size(1, 1))
                            .build()))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(IVMMMaterials.Ember)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)));

    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        return GTOres.create(GTCEu.id(name), config);
    }
}
