package com.jaysydney.Custom.enetities;


import com.jaysydney.Custom.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import com.jaysydney.Custom.Utils.WeightedList;

import java.util.ArrayList;

// container class for the raid :3
public class HerobrineRaidEntity extends PigEntity {

    private ArrayList<HostileEntity> raidMobs = new ArrayList<>(); //list of all mobs in raid
    private int wave = 1; // higher waves == more difficulty, scales equipment and # of mob spawns
    private boolean raidStart = false; //used for a fill up animation before hand
    private float raidMaxHealth; // max hp of all mobs in raid
    private float curRaidHealth = 0; // current hp of all mobs in raid
    private final int raidBoundsX1 = 16; //how long the spawn area will be
    private final int raidBoundsX2 = 30; // how wide the spawn area will be
    public static WeightedList<EntityType<?>> POSSIBLEMOBS = new WeightedList<>(); //weighted List of possible mob spawns; static and created on intialization
    private Vec3d playerPos = new Vec3d(0.0D, 0.0D, 0.0D);
    private ServerBossBar bossBar = new ServerBossBar(
            Text.literal("Herobrine's Army: Wave " + wave),
            BossBar.Color.RED, BossBar.Style.NOTCHED_12);

    public HerobrineRaidEntity(EntityType<?> type, World world) {
        super((EntityType<? extends PigEntity>) type, world);
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3000000,256));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 3000000,256));
        this.cannotDespawn();
        this.canFreeze();
        raidMaxHealth = 2500;
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
        raidMaxHealth = 0;
        curRaidHealth = 0;
        int waveSizeMax = (int) (wave * 4.5);
        int waveSizeMin = (int) (wave * 3.5);
        int waveSize = random.nextBetween(waveSizeMin, waveSizeMax);
        for (int i = 0; i < waveSize; i++) {
            HostileEntity raidMob = (HostileEntity) POSSIBLEMOBS.getRandom().create(this.getWorld(), SpawnReason.EVENT);
            raidMobs.add(raidMob);
            assert raidMob != null;
            if (raidMob.getType() == EntityType.WITHER_SKELETON || raidMob.getType() == EntityType.ZOMBIE) raidMob.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
            if (raidMob.getType() == EntityType.DROWNED) raidMob.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
            if (raidMob.getType() == EntityType.DROWNED || raidMob.getType() == EntityType.SKELETON || raidMob.getType() == EntityType.ZOMBIE) {
                raidMob.equipStack(EquipmentSlot.CHEST,new ItemStack(Items.GOLDEN_CHESTPLATE));
                raidMob.equipStack(EquipmentSlot.HEAD,new ItemStack(Items.GOLDEN_HELMET));
                raidMob.equipStack(EquipmentSlot.FEET,new ItemStack(Items.DIAMOND_BOOTS));
                raidMob.equipStack(EquipmentSlot.LEGS,new ItemStack(Items.DIAMOND_LEGGINGS));

                if (raidMob.getType() == EntityType.SKELETON) raidMob.equipStack(EquipmentSlot.MAINHAND,new ItemStack(Items.BOW));
            }
            float ranX = random.nextBetween(-15,15); //  arena X size
            float ranZ = random.nextBetween(-8,8); //  arena Z size
            raidMob.setPosition(getPlayerPos().x + ranX, getPlayerPos().y,  getPlayerPos().z + ranZ);
            getWorld().spawnEntity(raidMob);
            raidMaxHealth += raidMob.getHealth();
            curRaidHealth += raidMob.getHealth();
        }
        if (wave == 3) getEntityWorld().getPlayers().forEach(player -> {
            player.sendMessage(Text.of("You have done well to get this far..."), true);
        });
        raidStart = true;

    }

    //Mob list initlization

    public static void initMobTable() // called when game launches; assume it's properly executed :clueless:
    {
        POSSIBLEMOBS.add(EntityType.EVOKER, 5);
        POSSIBLEMOBS.add(EntityType.DROWNED, 23);
        POSSIBLEMOBS.add(EntityType.WITHER_SKELETON, 25);
        POSSIBLEMOBS.add(EntityType.ZOMBIE, 30);
        POSSIBLEMOBS.add(EntityType.SKELETON, 25);

    }

    //boss bar manipulation

    @Override
    public void tick() {
        if(wave >= 4)
        {
            EntityHerobrine herobrine = new EntityHerobrine(ModEntities.HEROBRINE, this.getWorld());
            herobrine.setPosition(getPlayerPos());
            getWorld().spawnEntity(herobrine);
            this.discard();
        }

        if (raidStart) {

            curRaidHealth = 0;
            super.tick();
            this.raidMobs.forEach(entity -> {
                curRaidHealth += entity.getHealth();
            });
            this.bossBar.setPercent(curRaidHealth / raidMaxHealth);
            if(curRaidHealth / raidMaxHealth <= 0)
            {
                raidStart = false;
                wave ++;
                raidMaxHealth = 800;
                bossBar.setName(Text.literal("Herobrine's Army: Wave " + wave));
            }

        }
        else
        {
            if (wave <= 1)
            {
                if (curRaidHealth / raidMaxHealth <= 0.25) {
                    getEntityWorld().getPlayers().forEach(player -> {
                        player.sendMessage(Text.of("So you have trespassed on my domain"), true);
                    });
                }
                else if (curRaidHealth / raidMaxHealth <= 0.45) {
                    getEntityWorld().getPlayers().forEach(player -> {
                        player.sendMessage(Text.of("Prepare yourself against my army..."), true);
                    });
                }
                else if (curRaidHealth / raidMaxHealth <= 0.65) {
                    getEntityWorld().getPlayers().forEach(player -> {
                        player.sendMessage(Text.of("If you best them, you may have the honor of dying by my hands"), true);
                    });
                }
                else if (curRaidHealth / raidMaxHealth <= 0.8) {
                    getEntityWorld().getPlayers().forEach(player -> {
                        player.sendMessage(Text.of("..."), true);
                    });
                }
            }
            super.tick();
            if (curRaidHealth / raidMaxHealth >= 1.0)
            {
                startRaid();
            }
            curRaidHealth += 10;
            this.bossBar.setPercent((float) Math.clamp(curRaidHealth / raidMaxHealth,0,1.0));
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

    public Vec3d getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(Vec3d playerPos) {
        this.playerPos = playerPos;
    }
}
