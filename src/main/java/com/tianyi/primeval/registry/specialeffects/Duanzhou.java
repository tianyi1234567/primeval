package com.tianyi.primeval.registry.specialeffects;

import com.dinzeer.legendreliclib.lib.compat.slashblade.SwordRainGenerator;
import com.dinzeer.legendreliclib.lib.compat.slashblade.entity.swordrain.BaseSwordRainEntity;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.Drive.Experdrive;
import com.tianyi.primeval.util.SwordRainul;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Duanzhou extends SpecialEffect {
    // 用于追踪每个玩家的冷却时间
    private static final java.util.HashMap<java.util.UUID, Long> COOLDOWN_MAP = new java.util.HashMap<>();
    private static final long COOLDOWN_DURATION = 10L;  // 冷却
    public Duanzhou() {
        super(0, false, false);
    }

    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.DUANZHOU.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }
            Player player = (Player) event.getUser();
            java.util.UUID playerId = player.getUUID();
            long gameTime = player.level().getGameTime();
            
            // 检查冷却期
            if (COOLDOWN_MAP.containsKey(playerId)) {
                long lastTrigger = COOLDOWN_MAP.get(playerId);
                if (gameTime - lastTrigger < COOLDOWN_DURATION) {
                    return;
                }
            }
            
            // 更新冷却时间
            COOLDOWN_MAP.put(playerId, gameTime);
            
            // 执行效果
            RandomSource random = player.getRandom();
            for (BaseSwordRainEntity swordRain : SwordRainul.generateDefensiveSwordRain(event.getUser(), event.getUser().level(), 6,3,5.5)) {
                swordRain.setDelay(random.nextInt(35));
                swordRain.setDamage(random.nextInt(25));
            }
        }
    }
}
