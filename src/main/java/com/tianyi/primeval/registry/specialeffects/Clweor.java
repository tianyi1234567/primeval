package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.Utils.RandomColor;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.*;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class Clweor extends SpecialEffect {

    public Clweor() {
        super(0, false, false);
    }

    @Mod.EventBusSubscriber
    public class RainBow extends SpecialEffect {
        public RainBow() {
            super(0, true, true);
        }

        private static final Random r = new Random();

        @SubscribeEvent
        public static void blaze(SlashBladeEvent.UpdateEvent event) {
            ISlashBladeState state = event.getSlashBladeState();
            if (state.hasSpecialEffect(PLSpecialEffectsRegistry.CLWEOR.getId())) {
                if (!(event.getEntity() instanceof Player)) {
                    return;
                }
                if (!event.isSelected())
                    return;
                Player player = (Player) event.getEntity();
                state.setColorCode(
                        RandomColor.returnner(r.nextInt(94))
                );

            }
        }
    }
}