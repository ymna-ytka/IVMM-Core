package com.ymnaytka.ivmm.common.data;

import com.gregtechceu.gtceu.common.data.models.GTModels;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.ymnaytka.ivmm.IVMMCore;
import com.ymnaytka.ivmm.common.util.CustomTags;

import java.util.function.Supplier;

import static com.ymnaytka.ivmm.common.registry.IVMMRegistration.REGISTRATE;

public class IVMMBlock {

    public static BlockEntry<Block> BLOOMERY_BRICKS = createCasingBlock(
            "bloomery_bricks",
            IVMMCore.id("block/casings/solid/bloomery_bricks"));

    public static BlockEntry<Block> LEATHER_BELLOWS = createSidedCasingBlock(
            "leather_bellows",
            IVMMCore.id("block/sided_casing/leather_bellows_side"),
            IVMMCore.id("block/sided_casing/leather_bellows_top"));

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createSidedCasingBlock(String name, ResourceLocation sideTexture,
                                                           ResourceLocation topTexture) {
        return createSidedCasingBlock(name, Block::new, sideTexture, topTexture,
                () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("removal")
    public static BlockEntry<Block> createSidedCasingBlock(String name,
                                                           NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                           ResourceLocation sideTexture,
                                                           ResourceLocation topTexture,
                                                           NonNullSupplier<? extends Block> properties,
                                                           Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models()
                        .cubeBottomTop(name, sideTexture, topTexture, topTexture)))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init() {}
}
