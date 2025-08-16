package com.tianyi.primeval.specialattacks.Drive;

import com.tianyi.primeval.entity.HuanRenEnity;
import com.tianyi.primeval.entity.SuperdriveEntity;
import com.tianyi.primeval.registry.*;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.tianyi.primeval.entity.BlowingEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

import com.tianyi.primeval.entity.BigDriveEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SuperDV {
    private static final int PROUD_SOUL_THRESHOLD = 500000;  // 耀魂值阈值,这些%非真实百分比，实际上应该再除以10(即小数点左移一位)
    private static final int REFINEMENT_THRESHOLD = 1250;    // 精练值阈值
    private static final double PROUD_SOUL_SCALE_NORMAL = 0.0005;  // 正常加成系数（每1点增加0.04%）
    private static final double PROUD_SOUL_SCALE_DIMINISHED = 0.0002;  // 衰减加成系数（每1点增加0.02%）
    private static final double REFINEMENT_SCALE_NORMAL = 0.26;  // 正常加成系数（每1点增加26%）
    private static final double REFINEMENT_SCALE_DIMINISHED = 0.085;  // 衰减加成系数（每1点增加8.5%）
    public static SuperdriveEntity doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                           boolean critical, double damage, float speed) {
        return doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, speed);
    }

    public static SuperdriveEntity doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                           boolean critical, double damage, KnockBacks knockback, float speed) {

        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);

        return doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, speed);
    }

    public static SuperdriveEntity doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                                           boolean critical, double damage, KnockBacks knockback, float speed) {

        if (playerIn.level().isClientSide())
            return null;

        // 获取武器属性并计算伤害乘区
        double damageMultiplier = 1.0;
        ItemStack mainHandItem = playerIn.getMainHandItem();

        // 从武器获取属性
        double baseAttackModifier = mainHandItem.getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> (double) state.getBaseAttackModifier()).orElse(1.0);

        int proudSoul = mainHandItem.getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getProudSoulCount()).orElse(0);

        int refine = mainHandItem.getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getRefine()).orElse(0);

        // 计算乘区 (线性加成公式)
        // 基础攻击修饰器乘区 (武器面板)
        damageMultiplier += baseAttackModifier / 1.25;

        // 耀魂值乘区（带衰减）
        if (proudSoul > PROUD_SOUL_THRESHOLD) {
            int excess = proudSoul - PROUD_SOUL_THRESHOLD;
            damageMultiplier += (PROUD_SOUL_THRESHOLD * PROUD_SOUL_SCALE_NORMAL)
                    + (excess * PROUD_SOUL_SCALE_DIMINISHED);
        } else {
            damageMultiplier += proudSoul * PROUD_SOUL_SCALE_NORMAL;
        }

        // 精练值乘区（带衰减）
        if (refine > REFINEMENT_THRESHOLD) {
            int excess = refine - REFINEMENT_THRESHOLD;
            damageMultiplier += (REFINEMENT_THRESHOLD * REFINEMENT_SCALE_NORMAL)
                    + (excess * REFINEMENT_SCALE_DIMINISHED);
        } else {
            damageMultiplier += refine * REFINEMENT_SCALE_NORMAL;
        }

        // 应用乘区到原始伤害
        double finalDamage = damage * damageMultiplier;

        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(lookAngle.scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(lookAngle.scale(centerOffset.z));
        SuperdriveEntity drive = new SuperdriveEntity(PLEntiteRegristrys.supde, playerIn.level());
        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(finalDamage);
        drive.setSpeed(speed);
        drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(),
                0);

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