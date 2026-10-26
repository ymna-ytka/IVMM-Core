package com.ymnaytka.ivmmcore.common.item;

import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.dries007.tfc.common.fluids.FluidHelpers;
import net.dries007.tfc.common.items.DiscreteFluidContainerItem;
import net.dries007.tfc.util.Drinkable;
import net.dries007.tfc.util.Helpers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.function.Supplier;

public class FlaskItem extends DiscreteFluidContainerItem {

    public FlaskItem(
                     Item.Properties properties, Supplier<Integer> capacity, TagKey<Fluid> whitelist) {
        super(properties, capacity, whitelist, false, false);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
                                                  Level level, Player player, InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        final BlockHitResult hit = Helpers.rayTracePlayer(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (FluidHelpers.transferBetweenWorldAndItem(
                stack,
                level,
                hit,
                player,
                hand,
                canPlaceLiquidsInWorld(),
                canPlaceSourceBlocks(),
                true)) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        final IFluidHandler handler = Helpers.getCapability(stack, Capabilities.FLUID_ITEM);
        if (handler == null) {
            return InteractionResultHolder.pass(stack);
        }
        if (handler.getFluidInTank(0).isEmpty()) {
            return afterFillFailed(handler, level, player, stack, hand);
        } else {
            return afterEmptyFailed(handler, level, player, stack, hand);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        final IFluidHandler handler = stack.getCapability(Capabilities.FLUID_ITEM).resolve().orElse(null);
        if (handler != null) {
            final FluidStack drained = handler.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.EXECUTE);
            if (entity instanceof Player player) {
                final Drinkable drinkable = Drinkable.get(drained.getFluid());
                if (drinkable != null && !level.isClientSide) {
                    drinkable.onDrink(player, drained.getAmount());
                }
            }
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return PotionItem.EAT_DURATION;
    }

    @Override
    protected InteractionResultHolder<ItemStack> afterEmptyFailed(
                                                                  IFluidHandler handler,
                                                                  Level level,
                                                                  Player player,
                                                                  ItemStack stack,
                                                                  InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            level.playSound(
                    player,
                    player.blockPosition(),
                    SoundEvents.BUCKET_EMPTY,
                    SoundSource.PLAYERS,
                    0.5f,
                    1.2f);
            handler.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.EXECUTE);
            return InteractionResultHolder.consume(stack);
        }
        final Drinkable drinkable = Drinkable.get(handler.getFluidInTank(0).getFluid());
        if (drinkable != null) {
            if (!drinkable.mayDrinkWhenFull() && player.getFoodData() instanceof TFCFoodData food &&
                    food.getThirst() >= TFCFoodData.MAX_THIRST) {
                return InteractionResultHolder.fail(stack);
            }
            return ItemUtils.startUsingInstantly(level, player, hand);
        }
        return InteractionResultHolder.pass(stack);
    }

    public static void registerModelPredicate(Item item) {
        ItemProperties.register(
                item,
                new ResourceLocation("ivmmcore", "filled"),
                (stack, level, entity, seed) -> {
                    IFluidHandler handler = Helpers.getCapability(stack, Capabilities.FLUID_ITEM);
                    if (handler != null && !handler.getFluidInTank(0).isEmpty()) {
                        return 1.0f;
                    }
                    return 0.0f;
                });
    }
}
