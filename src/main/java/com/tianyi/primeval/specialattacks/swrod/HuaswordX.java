package com.tianyi.primeval.specialattacks.swrod;

import com.tianyi.primeval.entity.*;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HuaswordX {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed, int lifetime) {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed, lifetime);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed, int lifetime) {
        if (playerIn.level().isClientSide()) return;

        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();

            int rank = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT)
                    .map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
            int count = 3; // 物体的数量，均分圆上的位置
            float radius = 2f; //半径(圈的大小)，可以根据需要调整

            Vec3 playerPos = playerIn.position(); // 获取玩家当前位置
            float playerYaw = Mth.wrapDegrees(playerIn.getYRot()); // 获取玩家朝向（Y轴旋转）

            for (int i = 0; i < count; i++) {
                // 计算每个实体的角度（度）
                double angleDeg = (double) 270 / count * i;
                // 将角度从度转换为弧度
                double angleRad = Math.toRadians(angleDeg);

                // 使用极坐标转换计算新位置
                double offsetX = radius * Math.cos(angleRad);
                double offsetZ = radius * Math.sin(angleRad);

                // 考虑玩家朝向进行偏移
                double cosYaw = Math.cos(Math.toRadians(playerYaw));
                double sinYaw = Math.sin(Math.toRadians(playerYaw));

                // 应用偏移量到玩家位置
                double finalX = playerPos.x + offsetX * cosYaw - offsetZ * sinYaw;
                double finalZ = playerPos.z + offsetX * sinYaw + offsetZ * cosYaw;

                YingYuEnity ss = new YingYuEnity(PLEntiteRegristrys.yy, worldIn);


                ss.setSpeed(speed);
                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(0);
                ss.setDamage(damage);
                ss.startRiding(playerIn, true);

                ss.setDelay(8 + i);
                //持续时间，可定义轮流发射

                double yOffset = i * 0.005 + 0.5;
                double zOffset = -1.0;

                // y轴设置实体位置，xz下面没定义
                ss.setPos(finalX, playerPos.y + yOffset, finalZ);
                ss.setOffset(new Vec3(offsetX, yOffset, offsetZ));
                worldIn.addFreshEntity(ss);
                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }

        });
    }
}