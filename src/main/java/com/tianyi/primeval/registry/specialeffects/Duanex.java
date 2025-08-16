package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.*;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.tianyi.primeval.Primeval.MODID;

@Mod.EventBusSubscriber
public class Duanex extends SpecialEffect {

    public Duanex() {
        super(30, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.DUANEX.getId())) {
            LivingEntity user = event.getUser();
            if (!(user instanceof Player)) return;

            // 第一次伤害配置（原配置保留）
            int firstDelay = 8;  // 20 ticks = 1秒
            float firstDamageMultiplier = 0.9f;

            // 第二次伤害配置（新增）
            int secondDelay = 16; // 30 ticks = 1.5秒
            float secondDamageMultiplier = 0.9f; // 可调整伤害系数

            // 第一次延迟伤害
            Primeval.queueServerWork(firstDelay, () -> {
                if (user.isAlive() && !user.level().isClientSide()) {
                    SlashEffect.SakuraEnd.doSlash(user,
                            event.getRoll() - 0F,
                            Vec3.ZERO,
                            false,
                            false,
                            event.getDamage() * firstDamageMultiplier);
                }
            });

            // 第二次延迟伤害
            Primeval.queueServerWork(secondDelay, () -> {
                if (user.isAlive() && !user.level().isClientSide()) {
                    SlashEffect.SakuraEnd.doSlash(user,
                            event.getRoll() - 0F,
                            Vec3.ZERO,
                            false,
                            false,
                            event.getDamage() * secondDamageMultiplier);
                }
            });
        }
    }
}