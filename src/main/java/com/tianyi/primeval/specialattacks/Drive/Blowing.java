package com.tianyi.primeval.specialattacks.Drive;

import com.tianyi.primeval.entity.BlowingEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class Blowing {
    public static BlowingEnity doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                       boolean critical, double damage, float speed) {
        return doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, speed);
    }
    public static BlowingEnity doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                       boolean critical, double damage, KnockBacks knockback, float speed) {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);

        return doSlashInternal(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, speed);
    }
    private static BlowingEnity doSlashInternal(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                                                boolean critical, double damage, KnockBacks knockback, float speed) {
        if (playerIn.level().isClientSide())
            return null;

        Random rand = new Random();

// 获取玩家当前的俯仰角和偏航角
        float pitch = playerIn.getXRot(); // 垂直方向（抬头/低头）
        float yaw = playerIn.getViewYRot(0); // 水平方向（左右看）

// 添加随机偏移（例如 ±5 度）
        float randomPitchOffset = (rand.nextFloat() * 0 - 0); // -5 ~ +5 度
        float randomYawOffset = (rand.nextFloat() * 0 - 0);   // -10 ~ +10 度

        float adjustedPitch = pitch + randomPitchOffset;
        float adjustedYaw = yaw + randomYawOffset;

        Vec3 lookAngle = VectorHelper.getVectorForRotation(adjustedPitch, adjustedYaw);

//        // 固定偏移角度（单位：度）
//        float fixedPitchOffset = 5.0f;  // 垂直方向固定偏移（例如+5°）
//        float fixedYawOffset = 10.0f;   // 水平方向固定偏移（例如+10°）
//
//        // 获取玩家视角角度并应用固定偏移
//        float pitch = playerIn.getXRot() + fixedPitchOffset;
//        float yaw = playerIn.getViewYRot(0) + fixedYawOffset;
//
//        // 计算发射方向
//        Vec3 lookAngle = VectorHelper.getVectorForRotation(pitch, yaw);

        Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(lookAngle.scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(lookAngle.scale(centerOffset.z));

        BlowingEnity drive = new BlowingEnity(PLEntiteRegristrys.bl, playerIn.level());
        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(damage);
        drive.setSpeed(speed);
        drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(), 0);

        drive.setOwner(playerIn);
        drive.setRotationRoll(roll);
        drive.setColor(colorCode);
        drive.setIsCritical(critical);
        drive.setKnockBack(knockback);
        drive.setLifetime(lifetime);

        playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                .ifPresent(rank -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));

        playerIn.level().addFreshEntity(drive);

        return drive;
    }
}
