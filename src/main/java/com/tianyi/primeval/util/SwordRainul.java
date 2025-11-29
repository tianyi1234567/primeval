package com.tianyi.primeval.util;

import com.dinzeer.legendreliclib.lib.compat.slashblade.SlashBladeCompatEntities;
import com.dinzeer.legendreliclib.lib.compat.slashblade.SwordRainGenerator;
import com.dinzeer.legendreliclib.lib.compat.slashblade.entity.swordrain.BaseSwordRainEntity;
import com.dinzeer.legendreliclib.lib.util.slashblade.SlashBladeUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SwordRainul extends SwordRainGenerator {//从牢丁那里扣过来的幻影剑

    private static BaseSwordRainEntity createSwordRainAtPosition(Level world, Entity owner, Vec3 position, int Delay, int color, int damage) {
        BaseSwordRainEntity swordRain = new BaseSwordRainEntity(SlashBladeCompatEntities.BASE_SWORD_RAIN.get(), world);
        swordRain.setPos(position.x, position.y, position.z);
        swordRain.setOwner(owner);
        swordRain.setDamage((double)damage);
        swordRain.setDelay(Delay);
        swordRain.setColor(color);
        swordRain.setForward(true);
        return swordRain;
    }

    private static void setSwordRainDirection(BaseSwordRainEntity swordRain, Vec3 direction) {
        double horizontalDistance = Math.sqrt(direction.x * direction.x + direction.z * direction.z);
        float yaw = (float)Math.toDegrees(Math.atan2(direction.x, direction.z));
        float pitch = (float)Math.toDegrees(Math.atan2(direction.y, horizontalDistance));
        swordRain.setYRot(yaw);
        swordRain.setXRot(pitch);
    }
    public static List<BaseSwordRainEntity> generateDefensiveSwordRain(LivingEntity player, Level world, int count, double radius, double height) {
        List<BaseSwordRainEntity> swordRains = new ArrayList();
        Vec3 playerPos = player.position();

        for(int i = 0; i < count; ++i) {
            double angle = (Math.PI * 2D) * (double)i / (double)count;
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);
            Vec3 position = playerPos.add(xOffset, height, zOffset);
            BaseSwordRainEntity swordRain = createSwordRainAtPosition(world, player, position, 1, SlashBladeUtil.getColorCode(player.getMainHandItem()), 10);
            Vec3 outwardDirection = position.subtract(playerPos).normalize();
            setSwordRainDirection(swordRain, outwardDirection);
            swordRains.add(swordRain);
            world.addFreshEntity(swordRain);
        }

        return swordRains;
    }
}
