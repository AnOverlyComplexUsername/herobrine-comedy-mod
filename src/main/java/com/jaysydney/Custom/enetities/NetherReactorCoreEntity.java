package com.jaysydney.Custom.enetities;

import com.jaysydney.Custom.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NetherReactorCoreEntity extends BlockEntity {
    public NetherReactorCoreEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NETHER_REACTOR_CORE_BLOCK, pos, state);
    }

}
