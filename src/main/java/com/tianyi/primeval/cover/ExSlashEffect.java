package com.tianyi.primeval.cover;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ExSlashEffect {//月影刀光

    /**
     * 独立刀光生成逻辑 - 月影模式
     * 与SakuraEnd互不干扰，使用不同命名空间和计算方式
     */
    public class MoonSlash {

        /**
         * 基础参数版本 - 使用默认击退效果
         */
        public static EntitySlashEffect create(LivingEntity playerIn, float roll, Vec3 centerOffset,
                                             boolean mute, boolean critical, double damage) {
            return create(playerIn, roll, centerOffset, mute, critical, damage, KnockBacks.toss);
        }

        /**
         * 中级参数版本 - 自动获取武器颜色
         */
        public static EntitySlashEffect create(LivingEntity playerIn, float roll, Vec3 centerOffset,
                                             boolean mute, boolean critical, double damage,
                                             KnockBacks knockback) {
            // 从主手物品获取颜色代码，失败时使用银色
            int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode())
                .orElse(0xC0C0C0); // 银色备用颜色

            return create(playerIn, roll, colorCode, centerOffset, mute, critical, damage, knockback);
        }

        /**
         * 完整参数版本 - 直接指定所有属性
         */
        public static EntitySlashEffect create(LivingEntity playerIn, float roll, int colorCode,
                                             Vec3 centerOffset, boolean mute, boolean critical,
                                             double damage, KnockBacks knockback) {
            // 仅在服务端生成实体
            if (playerIn.level().isClientSide()) {
                return null;
            }

            // 新的坐标计算逻辑 - 月影风格偏移
            Vec3 pos = calculateMoonPosition(playerIn, centerOffset);

            // 创建实体并配置属性
            EntitySlashEffect jc = new EntitySlashEffect(SlashBlade.RegistryEvents.SlashEffect, playerIn.level());
            configureMoonSlash(jc, playerIn, pos, roll, colorCode, mute, critical, damage, knockback);

            // 添加到世界
            playerIn.level().addFreshEntity(jc);
            return jc;
        }

        /**
         * 月影风格坐标计算
         */
        private static Vec3 calculateMoonPosition(LivingEntity player, Vec3 centerOffset) {
            // 基础位置计算（保持不变）
            Vec3 base = player.position()
                    .add(0.0, player.getEyeHeight() * 0.75, 0.0)
                    .add(player.getLookAngle().scale(0.3));

            // 自定义偏移（直接硬编码，无需修改参数）
            Vec3 customOffset = new Vec3(3, 0, 0.0); // XYZ 偏移量

            // 结合原有偏移逻辑
            return base
                    .add(VectorHelper.getVectorForRotation(-90.0F, player.getViewYRot(0.0F)).scale(centerOffset.y))
                    .add(VectorHelper.getVectorForRotation(0.0F, player.getViewYRot(0.0F) + 90.0F).scale(centerOffset.z))
                    .add(customOffset); // 应用自定义偏移
        }

        /**
         * 配置月影风格属性
         */
        private static void configureMoonSlash(EntitySlashEffect entity, LivingEntity player,
                                               Vec3 position, float roll, int color,
                                               boolean mute, boolean critical,
                                               double damage, KnockBacks knockback) {
            // 基础属性设置
            entity.setPos(position.x, position.y, position.z);
            entity.setOwner(player);
            entity.setRotationRoll(roll);
            entity.setYRot(player.getYRot());
            entity.setXRot(0.0F);
            entity.setColor(color);


            // 状态设置
            entity.setMute(mute);
            entity.setIsCritical(critical);
            entity.setDamage(damage);
            entity.setKnockBack(knockback);

            // 等级同步
            if (player != null) {
                player.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                    .ifPresent(rank -> entity.setRank(rank.getRankLevel(player.level().getGameTime())));
            }

            // 月影特有属性
            entity.setLifetime(15); // 生命周期,不过没啥用，改了也只造成一次伤害
            entity.setCustomName(Component.literal("MoonSlash"));
        }

    }
}
