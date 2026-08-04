package com.ymnaytka.ivmmcore.mixin.gtceu;

import com.gregtechceu.gtceu.common.block.StoneTypes;
import net.minecraft.world.level.levelgen.WorldgenRandom;

import org.spongepowered.asm.mixin.Mixin;

// This is a dummy mixin! It doesn't actually do anything.
// Mixins are ways to modify code in other classes.
// They can be very valuable in the right circumstances,
// but it is generally preferred that you try to use other means
// to get your code to work before resorting to mixins,
// as they can be highly invasive.
@Mixin(value = StoneTypes.class, remap = false)
public class StoneTypesMixins {}
