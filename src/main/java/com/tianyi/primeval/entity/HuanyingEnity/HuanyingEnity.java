package com.tianyi.primeval.entity.HuanyingEnity;

import com.tianyi.primeval.entity.BlowingEnity;
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

public class HuanyingEnity extends EntityDrive {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(HuanyingEnity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(HuanyingEnity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(HuanyingEnity.class, EntityDataSerializers.VECTOR3);
    private static final EntityDataAccessor<Float> ROTATION_SPEED = SynchedEntityData.defineId(HuanyingEnity.class, EntityDataSerializers.FLOAT);

    // 默认旋转速度配置
    public static final float DEFAULT_ROTATION_SPEED = 50.0f;
    private float lockedYRot = 0.0f;  // 发射后锁定的Y轴旋转角
    private boolean isRotationLocked = false;  // 是否已锁定旋转
    public HuanyingEnity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public static HuanyingEnity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new HuanyingEnity(PLEntiteRegristrys.Huanying, worldIn);
    }
    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();

        this.entityData.define(IT_FIRED, false);
        this.entityData.define(SPEED, 3.0f);
        this.entityData.define(OFFSET, Vec3.ZERO.toVector3f());
        this.entityData.define(ROTATION_SPEED, DEFAULT_ROTATION_SPEED);
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

    public void setRotationSpeed(float rotationSpeed)
    {
        this.getEntityData().set(ROTATION_SPEED, rotationSpeed);
    }

    public float getRotationSpeed() {return this.getEntityData().get(ROTATION_SPEED);}

    /**
     * 锁定实体的旋转角度（发射后调用）
     */
    public void lockRotation() {
        this.lockedYRot = this.getYRot();
        this.isRotationLocked = true;
        // 确保X轴旋转为0（水平状态）
        this.setXRot(0.0f);
    }

    public boolean isRotationLocked() {
        return this.isRotationLocked;
    }

    @Override
    public void tick()
    {
        super.tick();

        // 在客户端进行旋转，以获得平滑的视觉效果
        if (this.level().isClientSide) {
            if (this.isRotationLocked) {
                // 发射后：绕世界Y轴（垂直轴）进行水平旋转
                float rotationAmount = this.getRotationSpeed();
                this.lockedYRot += rotationAmount;
                // 只修改YRot（水平旋转），保持XRot为0（俯仰角）
                this.setYRot(this.lockedYRot % 360.0f);
                this.setXRot(0.0f);  // 保持水平状态
            } else {
                // 发射前：允许自由旋转
                float rotationAmount = this.getRotationSpeed();
                this.setYRot(this.getYRot() + rotationAmount);
            }
        }
    }

    @Override
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
