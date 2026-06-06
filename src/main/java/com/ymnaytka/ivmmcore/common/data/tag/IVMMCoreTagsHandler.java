package com.ymnaytka.ivmmcore.common.data.tag;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import com.tterrag.registrate.providers.RegistrateTagsProvider;

public class IVMMCoreTagsHandler {

    public static void initItem(RegistrateTagsProvider<Item> provider) {}

    public static void initBlock(RegistrateTagsProvider<Block> provider) {}

    public static void initFluid(RegistrateTagsProvider<Fluid> provider) {}

    public static void initEntity(RegistrateTagsProvider<EntityType<?>> provider) {}
}
