package com.ymnaytka.ivmm.common.data;

import com.gregtechceu.gtceu.common.data.models.GTModels;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.ymnaytka.ivmm.IVMMCore;
import com.ymnaytka.ivmm.common.util.CustomTags;

import java.util.function.Supplier;

import static com.ymnaytka.ivmm.common.registry.IVMMRegistration.REGISTRATE;

public class IVMMBlock {

    public static BlockEntry<Block> BLOOMERY_BRICKS = createBrickCasingBlock(
            "bloomery_bricks",
            IVMMCore.id("block/casings/solid/bloomery_bricks"));

    public static final BlockEntry<Block> LEATHER_BELLOWS = REGISTRATE
            .block("leather_bellows", Block::new)
            .initialProperties(() -> Blocks.SPRUCE_PLANKS)
            .lang("Leather Bellows")
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models().cubeBottomTop(ctx.getName(),
                    IVMMCore.id("block/leather_bellows_side"),
                    IVMMCore.id("block/leather_bellows_bottom"),
                    IVMMCore.id("block/leather_bellows_top"))))
            .simpleItem()
            .register();

    public static final BlockEntry<GlassBlock> CAMINITE_GLASS = createGlassCasingBlock("caminite_glass",
            IVMMCore.id("block/casings/transparent/caminite_glass"), () -> RenderType::cutoutMipped);

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

    private static BlockEntry<Block> createBrickCasingBlock(String name, ResourceLocation texture) {
        // return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS, type);
        return REGISTRATE.block(name, Block::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    private static BlockEntry<GlassBlock> createGlassCasingBlock(String name, ResourceLocation texture,
                                                                 Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, GlassBlock::new)
                .initialProperties(() -> Blocks.GLASS)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init() {}
}
