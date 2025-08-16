package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ProDriveEnity extends EntityDrive {
    public ProDriveEnity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public static ProDriveEnity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new ProDriveEnity(PLEntiteRegristrys.wd, worldIn);
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity.isOnFire() && entity.isAlive()) {
            entity.extinguishFire();
        }

    }
}
