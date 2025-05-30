package com.jaysydney.Custom;

import com.jaysydney.HerobrineComedyMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {

//    public static final ComponentType<?> MY_COMPONENT_TYPE = Registry.register(
//            Registries.DATA_COMPONENT_TYPE,
//            Identifier.of(HerobrineComedyMod.MOD_ID, "my_component"),
//            ComponentType.<?>builder().codec(null).build()
//    );


    protected static void initialize() {
        HerobrineComedyMod.LOGGER.info("Registering {} components", HerobrineComedyMod.MOD_ID);

    }

}
