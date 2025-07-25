package com.jaysydney;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jaysydney.Custom.*;


import net.fabricmc.api.ModInitializer;

public class HerobrineComedyMod implements ModInitializer {
	public static final String MOD_ID = "herobrinecomedymod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItemGroups.initialize();
		ModSounds.initialize();
		ModItems.initialize();
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModEntities.initialize();
		ModDimensions.initialize();
		
		LOGGER.info("Hello Fabric world!");
	}
}