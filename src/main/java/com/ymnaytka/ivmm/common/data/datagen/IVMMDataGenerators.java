package com.ymnaytka.ivmm.common.data.datagen;

import com.gregtechceu.gtceu.api.registry.registrate.SoundEntryBuilder;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ymnaytka.ivmm.IVMMCore;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IVMMDataGenerators {

    public class CosmicDataGenerators {

        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput packOutput = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            var registries = event.getLookupProvider();
            if (event.includeClient()) {
                generator.addProvider(true, new SoundEntryBuilder.SoundEntryProvider(packOutput, IVMMCore.MOD_ID));
            }
        }
    }
}
