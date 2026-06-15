package com.iamkaf.happyghastimprovements;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HappyGhastImprovementsConstants {
    /**
     * Mod identifier and configuration fields.
     * Update these fields when reusing this code for other projects.
     */
    public static final String MOD_ID = "happyghastimprovements";
    public static final String MOD_NAME = "Happy Ghast Improvements";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // Particle configuration
    public static final SimpleParticleType FEED_PARTICLE_TYPE = ParticleTypes.CLOUD;
    public static final int FEED_PARTICLE_COUNT = 80;
    public static final double PARTICLE_OFFSET_X = 0.5d;
    public static final double PARTICLE_OFFSET_Y = 1.0d;
    public static final double PARTICLE_OFFSET_Z = 0.5d;
    public static final double PARTICLE_SPEED = 0.05d;
}
