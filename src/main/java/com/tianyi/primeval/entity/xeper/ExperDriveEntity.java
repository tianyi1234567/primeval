package com.tianyi.primeval.entity.xeper;

import com.tianyi.primeval.entity.SuperdriveEntity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PlayMessages;

import java.util.List;

public class ExperDriveEntity extends Projectile {
    private static final EntityDataAccessor<Integer> COLOR= SynchedEntityData.defineId(ExperDriveEntity.class, EntityDataSerializers.INT);;
    private static final EntityDataAccessor<Float> LIFETIME= SynchedEntityData.defineId(ExperDriveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> ROT_X= SynchedEntityData.defineId(ExperDriveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ROT_Y= SynchedEntityData.defineId(ExperDriveEntity.class, EntityDataSerializers.INT);;
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(ExperDriveEntity.class, EntityDataSerializers.FLOAT);

    public ExperDriveEntity(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> type, Level level) {
        super(type, level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, 3355647);
        this.entityData.define(ROT_X, random.nextInt(-360,360));
        this.entityData.define(ROT_Y, random.nextInt(-360,360));
        this.entityData.define(LIFETIME, 10.0F);
        this.entityData.define(DAMAGE, 0.0F);
    }

    public static ExperDriveEntity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new ExperDriveEntity(PLEntiteRegristrys.ExperDrive, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > this.getLifetime()){
            this.discard();
        }
    }

    @Override
    public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
        // 完全阻止伤害处理，包括音效播放
        return false;
    }

    @Override
    public boolean isPushable() {
        // 禁止被推动
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        // 禁止与其他实体碰撞
        return false;
    }

    @Override
    public boolean canCollideWith(net.minecraft.world.entity.Entity entity) {
        // 禁止与指定实体碰撞
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        // 无法被弹射物击中
        return false;
    }

    @Override
    public boolean isPickable() {
        // 实体不可被选中/点击
        return false;
    }

    public int getRotX() {
        return (Integer)this.getEntityData().get(ROT_X);
    }

    public void setRotX(int value) {
        this.getEntityData().set(ROT_X, value);
    }

    public int getRotY() {
        return (Integer)this.getEntityData().get(ROT_Y);
    }

    public void setRotY(int value) {
        this.getEntityData().set(ROT_Y, value);
    }

    public int getColor() {
        return (Integer)this.getEntityData().get(COLOR);
    }

    public void setColor(int value) {
        this.getEntityData().set(COLOR, value);
    }

    public float getLifetime() {
        return (Float)this.getEntityData().get(LIFETIME);
    }

    public void setLifetime(float value) {
        this.getEntityData().set(LIFETIME, value);
    }

    public float getDamage() {
        return (Float)this.getEntityData().get(DAMAGE);
    }

    public void setDamage(float value) {
        this.getEntityData().set(DAMAGE, value);
    }
}