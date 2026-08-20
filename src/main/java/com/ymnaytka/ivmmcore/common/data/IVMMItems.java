package com.ymnaytka.ivmmcore.common.data;

import com.ymnaytka.ivmmcore.common.item.FlaskItem;

import com.gregtechceu.gtceu.GTCEu;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.util.Helpers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import java.util.function.Function;

import static com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration.REGISTRATE;

public class IVMMItems {

    static {
        REGISTRATE.creativeModeTab(() -> IVMMCoreCreativeModeTab.IVMM_CORE);
    }

    public static ItemEntry<FlaskItem> FLASK_RABBIT = REGISTRATE.item("flask_rabbit",
                    p -> new FlaskItem(p, () -> 1000, TFCTags.Fluids.USABLE_IN_JUG))
            .lang("Waterskin (Rabbit)")
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> {
                var builder = prov.getBuilder(ctx.getName())
                        .parent(prov.getExistingFile(ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")))
                        .texture("layer0", "ivmmcore:item/flask_rabbit");

                prov.getBuilder(ctx.getName() + "_filled")
                        .parent(prov.getExistingFile(ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")))
                        .texture("layer0", "ivmmcore:item/flask_rabbit_filled");

                builder.override()
                        .predicate(ResourceLocation.fromNamespaceAndPath("ivmmcore", "filled"), 1.0f)
                        .model(prov.getExistingFile(prov.modLoc("item/" + ctx.getName() + "_filled")))
                        .end();
            })
            .onRegister(modelPredicate(new ResourceLocation("ivmmcore", "filled"), IVMMItems::isFilled))
            .register();
    // #TODO Enable the option to fill the flask with more than one bucket and make it work properly
    public static ItemEntry<FlaskItem> FLASK_SHEEP = REGISTRATE.item("flask_sheep",
                    p -> new FlaskItem(p, () -> 1000, TFCTags.Fluids.USABLE_IN_JUG))
            .lang("Waterskin (Sheep)")
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> {
                var builder = prov.getBuilder(ctx.getName())
                        .parent(prov.getExistingFile(ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")))
                        .texture("layer0", "ivmmcore:item/flask_sheep");

                prov.getBuilder(ctx.getName() + "_filled")
                        .parent(prov.getExistingFile(ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")))
                        .texture("layer0", "ivmmcore:item/flask_sheep_filled");

                builder.override()
                        .predicate(ResourceLocation.fromNamespaceAndPath("ivmmcore", "filled"), 1.0f)
                        .model(prov.getExistingFile(prov.modLoc("item/" + ctx.getName() + "_filled")))
                        .end();
            })
            .onRegister(modelPredicate(new ResourceLocation("ivmmcore", "filled"), IVMMItems::isFilled))
            .register();

    private static float isFilled(ItemStack stack) {
        IFluidHandler handler = Helpers.getCapability(stack, Capabilities.FLUID_ITEM);
        if (handler != null && !handler.getFluidInTank(0).isEmpty()) {
            return 1.0f;
        }
        return 0.0f;
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