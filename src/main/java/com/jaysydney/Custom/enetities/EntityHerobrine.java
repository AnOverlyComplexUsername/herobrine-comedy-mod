package com.jaysydney.Custom.enetities;


import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;


//TODO: WORK ON THIS
public class EntityHerobrine extends PathAwareEntity {
    //animation states TBD??
    //public final AnimationState idleAnimationState = new AnimationState();
    //private int idleAnimationTimeout = 0;


    public EntityHerobrine(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
    }

    @Override //determines entity behavior
    protected void initGoals() {
        this.goalSelector.add(0, new net.minecraft.entity.ai.goal.MeleeAttackGoal(this, 1.0D, true));
        // temp attack goal ^^
        this.goalSelector.add(1, new WanderAroundFarGoal((PathAwareEntity) this, 1.0D));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));

        //target select goals 50/50 if this works
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes() //might replace with MobEntity.createMobAttributes()
                .add(net.minecraft.entity.attribute.EntityAttributes.MAX_HEALTH, 100.0D)
                .add(net.minecraft.entity.attribute.EntityAttributes.MOVEMENT_SPEED, 0.35D)
                .add(net.minecraft.entity.attribute.EntityAttributes.ATTACK_DAMAGE, 10.0D)
                .add(net.minecraft.entity.attribute.EntityAttributes.FOLLOW_RANGE, 20);//necessary?
                //tutorial had these all listed as generic but they didnt compile
                //all temp values
    }

    //animation methods go here



    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

}
