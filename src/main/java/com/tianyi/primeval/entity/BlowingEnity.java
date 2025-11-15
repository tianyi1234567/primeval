package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.joml.Vector3f;

public class BlowingEnity extends EntityDrive {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(BlowingEnity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(BlowingEnity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(BlowingEnity.class, EntityDataSerializers.VECTOR3);
    public BlowingEnity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public static BlowingEnity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new BlowingEnity(PLEntiteRegristrys.bl, worldIn);
    }
    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();

        this.entityData.define(IT_FIRED, false);
        this.entityData.define(SPEED, 3.0f);
        this.entityData.define(OFFSET, Vec3.ZERO.toVector3f());
    }

    public void doFire()
    {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired()
    {
        return this.getEntityData().get(IT_FIRED);
    }

    public void setSpeed(float speed)
    {
        this.getEntityData().set(SPEED, speed);
    }

    public float getSpeed() {return this.getEntityData().get(SPEED);}

    public void setOffset(Vec3 offset)
    {
        this.getEntityData().set(OFFSET, offset.toVector3f());
    }

    public Vec3 getOffset() {return new Vec3(this.getEntityData().get(OFFSET));}

    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(this.level());
                if (lightningbolt != null) {
                    lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningbolt.setCause(this.getOwner() instanceof ServerPlayer ? (ServerPlayer)this.getOwner() : null);
                    this.level().addFreshEntity(lightningbolt);
                    this.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
                }
            }
        }

        super.onHitEntity(result);
    }
}

