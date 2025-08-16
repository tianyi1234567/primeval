package com.tianyi.primeval.entity;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.UUID;

public class SwordRainEntity extends EntityAbstractSummonedSword {
    private static final EntityDataAccessor<Boolean> IT_FIRED;
    long fireTime = -1L;
    int ON_GROUND_LIFE_TIME = 20;
    int ticksInGround = 0;
    private LivingEntity target;
    private static final EntityDataAccessor<String> TARGET_UUID = SynchedEntityData.defineId(SwordRainEntity.class, EntityDataSerializers.STRING);
    private boolean hasHit = false;
    public void setTarget(LivingEntity target) {
        this.target = target;
        this.getEntityData().set(TARGET_UUID, target.getUUID().toString());
    }


    public SwordRainEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte)5);
        this.setXRot(-90);//方向直接朝下
        CompoundTag compoundtag = this.getPersistentData();
        ListTag listtag = compoundtag.getList("CustomPotionEffects", 9);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10);
        listtag.add(mobeffectinstance.save(new CompoundTag()));
        this.getPersistentData().put("CustomPotionEffects", listtag);
    }
    private Vec3 targetPos;
    public void setTargetPos(Vec3 pos) {
        this.targetPos = pos;
        // 立即更新运动状态
        if(!this.level().isClientSide){
            this.setDeltaMovement(Vec3.ZERO);
        }
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(!this.level().isClientSide){
            // 服务端同步初始位置
            Vec3 pos = this.position();
            this.setPos(pos.x, pos.y + 5.0, pos.z);
        }
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TARGET_UUID, "");
        // 其他define保持不变...
    }


    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired() {
        return (Boolean)this.getEntityData().get(IT_FIRED);
    }

    public static SwordRainEntity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new SwordRainEntity(SlashBlade.RegistryEvents.HeavyRainSwords, worldIn);
    }
    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            // 获取最新目标位置
            LivingEntity currentTarget = getCurrentTarget();
            if (currentTarget != null && currentTarget.isAlive()) {
                // 动态更新目标位置
                this.targetPos = currentTarget.position()
                        .add(0, currentTarget.getBbHeight() / 2, 0); // 瞄准身体中部

                // 计算追踪方向
                Vec3 toTarget = targetPos.subtract(this.position()).normalize();
                double speed = 0.8;

                // 增加垂直向下的加速度
                Vec3 motion = toTarget.scale(speed).add(0, -0.2, 0);
                this.setDeltaMovement(motion);

                // 仅调整Y轴旋转（保持剑尖朝下）
                float targetYRot = (float) Math.toDegrees(Math.atan2(toTarget.z, toTarget.x)) - 90.0F;
                this.setYRot(targetYRot);
            } else {
                // 无目标时保持初始朝下(完美解决生成时的重置朝向)最好搭配下面的setRot方法一起
                this.setYRot(180.0F);
                this.setXRot(-90.0F);

                // 保持垂直下落速度
                if(this.getDeltaMovement().y > -2.0) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
                }
            }
            // 在SwordRainEntity的tick方法中添加：
            if (!this.level().isClientSide) {
                // 每5tick微调方向
                if (this.tickCount % 5 == 0 && getCurrentTarget() != null) {
                    Vec3 predictedPos = target.position()
                            .add(target.getDeltaMovement().scale(2)); // 预测目标移动
                    Vec3 adjustDir = predictedPos.subtract(this.position()).normalize();
                    this.setDeltaMovement(this.getDeltaMovement().add(adjustDir.scale(0.1)));
                }
            }
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        // 禁用父类的射击逻辑
        if(targetPos == null)
            super.shoot(x, y, z, velocity, inaccuracy);
    }
    private LivingEntity getCurrentTarget() {
        if (this.target == null || !this.target.isAlive()) {
            String uuidStr = this.getEntityData().get(TARGET_UUID);
            if (!uuidStr.isEmpty()) {
                this.target = (LivingEntity) ((ServerLevel) this.level())
                        .getEntity(UUID.fromString(uuidStr));
            }
        }
        return this.target;
    }
    public void rideTick() {
        // 保留基础骑乘逻辑
        super.rideTick();

        // 取消原有的发射控制逻辑
        if (this.getVehicle() != null) {
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    private void faceEntityStandby() {
        this.setPos(this.position());
        this.setRot(this.getYRot(), -90.0F);
    }

    public void setSpread(Vec3 basePos) {
        double areaSize = 2.5;
        double offsetX = (this.random.nextDouble() * 2.0 - 1.0) * areaSize;
        double offsetZ = (this.random.nextDouble() * 2.0 - 1.0) * areaSize;
        this.setPos(basePos.x + offsetX, basePos.y, basePos.z + offsetZ);
    }

    protected void onHitEntity(EntityHitResult p_213868_1_) {
        Entity targetEntity = p_213868_1_.getEntity();
        if (targetEntity instanceof LivingEntity a) {
            KnockBacks.cancel.action.accept((LivingEntity) targetEntity);
            StunManager.setStun((LivingEntity) targetEntity);
            a.invulnerableTime = 0;
            // 清除追踪目标
        }
            // 只在首次命中时处理
            if (!hasHit) {
            this.target = null;
            this.getEntityData().set(TARGET_UUID, "");
        }

        super.onHitEntity(p_213868_1_);
    }

    protected void tryDespawn() {
        ++this.ticksInGround;
        if (this.ON_GROUND_LIFE_TIME <= this.ticksInGround) {
            this.burst();
        }

    }
    @Override
    public void setRot(float yRot, float xRot) {
        // 空实现，禁用自动旋转
        // super.setRot(yRot, xRot); // 注释掉这行
    }
    static {
        IT_FIRED = SynchedEntityData.defineId(SwordRainEntity.class, EntityDataSerializers.BOOLEAN); // 修正类引用
    }

}