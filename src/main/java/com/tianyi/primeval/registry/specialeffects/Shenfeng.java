package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber
public class Shenfeng extends SpecialEffect {

    public Shenfeng() {
        super(30, false, false);
    }

    @SubscribeEvent
    public static void blaze(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.SHENFENG.getId())) {
            if (!(event.getEntity() instanceof Player)) {
                return;
            }

            if (!event.isSelected())
                return;

            Player player = (Player) event.getEntity();
            int level = player.experienceLevel;
            if (!player.hasEffect(MobEffects.HEALTH_BOOST)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 4));
            }
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 300, 1));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1));
        }
    }
}