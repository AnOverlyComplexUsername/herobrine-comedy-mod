package com.jaysydney.Custom;

import com.jaysydney.HerobrineComedyMod;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypeRegistrar;
import net.minecraft.world.dimension.DimensionTypes;

import java.awt.*;
import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> HEROBRINEDIM = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedimension"));
    public static final RegistryKey<World> HEROBRINE_WORLD = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedimension"));
    public static final RegistryKey<DimensionType> HEROBRINEDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(HerobrineComedyMod.MOD_ID, "herobrinedim"));

//    public static final DimensionType TYPE = new DimensionType(
//                        OptionalLong.of(12000), // fixedTime
//                        false, // hasSkylight
//                        false, // hasCeiling
//                        false, // ultraWarm
//                        true, // natural
//                        1.0, // coordinateScale
//                        true, // bedWorks
//                        false, // respawnAnchorWorks
//                        0, // minY
//                        256, // height
//                        256, // logicalHeight
//                        BlockTags.INFINIBURN_OVERWORLD, // infiniburn
//                        DimensionTypes.OVERWORLD_ID, // effectsLocation
//                        0.8f, // ambientLight
//                        new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0));
    public static void initialize() {
       // Registry.register(Registries.bootstrap().DIMENSION_TYPE, HEROBRINEDIM_TYPE, TYPE);
            HerobrineComedyMod.LOGGER.info("Initializing worlds");

    }
}