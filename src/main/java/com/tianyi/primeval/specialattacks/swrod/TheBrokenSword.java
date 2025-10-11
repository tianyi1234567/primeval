package com.tianyi.primeval.specialattacks.swrod;


import com.tianyi.primeval.entity.TheBreakSwordPlus;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.List;
public class TheBrokenSword {


    public static void doSlash(LivingEntity livingEntity, float damage, float speed) {
        Level world = livingEntity.level();

        Vec3 _center = livingEntity.position();
        List<TheBreakSwordPlus> entitiesOfClass = world.getEntitiesOfClass(TheBreakSwordPlus.class, new AABB(_center, _center).inflate(3));
        for (TheBreakSwordPlus entity : entitiesOfClass) {
            if (entity.getOwner() == livingEntity &&entity.tickCount>10) {
                entity.discard();
            }
        }
        for (int i = 0; i < 1; i++) {
            TheBreakSwordPlus sword = new TheBreakSwordPlus(PLEntiteRegristrys.TBS, world);
            sword.setFormationParams(i % 6); // 6个为一组
            sword.setOwner(livingEntity);
            sword.setDamage(damage);
           // sword.setNoGravity(true);
            sword.setInvulnerable(true);
            sword.setColor( livingEntity.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(state -> state.getColorCode()).orElse(0xFF3333FF));
            sword.startRiding(livingEntity, true);


            sword.setPos(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            world.addFreshEntity(sword);
        }
    }
}
