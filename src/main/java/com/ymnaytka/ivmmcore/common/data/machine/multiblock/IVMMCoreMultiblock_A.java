package com.ymnaytka.ivmmcore.common.data.machine.multiblock;

import com.ymnaytka.ivmmcore.IVMMCore;
import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;
import com.ymnaytka.ivmmcore.common.data.IVMMBlocks;
import com.ymnaytka.ivmmcore.common.data.IVMMCoreCreativeModeTab;
import com.ymnaytka.ivmmcore.common.data.IVMMCoreRecipeTypes;
import com.ymnaytka.ivmmcore.common.machine.multiblock.primitive.EarthCharcoalPileIgniterMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.api.pattern.Predicates.controller;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration.REGISTRATE;

public class IVMMCoreMultiblock_A {

    static {
        IVMMCoreRegistration.REGISTRATE.creativeModeTab(() -> IVMMCoreCreativeModeTab.IVMM_CORE);
    }

    public static MultiblockMachineDefinition PRIMITIVE_EARTH_FURNACE = REGISTRATE
            .multiblock("primitive_earth_furnace", PrimitiveBlastFurnaceMachine::new)
            .langValue("Primitive Earth Furnace")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(IVMMCoreRecipeTypes.PRIMITIVE_EARTH_FURNACE_RECIPES)
            .appearanceBlock(IVMMBlocks.TACKY_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCC", "CCC", "CCC", "CCC", " C ", " C ")
                    .aisle("CCC", "CKC", "CKC", "CKC", "CKC", "CKC")
                    .aisle("CCC", "CCC", "C@C", "CCC", " C ", " C ")
                    .where("@", controller(blocks(definition.get())))
                    .where(" ", Predicates.any())
                    .where("C", blocks(IVMMBlocks.TACKY_BRICKS.get()))
                    .where('K', Predicates.air())
                    .build())
            .model(createWorkableCasingMachineModel(IVMMCore.id("block/casings/solid/tacky_bricks"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace")))
            .register();

    public static MultiblockMachineDefinition EARTH_PILE_IGNITER = REGISTRATE
            .multiblock("earth_pile_igniter", EarthCharcoalPileIgniterMachine::new)
            .langValue("Earth Pile Igniter")
            .rotationState(RotationState.NONE)
            .recipeTypes(DUMMY_RECIPES)
            .appearanceBlock(IVMMBlocks.TACKY_BRICKS)
            .tooltips(Component.translatable("gtceu.machine.charcoal_pile.tooltip.0"),
                    Component.translatable("gtceu.machine.charcoal_pile.tooltip.1"),
                    Component.translatable("gtceu.machine.charcoal_pile.tooltip.2"),
                    Component.translatable("gtceu.machine.charcoal_pile.tooltip.3"))
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("     ", " XXX ", " XXX ", " XXX ", "     ")
                    .aisle(" BBB ", "XCCCX", "XCCCX", "XCCCX", " DDD ")
                    .aisle(" BBB ", "XCCCX", "XCCCX", "XCCCX", " DSD ")
                    .aisle(" BBB ", "XCCCX", "XCCCX", "XCCCX", " DDD ")
                    .aisle("     ", " XXX ", " XXX ", " XXX ", "     ")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('B', blocks(IVMMBlocks.TACKY_BRICKS.get()))
                    .where('X', blocks(Blocks.DIRT))
                    .where('D', blocks(Blocks.DIRT))
                    .where('C', blocks(Blocks.OAK_LOG))
                    .build())
            .allowFlip(false)
            .allowExtendedFacing(false)
            .workableCasingModel(IVMMCore.id("block/casings/solid/tacky_bricks"),
                    GTCEu.id("block/multiblock/charcoal_pile_igniter"))
            .register();

    public static void init() {}
}
