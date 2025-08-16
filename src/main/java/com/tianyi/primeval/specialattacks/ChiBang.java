package com.tianyi.primeval.specialattacks;

import com.tianyi.primeval.entity.ChiBangEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChiBang {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed,int cc1,int cc2)
    {
        int colorCode = cc1;
        int colorCode2 = cc2;
        doSlash(playerIn, colorCode,colorCode2, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, int colorCode3,  boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();

        int rank = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        int count = 6;
        double radius = 1.0; // Radius for the circular spread
        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent
                (
                        (state) -> {
                            for (int i = 1; i < count / 1; i++) {
                                ChiBangEnity ss = new ChiBangEnity(PLEntiteRegristrys.cbsd, worldIn);
                                worldIn.addFreshEntity(ss);

                                ss.setSpeed(speed);
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);
                                ss.setColor(colorCode);
                                ss.setRoll(0);
                                ss.setDamage(damage);
                                ss.startRiding(playerIn, true);
                                ss.setDelay(20);

                                // Calculate angle for clockwise and counterclockwise distribution
                                double angleClockwise = Math.toRadians(i * (360.0 / (count / 2)));
                                double angleCounterclockwise = Math.toRadians(-i * (360.0 / (count / 2)));

                                // Set positions based on the angles and radius
                                double yOffset = i * 0.005 + 0.5;
                                double xClockwise = Math.cos(angleClockwise) * radius;
                                double zClockwise = Math.sin(angleClockwise) * radius;

                                // Set the position for clockwise entities
                                ss.setPos(playerIn.position().add(xClockwise, yOffset, zClockwise));
                                ss.setOffset(new Vec3(xClockwise, yOffset, zClockwise));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }

                            for (int i = 1; i < count / 1; i++) {
                                ChiBangEnity ss = new ChiBangEnity(PLEntiteRegristrys.cbsd, worldIn);
                                worldIn.addFreshEntity(ss);

                                ss.setSpeed((float) (speed * 1.2));
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);
                                ss.setColor(colorCode3);
                                ss.setRoll(0);
                                ss.setDamage(damage);
                                ss.startRiding(playerIn, true);
                                ss.setDelay(30);

                                // Calculate positions using angles for the second half
                                double angleCounterclockwise = Math.toRadians(i * (360.0 / (count / 2)));

                                double yOffset = i * 0.005 + 0.5;
                                double xCounterclockwise = Math.cos(angleCounterclockwise) * radius;
                                double zCounterclockwise = Math.sin(angleCounterclockwise) * radius;

                                // Set position for counterclockwise entities
                                ss.setPos(playerIn.position().add(xCounterclockwise, yOffset, -zCounterclockwise)); // Negative Z for the opposite direction
                                ss.setOffset(new Vec3(xCounterclockwise, yOffset, -zCounterclockwise));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }
                        }
                );
    }

}