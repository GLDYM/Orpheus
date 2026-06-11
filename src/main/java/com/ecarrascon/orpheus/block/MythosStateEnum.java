package com.ecarrascon.orpheus.block;

import net.minecraft.util.StringRepresentable;

public enum MythosStateEnum implements StringRepresentable {
    OVERWORLD("overworld"),
    OVERWORLD_ACTIVE("overworld_active"),
    NETHER("nether"),
    NETHER_ACTIVE("nether_active");

    private final String name;

    MythosStateEnum(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
