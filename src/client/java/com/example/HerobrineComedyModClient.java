package com.example;

import com.example.entityProperties.HerobrineRaidRenderer;
import com.jaysydney.Custom.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class HerobrineComedyModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.
//		EntityRendererRegistry.register(ModEntities.HEROBRINESARMY,
//				(context) ->
//				{return new HerobrineRaidRenderer(context);});
	}
}