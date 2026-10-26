package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public final class TfcRocks {

    public static final List<String> RAW_ROCKS = List.of(
            "granite", "diorite", "gabbro", "shale", "claystone", "limestone", "conglomerate", "dolomite",
            "chert", "chalk", "rhyolite", "basalt", "andesite", "dacite", "quartzite", "slate", "phyllite",
            "schist", "gneiss", "marble");

    private TfcRocks() {}

    public static ResourceLocation rawBlockId(String rock) {
        return ResourceLocation.fromNamespaceAndPath("tfc", "rock/raw/" + rock);
    }
}
