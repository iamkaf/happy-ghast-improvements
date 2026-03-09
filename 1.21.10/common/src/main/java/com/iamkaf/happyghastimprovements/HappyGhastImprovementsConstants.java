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

    // Sound intervals (in ticks)
    public static final int NORMAL_AMBIENT_SOUND_INTERVAL = 720; // 36 seconds
    public static final int RIDDEN_AMBIENT_SOUND_INTERVAL = 1200; // 60 seconds

    // Speed effect configuration
    public static final int SPEED_EFFECT_DURATION = 400; // 20 seconds in ticks
    public static final float SPEED_BOOST_BASE = 1.5F; // 50% base speed boost
    public static final float SPEED_BOOST_PER_LEVEL = 0.5F; // Additional 50% per amplifier level

    // Feeding configuration
    public static final int SUGAR_SPEED_AMPLIFIER = 1; // Speed II
    public static final int HONEY_SPEED_AMPLIFIER = 2; // Speed III
    public static final int DRAGON_BREATH_SPEED_AMPLIFIER = 3; // Speed IV

    // Reach distance increase
    public static final double REACH_DISTANCE_BONUS = 1.0; // +1 block reach when riding

    // Particle configuration
    public static final SimpleParticleType FEED_PARTICLE_TYPE = ParticleTypes.CLOUD;
    public static final int FEED_PARTICLE_COUNT = 80;
    public static final double PARTICLE_OFFSET_X = 0.5d;
    public static final double PARTICLE_OFFSET_Y = 1.0d;
    public static final double PARTICLE_OFFSET_Z = 0.5d;
    public static final double PARTICLE_SPEED = 0.05d;
}
