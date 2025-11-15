package com.tianyi.primeval.specialattacks.Drive;

import com.tianyi.primeval.entity.xeper.ExperDriveEntity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource; // 添加此行导入
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class Experdrive {

    /**
     * 在combo中简便生成ExperDriveEntity实体的方法，自动锁定最近的生物
     *
     * @param entity   使用技能的实体
     * @param yaw      实体的朝向角度
     * @param lifetime 实体存在时间(单位:tick)
     * @param damage   造成的伤害值
     * @param color    实体颜色
     * @param scale    实体缩放比例
     */
    public static void doSlash(LivingEntity entity, float yaw, float lifetime, float damage, int color, float scale) {
        // 查找最近的生物实体
        LivingEntity target = findNearestEntity(entity, 45D);

        // 确定生成位置
        Vec3 spawnPosition;
        float spawnYaw = yaw; // 保持原始朝向角度

        if (target != null) {
            // 如果找到目标，在目标胸部以上位置生成，并在X轴和Z轴2.5*2.5范围内随机偏移
            spawnPosition = target.position().add(
                    (target.getRandom().nextDouble() - 0.5D) * 2.5D,  // X轴随机偏移±1.25
                    target.getEyeHeight() + 0.25D, // 胸部以上位置（眼部高度加上一定值）
                    (target.getRandom().nextDouble() - 0.5D) * 2.5D   // Z轴随机偏移±1.25
            );

            // 计算面向目标的角度
            Vec3 targetPos = target.position();
            Vec3 entityPos = spawnPosition;
            Vec3 direction = targetPos.subtract(entityPos);
            spawnYaw = (float) Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0F;
        }
        else {
            // 如果没有找到目标，在使用者位置附近生成，使用固定角度范围
            double angleRange = Math.toRadians(60); // 固定角度范围为±60度
            double randomAngle = (entity.getRandom().nextDouble() - 0.5D) * 2.0D * angleRange;
            spawnYaw = (float)(yaw + Math.toDegrees(randomAngle)); // 计算最终角度

            spawnPosition = entity.position().add(
                    (entity.getRandom().nextDouble() - 0.5D) * 2.0D,
                    1.0D,
                    (entity.getRandom().nextDouble() - 0.5D) * 2.0D
            );
        }

        // 创建 ExperDriveEntity 实体
        ExperDriveEntity experDrive = new ExperDriveEntity(PLEntiteRegristrys.ExperDrive, entity.level());

        // 设置实体位置
        experDrive.setPos(spawnPosition.x, spawnPosition.y, spawnPosition.z);

        // 修改伤害计算方式：使用使用者最大生命值的倍数（damage参数现在表示倍数）
        float calculatedDamage = entity.getMaxHealth() * damage;
        experDrive.setDamage(calculatedDamage);     // 设置基于最大生命值计算的伤害值

        // 设置实体参数
        experDrive.setLifetime(lifetime); // 设置存在时间
        experDrive.setColor(color);       // 设置颜色

        // 设置实体朝向为计算后的角度
        experDrive.setYRot(spawnYaw);
        experDrive.setXRot(0);

        // 添加实体到世界
        entity.level().addFreshEntity(experDrive);

        // 在生成实体后立即应用范围伤害
        applyAreaDamage(entity, experDrive, calculatedDamage);
    }

    /**
     * 查找最近的生物实体
     *
     * @param entity 搜索的起始实体
     * @param range  搜索范围
     * @return 最近的生物实体，如果没有找到则返回null
     */
    private static LivingEntity findNearestEntity(LivingEntity entity, double range) {
        AABB searchBox = entity.getBoundingBox().inflate(range, range, range);

        // 获取范围内的所有生物实体（排除玩家）
        List<LivingEntity> entities = entity.level().getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                e -> e != entity && e.isAlive() && !e.isSpectator() && !(e instanceof net.minecraft.world.entity.player.Player)
        );
        
        return entities.stream()
                .min(Comparator.comparingDouble(e -> e.distanceToSqr(entity)))
                .orElse(null);
    }
    private static void applyAreaDamage(LivingEntity user, ExperDriveEntity experDrive, float damage) {
        // 创建4*4范围的边界框
        AABB damageBox = experDrive.getBoundingBox().inflate(2.0D, 2.0D, 2.0D);

        // 获取范围内的所有生物实体（排除使用者）
        List<LivingEntity> entities = user.level().getEntitiesOfClass(
                LivingEntity.class,
                damageBox,
                e -> e != user && e.isAlive() && !e.isSpectator()
        );

        for (LivingEntity target : entities) {
            if (user instanceof net.minecraft.world.entity.player.Player) {
                target.hurt(user.damageSources().playerAttack((net.minecraft.world.entity.player.Player) user), damage);
            } else {
                target.hurt(user.damageSources().mobAttack(user), damage);
            }
            // 重置无敌帧
            target.invulnerableTime = 0;
        }
    }
}