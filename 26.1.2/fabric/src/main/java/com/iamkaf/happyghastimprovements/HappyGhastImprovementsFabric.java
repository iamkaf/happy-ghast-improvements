package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Fabric entry point.
 */
public class HappyGhastImprovementsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        HappyGhastImprovementsMod.init();

        // Register Fabric callback for mounted feeding
        UseItemCallback.EVENT.register((Player player, Level level, InteractionHand hand) -> {
            if (HappyGhastImprovementsMod.tryFeedMountedGhast(player, hand)) {
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
    }
}
