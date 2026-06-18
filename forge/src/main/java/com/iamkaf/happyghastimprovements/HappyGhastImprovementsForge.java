package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import com.iamkaf.happyghastimprovements.HappyGhastImprovementsMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(HappyGhastImprovementsConstants.MOD_ID)
public class HappyGhastImprovementsForge {

    public HappyGhastImprovementsForge() {
        HappyGhastImprovementsMod.init();
        PlayerInteractEvent.RightClickItem.BUS.addListener(this::onRightClickItem);
    }

    private boolean onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        return HappyGhastImprovementsMod.tryFeedMountedGhast(event.getEntity(), event.getHand());
    }
}
