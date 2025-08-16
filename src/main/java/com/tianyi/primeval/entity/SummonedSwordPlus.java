package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.NBTHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SummonedSwordPlus extends EntityHeavyRainSwords {
    public SummonedSwordPlus(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.noCulling = true;
        setNoGravity(true);

    }

    public static SummonedSwordPlus createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new SummonedSwordPlus(PLEntiteRegristrys.swplis, worldIn);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        AreaAttackEnity areaAttackEnity = new AreaAttackEnity(PLEntiteRegristrys.aata,level());
        areaAttackEnity.setOwner(getOwner());
        areaAttackEnity.setPos(position().add(0,0,0));
        level().addFreshEntity(areaAttackEnity);
        super.onHitBlock(blockraytraceresult);
    }

    public final Map<UUID,Integer> hitEntites = new HashMap<>();
    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        Entity targetEntity = p_213868_1_.getEntity();
        if (hitEntites.containsKey(targetEntity.getUUID()) && hitEntites.get(targetEntity.getUUID())>0){
            hitEntites.put(targetEntity.getUUID(), hitEntites.get(targetEntity.getUUID())-1);
            return;
        }
        hitEntites.put(targetEntity.getUUID(),10);
        if (targetEntity instanceof LivingEntity) {
            KnockBacks.cancel.action.accept((LivingEntity)targetEntity);
            StunManager.setStun((LivingEntity)targetEntity);
        }

        super.onHitEntity(p_213868_1_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BASESIZE, 1.0f);
        this.entityData.define(Particle, true);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        NBTHelper.getNBTCoupler(compound)
          .put("BaseSize", this.getBaseSize())
                .put("Particle", this.getParticle())
        ;

    }

    public float getBaseSize() {
        return this.getEntityData().get(BASESIZE);
    }

    public void setBaseSize(float value) {
        this.getEntityData().set(BASESIZE, value);
    }
    public boolean getParticle() {
        return this.getEntityData().get(Particle);
    }

    public void setParticle(boolean value) {
        this.getEntityData().set(Particle, value);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        NBTHelper.getNBTCoupler(compound)
         .get("BaseSize", this::setBaseSize)
                .get("Particle", this::setParticle)
        ;

    }
    private static final EntityDataAccessor<Float> BASESIZE = SynchedEntityData.<Float>defineId(SummonedSwordPlus.class,
            EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> Particle = SynchedEntityData.<Boolean>defineId(SummonedSwordPlus.class,
            EntityDataSerializers.BOOLEAN);
    @Override
    public void tick() {
        Level level = this.level();
        float radius = getBaseSize()*60f;

        super.tick();
        if (!getParticle())return;
        for (int i = 0; i < 35; ++i) {
            if (this.level().isClientSide()) {
                double baseSize = getBaseSize(); // 假设 baseSize 是 0.5，可以根据实际情况调整
                double randomX = this.getX() + (this.random.nextDouble() - 0.5) * baseSize * 200;
                double randomY = this.getY() + (this.random.nextDouble() - 0.5) * baseSize * 200;
                double randomZ = this.getZ() + (this.random.nextDouble() - 0.5) * baseSize * 200;

                this.level().addParticle(ParticleTypes.SOUL, randomX, randomY, randomZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
