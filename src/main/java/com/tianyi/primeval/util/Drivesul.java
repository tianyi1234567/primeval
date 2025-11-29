package com.tianyi.primeval.util;

import com.dinzeer.legendreliclib.lib.compat.slashblade.Drives;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class Drivesul extends Drives {
    public static void HuanyingDrives(LivingEntity playerIn, int numPoints, float damage, float roll, float lifetime) {
        Vec3 centerOffset = Vec3.ZERO;
        int colorCode = (Integer)playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map((state) -> state.getColorCode()).orElse(-13421569);
        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add((double)0.0F, (double)(playerIn.getEyeHeight() * 0.75F), (double)0.0F).add(lookAngle.scale((double)0.3F));

        for(int i = 0; i < numPoints; ++i) {
            double angleDeg = (double)360.0F / (double)numPoints * (double)i;
            double angleRad = Math.toRadians(angleDeg);
            double offsetX = Math.cos(angleRad);
            double offsetZ = Math.sin(angleRad);
            EntityDrive drive = new EntityDrive(PLEntiteRegristrys.Huanying, playerIn.level());
            drive.setPos(pos.x, pos.y, pos.z);
            DriveBaseSet(drive, damage / (float)numPoints, playerIn, colorCode, roll, lifetime);
            Vec3 driveDirection = (new Vec3(offsetX, (double)0.0F, offsetZ)).normalize();
            drive.shoot(driveDirection.x, driveDirection.y, driveDirection.z, drive.getSpeed(), 0.0F);
            setConcentrationRank(drive, playerIn);
            playerIn.level().addFreshEntity(drive);
        }
    }

    public static void HuanyingDrivesPro(LivingEntity playerIn, float damage, int count, float roll, float lifetime) {
        int colorCode = getColorCode(playerIn);
        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = calculatePosition(playerIn, lookAngle);

        for(int i = 0; i < count; ++i) {
            EntityDrive drive = new EntityDrive(PLEntiteRegristrys.Huanying, playerIn.level());
            drive.setPos(pos.x, pos.y, pos.z);
            DriveBaseSet(drive, damage, playerIn, colorCode, roll, lifetime);
            drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(), 0.0F);
            setConcentrationRank(drive, playerIn);
            playerIn.level().addFreshEntity(drive);
        }

    }

    private static void setConcentrationRank(EntityDrive drive, LivingEntity playerIn) {
        if (playerIn != null) {
            playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT).ifPresent((rank) -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));
        }
    }
    private static int getColorCode(LivingEntity playerIn) {
        return (Integer)playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map((state) -> state.getColorCode()).orElse(-13421569);
    }
    private static Vec3 calculatePosition(LivingEntity playerIn, Vec3 lookAngle) {
        return playerIn.position().add((double)0.0F, (double)(playerIn.getEyeHeight() * 0.75F), (double)0.0F).add(lookAngle.scale((double)0.3F));
    }
}
