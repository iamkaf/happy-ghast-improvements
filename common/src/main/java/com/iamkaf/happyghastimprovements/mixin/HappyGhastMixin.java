package com.iamkaf.happyghastimprovements.mixin;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to make Happy Ghasts quieter by increasing their ambient sound interval.
 * Normal: 36 seconds between sounds (was 6 seconds)
 * When ridden: 60 seconds between sounds (was 36 seconds)
 */
@Mixin(HappyGhast.class)
public class HappyGhastMixin {

    @Inject(method = "getAmbientSoundInterval", at = @At("HEAD"), cancellable = true)
    private void happyghastimprovements$getAmbientSoundInterval(CallbackInfoReturnable<Integer> cir) {
        // Return the appropriate interval based on whether the ghast is being ridden
        if (((HappyGhast)(Object)this).isVehicle()) {
            cir.setReturnValue(HappyGhastImprovementsConstants.RIDDEN_AMBIENT_SOUND_INTERVAL);
        } else {
            cir.setReturnValue(HappyGhastImprovementsConstants.NORMAL_AMBIENT_SOUND_INTERVAL);
        }
    }
}