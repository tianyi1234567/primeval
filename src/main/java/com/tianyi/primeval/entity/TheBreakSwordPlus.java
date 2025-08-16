package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PlayMessages;

public class TheBreakSwordPlus extends EntityAbstractSummonedSword {
    private static final EntityDataAccessor<Boolean> IT_FIRED;
    private boolean isInFormation = false;
    private static final EntityDataAccessor<Integer> FORMATION_INDEX ;

    public void setFormationParams(int index) {
        this.isInFormation = true;
        this.setFormationIndex(index);
    }
    public TheBreakSwordPlus(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte)1);
        this.noCulling = true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IT_FIRED, false);
        this.entityData.define(FORMATION_INDEX, 0);
    }
    public int formationIndex(){
        return (Integer)this.getEntityData().get(FORMATION_INDEX);
    };
    public void setFormationIndex(int index) {
        this.getEntityData().set(FORMATION_INDEX, index);
    }

    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired() {
        return (Boolean)this.getEntityData().get(IT_FIRED);
    }

    public static TheBreakSwordPlus createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new TheBreakSwordPlus(PLEntiteRegristrys.TBS, worldIn);
    }

    // 修改后的背后位置计算方法
    private Vec3 calculateBehindPlayer(Player player, double distance) {
        float yaw = player.getYHeadRot();
        float pitch = player.getXRot();

        return player.position().add(
                -Math.sin(Math.toRadians(yaw)) * distance,
                -Math.sin(Math.toRadians(pitch)) * distance * 0.5,
                Math.cos(Math.toRadians(yaw)) * distance
        );
    }

    @Override
    public void tick() {
        if (isInFormation && !itFired()) {

        }
        super.tick();
    }
    public void rideTick() {
        if (this.itFired()) {

            Entity target = this.getVehicle();
            this.stopRiding();
            this.tickCount = 0;
            Vec3 dir = this.getViewVector(1.0F);
            if (target != null) {
                dir = target.position().subtract(this.position()).multiply((double) 1.0F, (double) 0.0F, (double) 1.0F).normalize();
            }

            this.shoot(dir.x, dir.y, dir.z, 3.0F, 1.0F);
        } else {
            this.setDeltaMovement(Vec3.ZERO);
            if (this.canUpdate()) {
                this.baseTick();
            }
            this.faceEntityStandby();
            if (!level().isClientSide)
                hitCheck();

            // 在这里施加一次 buff
            Entity shooter = this.getShooter();
            if (shooter instanceof Player player && !player.isPassenger() && !player.isDeadOrDying()) {
                applyBuffs(player);
            }
        }
    }

    private void hitCheck() {
        Vec3 positionVec = this.position();
        Vec3 dirVec = this.getViewVector(1.0F);
        EntityHitResult raytraceresult = null;
        EntityHitResult entityraytraceresult = this.getRayTrace(positionVec, dirVec);
        if (entityraytraceresult != null) {
            raytraceresult = entityraytraceresult;
        }

        if (raytraceresult != null && raytraceresult.getType() == HitResult.Type.ENTITY) {
            Entity entity = raytraceresult.getEntity();
            Entity entity1 = this.getShooter();
            if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
                raytraceresult = null;
                EntityHitResult var7 = null;
            }
        }

        if (raytraceresult != null && raytraceresult.getType() == HitResult.Type.ENTITY && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
            this.onHit(raytraceresult);
            this.resetAlreadyHits();
            this.hasImpulse = true;
        }

    }

    private void faceEntityStandby() {
        Entity vehicle = this.getVehicle();
        if (vehicle instanceof Player) {
            Player player = (Player) vehicle;
            
            // 简化位置计算为直接绑定在玩家背部
            Vec3 basePos = calculateBehindPlayer(player, -0.7)
                .add(0, player.getEyeHeight() * 0.8, 0); // 调整垂直偏移
            
            this.setPos(basePos.x, basePos.y, basePos.z);
            
            // 设置固定朝向
            this.setYRot(player.getYHeadRot());
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
       // if (isInFormation) return; // 剑阵阶段不造成伤害
        Entity targetEntity = entityHitResult.getEntity();
        if (targetEntity instanceof LivingEntity) {
            KnockBacks.toss.action.accept((LivingEntity)targetEntity);
            StunManager.setStun((LivingEntity)targetEntity);
        }

        super.onHitEntity(entityHitResult);
    }

    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        this.burst();
    }

    static {
        IT_FIRED = SynchedEntityData.defineId(TheBreakSwordPlus.class, EntityDataSerializers.BOOLEAN);
        FORMATION_INDEX = SynchedEntityData.defineId(TheBreakSwordPlus.class, EntityDataSerializers.INT);
    }
    private void applyBuffs(Player player) {
        MobEffectInstance night = new MobEffectInstance(MobEffects.NIGHT_VISION, 280, 0); // 夜视，持续 6 秒
        MobEffectInstance strength = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 280, 2); // 力量 3，持续 6 秒
        MobEffectInstance resistance = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 280, 1); // 抗性提升 II，持续 6 秒
        MobEffectInstance fire = new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 280, 1); // 水下呼吸，持续 6 秒
        MobEffectInstance conduit = new MobEffectInstance(MobEffects.CONDUIT_POWER, 280, 1); // 抗火，持续 6 秒
        MobEffectInstance regen = new MobEffectInstance(MobEffects.REGENERATION, 280, 1); // 潮涌能量，持续 6 秒

        player.addEffect(night);
        player.addEffect(strength);
        player.addEffect(resistance);
        player.addEffect(fire);
        player.addEffect(conduit);
        player.addEffect(regen);
    }
}
