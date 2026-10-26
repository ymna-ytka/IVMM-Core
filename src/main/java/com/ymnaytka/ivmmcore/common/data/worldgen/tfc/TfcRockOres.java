package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public final class TfcRockOres {

    private static final List<TagPrefix> PREFIXES = new ArrayList<>();

    private TfcRockOres() {}

    /** Called from {@link IVMMCoreGTAddon#registerTagPrefixes()} (GT calls it once, before ore blocks are generated). */
    public static void init() {
        if (!PREFIXES.isEmpty()) return;

        for (String rock : TfcRocks.RAW_ROCKS) {
            final TagPrefix prefix = TagPrefix.oreTagPrefix("tfc_" + rock, BlockTags.MINEABLE_WITH_PICKAXE)
                    .langValue(capitalize(rock) + " %s Ore")
                    .registerOre(
                            new RawRockState(TfcRocks.rawBlockId(rock)),
                            () -> GTMaterials.Stone,
                            // Same hardness as GT's stone ore. TFC raw rock is harder; tune here if wanted.
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.STONE).requiresCorrectToolForDrops()
                                    .strength(3.0F, 3.0F).sound(SoundType.STONE),
                            new ResourceLocation("tfc", "block/rock/raw/" + rock));
            PREFIXES.add(prefix);
        }
    }

    public static List<TagPrefix> prefixes() {
        return PREFIXES;
    }

    private static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * Lazily resolves and caches the raw rock's default state. GT calls {@code get()} for every registered ore prefix
     * on every ore block it places, so after the first successful lookup this is a field read.
     * Until TFC's block is registered it returns air and is not cached.
     */
    private static final class RawRockState implements Supplier<BlockState> {

        private final ResourceLocation id;
        private volatile BlockState cached;

        private RawRockState(ResourceLocation id) {
            this.id = id;
        }

        @Override
        public BlockState get() {
            BlockState state = cached;
            if (state == null) {
                final Block block = ForgeRegistries.BLOCKS.getValue(id);
                if (block == null || block == Blocks.AIR) return Blocks.AIR.defaultBlockState();
                cached = state = block.defaultBlockState();
            }
            return state;
        }
    }
}
