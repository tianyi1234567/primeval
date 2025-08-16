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
public class Primevalt extends SpecialEffect {

    public Primevalt() {
        super(1, true, true);
    }

    @SubscribeEvent
    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.PRIMEVALT.getId())) {
            if (!(event.getEntity() instanceof Player)) return;
            if (!event.isSelected()) return;

            Player player = (Player) event.getEntity();

            addEffectSmart(player, MobEffects.NIGHT_VISION, 300, 1);
            addEffectSmart(player, MobEffects.FIRE_RESISTANCE, 300, 1);
            addEffectSmart(player, MobEffects.DAMAGE_RESISTANCE, 300, 1);
            addEffectSmart(player, MobEffects.DAMAGE_BOOST, 300, 3);
            addEffectSmart(player, MobEffects.CONDUIT_POWER, 300, 1);
            addEffectSmart(player, MobEffects.REGENERATION, 300, 1);
        }
    }
    private static void addEffectSmart(Player player, MobEffect effect, int duration, int amplifier) {
        MobEffectInstance existing = player.getEffect(effect);
        if (existing == null || existing.getDuration() < duration / 3) {
            player.addEffect(new MobEffectInstance(effect, duration, amplifier, true, false));
        }
    }
        }