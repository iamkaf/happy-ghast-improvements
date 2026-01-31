package com.iamkaf.happyghastimprovements.mixin;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to increase player's reach distance by 1 block when riding a Happy Ghast.
 * This allows players to interact with blocks from further away while flying.
 */
@Mixin(Player.class)
public class HappyGhastReachMixin {

    @Inject(method = "blockInteractionRange", at = @At("RETURN"), cancellable = true)
    private void happyghastimprovements$modifyBlockInteractionRange(CallbackInfoReturnable<Double> cir) {
        Player player = (Player)(Object)this;

        // If player is riding a Happy Ghast, add reach distance bonus
        if (player.getVehicle() instanceof HappyGhast) {
            double originalReach = cir.getReturnValue();
            cir.setReturnValue(originalReach + HappyGhastImprovementsConstants.REACH_DISTANCE_BONUS);
        }
    }

    @Inject(method = "entityInteractionRange", at = @At("RETURN"), cancellable = true)
    private void happyghastimprovements$modifyEntityInteractionRange(CallbackInfoReturnable<Double> cir) {
        Player player = (Player)(Object)this;

        // If player is riding a Happy Ghast, add reach distance bonus
        if (player.getVehicle() instanceof HappyGhast) {
            double originalReach = cir.getReturnValue();
            cir.setReturnValue(originalReach + HappyGhastImprovementsConstants.REACH_DISTANCE_BONUS);
        }
    }
}