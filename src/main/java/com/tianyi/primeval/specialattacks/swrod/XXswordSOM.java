package com.tianyi.primeval.specialattacks.swrod;

import com.tianyi.primeval.entity.SumonSwordEntity;
import com.tianyi.primeval.entity.XXXperswordEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeConfig;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.inputstate.CapabilityInputState;
import mods.flammpfeil.slashblade.entity.EntityStormSwords;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.AdvancementHelper;
import mods.flammpfeil.slashblade.util.InputCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ClipContext;
import java.util.Comparator;
import java.util.List;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class XXswordSOM {
    public static void doXXsword(LivingEntity playerIn, boolean critical, double damage, float speed) {
        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();
            if (state.isBroken() || state.isSealed() ||
                    !SwordType.from(playerIn.getMainHandItem()).contains(SwordType.BEWITCHED)) return;

            // 新目标选择逻辑
            LivingEntity finalTarget = null;
            double range = 20.0;
            Vec3 startPos = playerIn.getEyePosition(1.0f);
            Vec3 endPos = startPos.add(playerIn.getViewVector(1.0f).scale(range));

            HitResult hitResult = worldIn.clip(new ClipContext(
                    startPos, endPos,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    playerIn
            ));

            if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) hitResult;
                Entity entity = entityHit.getEntity();
                if (entity instanceof LivingEntity livingEntity &&
                        livingEntity.isAlive() &&
                        !livingEntity.isAlliedTo(playerIn) &&
                        livingEntity.distanceToSqr(playerIn) <= range * range) {
                    finalTarget = livingEntity;
                }
            }

            if (finalTarget == null) {
                List<LivingEntity> candidates = worldIn.getEntitiesOfClass(
                        LivingEntity.class,
                        new AABB(playerIn.blockPosition()).inflate(range),
                        e -> e != playerIn && e.isAlive() && !e.isAlliedTo(playerIn)
                );

                finalTarget = candidates.stream()
                        .min(Comparator.comparingDouble(e ->
                                calculateAngle(playerIn, e) +
                                        e.distanceToSqr(playerIn) * 0.01
                        ))
                        .orElse(null);
            }

            if (finalTarget == null) return;

            // 资源消耗检查
            int cost = 0; // 根据实际配置调整
            if (state.getProudSoulCount() < cost) return;
            state.setProudSoulCount(state.getProudSoulCount() - cost);

            // 生成参数
            int count = 5;
            //int count = playerIn.getMainHandItem().getEnchantmentLevel(Enchantments.POWER_ARROWS) >= 3 ? 6 : 3;//可配置锻造参数
            // 生成剑阵
            for (int i = 0; i < count; i++) {
                XXXperswordEnity sword = new XXXperswordEnity(PLEntiteRegristrys.xrt, worldIn);

                sword.setPos(finalTarget.getX(),
                        finalTarget.getY() + finalTarget.getBbHeight() * 0.8,
                        finalTarget.getZ());
                sword.setOwner(playerIn);
                sword.setColor(state.getColorCode());
                sword.setDamage(damage * (critical ? 1.5f : 1.0f));

                // 立即开始骑行并设置初始偏移
                sword.startRiding(finalTarget, true);
                sword.setDelay((int) (360.0f / count * i));
                sword.setPierce((byte) 3);

                // 强制设置初始运动状态
                sword.setDeltaMovement(Vec3.ZERO);
                worldIn.addFreshEntity(sword);
            }

            // 音效
            playerIn.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 0.8F, 1.2F);
            playerIn.playSound(SoundEvents.SOUL_ESCAPE, 0.6F, 0.8F);
        });
    }

    private static float calculateAngle(LivingEntity player, Entity target) {
        Vec3 viewVec = player.getViewVector(1.0F);
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return (float) Math.toDegrees(Math.acos(viewVec.dot(toTarget)));
    }
}