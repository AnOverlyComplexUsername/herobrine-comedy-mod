package com.jaysydney.Custom.blocks;

import com.jaysydney.Custom.ModDimensions;
import com.jaysydney.Custom.ModEntities;
import com.jaysydney.Custom.ModSounds;
import com.jaysydney.Custom.enetities.HerobrineRaidEntity;
import com.jaysydney.HerobrineComedyMod;
import com.mojang.serialization.MapCodec;
import me.emafire003.dev.structureplacerapi.StructurePlacerAPI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElivFumoBlock extends HorizontalFacingBlock {
    public static final MapCodec<ElivFumoBlock> CODEC = createCodec(ElivFumoBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 9, 11);
    public ElivFumoBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        world.playSound(player,pos, ModSounds.EVIL_PLUSH, SoundCategory.BLOCKS, 1.0F, 1.0F);
        MinecraftServer server = world.getServer();

        if (server != null)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            ServerWorld herobrineWorld = server.getWorld(ModDimensions.HEROBRINE_WORLD);

            if(herobrineWorld != null)
            {
                StructurePlacerAPI placer = new StructurePlacerAPI(herobrineWorld,
                        Identifier.of(HerobrineComedyMod.MOD_ID,"herobrine_stadium"),
                        new BlockPos(0,98,0), BlockMirror.NONE, BlockRotation.NONE,
                        true,1.0f, new BlockPos(0,0,0));
                TeleportTarget target =
                        new TeleportTarget(herobrineWorld,
                                new Vec3d(28f,100.5f,24.2f),
                                new Vec3d(0,0,0),
                                serverPlayer.getYaw(),serverPlayer.getPitch(),
                                TeleportTarget.NO_OP);
                TeleportTarget raidTarget =
                        new TeleportTarget(herobrineWorld,
                                new Vec3d(9f,109f,22f),
                                new Vec3d(0,0,0),
                                serverPlayer.getYaw(),serverPlayer.getPitch(),
                                TeleportTarget.NO_OP);
                placer.loadStructure();
                serverPlayer.teleportTo(target);
                HerobrineRaidEntity testEntity = new HerobrineRaidEntity(ModEntities.HEROBRINESARMY, serverPlayer.getWorld());
                testEntity.setPlayerPos(serverPlayer.getPos());
                testEntity.teleportTo(raidTarget);
                testEntity.setAiDisabled(true);
                herobrineWorld.spawnEntity(testEntity);

            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPE;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
