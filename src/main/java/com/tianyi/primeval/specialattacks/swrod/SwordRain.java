package com.tianyi.primeval.specialattacks.swrod;

import com.tianyi.primeval.entity.SwordRainEntity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.util.AdvancementHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static mods.flammpfeil.slashblade.ability.SummonedSwordArts.ADVANCEMENT_HEAVY_RAIN_SWORDS;

public class SwordRain {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;

        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();
            if (state.isBroken() || state.isSealed() ||
                    !SwordType.from(playerIn.getMainHandItem()).contains(SwordType.BEWITCHED)) return;

            // 目标选择逻辑
            List<LivingEntity> targets = worldIn.getEntitiesOfClass(LivingEntity.class,
                            new AABB(playerIn.blockPosition()).inflate(20),
                            e -> e != playerIn && e.isAlive() && !e.isAlliedTo(playerIn))
                    .stream()
                    .sorted(Comparator.comparingDouble(e -> e.distanceToSqr(playerIn)))
                    .collect(Collectors.toList());

            LivingEntity closestTarget = targets.stream()
                    .min(Comparator.comparingDouble(e -> calculateAngle(playerIn, e)))
                    .orElse(null);

            if (closestTarget == null) return;

            // 基础位置设定（敌人头顶）
            Vec3 basePos = closestTarget.position()
                    .add(0, closestTarget.getBbHeight() + 1.5, 0);

            // 剑雨生成参数
            int powerLevel = playerIn.getMainHandItem().getEnchantmentLevel(Enchantments.POWER_ARROWS);
            int layers = 2;    // 层数
            int swordsPerLayer = 5; // 每层数量
            float baseRadius = 2.0f;

            // 生成规律性剑雨
            for (int layer = 0; layer < layers; layer++) {
                float currentRadius = baseRadius + (layer * 0.8f);
                float yOffset = layer * 1.2f;

                for (int i = 0; i < swordsPerLayer; i++) {
                    SwordRainEntity sword = new SwordRainEntity(PLEntiteRegristrys.ro, worldIn);
                    sword.setOwner(playerIn);
                    sword.setColor(state.getColorCode());
                    sword.setDamage(10 * (powerLevel + 1));//伤害乘区
                    sword.setTarget(closestTarget); // 传递目标引用
                    sword.setTargetPos(closestTarget.position().add(0, closestTarget.getBbHeight()/2, 0));

                    // 计算环形位置
                    double angle = Math.toRadians((360.0 / swordsPerLayer) * i);
                    double x = basePos.x + currentRadius * Math.cos(angle);
                    double z = basePos.z + currentRadius * Math.sin(angle);
                    double y = basePos.y + yOffset;

                    sword.setPos(x, y + 5, z); // 初始生成在高处
                    sword.setTargetPos(new Vec3(x, basePos.y, z)); // 设置下落目标位置
                    sword.setXRot(-90); // 保持原有俯仰角
                    sword.setYRot(180); // 新增：设置水平旋转朝南（默认向下）
                    sword.yRotO = 180;  // 设置上一刻的旋转角度
                    sword.setDeltaMovement(new Vec3(0, -0.5, 0)); // 初始向下速度
                    worldIn.addFreshEntity(sword);
                }
            }

            AdvancementHelper.grantCriterion(playerIn, ADVANCEMENT_HEAVY_RAIN_SWORDS);
            playerIn.playSound(SoundEvents.ENDER_DRAGON_FLAP, 0.2F, 1.45F);
        });
    }

    // 计算视角夹角辅助方法
    private static float calculateAngle(LivingEntity player, Entity target) {
        Vec3 viewVec = player.getViewVector(1.0F);
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return (float) Math.toDegrees(Math.acos(viewVec.dot(toTarget)));
    }

    // 原有计算视图向量的方法保持不变
    static Vec3 calculateViewVector(float x, float y) {
        float f = x * ((float) Math.PI / 180F);
        float f1 = -y * ((float) Math.PI / 180F);
        float f2 = Mth.cos(f1*2);
        float f3 = Mth.sin(f1*2);
        float f4 = Mth.cos(f*2);
        float f5 = Mth.sin(f*2);
        return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
    }
}