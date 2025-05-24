package com.jaysydney.Custom.blocks;

import com.jaysydney.Custom.enetities.NetherReactorCoreEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class NetherReactorCoreBlock extends BlockWithEntity {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    public NetherReactorCoreBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ACTIVATED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }


    @Override
    protected MapCodec<? extends NetherReactorCoreBlock> getCodec() {
        return createCodec(NetherReactorCoreBlock::new);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        boolean activated = state.get(ACTIVATED);
        if(!activated && checkAroundBlock(world,pos) && checkStructurePresent(world,pos) )
        {
            // Flip the value of activated and save the new blockstate.
            world.setBlockState(pos, state.with(ACTIVATED, Boolean.TRUE));
            //TODO: ADD EVENT WHEN TRIGGERED SUCCESSFULLY
            // EVENT SHOULD TURN ALL BLOCKS INTO OBSIDIAN AND SPAWN HEROBRINE (ALSO MAYBE ADD LIGHTNING)
            world.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private boolean checkAroundBlock(World world, BlockPos pos) {
        // checks north, south, east, north for air also for top corners
        return world.isAir(pos.east()) && world.isAir(pos.west())
            && world.isAir(pos.north()) && world.isAir(pos.south())
            && world.isAir(newPos(1,1,1, pos)) && world.isAir(newPos(1,1,-1, pos ))
        && world.isAir(newPos(-1,1,1, pos)) && world.isAir(newPos(-1,1,-1, pos));
    }

    private boolean checkStructurePresent(World world, BlockPos pos) {
        BlockPos[] goldPos = {newPos(1,-1,1, pos),
                newPos(1,-1,-1, pos ),
                newPos(-1,-1,1, pos),
                newPos(-1,-1,-1, pos),};
        BlockPos[] cobblePos = { //checks if gold and cobble are placed properly
            pos.up(), pos.down(),
            newPos(0,-1,1,pos),
            newPos(0,-1,-1,pos),
            newPos(-1,-1,0,pos),
            newPos(1,-1,0,pos),
            newPos(0,1,1,pos),
            newPos(0,1,-1,pos),
            newPos(-1,1,0,pos),
            newPos(1,1,0,pos),
            newPos(1,0,1, pos),
            newPos(1,0,-1, pos ),
            newPos(-1,0,1, pos),
            newPos(-1,0,-1, pos)
        };
        for (BlockPos i : goldPos)
        {
            if (!world.getBlockState(i).getBlock().equals(Blocks.GOLD_BLOCK)) return false;
        }
        for (BlockPos i : cobblePos)
        {
            if (!world.getBlockState(i).getBlock().equals(Blocks.COBBLESTONE)) return false;
        }
        for (BlockPos i : goldPos)
        {
            world.setBlockState(i, Blocks.OBSIDIAN.getDefaultState());
        }
        for (BlockPos i : cobblePos)
        {
            world.setBlockState(i, Blocks.OBSIDIAN.getDefaultState());
        }
        return true;
    }
    private BlockPos newPos(int x, int y, int z, BlockPos pos) {
        return new BlockPos(pos.getX() + x,pos.getY() + y,pos.getZ() +  z);
    }

    public static int getLuminance(BlockState currentBlockState) {
        // Get the value of the "activated" property.
        boolean activated = currentBlockState.get(NetherReactorCoreBlock.ACTIVATED);

        // Return a light level if activated = true
        return activated ? 40 : 5;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetherReactorCoreEntity(pos, state);
    }
}
