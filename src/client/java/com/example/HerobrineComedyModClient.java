package com.example;

import com.example.entityProperties.HerobrineRaidRenderer;
import com.jaysydney.Custom.ModEntities;
import com.example.entityProperties.HerobrineModel;
import com.example.entityProperties.HerobrineRenderer;
import com.jaysydney.Custom.enetities.EntityHerobrine;
import com.jaysydney.Custom.enetities.HerobrineRaidEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;


public class HerobrineComedyModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.

		EntityModelLayerRegistry.registerModelLayer(HerobrineModel.HEROBRINE, HerobrineModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.HEROBRINE, HerobrineRenderer::new);


		EntityRendererRegistry.register((EntityType<? extends PigEntity>) ModEntities.HEROBRINESARMY, HerobrineRaidRenderer::new);
		FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) ModEntities.HEROBRINESARMY, HerobrineRaidEntity.createPigAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HEROBRINE, EntityHerobrine.createAttributes());
	}
}