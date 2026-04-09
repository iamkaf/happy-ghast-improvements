package com.iamkaf.happyghastimprovements;

import com.iamkaf.konfig.api.v1.ConfigBuilder;
import com.iamkaf.konfig.api.v1.ConfigHandle;
import com.iamkaf.konfig.api.v1.ConfigScope;
import com.iamkaf.konfig.api.v1.ConfigValue;
import com.iamkaf.konfig.api.v1.Konfig;
import com.iamkaf.konfig.api.v1.SyncMode;

public final class HappyGhastImprovementsConfig {
    public static final ConfigHandle HANDLE;
    public static final ConfigValue<Integer> NORMAL_AMBIENT_SOUND_INTERVAL;
    public static final ConfigValue<Integer> RIDDEN_AMBIENT_SOUND_INTERVAL;
    public static final ConfigValue<Integer> SPEED_EFFECT_DURATION;
    public static final ConfigValue<Double> SPEED_BOOST_BASE;
    public static final ConfigValue<Double> SPEED_BOOST_PER_LEVEL;
    public static final ConfigValue<Integer> SUGAR_SPEED_AMPLIFIER;
    public static final ConfigValue<Integer> HONEY_SPEED_AMPLIFIER;
    public static final ConfigValue<Integer> DRAGON_BREATH_SPEED_AMPLIFIER;
    public static final ConfigValue<Double> REACH_DISTANCE_BONUS;
    public static final ConfigValue<Boolean> PLAY_SPEED_EXPIRE_SOUND;

    static {
        ConfigBuilder builder = Konfig.builder(HappyGhastImprovementsConstants.MOD_ID, "common")
                .scope(ConfigScope.COMMON)
                .syncMode(SyncMode.LOGIN)
                .comment("Gameplay settings for Happy Ghast Improvements.");

        builder.push("sounds");
        builder.categoryComment("Ambient and feedback sounds.");
        NORMAL_AMBIENT_SOUND_INTERVAL = builder.intRange("normal_ambient_sound_interval", 720, 20, 72000)
                .comment("Ambient sound interval in ticks for unridden happy ghasts.")
                .sync(true)
                .build();
        RIDDEN_AMBIENT_SOUND_INTERVAL = builder.intRange("ridden_ambient_sound_interval", 1200, 20, 72000)
                .comment("Ambient sound interval in ticks for ridden happy ghasts.")
                .sync(true)
                .build();
        PLAY_SPEED_EXPIRE_SOUND = builder.bool("play_speed_expire_sound", true)
                .comment("Play a sound when a happy ghast loses its speed boost.")
                .sync(true)
                .build();
        builder.pop();

        builder.push("feeding");
        builder.categoryComment("Food buffs for happy ghasts.");
        SPEED_EFFECT_DURATION = builder.intRange("speed_effect_duration", 400, 20, 72000)
                .comment("Speed effect duration in ticks after feeding.")
                .sync(true)
                .build();
        SUGAR_SPEED_AMPLIFIER = builder.intRange("sugar_speed_amplifier", 1, 0, 10)
                .comment("Speed amplifier applied when feeding sugar.")
                .sync(true)
                .build();
        HONEY_SPEED_AMPLIFIER = builder.intRange("honey_speed_amplifier", 2, 0, 10)
                .comment("Speed amplifier applied when feeding honey bottles.")
                .sync(true)
                .build();
        DRAGON_BREATH_SPEED_AMPLIFIER = builder.intRange("dragon_breath_speed_amplifier", 3, 0, 10)
                .comment("Speed amplifier applied when feeding dragon's breath.")
                .sync(true)
                .build();
        SPEED_BOOST_BASE = builder.doubleRange("speed_boost_base", 1.5D, 0.0D, 10.0D)
                .comment("Base travel speed multiplier while the speed effect is active.")
                .sync(true)
                .build();
        SPEED_BOOST_PER_LEVEL = builder.doubleRange("speed_boost_per_level", 0.5D, 0.0D, 10.0D)
                .comment("Additional travel speed multiplier added per amplifier level.")
                .sync(true)
                .build();
        builder.pop();

        builder.push("riding");
        builder.categoryComment("Interaction improvements while riding a happy ghast.");
        REACH_DISTANCE_BONUS = builder.doubleRange("reach_distance_bonus", 1.0D, 0.0D, 16.0D)
                .comment("Extra block and entity interaction reach while riding a happy ghast.")
                .sync(true)
                .build();
        builder.pop();

        HANDLE = builder.build();
    }

    private HappyGhastImprovementsConfig() {
    }

    public static void init() {
    }
}
