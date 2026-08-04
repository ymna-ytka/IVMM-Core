package com.ymnaytka.ivmmcore.common.data;

import com.gregtechceu.gtceu.common.block.StoneBlockType;
import com.ymnaytka.ivmmcore.IVMMCore;
import com.ymnaytka.ivmmcore.api.data.tag.IVMMCoreTagPrefix;
import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;

import com.gregtechceu.gtceu.common.data.models.GTModels;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;

import com.tterrag.registrate.util.entry.BlockEntry;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration.REGISTRATE;

public class IVMMBlocks {

    static {
        IVMMCoreRegistration.REGISTRATE.creativeModeTab(() -> IVMMCoreCreativeModeTab.IVMM_CORE);
    }

    public static final BlockEntry<Block> TACKY_BRICKS = createBrickCasingBlock("tacky_bricks",
            IVMMCore.id("block/casings/solid/tacky_bricks"));

    private static BlockEntry<Block> createBrickCasingBlock(String name, ResourceLocation texture) {
        return REGISTRATE.block(name, Block::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }


    public static void init() {}
}
