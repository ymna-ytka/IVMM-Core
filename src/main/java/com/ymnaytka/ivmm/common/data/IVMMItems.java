package com.ymnaytka.ivmm.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.item.component.IRecipeRemainder;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import java.util.function.Function;

import static com.ymnaytka.ivmm.common.registry.IVMMRegistration.REGISTRATE;

@SuppressWarnings({ "unused" })
public class IVMMItems {

    public static final ItemEntry<ComponentItem> FIBER = REGISTRATE
            .item("fiber", ComponentItem::create)
            .lang("Fiber")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> DRIED_FIBER = REGISTRATE
            .item("dried_fiber", ComponentItem::create)
            .lang("Dried Fiber")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> ROPE = REGISTRATE
            .item("rope", ComponentItem::create)
            .lang("Rope")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> STRONG_ROPE = REGISTRATE
            .item("strong_rope", ComponentItem::create)
            .lang("Strong Rope")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PRIMITIVE_CAMINITE_HAMMER = REGISTRATE
            .item("primitive_caminite_hammer", ComponentItem::create)
            .lang("Caminite Hammer")
            .properties(p -> p.stacksTo(1).defaultDurability(75))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PRIMITIVE_CAMINITE_KNIFE = REGISTRATE
            .item("primitive_caminite_knife", ComponentItem::create)
            .lang("Caminite Knife")
            .properties(p -> p.stacksTo(1).defaultDurability(75))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PRIMITIVE_CAMINITE_SAW = REGISTRATE
            .item("primitive_caminite_saw", ComponentItem::create)
            .lang("Caminite Saw")
            .properties(p -> p.stacksTo(1).defaultDurability(75))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> COMPRESSED_CAMINITE_CLAY = REGISTRATE
            .item("compressed_caminite_clay", ComponentItem::create)
            .lang("Compressed Caminite Clay")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PRIMITIVE_CAMINITE_FILE = REGISTRATE
            .item("primitive_caminite_file", ComponentItem::create)
            .lang("Primitive Caminite File")
            .properties(p -> p.stacksTo(1).defaultDurability(75))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PRIMITIVE_CAMINITE_MORTAR = REGISTRATE
            .item("primitive_caminite_mortar", ComponentItem::create)
            .lang("Primitive Caminite Mortar")
            .properties(p -> p.stacksTo(1).defaultDurability(32))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> WOODEN_BLOCK_FORM = REGISTRATE
            .item("wooden_block_form", ComponentItem::create)
            .lang("Wooden Block Form")
            .properties(p -> p.stacksTo(1))
            .tag()
            .defaultModel()
            .onRegister(attach((IRecipeRemainder) ItemStack::copy))
            .register();

    public static final ItemEntry<ComponentItem> WOODEN_BUCKET_FORM = REGISTRATE
            .item("wooden_bucket_form", ComponentItem::create)
            .lang("Wooden Bucket Form")
            .properties(p -> p.stacksTo(1))
            .tag()
            .defaultModel()
            .onRegister(attach((IRecipeRemainder) ItemStack::copy))
            .register();

    public static final ItemEntry<ComponentItem> WREATH = REGISTRATE
            .item("wreath", ComponentItem::create)
            .lang("Wreath")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> DRY_WREATH = REGISTRATE
            .item("dry_wreath", ComponentItem::create)
            .lang("Dry Wreath")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> BLOOM = REGISTRATE
            .item("bloom", ComponentItem::create)
            .lang("Bloom (Iron)")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> DRIED_LEATHER = REGISTRATE
            .item("dried_leather", ComponentItem::create)
            .lang("Dried Leather")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> WORN_LEATHER = REGISTRATE
            .item("worn_leather", ComponentItem::create)
            .lang("Worn Leather")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> CLEANSED_LEATHER = REGISTRATE
            .item("cleansed_leather", ComponentItem::create)
            .lang("Cleansed Leather")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> TANNED_LEATHER = REGISTRATE
            .item("tanned_leather", ComponentItem::create)
            .lang("Tanned Leather")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> PROCESSED_LEATHER = REGISTRATE
            .item("processed_leather", ComponentItem::create)
            .lang("Processed Leather")
            .properties(p -> p.stacksTo(64))
            .onRegister(attach(new TooltipBehavior(tooltips -> {
                tooltips.add(Component.translatable("ivmmcore.stage_processed.1_stage"));
            })))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> TREATED_LEATHER = REGISTRATE
            .item("treated_leather", ComponentItem::create)
            .lang("Treated Leather")
            .properties(p -> p.stacksTo(64))
            .onRegister(attach(new TooltipBehavior(tooltips -> {
                tooltips.add(Component.translatable("ivmmcore.stage_processed.2_stage"));
            })))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> CHUNK_LEATHER = REGISTRATE
            .item("chunk_leather", ComponentItem::create)
            .lang("Chunk Leather")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> SEARED_STONE_PLATE = REGISTRATE
            .item("seared_stone_plate", ComponentItem::create)
            .lang("Seared Stone Plate")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> SEARED_STONE_DUST = REGISTRATE
            .item("seared_stone_dust", ComponentItem::create)
            .lang("Seared Stone dust")
            .properties(p -> p.stacksTo(64))
            .tag()
            .defaultModel()
            .register();

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static <T extends Item> NonNullConsumer<T> modelPredicate(ResourceLocation predicate,
                                                                     Function<ItemStack, Float> property) {
        return item -> {
            if (GTCEu.isClientSide()) {
                ItemProperties.register(item, predicate, (itemStack, c, l, i) -> property.apply(itemStack));
            }
        };
    }

    public static void init() {}
}
