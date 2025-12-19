package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.platform.Services;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import com.iamkaf.amber.api.event.v1.events.common.PlayerEvents;

/**
 * Common entry point for the mod.
 * Replace the contents with your own implementation.
 */
public class HappyGhastImprovementsMod {

    /**
     * Called during mod initialization for all loaders.
     */
    public static void init() {
        HappyGhastImprovementsConstants.LOG.info("Initializing {} on {}...", HappyGhastImprovementsConstants.MOD_NAME,
                Services.PLATFORM.getPlatformName());

        PlayerEvents.ENTITY_INTERACT.register(HappyGhastImprovementsMod::onPlayerEntityInteract);
    }

    public static InteractionResult onPlayerEntityInteract(Player player, Level level, InteractionHand hand,
            Entity entity) {
        if (!(entity instanceof HappyGhast ghast)) {
            return InteractionResult.PASS;
        }

        return feedHappyGhast(player, ghast, hand) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    public static boolean feedHappyGhast(Player player, HappyGhast ghast, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);
        int amplifier;
        String foodName;

        if (item.is(Items.SUGAR)) {
            // Sugar gives Speed II (amplifier 1)
            amplifier = 1;
            foodName = "sugar";
        } else if (item.is(Items.HONEY_BOTTLE)) {
            // Honey gives Speed III (amplifier 2)
            amplifier = 2;
            foodName = "honey";
        } else {
            return false;
        }

        if (!player.level().isClientSide()) {
            HappyGhastImprovementsConstants.LOG.info(
                    "{} fed {} to a Happy Ghast in level {}",
                    player.getName().getString(),
                    foodName,
                    ghast.level().dimension().location());

            // Don't consume items in creative mode
            if (!player.getAbilities().instabuild) {
                item.shrink(1);
            }

            // Give the Happy Ghast a speed effect for 30 seconds (600 ticks)
            ghast.addEffect(new MobEffectInstance(MobEffects.SPEED, 600, amplifier));

            BlockPos pos = player.blockPosition();
            // Play different sounds for sugar vs honey
            var soundEvent = foodName.equals("sugar")
                ? SoundEvents.GENERIC_EAT
                : SoundEvents.HONEY_DRINK;
            player.level().playSound(null, ghast.blockPosition(), soundEvent.value(), SoundSource.NEUTRAL);

            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.CLOUD,
                        pos.getX() + 0.5d,
                        pos.getY() + 1,
                        pos.getZ() + 0.5d,
                        80,
                        0.01d,
                        0.5d,
                        0.01d,
                        0.05d);
            }
        }
        return true;
    }

    public static boolean tryFeedMountedGhast(Player player, InteractionHand hand) {
        // Check if player is riding a Happy Ghast
        if (player.getVehicle() instanceof HappyGhast ghast) {
            return feedHappyGhast(player, ghast, hand);
        }
        return false;
    }
}
