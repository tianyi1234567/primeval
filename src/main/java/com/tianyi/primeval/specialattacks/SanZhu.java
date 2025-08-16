package com.tianyi.primeval.specialattacks;

import com.tianyi.primeval.entity.SummonedSwordPlus;
import com.tianyi.primeval.entity.SumonSwordPROEntity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class SanZhu
{
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed)
    {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;

        ItemStack mainHandItem = playerIn.getMainHandItem();
        mainHandItem.getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();

            int rank = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT)
                    .map(r -> r.getRank(worldIn.getGameTime()).level)
                    .orElse(0);
            int count = IConcentrationRank.ConcentrationRanks.S.level;
            final Vec3 _center = new Vec3(playerIn.getX(), playerIn.getY()+0.2, playerIn.getZ());
            List<Entity> _entfound = playerIn.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20f), a -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if (entityiterator instanceof LivingEntity livingEntity && livingEntity!=playerIn && playerIn.canAttack(livingEntity)) {
                    for (int i = 0; i < count; i++) {
                        SummonedSwordPlus ss = new SummonedSwordPlus(PLEntiteRegristrys.swplis, worldIn);
                        ss.setPierce((byte) 100);
                        ss.setXRot(-90);
                        ss.setDelay(25);
                        ss.setBaseSize(4);
                        ss.setParticle(false);
                        // 计算玩家头部位置（眼睛位置上方0.2米）
                     //   ISlashBladeState iSlashBladeState = playerIn.getCapability(ItemSlashBlade.BLADESTATE).orElse(new SlashBladeState(mainHandItem));
                     //   Entity targetEntity = iSlashBladeState.getTargetEntity(worldIn);
                        Vec3 headPos = livingEntity != null ? livingEntity.getEyePosition(1).add(0, 10, 0) : playerIn.getEyePosition(1).add(0, 10, 0);
                        double radius = (double) count / 2; // 可根据需要调整半径
                        double angle = Math.toRadians((360.0 / count) * i); // 计算每个实体的角度

                        // 计算坐标偏移
                        double offsetX = radius * Math.cos(angle);
                        double offsetZ = radius * Math.sin(angle);

                        // 设置实体位置
                        ss.setPos(
                                headPos.x + offsetX,
                                headPos.y,
                                headPos.z + offsetZ
                        );

                        // 设置实体属性
                        ss.setIsCritical(critical);
                        ss.setOwner(playerIn);
                        ss.setColor(colorCode);
                        ss.setRoll(0);
                        ss.setDamage(damage);
                        ss.setDelay(10);

                        // 将实体添加到世界并绑定到玩家
                        worldIn.addFreshEntity(ss);
                        ss.startRiding(playerIn, true);

                        // 播放音效
                        playerIn.playSound(SoundEvents.TRIDENT_RETURN, 1f, 1.45F);
                    }
                }
            }
        });
    }
}
