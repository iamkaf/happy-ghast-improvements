package com.iamkaf.happyghastimprovements.mixin;

import java.util.Collection;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;

/**
 * Mixin to play a sound when Happy Ghast loses the Speed effect.
 * This provides audio feedback when the speed boost expires.
 */
@Mixin(LivingEntity.class)
public class HappyGhastEffectRemovalMixin {

    @Inject(method = "onEffectsRemoved", at = @At("TAIL"))
    private void happyghastimprovements$onEffectsRemoved(Collection<MobEffectInstance> effects, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // Only proceed for Happy Ghasts
        if (!(entity instanceof HappyGhast)) {
            return;
        }

        // Only run server side
        if (entity.level().isClientSide()) {
            return;
        }

        // Check if Speed effect was removed
        boolean hadSpeedEffect = effects.stream()
                .anyMatch(effect -> effect.getEffect().is(MobEffects.SPEED));

        if (hadSpeedEffect) {
            entity.level().playSound(null, entity.blockPosition(), SoundEvents.ZOMBIE_INFECT,
                    SoundSource.NEUTRAL, 2.5F, 1.1F);
            HappyGhastImprovementsConstants.LOG.debug("SENDING SOUND!");
        }
    }
}