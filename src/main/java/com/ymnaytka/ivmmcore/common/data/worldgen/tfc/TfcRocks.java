package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * The 20 TFC rock types (net.dries007.tfc.common.blocks.rock.Rock in v3.2.23). Raw rock block id:
 * {@code tfc:rock/raw/<name>}. Hard-coded names avoid a compile-time dependency on TFC internals.
 * If a TFC update adds a rock, add it here.
 */
public final class TfcRocks {

    public static final List<String> RAW_ROCKS = List.of(
            "granite", "diorite", "gabbro", "shale", "claystone", "limestone", "conglomerate", "dolomite",
            "chert", "chalk", "rhyolite", "basalt", "andesite", "dacite", "quartzite", "slate", "phyllite",
            "schist", "gneiss", "marble");

    private TfcRocks() {}

    public static ResourceLocation rawBlockId(String rock) {
        return new ResourceLocation("tfc", "rock/raw/" + rock);
    }
}
