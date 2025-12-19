package com.iamkaf.happyghastimprovements.mixin;

import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HappyGhast.class)
public class HappyGhastSpeedMixin {

    @ModifyVariable(
        method = "travel",
        at = @At("STORE"),
        ordinal = 0
    )
    private float modifyTravelSpeed(float originalSpeed) {
        HappyGhast ghast = (HappyGhast)(Object)this;

        if (ghast.hasEffect(MobEffects.SPEED)) {
            MobEffectInstance speedEffect = ghast.getEffect(MobEffects.SPEED);
            if (speedEffect != null) {
                int amplifier = speedEffect.getAmplifier();
                // Each amplifier level increases speed by 100% + 100% additional per level
                float speedBoost = 2.0F + (amplifier * 1.0F);
                return originalSpeed * speedBoost;
            }
        }

        return originalSpeed;
    }
}