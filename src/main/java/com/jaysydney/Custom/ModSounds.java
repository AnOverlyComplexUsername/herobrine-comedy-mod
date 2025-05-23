package com.jaysydney.Custom;

import com.jaysydney.HerobrineComedyMod;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent EVIL_SCREAM = registerSound("evil_scream");

     public static final BlockSoundGroup SOUND_BLOCk_SOUNDS = new BlockSoundGroup(1f,1f ,ModSounds.EVIL_SCREAM,ModSounds.EVIL_SCREAM,ModSounds.EVIL_SCREAM,ModSounds.EVIL_SCREAM,ModSounds.EVIL_SCREAM);

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(HerobrineComedyMod.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    // This static method starts class initialization, which then initializes
    // the static class variables (e.g. ITEM_METAL_WHISTLE).
    public static void initialize() {
        HerobrineComedyMod.LOGGER.info("Registering " + HerobrineComedyMod.MOD_ID + " Sounds");
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}
