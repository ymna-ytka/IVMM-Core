package com.ymnaytka.ivmmcore.common.data.worldgen.tfc;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.registries.ForgeRegistries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;

import java.util.Set;

public final class TfcRawRockTest extends RuleTest {

    public static final Codec<TfcRawRockTest> CODEC = RecordCodecBuilder.create(i -> i.group(
            RuleTest.CODEC.fieldOf("delegate").forGetter(t -> t.delegate)).apply(i, TfcRawRockTest::new));

    private final RuleTest delegate;
    private volatile Set<Block> rocks;

    public TfcRawRockTest(RuleTest delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean test(BlockState state, RandomSource random) {
        return delegate.test(state, random) || rocks().contains(state.getBlock());
    }

    @Override
    protected RuleTestType<?> getType() {
        return IVMMCoreRuleTests.TFC_RAW_ROCK.get();
    }

    private Set<Block> rocks() {
        Set<Block> set = rocks;
        if (set == null) {
            synchronized (this) {
                set = rocks;
                if (set == null) {
                    final Set<Block> built = new ReferenceOpenHashSet<>();
                    for (String rock : TfcRocks.RAW_ROCKS) {
                        final Block block = ForgeRegistries.BLOCKS.getValue(TfcRocks.rawBlockId(rock));
                        if (block != null && block != Blocks.AIR) built.add(block);
                    }
                    rocks = set = built;
                }
            }
        }
        return set;
    }
}
