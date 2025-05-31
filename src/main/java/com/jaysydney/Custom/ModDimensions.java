package com.jaysydney.Custom;

import com.jaysydney.HerobrineComedyMod;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;



public class ModDimensions {
    public static final RegistryKey<DimensionOptions> HEROBRINEDIM = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedimension"));
    public static final RegistryKey<World> HEROBRINE_WORLD = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedimension"));
    public static final RegistryKey<DimensionType> HEROBRINEDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedim"));


    public static void initialize() {

            HerobrineComedyMod.LOGGER.info("Initializing worlds");

    }
}