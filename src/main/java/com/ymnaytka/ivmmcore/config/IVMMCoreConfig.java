package com.ymnaytka.ivmmcore.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * COMMON config (config/ivmmcore-common.toml). Read once per biome / per layer registration, never per tick.
 * Changing values requires a game restart.
 */
public final class IVMMCoreConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue DISABLE_TFC_ORES;
    public static final ForgeConfigSpec.BooleanValue REMOVE_DEPOSITS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> KEPT_FEATURES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_GT_ORES_IN_TFC;

    static {
        final ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        b.push("tfc_ore_removal");
        DISABLE_TFC_ORES = b
                .comment("Remove TFC ore veins (and the surface indicators they create) from TFC biomes.")
                .define("disableTfcOres", true);
        REMOVE_DEPOSITS = b
                .comment("Also remove TFC '*_deposit' / '*_deep_deposit' features (small surface/river ores).")
                .define("removeDeposits", true);
        KEPT_FEATURES = b
                .comment("Placed features under 'tfc:vein/' that are NOT ores and must stay (rock dikes, gravel, kaolin).",
                        "Add ids here to keep them; remove ids to delete them too.")
                .defineListAllowEmpty("keptFeatures",
                        List.of("tfc:vein/granite_dike", "tfc:vein/diorite_dike", "tfc:vein/gabbro_dike",
                                "tfc:vein/gravel", "tfc:vein/kaolin_disc"),
                        o -> o instanceof String s && ResourceLocation.tryParse(s) != null);
        b.pop();

        b.push("gregtech");
        ENABLE_GT_ORES_IN_TFC = b
                .comment("Generate GTCEu ore veins in TFC worlds (hooks TFCChunkGenerator, extends GT layers to TFC raw rock).")
                .define("enableGtOresInTfc", true);
        b.pop();

        SPEC = b.build();
    }

    private IVMMCoreConfig() {}

    // COMMON config is not loaded during data generation (runData) and some very early init stages, where
    // ConfigValue#get() throws IllegalStateException. These accessors fall back to the defaults there.

    public static boolean disableTfcOres() {
        return read(DISABLE_TFC_ORES, true);
    }

    public static boolean removeDeposits() {
        return read(REMOVE_DEPOSITS, true);
    }

    public static boolean gtOresInTfc() {
        return read(ENABLE_GT_ORES_IN_TFC, true);
    }

    private static boolean read(ForgeConfigSpec.BooleanValue value, boolean fallback) {
        try {
            return value.get();
        } catch (IllegalStateException notLoaded) {
            return fallback;
        }
    }

    public static Set<ResourceLocation> keptFeatureIds() {
        final Set<ResourceLocation> ids = new HashSet<>();
        final List<? extends String> configured;
        try {
            configured = KEPT_FEATURES.get();
        } catch (IllegalStateException notLoaded) {
            return ids;
        }
        for (String s : configured) {
            final ResourceLocation id = ResourceLocation.tryParse(s);
            if (id != null) ids.add(id);
        }
        return ids;
    }
}
