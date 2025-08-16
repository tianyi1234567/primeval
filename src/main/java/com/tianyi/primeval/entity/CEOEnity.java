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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.joml.Vector3f;

public class CEOEnity extends EntityDrive {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(CEOEnity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(CEOEnity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(CEOEnity.class, EntityDataSerializers.VECTOR3);
    private LivingEntity target; // 新增目标字段
    private int age = 0;
    public CEOEnity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    // 新增设置目标方法
    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    @Override
    public void tick() {
        super.tick();
        age++;

        if (!this.level().isClientSide) {
            // 确保目标有效
            if (target == null || !target.isAlive()) {
                this.discard();
                return;
            }

            // 计算目标位置（胸部高度）
            Vec3 targetPos = target.position().add(0, target.getBbHeight() * 0.6, 0);
            Vec3 currentPos = this.position();

            // 距离检查（小于0.5米视为命中）
            if (currentPos.distanceTo(targetPos) < 0.5) {
                this.discard();
                return;
            }

            // 简化运动逻辑：固定速度直线飞行
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            float actualSpeed = getSpeed() * 0.8f; // 80%的基础速度
            this.setDeltaMovement(direction.scale(actualSpeed));

            // 生命周期保护期（前20tick不消失）
            if (age > 20 && age % 10 == 0) {
                // 每10tick检查是否卡住
                if (this.getDeltaMovement().lengthSqr() < 0.001) {
                    this.discard();
                }
            }
        }
    }

    public static CEOEnity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new CEOEnity(PLEntiteRegristrys.ceo, worldIn);
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
        this.discard(); // 击中任何实体后消失
        super.onHitEntity(result); // 保留原有特效
    }
}
