package com.jaysydney.Custom.blocks;

import com.jaysydney.Custom.ModSounds;
import com.jaysydney.Custom.enetities.NetherReactorCoreEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherReactorCoreBlock extends BlockWithEntity {
    public NetherReactorCoreBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends NetherReactorCoreBlock> getCodec() {
        return createCodec(NetherReactorCoreBlock::new);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        world.playSound(player,pos, ModSounds.EVIL_SCREAM, SoundCategory.BLOCKS, 1.0F, 1.0F);
        this.settings.hardness(900f).resistance(300f).noBlockBreakParticles().noCollision();
        return ActionResult.SUCCESS;
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
