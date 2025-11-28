package com.ymnaytka.ivmm.common.util;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CustomTags {

    public static final TagKey<Block> MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH = TagUtil
            .createModBlockTag("mineable/pickaxe_or_wrench");
}
