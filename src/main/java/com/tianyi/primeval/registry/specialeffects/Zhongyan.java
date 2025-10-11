package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Zhongyan extends SpecialEffect {

    public Zhongyan() {
        super(30, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.ZHONGYAN.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.ZHONGYAN.get(), level)) {
                if (random.nextInt(100)>=60){
                    SlashEffect.SakuraEnd.doSlash(player, 145F, Vec3.ZERO, false, false, 1);
                    SlashEffect.SakuraEnd.doSlash(player, 45F, Vec3.ZERO, false, false, 1);
                }
            }
        }
    }
}
