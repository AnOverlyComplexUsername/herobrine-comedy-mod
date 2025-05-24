package com.jaysydney.Custom;

import com.jaysydney.Custom.enetities.NetherReactorCoreEntity;
import com.jaysydney.HerobrineComedyMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(HerobrineComedyMod.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<NetherReactorCoreEntity> NETHER_REACTOR_CORE_BLOCK = register(
            "nether_reactor_core_block",
            // For versions before 1.21.2, please use BlockEntityType.Builder.
            FabricBlockEntityTypeBuilder.create(NetherReactorCoreEntity::new, ModBlocks.NETHER_REACTOR_CORE).build()
    );

    public static void initialize() {
    }
}