package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import com.iamkaf.happyghastimprovements.HappyGhastImprovementsMod;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(HappyGhastImprovementsConstants.MOD_ID)
public class HappyGhastImprovementsForge {

    public HappyGhastImprovementsForge() {
        HappyGhastImprovementsMod.init();
        //? if >=26.1
        PlayerInteractEvent.EntityInteractSpecific.BUS.addListener(this::onEntityInteractSpecific);
        //? if <26.1
        /*PlayerInteractEvent.EntityInteract.BUS.addListener(this::onEntityInteract);*/
        PlayerInteractEvent.RightClickItem.BUS.addListener(this::onRightClickItem);
    }

    //? if >=26.1 {
    private boolean onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        InteractionResult result = HappyGhastImprovementsMod.tryFeedTargetEntity(
                event.getEntity(), event.getEntity().level(), event.getHand(), event.getTarget());
        if (result == InteractionResult.PASS) {
            return false;
        }

        event.setCancellationResult(result);
        return true;
    }
    //?}
    //? if <26.1 {
    /*private boolean onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        InteractionResult result = HappyGhastImprovementsMod.tryFeedTargetEntity(
                event.getEntity(), event.getEntity().level(), event.getHand(), event.getTarget());
        if (result == InteractionResult.PASS) {
            return false;
        }

        event.setCancellationResult(result);
        return true;
    }*/
    //?}

    private boolean onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        return HappyGhastImprovementsMod.tryFeedMountedGhast(event.getEntity(), event.getHand());
    }
}
