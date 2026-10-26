package com.ymnaytka.ivmmcore.common.item;

import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.dries007.tfc.common.items.DiscreteFluidContainerItem;
import net.dries007.tfc.util.Drinkable;
import net.dries007.tfc.util.Helpers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class FlaskItem extends DiscreteFluidContainerItem {

    private static final int BAR_COLOR = 0x3F76E4;
    /** mB consumed per drink. */
    private static final int DRINK_AMOUNT = 100;
    /** Max mB accepted per fill: one bucket per click. */
    private static final int FILL_STEP = 1000;

    private final Supplier<Integer> flaskCapacity;
    private final TagKey<Fluid> flaskWhitelist;

    public FlaskItem(Item.Properties properties, Supplier<Integer> capacity, TagKey<Fluid> whitelist) {
        super(properties, capacity, whitelist, false, false);
        this.flaskCapacity = capacity;
        this.flaskWhitelist = whitelist;
    }

    public int getFlaskCapacity() {
        return flaskCapacity.get();
    }

    /** Fluid amount read directly from the stack NBT ({@code Fluid.Amount}), no capability lookup. */
    public int getAmount(ItemStack stack) {
        final CompoundTag tag = stack.getTagElement(FluidHandlerItemStack.FLUID_NBT_KEY);
        return tag == null ? 0 : tag.getInt("Amount");
    }

    public boolean isFull(ItemStack stack) {
        return getAmount(stack) >= getFlaskCapacity();
    }

    // ---------------------------------------------------------------- capability

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidHandlerItemStack(stack, getFlaskCapacity()) {

            @Override
            public boolean isFluidValid(int tank, FluidStack fluid) {
                return fluid.getFluid().is(flaskWhitelist);
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {
                if (resource.getAmount() > FILL_STEP) {
                    resource = resource.copy();
                    resource.setAmount(FILL_STEP);
                }
                return super.fill(resource, action);
            }
        };
    }

    // ---------------------------------------------------------------- fill bar

    /** Visible only while partially filled: hidden when empty and when full (the texture changes instead). */
    @Override
    public boolean isBarVisible(ItemStack stack) {
        final int amount = getAmount(stack);
        return amount > 0 && amount < getFlaskCapacity();
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Mth.clamp(Math.round(13.0F * getAmount(stack) / getFlaskCapacity()), 0, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    // ---------------------------------------------------------------- interaction

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        final BlockHitResult hit = Helpers.rayTracePlayer(level, player, ClipContext.Fluid.SOURCE_ONLY);

        final IFluidHandler handler = Helpers.getCapability(stack, Capabilities.FLUID_ITEM);
        if (handler == null) {
            return InteractionResultHolder.pass(stack);
        }

        // Own pickup logic: up to one bucket per click, works while the flask is already partially filled
        if (hit.getType() == HitResult.Type.BLOCK && !isFull(stack) && !player.isShiftKeyDown()) {
            final BlockPos pos = hit.getBlockPos();
            final FluidState fluidState = level.getFluidState(pos);

            if (fluidState.isSource() && fluidState.getType().is(flaskWhitelist)) {
                final FluidStack toFill = new FluidStack(fluidState.getType(), FILL_STEP);

                // Accept any amount that fits (up to one bucket), not only a full bucket
                final int accepted = handler.fill(toFill, IFluidHandler.FluidAction.SIMULATE);
                if (accepted > 0) {
                    if (level.isClientSide) {
                        return InteractionResultHolder.success(stack);
                    }
                    final BlockState state = level.getBlockState(pos);
                    if (state.getBlock() instanceof BucketPickup pickup &&
                            !pickup.pickupBlock(level, pos, state).isEmpty()) {
                        handler.fill(toFill, IFluidHandler.FluidAction.EXECUTE);
                        level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.PLAYERS, 1.0f, 1.0f);
                        return InteractionResultHolder.consume(stack);
                    }
                }
            }
        }

        if (handler.getFluidInTank(0).isEmpty()) {
            return afterFillFailed(handler, level, player, stack, hand);
        } else {
            return afterEmptyFailed(handler, level, player, stack, hand);
        }
    }

    @Override
    protected InteractionResultHolder<ItemStack> afterEmptyFailed(
                                                                  IFluidHandler handler,
                                                                  Level level,
                                                                  Player player,
                                                                  ItemStack stack,
                                                                  InteractionHand hand) {
        if (player.isShiftKeyDown()) {
            level.playSound(player, player.blockPosition(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 0.5f, 1.2f);
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

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        final IFluidHandler handler = stack.getCapability(Capabilities.FLUID_ITEM).resolve().orElse(null);
        if (handler != null) {
            final FluidStack drained = handler.drain(DRINK_AMOUNT, IFluidHandler.FluidAction.EXECUTE);
            if (entity instanceof Player player && !drained.isEmpty() && !level.isClientSide) {
                final Drinkable drinkable = Drinkable.get(drained.getFluid());
                if (drinkable != null) {
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

    // ---------------------------------------------------------------- client

    /** Registers the {@code ivmmcore:filled} predicate: 1.0 only when the flask is completely full. */
    @OnlyIn(Dist.CLIENT)
    public static void registerModelPredicate(Item item) {
        ItemProperties.register(
                item,
                ResourceLocation.fromNamespaceAndPath("ivmmcore", "filled"),
                (stack, level, entity, seed) -> stack.getItem() instanceof FlaskItem flask && flask.isFull(stack) ?
                        1.0f :
                        0.0f);
    }
}
