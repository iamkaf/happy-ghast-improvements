package com.iamkaf.happyghastimprovements.mixin;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Mixin to boost Happy Ghast movement speed when they have the Speed effect.
 * Speed boost: 100% + 100% per effect level (Speed I = 2x, Speed II = 3x, etc.)
 * This makes sugar-fed and honey-fed Happy Ghasts significantly faster.
 */
@Mixin(HappyGhast.class)
public class HappyGhastSpeedMixin {

    @ModifyVariable(
        method = "travel",
        at = @At("STORE"),
        ordinal = 0
    )
    private float happyghastimprovements$modifyTravelSpeed(float originalSpeed) {
        HappyGhast ghast = (HappyGhast)(Object)this;

        if (ghast.hasEffect(MobEffects.SPEED)) {
            MobEffectInstance speedEffect = ghast.getEffect(MobEffects.SPEED);
            if (speedEffect != null) {
                int amplifier = speedEffect.getAmplifier();
                // Each amplifier level increases speed by base boost + additional per level
                float speedBoost = HappyGhastImprovementsConstants.SPEED_BOOST_BASE +
                                 (amplifier * HappyGhastImprovementsConstants.SPEED_BOOST_PER_LEVEL);
                return originalSpeed * speedBoost;
            }
        }

        return originalSpeed;
    }
}