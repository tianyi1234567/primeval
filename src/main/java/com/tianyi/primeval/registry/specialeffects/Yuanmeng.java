package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber
public class Yuanmeng extends SpecialEffect {

    public Yuanmeng() {super(7, false, false);}

    @SubscribeEvent
    public static void blaze(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.YUANMENG.getId())) {
            if (!(event.getEntity() instanceof Player)) {
                return;
            }

            if (!event.isSelected())
                return;

            Player player = (Player) event.getEntity();
            if (!player.hasEffect(MobEffects.HEALTH_BOOST)) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0));
            }
            addEffectSmart(player, MobEffects.REGENERATION, 300, 1);
            addEffectSmart(player, MobEffects.ABSORPTION, 300, 3);
        }
    }
    private static void addEffectSmart(Player player, MobEffect effect, int duration, int amplifier) {
        MobEffectInstance existing = player.getEffect(effect);
        if (existing == null || existing.getDuration() < duration / 3) {
            player.addEffect(new MobEffectInstance(effect, duration, amplifier, true, false));
        }
    }
}