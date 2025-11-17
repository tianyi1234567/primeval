package com.tianyi.primeval.registry.specialeffects;

import com.dinzeer.legendreliclib.lib.compat.slashblade.SwordRainGenerator;
import com.dinzeer.legendreliclib.lib.compat.slashblade.entity.swordrain.BaseSwordRainEntity;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.swrod.YingYu;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Yingyu extends SpecialEffect {

    public Yingyu() {
        super(10, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.YINGYU.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.YINGYU.get(), level)) {
                if (random.nextInt(100) >= 65) {
                    for (BaseSwordRainEntity swordRain : SwordRainGenerator.generateFivePointSwordRain(event.getUser(), event.getUser().level(), 5)) {
                        swordRain.setDelay(random.nextInt(20));//延迟释放的时间
                    }
                }
            }
        }
    }
}