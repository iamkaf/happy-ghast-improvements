package com.iamkaf.happyghastimprovements;

import com.iamkaf.konfig.neoforge.api.v1.KonfigNeoForgeClientScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = HappyGhastImprovementsConstants.MOD_ID, dist = Dist.CLIENT)
public final class HappyGhastImprovementsNeoForgeClient {
    public HappyGhastImprovementsNeoForgeClient(ModContainer container) {
        KonfigNeoForgeClientScreens.register(container, HappyGhastImprovementsConstants.MOD_ID);
    }
}
