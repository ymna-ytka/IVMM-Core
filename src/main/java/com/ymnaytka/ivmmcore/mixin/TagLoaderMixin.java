package com.ymnaytka.ivmmcore.mixin;

import com.gregtechceu.gtceu.common.data.GTMaterialBlocks;
import com.ymnaytka.ivmmcore.IVMMCore;
import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;
import com.ymnaytka.ivmmcore.common.data.worldgen.tfc.TfcRockOres;
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

/**
 * Adds every {@code gtceu:tfc_<rock>_<material>_ore} block to the block tag {@code tfc:rock/ores}.
 * TFC's {@code can_start_collapse} and {@code can_trigger_collapse} reference that tag, so GT ores in TFC rock take
 * part in TFC cave-ins like TFC's own ores. Same technique as GTCEu's own TagLoaderMixin (hook at RETURN of load).
 *
 * <p>Runs once per tag reload, not per tick. Restricted to the block tag directory so the entries never reach
 * item/fluid tag loaders (an unknown element would fail the whole tag).
 */
@Mixin(TagLoader.class)
public abstract class TagLoaderMixin {

    private static final ResourceLocation TFC_ROCK_ORES = new ResourceLocation("tfc", "rock/ores");
    private static final String BLOCK_TAG_DIRECTORY = "tags/blocks";

    @Shadow
    @Final
    private String directory;

    @Inject(method = "load", at = @At("RETURN"))
    private void ivmmcore$addGtRockOresToTfcOreTag(ResourceManager resourceManager,
                                              CallbackInfoReturnable<Map<ResourceLocation, List<TagLoader.EntryWithSource>>> cir) {
        if (!BLOCK_TAG_DIRECTORY.equals(directory)
                || !IVMMCoreConfig.gtOresInTfc()
                || GTMaterialBlocks.MATERIAL_BLOCKS == null) return;

        final List<TagLoader.EntryWithSource> entries = cir.getReturnValue()
                .computeIfAbsent(TFC_ROCK_ORES, id -> new ArrayList<>());

        int added = 0;
        for (var prefix : TfcRockOres.prefixes()) {
            for (var blockEntry : GTMaterialBlocks.MATERIAL_BLOCKS.row(prefix).values()) {
                entries.add(new TagLoader.EntryWithSource(TagEntry.element(blockEntry.getId()), IVMMCore.MOD_ID));
                added++;
            }
        }
        IVMMCore.LOGGER.debug("[IVMMCore] added {} GT rock ore blocks to #tfc:rock/ores", added);
    }
}
