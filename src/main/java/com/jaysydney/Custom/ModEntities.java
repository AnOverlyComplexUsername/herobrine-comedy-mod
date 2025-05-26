package com.jaysydney.Custom;

import com.jaysydney.Custom.enetities.EntityHerobrine;
import com.jaysydney.HerobrineComedyMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModEntities {

    public static final EntityType<EntityHerobrine> HEROBRINE = (EntityType<EntityHerobrine>) register("herobrine",
        EntityType.Builder.create(EntityHerobrine::new, SpawnGroup.MONSTER).dimensions(1f,2f)
                .makeFireImmune());


    public static EntityType<?> register(String name, EntityType.Builder<?> settings) {
        // Create the item key.
        RegistryKey<EntityType<?>> entityKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                Identifier.of(HerobrineComedyMod.MOD_ID, name));

        // Create the item instance.
        EntityType<?> entity = settings.build(entityKey);

        // Register the item.
        Registry.register(Registries.ENTITY_TYPE, entityKey, entity);

        return entity;
    }
    public static void initialize() {


        HerobrineComedyMod.LOGGER.info("Registering Custom Entities for: " + HerobrineComedyMod.MOD_ID);

    }
}
