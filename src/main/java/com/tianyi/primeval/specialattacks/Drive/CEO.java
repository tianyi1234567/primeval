package com.tianyi.primeval.specialattacks.Drive;

import com.tianyi.primeval.entity.CEOEnity;
import com.tianyi.primeval.entity.HuanRenEnity;
import com.tianyi.primeval.registry.*;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class CEO {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;

        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();

            // 获取玩家准星最近的实体
            LivingEntity target = findNearestTarget(playerIn, 20.0);
            if (target == null) return;

            CEOEnity ss = new CEOEnity(PLEntiteRegristrys.ceo, worldIn);
            // 计算2格外的生成位置（基于目标位置和玩家朝向）
            Vec3 targetPos = target.position();

            // 方法1：简单环形生成（3格半径随机位置）
            Vec3 spawnPos = targetPos.add(
                    worldIn.getRandom().nextBoolean() ? 3 : -3, // X: 正负3格
                    0.25 + worldIn.getRandom().nextDouble() * 2.15,
                    worldIn.getRandom().nextBoolean() ? 3 : -3  // Z: 正负3格
            );
            ss.setPos(spawnPos);
            ss.setTarget(target);
            worldIn.addFreshEntity(ss);
            // 保留原有属性设置
            ss.setSpeed(speed);
            ss.setIsCritical(critical);
            ss.setOwner(playerIn);
            ss.setColor(0xf7892d);
            ss.setRoll(0);
            ss.setDamage(damage);
            playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
        });
    }

    // 新增方法：查找最近的敌人
    private static LivingEntity findNearestTarget(LivingEntity player, double range) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 targetPos = eyePos.add(lookVec.scale(range));

        // 获取视线范围内的所有实体
        List<LivingEntity> entities = player.level().getEntitiesOfClass(
                LivingEntity.class,
                new AABB(eyePos, targetPos),
                e -> e != player && e.isAlive() && !e.isAlliedTo(player)
        );

        // 找出最近的实体
        return entities.stream()
                .min(Comparator.comparingDouble(e -> e.distanceToSqr(eyePos)))
                .orElse(null);
    }
}