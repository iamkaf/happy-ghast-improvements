package com.iamkaf.happyghastimprovements;

import com.iamkaf.happyghastimprovements.HappyGhastImprovementsConstants;
import com.iamkaf.happyghastimprovements.HappyGhastImprovementsMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(HappyGhastImprovementsConstants.MOD_ID)
public class HappyGhastImprovementsNeoForge {
    public HappyGhastImprovementsNeoForge(IEventBus eventBus) {
        HappyGhastImprovementsMod.init();
    }
}