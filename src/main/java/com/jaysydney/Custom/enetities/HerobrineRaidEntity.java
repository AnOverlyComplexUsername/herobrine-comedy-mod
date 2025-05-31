package com.jaysydney.Custom.enetities;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import com.jaysydney.Custom.Utils.WeightedList;

import java.util.ArrayList;

// container class for the raid :3
public class HerobrineRaidEntity extends Entity {

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
        super(type, world);
        raidMaxHealth = 1000;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

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
