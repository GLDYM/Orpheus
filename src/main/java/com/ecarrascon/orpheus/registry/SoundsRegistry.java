package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class SoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, Orpheus.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VIPER_AMBIENT = registerSoundEvents("entity.viper.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VIPER_DEATH = registerSoundEvents("entity.viper.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_VIPER_HURT = registerSoundEvents("entity.viper.hurt");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, name)));
    }
}
