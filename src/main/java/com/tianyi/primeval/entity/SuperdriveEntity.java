package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.joml.Vector3f;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageTypes;

public class SuperdriveEntity extends EntityDrive {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(SuperdriveEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(SuperdriveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(SuperdriveEntity.class, EntityDataSerializers.VECTOR3);
    public SuperdriveEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    // 添加一个标记，表示实体是否已定位
    private boolean positioned = false;

    // 添加计数器，用于控制每秒伤害次数
    private int damageCounter = 0;

    public static SuperdriveEntity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new SuperdriveEntity(PLEntiteRegristrys.supde, worldIn);
    }
    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();

        this.entityData.define(IT_FIRED, false);
        this.entityData.define(SPEED, 3.0f);
        this.entityData.define(OFFSET, Vec3.ZERO.toVector3f());
    }
    @Override
    public void tick() {//虽然可以使用原版的实体类，但我懒得改了，能跑起来就行
        // 在第一次tick时将实体定位到玩家前方
        if (!positioned && this.getOwner() != null) {
            Entity owner = this.getOwner();
            // 获取玩家的朝向
            Vec3 lookVec = owner.getLookAngle();
            // 计算玩家前方X格的位置
            Vec3 spawnPos = owner.position().add(0, owner.getEyeHeight(), 0).add(lookVec.scale(14.0D));
            // 设置实体位置
            this.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
            positioned = true;
        }

        super.tick();

        // 每X tick造成一次伤害
        if (!this.level().isClientSide()) {
            damageCounter++;
            if (damageCounter >= 3) {
                damageCounter = 0;
                dealDamageToNearbyEntities();
            }
        }
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

    private void dealDamageToNearbyEntities() {
        // 获取附近的实体（X格范围）
        AABB boundingBox = this.getBoundingBox().inflate(30.0D);
        java.util.List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, boundingBox,
                entity -> !(entity instanceof Player) && entity != this.getOwner());

        //对每个实体造成魔法伤害（虽然实际上是物理伤害来源）
        for (LivingEntity entity : entities) {
            //检查，确保不是玩家
            if (!(entity instanceof Player)) {
                // 重置无敌时间，使伤害无视无敌帧
                entity.invulnerableTime = 0;
                // 使用正确的玩家伤害来源实现
                if (this.getOwner() instanceof Player player) {
                    // 从注册表获取玩家攻击伤害类型
                    Holder<DamageType> playerAttackType = this.level().registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DamageTypes.PLAYER_ATTACK);

                    // 创建玩家来源的伤害源
                    DamageSource damageSource = new DamageSource(playerAttackType, player, player);
                    entity.hurt(damageSource, (float) this.getDamage());
                } else {
                    // 如果拥有者非玩家，保持原始魔法伤害类型作为回退
                    Holder<DamageType> magicType = this.level().registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DamageTypes.MAGIC);
                    DamageSource fallbackSource = new DamageSource(magicType, this, this.getOwner());
                    entity.hurt(fallbackSource, (float) this.getDamage());
                }
            }
        }
    }
}