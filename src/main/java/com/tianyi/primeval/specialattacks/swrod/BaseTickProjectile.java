package com.tianyi.primeval.specialattacks.swrod;

import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class BaseTickProjectile extends Projectile {
    public static final EntityDataAccessor<Integer> TICK = SynchedEntityData.defineId(BaseTickProjectile.class, EntityDataSerializers.INT);

    protected BaseTickProjectile(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    ;
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TICK, 0);
    }
    public void setTick(int value) {
        this.getEntityData().set(TICK, value);
    }

    public Integer getTick() {
        return this.getEntityData().get(TICK);
    }

    @Override
    public void tick() {
        setTick(getTick()+1);
        super.tick();
    }
}
