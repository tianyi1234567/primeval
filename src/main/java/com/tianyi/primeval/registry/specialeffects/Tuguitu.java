package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.swrod.*;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Tuguitu extends SpecialEffect {
    // 添加冷却时间常量和最后触发时间变量
    private static final long COOLDOWN = 1500; // 冷却时间（毫秒）
    private static long lastTriggerTime = 0; // 最后一次触发时间

    public Tuguitu() {
        super(1, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.TUGUITU.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();
            long currentTime = System.currentTimeMillis();

            // 检查冷却时间
            if (currentTime < lastTriggerTime + COOLDOWN) {
                return;
            }

            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.TUGUITU.get(), level)) {
                // 随机选择一个效果触发
                int effectChoice = random.nextInt(3);
                switch (effectChoice) {
                    case 0:
                        Tuiguitu.doSlash(player, false, 40, 3);
                        break;
                    case 1:
                        SwordRainSOM.doSlash(player, false, 40, 2);
                        break;
                    case 2:
                        XXswordSOM.doXXsword(player, false, 40, 2);
                        break;
                }
                // 更新最后触发时间
                lastTriggerTime = currentTime;
            }
        }
    }
}