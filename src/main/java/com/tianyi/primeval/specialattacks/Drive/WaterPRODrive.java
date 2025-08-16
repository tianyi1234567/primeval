package com.tianyi.primeval.specialattacks.Drive;

import com.tianyi.primeval.entity.HuanRenEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WaterPRODrive {
    public WaterPRODrive() {
    }

    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset, boolean critical, double damage, float speed) {
        return doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, speed);
    }

    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset, boolean critical, double damage, KnockBacks knockback, float speed) {
        int colorCode = (Integer)playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map((state) -> {
            return state.getColorCode();
        }).orElse(-13421569);
        return doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, speed);
    }

    private static void spawnParticle(ParticleOptions type, LivingEntity player, int num, double rate) {
        Level world = player.level();
        RandomSource random = player.getRandom();

        for(int i = 0; i < num && !player.level().isClientSide(); ++i) {
            double xDist = (double)(random.nextFloat() * 2.0F - 1.0F);
            double yDist = (double)(random.nextFloat() * 2.0F - 1.0F);
            double zDist = (double)(random.nextFloat() * 2.0F - 1.0F);
            if (!(xDist * xDist + yDist * yDist + zDist * zDist > 1.0)) {
                double x = player.getX() + ((random.nextDouble() * 2.0 - 1.0) * (double)player.getBbWidth() - random.nextGaussian() * 0.02 * 10.0) * rate;
                double y = player.getY();
                double z = player.getZ() + ((random.nextDouble() * 2.0 - 1.0) * (double)player.getBbWidth() - random.nextGaussian() * 0.02 * 10.0) * rate;
                ((ServerLevel)world).sendParticles(type, x, y, z, 0, xDist, yDist + 0.2, zDist, 1.0);
            }
        }

    }

    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset, boolean critical, double damage, KnockBacks knockback, float speed) {
        if (playerIn.level().isClientSide()) {
            return null;
        } else {
            playerIn.playSound(SoundEvents.PLAYER_SWIM, 1.0F, 1.5F);
            if (playerIn.isOnFire()) {
                playerIn.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 0.7F, 1.2F + 0.8F * playerIn.getRandom().nextFloat());
                playerIn.extinguishFire();
            }

            spawnParticle(ParticleTypes.FALLING_WATER, playerIn, 30, 2.0);
            Vec3 lookAngle = playerIn.getLookAngle();
            Vec3 pos = playerIn.position().add(0.0, (double)playerIn.getEyeHeight() * 0.75, 0.0).add(lookAngle.scale(0.30000001192092896));
            pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0.0F)).scale(centerOffset.y)).add(VectorHelper.getVectorForRotation(0.0F, playerIn.getViewYRot(0.0F) + 90.0F).scale(centerOffset.z)).add(lookAngle.scale(centerOffset.z));
            HuanRenEnity drive = new HuanRenEnity(PLEntiteRegristrys.hr, playerIn.level());
            drive.setPos(pos.x, pos.y, pos.z);
            drive.setDamage(damage);
            drive.setSpeed(speed);
            drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(), 0.0F);
            drive.setOwner(playerIn);
            drive.setColor(colorCode);
            drive.setIsCritical(critical);
            }
        return null;
    }
    }
