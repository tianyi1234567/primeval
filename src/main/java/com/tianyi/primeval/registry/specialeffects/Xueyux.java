package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.mclib.Utils.SlashBlade.SlashEffectUtils;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.*;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Xueyux extends SpecialEffect {

    public Xueyux() {
        super(30, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.XUEYUX.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            int level = player.experienceLevel;
            RandomSource random = player.getRandom();
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.XUEYUX.get(), level)) {
                if (random.nextInt(100) >= 65) {
                    player.heal(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.XUEYUX.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.XUEYUX.get(), level)) {
                if (random.nextInt(100)>=70){
                    SlashEffect.SakuraEnd.doSlash(player, 145F, Vec3.ZERO, false, false, 0.75);
                    SlashEffect.SakuraEnd.doSlash(player, 45F, Vec3.ZERO, false, false, 0.75);
                }
            }
        }
    }
}
