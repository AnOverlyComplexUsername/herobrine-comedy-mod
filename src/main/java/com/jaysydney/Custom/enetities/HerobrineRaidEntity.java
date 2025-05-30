package com.jaysydney.Custom.enetities;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import com.jaysydney.Custom.Utils.WeightedList;

import java.util.ArrayList;

// container class for the raid :3
public class HerobrineRaidEntity extends PigEntity {

    private ArrayList<HostileEntity> raidMobs; //list of all mobs in raid
    private int wave = 1; // higher waves == more difficulty, scales equipment and # of mob spawns
    private boolean raidStart = false; //used for a fill up animation before hand
    private float raidMaxHealth; // max hp of all mobs in raid
    private float curRaidHealth = 0; // current hp of all mobs in raid
    private final int raidBoundsX1 = 16; //how long the spawn area will be
    private final int raidBoundsX2 = 30; // how wide the spawn area will be
    public static WeightedList<EntityType<?>> POSSIBLEMOBS = new WeightedList<>(); //weighted List of possible mob spawns; static and created on intialization

    private ServerBossBar bossBar = new ServerBossBar(
            Text.literal("Herobrine's Army: Wave " + wave),
            BossBar.Color.RED, BossBar.Style.NOTCHED_12);

    public HerobrineRaidEntity(EntityType<?> type, World world) {
        super((EntityType<? extends PigEntity>) type, world);
        raidMaxHealth = 1000;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.wave = nbt.getInt("Wave", 0);
        this.raidStart = nbt.getBoolean("RaidStart", false);
        this.raidMaxHealth = nbt.getFloat("RaidMaxHealth", 0);
        this.curRaidHealth = nbt.getFloat("CurRaidHealth", 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putFloat("RaidMaxHealth", this.raidMaxHealth);
        nbt.putFloat("CurRaidHealth", this.curRaidHealth);
        nbt.putBoolean("RaidStart", this.raidStart);
        nbt.putInt("Wave", this.wave);
        nbt.putInt("RaidBoundsX1", this.raidBoundsX1);
        nbt.putInt("RaidBoundsX2", this.raidBoundsX2);
    }

    // raid logic
    public void startRaid() { //starts raid and creates mobs
        raidStart = true;
        raidMaxHealth = 0;
        curRaidHealth = 0;
        int waveSizeMax = (int) (wave * 4.5);
        int waveSizeMin = (int) (wave * 3.5);
        int waveSize = random.nextBetween(waveSizeMin, waveSizeMax);
        for (int i = 0; i < waveSize; i++) {
            HostileEntity raidMob = (HostileEntity) POSSIBLEMOBS.getRandom().create(this.getWorld(), SpawnReason.EVENT);
            raidMobs.add(raidMob);
            assert raidMob != null;
            raidMob.setPosition(this.getX(), this.getY(), this.getZ());
            getWorld().spawnEntity(raidMob);
            raidMaxHealth += raidMob.getHealth();
        }
        curRaidHealth = raidMaxHealth;

    }

    //Mob list initlization

    public static void initMobTable() // called when game launches; assume it's properly executed :clueless:
    {
        POSSIBLEMOBS.add(EntityType.EVOKER, 20);
    }

    //boss bar manipulation

    @Override
    public void tick() {
        if (raidStart) {
            super.tick();
            this.raidMobs.forEach(entity -> {
                curRaidHealth += entity.getHealth();
            });
            this.bossBar.setPercent(curRaidHealth / raidMaxHealth);

        }
        else
        {
            super.tick();
            if (this.bossBar.getPercent() >= 100)
            {
                startRaid();
            }
            curRaidHealth += 10;
            this.bossBar.setPercent(curRaidHealth / raidMaxHealth);
        }
    }

    @Override
    public Arm getMainArm() {
        return null;
    }

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


}
