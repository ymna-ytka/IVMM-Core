package com.ymnaytka.ivmmcore;

import com.ymnaytka.ivmmcore.api.registries.IVMMCoreRegistration;
import com.ymnaytka.ivmmcore.common.data.*;
import com.ymnaytka.ivmmcore.common.data.machine.multiblock.IVMMCoreMultiblock_A;
import com.ymnaytka.ivmmcore.common.data.materials.IVMMCoreMaterials_A;
import com.ymnaytka.ivmmcore.common.data.materials.IVMMCoreModification;
import com.ymnaytka.ivmmcore.common.data.worldgen.IVMMCoreWorldgen;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IVMMCore.MOD_ID)
@SuppressWarnings("removal")
public class IVMMCore {

    public static final String MOD_ID = "ivmmcore";
    public static final Logger LOGGER = LogManager.getLogger();
    public static MaterialRegistry MATERIAL_REGISTRY;

    public IVMMCore() {
        IVMMCore.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addGenericListener(RecipeConditionType.class, this::registerConditions);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(SoundEntry.class, this::registerSounds);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);

        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);
    }



    public static void init() {
        IVMMCoreRegistration.REGISTRATE.registerRegistrate();
        IVMMCoreDatagen.init();
        IVMMItems.init();
        IVMMBlocks.init();
        IVMMCoreCreativeModeTab.init();
        IVMMCoreWorldgen.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @SubscribeEvent
    public void addMaterialRegistries(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(MOD_ID);
    }

    @SubscribeEvent
    public void addMaterials(MaterialEvent event) {
        IVMMCoreMaterials_A.register();
    }

    /**
     * (Optional) Used to modify pre-existing materials from GregTech
     *
     * @param event
     */
    private void modifyMaterials(PostMaterialEvent event) {
        IVMMCoreModification.modifyMaterials();
    }

    public void registerConditions(GTCEuAPI.RegisterEvent<String, RecipeConditionType<?>> event) {}

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        IVMMCoreRecipeTypes.init();
    }

    /**
     * Used to register your own new machines.
     * Call init() from your Machine class(es) here
     *
     * @param event
     */
    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        IVMMCoreMultiblock_A.init();
    }

    /**
     * Used to register your own new sounds
     * Call init from your Sound class(es) here
     *
     * @param event
     */
    public void registerSounds(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        // CustomSounds.init();
    }
}
