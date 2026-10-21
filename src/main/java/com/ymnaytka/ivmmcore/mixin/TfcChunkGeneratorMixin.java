package com.ymnaytka.ivmmcore.mixin;

import com.gregtechceu.gtceu.api.data.worldgen.ores.OrePlacer;
import com.ymnaytka.ivmmcore.config.IVMMCoreConfig;
import net.dries007.tfc.world.TFCChunkGenerator;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * GTCEu places its veins from a Mixin at the TAIL of {@code ChunkGenerator#applyBiomeDecoration}. TFC overrides that
 * method and never calls super, so GT's hook never fires for TFC worlds. This repeats GT's hook on the TFC override.
 *
 * <p>One {@link OrePlacer} per generator instance, exactly like GT's own mixin (the placer owns the vein cache).
 */
@Mixin(TFCChunkGenerator.class)
public abstract class TfcChunkGeneratorMixin {

    @Unique
    private final OrePlacer ivmmcore$orePlacer = new OrePlacer();

    @Inject(method = "applyBiomeDecoration", at = @At("TAIL"))
    private void ivmmcore$placeGtOres(WorldGenLevel level, ChunkAccess chunk, StructureManager structureManager,
                                  CallbackInfo ci) {
        if (IVMMCoreConfig.gtOresInTfc()) {
            ivmmcore$orePlacer.placeOres(level, (ChunkGenerator) (Object) this, chunk);
        }
    }
}
