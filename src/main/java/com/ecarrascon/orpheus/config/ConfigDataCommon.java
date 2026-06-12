package com.ecarrascon.orpheus.config;


import net.neoforged.neoforge.common.ModConfigSpec;


public class ConfigDataCommon {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<String> ORPHEUS_LYRE_POWER;
    public static final ModConfigSpec.ConfigValue<Boolean> ONE_TIME_USE;


    static {
        BUILDER.push("Configs for Orpheus Mod");

        ORPHEUS_LYRE_POWER = BUILDER.comment("If you input \"keep\" it will give you the KeepInventory effect. " +
                        "If you input \"protect\" it will grant you immunity to arrows. If you input the word \"both\", " +
                        "you will have both effects.")
                .define("Orpheus Lyre Power", "keep");
        ONE_TIME_USE = BUILDER
                .comment("If true, the Orpheus Lyre will be consumed when its keep-inventory effect is applied. " +
                        "If false, the Lyre will not be consumed and can be used repeatedly.")
                .define("Orpheus Lyre One Time Use", false);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
