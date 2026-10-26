package com.ymnaytka.ivmmcore.mixin.gtceu;

import com.ymnaytka.ivmmcore.IVMMCore;
import com.ymnaytka.ivmmcore.common.data.worldgen.tfc.TfcRockOres;
import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;

import com.gregtechceu.gtceu.common.data.GTMaterialBlocks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(TagLoader.class)
public abstract class TagLoaderMixin {

    private static final ResourceLocation TFC_ROCK_ORES = ResourceLocation.fromNamespaceAndPath("tfc", "rock/ores");
    private static final String BLOCK_TAG_DIRECTORY = "tags/blocks";

    @Shadow
    @Final
    private String directory;

    @Inject(method = "load", at = @At("RETURN"))
    private void ivmmcore$addGtRockOresToTfcOreTag(ResourceManager resourceManager,
                                                   CallbackInfoReturnable<Map<ResourceLocation, List<TagLoader.EntryWithSource>>> cir) {
        if (!BLOCK_TAG_DIRECTORY.equals(directory) || !IVMMCoreConfig.gtOresInTfc() ||
                GTMaterialBlocks.MATERIAL_BLOCKS == null)
            return;

        final List<TagLoader.EntryWithSource> entries = cir.getReturnValue()
                .computeIfAbsent(TFC_ROCK_ORES, id -> new ArrayList<>());

        int added = 0;
        for (var prefix : TfcRockOres.prefixes()) {
            for (var blockEntry : GTMaterialBlocks.MATERIAL_BLOCKS.row(prefix).values()) {
                entries.add(new TagLoader.EntryWithSource(TagEntry.element(blockEntry.getId()), IVMMCore.MOD_ID));
                added++;
            }
        }
    }
}
