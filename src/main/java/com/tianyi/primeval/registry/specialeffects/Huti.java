package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.*;
import com.tianyi.primeval.specialattacks.Drive.CEO;
import com.tianyi.primeval.specialattacks.Drive.Experdrive;
import com.tianyi.primeval.specialattacks.swrod.*;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Huti extends SpecialEffect {

    public Huti() {
        super(1, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.HUTI.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.HUTI.get(), level)) {
//                if (random.nextInt(100) >= 80) {
//                    XXswordSOM.doXXsword(player, false, 20, 1f);
//                }
//                if (random.nextInt(100) >= 80) {
//                    SwordRainSOM.doSlash(player, false, 15, 1f);
//                }
                Experdrive.doSlash(player,0F, 60.0F, 2.0F, 2156543, 1.0F);
                }
            }
        }
    }