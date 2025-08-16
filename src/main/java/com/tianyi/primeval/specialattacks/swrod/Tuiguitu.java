package com.tianyi.primeval.specialattacks.swrod;

import com.tianyi.primeval.entity.SumonSwordPROEntity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Tuiguitu
{
    private static final double RANK_BONUS_MAX = 20.0; // 耀魂值加成上限
    private static final double REFINEMENT_BONUS_MAX = 20.0; // 精练值加成上限
    // 每10000耀魂值增加2点伤害
    private static final int RANK_POINT_PER_BONUS = 40000;
    private static final double RANK_BONUS_PER_INCREMENT = 2.0;
    // 每20点精练值增加0.5点伤害
    private static final int REFINEMENT_BONUS_PER_BONUS = 20;
    private static final double REFINEMENT_BONUS_PER_LEVEL = 1.0;

    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed)
    {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed)
    {
        if (playerIn.level().isClientSide()) return;

        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
            Level worldIn = playerIn.level();

            // 获取耀魂值加成
            int rankPoint = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT)
                    .map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);

            // 获取精练值加成
            ItemStack blade = playerIn.getMainHandItem();
            int refinementLevel = blade.getBaseRepairCost();

            // 计算额外伤害加成
            double rankBonus = Math.min((rankPoint / RANK_POINT_PER_BONUS) * RANK_BONUS_PER_INCREMENT, RANK_BONUS_MAX);
            double refinementBonus = Math.min(refinementLevel / REFINEMENT_BONUS_PER_BONUS * REFINEMENT_BONUS_PER_LEVEL, REFINEMENT_BONUS_MAX);


            // 应用伤害加成
            double finalDamage = damage + rankBonus + refinementBonus;

            int count = 5;
            for (int i = 0; i < count; i++)
            {
                SumonSwordPROEntity ss = new SumonSwordPROEntity(PLEntiteRegristrys.qj, worldIn);

                worldIn.addFreshEntity(ss);

                ss.setSpeed(speed);
                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(0);
                ss.setDamage(damage);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(5 + i);

                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();

                double xOffset = random.nextDouble() * 2.5 * (isRight ? 1 : -1);
                double yOffset = random.nextFloat() * 5;
                double zOffset = random.nextFloat() * -5;

                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }
}
