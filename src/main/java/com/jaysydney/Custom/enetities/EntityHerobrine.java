package com.jaysydney.Custom.enetities;



import com.jaysydney.Custom.ModSounds;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.jetbrains.annotations.Nullable;

//TODO: WORK ON THIS
public class EntityHerobrine extends HostileEntity {
    //animation states TBD??
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private ServerBossBar bossBar = new ServerBossBar(Text.literal("Herobrine"),
            BossBar.Color.RED, BossBar.Style.NOTCHED_10);;

    @Override
    public net.minecraft.util.Arm getMainArm() {
        // Return the main arm, usually RIGHT for most entities
        return net.minecraft.util.Arm.RIGHT;
    }


    public EntityHerobrine(EntityType<? extends PathAwareEntity> type, World world) {
        super((EntityType<? extends HostileEntity>) type, world);

    }

    @Override //determines entity behavior
    protected void initGoals() {
        this.goalSelector.add(0, new net.minecraft.entity.ai.goal.MeleeAttackGoal(this, 2.0D, false));
        // temp attack goal ^^
        this.goalSelector.add(1, new WanderAroundFarGoal((PathAwareEntity) this, 1.0D));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));

        //target select goals 50/50 if this works
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes() //might replace with MobEntity.createMobAttributes()
                .add(net.minecraft.entity.attribute.EntityAttributes.MAX_HEALTH, 250.0D)
                .add(net.minecraft.entity.attribute.EntityAttributes.MOVEMENT_SPEED, 0.35D)
                .add(net.minecraft.entity.attribute.EntityAttributes.ATTACK_DAMAGE, 10.0D)
                .add(net.minecraft.entity.attribute.EntityAttributes.FOLLOW_RANGE, 40);//necessary?

    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        getEntityWorld().getPlayers().getFirst().sendMessage(Text.of("NOOO!!!"), true);
        return ModSounds.EVIL_SCREAM;
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.HEROBRINE_OUCH;

    }

    //animation methods go here
    private void setUpAnimationStates() {
        if(this.idleAnimationTimeout <= 40) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }
    //restarts animation after 40 ticks (2 seconds)
    //subject to change with imported animations

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setUpAnimationStates();
        }
    }
    //20 times per second (every tick) animaation updates

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }


    //boss bar
    /* BOSS BAR */
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick(ServerWorld world) {
        super.mobTick(world);
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

}
