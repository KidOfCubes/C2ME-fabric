package com.ishland.c2me.mixin.fixes.worldgen.threading;

import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChunkGenerator.class)
public abstract class MixinChunkGenerator {

//    @Mutable
//    @Shadow @Final private List<ChunkPos> strongholds;
//
//    @Shadow public abstract MultiNoiseUtil.MultiNoiseSampler getMultiNoiseSampler();
//
//    @Shadow @Final private StructuresConfig structuresConfig;
//
//    @Shadow @Final protected BiomeSource populationSource;
//
//    @Shadow
//    private static boolean canPlaceStrongholdInBiome(Biome biome) {
//        throw new AbstractMethodError();
//    }
//
//    @Shadow @Final private long worldSeed;
//
//    @Shadow protected abstract void generateStrongholdPositions();
//
//    @Inject(method = "<init>(Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/gen/chunk/StructuresConfig;J)V", at = @At("RETURN"))
//    private void onInit(CallbackInfo info) {
////        this.strongholds = Collections.synchronizedList(strongholds);
//    }
//
//
//    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;generateStrongholdPositions()V"))
//    private void synchronizeGenerateStrongholdPositions(ChunkGenerator instance) {
//        final List<ChunkPos> strongholds = this.strongholds;
//        StrongholdConfig strongholdConfig = this.structuresConfig.getStronghold();
//        if (strongholds.isEmpty() && strongholdConfig != null && strongholdConfig.getCount() != 0) {
//            //noinspection SynchronizationOnLocalVariableOrMethodParameter
//            synchronized (strongholds) {
//                if (strongholds.isEmpty()) {
//                    if (this.strongholds.isEmpty()) {
//                        System.out.println("Initializing stronghold positions, this may take a while");
//                        this.generateStrongholdPositions();
//                        System.out.println("Stronghold positions initialized");
//                    }
//                }
//            }
//        }
//    }

    @Shadow private boolean field_37056;

    @Shadow protected abstract void method_41057();

    /**
     * @author ishland
     * @reason synchronize stronghold position generation
     */
    @Overwrite
    private void method_41058() {
        if (!this.field_37056) {
            synchronized (this) {
                if (!this.field_37056) {
                    System.out.println("Initializing stronghold positions, this may take a while");
                    this.method_41057();
                    this.field_37056 = true;
                    System.out.println("Stronghold positions initialized");
                }
            }
        }

    }

}
