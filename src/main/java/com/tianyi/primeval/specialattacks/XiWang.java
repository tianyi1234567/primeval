package com.tianyi.primeval.specialattacks;

import com.tianyi.primeval.Utils.*;
import com.tianyi.primeval.entity.XiWangSwordEnity;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class XiWang {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed)
    {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        double addDamage = 0;
        if (playerIn != null && playerIn.getAttribute(Attributes.ATTACK_DAMAGE) !=null){
            addDamage = playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.1;
        }
        doSlash(playerIn, colorCode, critical, damage +addDamage, speed);

    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (!playerIn.level().isClientSide()) {
            playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {
                Level worldIn = playerIn.level();



                int rank = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
                int count = 8;

                for(int i = 0; i < count; ++i) {
                    XiWangSwordEnity ss = new XiWangSwordEnity(PLEntiteRegristrys.xysd, worldIn);

                    worldIn.addFreshEntity(ss);


                    ss.setSpeed(speed);
                    ss.setIsCritical(critical);
                    ss.setOwner(playerIn);
                    ss.setColor(13683145);
                    ss.setRoll(0);
                    ss.setDamage(damage);





                    // force riding
                    ss.startRiding(playerIn, true);
                    ss.setDelay(i);

                    boolean isRight = ss.getDelay() % 2 == 0;
                    RandomSource random = worldIn.getRandom();

                    double xOffset = 2.5;
                    double yOffset = i*0.1;
                    double zOffset = random.nextFloat() * 0.5+i;

                    ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                    ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                    playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);                }
            });
        }
    }
}