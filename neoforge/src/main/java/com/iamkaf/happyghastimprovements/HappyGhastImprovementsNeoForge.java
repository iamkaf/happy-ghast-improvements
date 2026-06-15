package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import com.iamkaf.happyghastimprovements.HappyGhastImprovementsMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@Mod(HappyGhastImprovementsConstants.MOD_ID)
public class HappyGhastImprovementsNeoForge {
    public HappyGhastImprovementsNeoForge(IEventBus modEventBus) {
        HappyGhastImprovementsMod.init();
        // Register to the NeoForge game event bus
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        // Try to feed the mounted ghast when player uses an item
        if (HappyGhastImprovementsMod.tryFeedMountedGhast(event.getEntity(), event.getHand())) {
            event.setCanceled(true); // Cancel the event to prevent other processing
        }
    }
}