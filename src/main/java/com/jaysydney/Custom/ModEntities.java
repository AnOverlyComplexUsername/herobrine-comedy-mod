package com.jaysydney.Custom;

import com.jaysydney.Custom.enetities.EntityHerobrine;
import com.jaysydney.Custom.enetities.HerobrineRaidEntity;
import com.jaysydney.HerobrineComedyMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModEntities {

    public static final EntityType<EntityHerobrine> HEROBRINE = (EntityType<EntityHerobrine>) register("herobrine",
        EntityType.Builder.create(EntityHerobrine::new, SpawnGroup.MONSTER).dimensions(0.6f,2f)
                .makeFireImmune());

    public static final EntityType<?> HEROBRINESARMY =
            register("herobrines_army",
                    EntityType.Builder.create(HerobrineRaidEntity::new, SpawnGroup.MISC)
                            .makeFireImmune().dropsNothing().dimensions(0.1f, 0.1f));

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

        HerobrineRaidEntity.initMobTable();
        HerobrineComedyMod.LOGGER.info("Registering Custom Entities for: " + HerobrineComedyMod.MOD_ID);

    }
}
