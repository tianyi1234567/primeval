package com.tianyi.primeval.registry.specialeffects;

import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.specialattacks.swrod.TheBrokenSword;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.tianyi.primeval.Primeval.MODID;

@Mod.EventBusSubscriber
public class Yuangaung extends SpecialEffect {

    // 每分钟触发一次 = 1200 ticks
    private static final int TRIGGER_INTERVAL = 1200;

    public Yuangaung() {
        super(10, false, false);
    }
    @SubscribeEvent
    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.YUANGUANG.getId())) {
            if (!(event.getEntity() instanceof Player)) return;
            if (!event.isSelected()) return;

            Player player = (Player) event.getEntity();
            Level level = player.level();

            // 只在服务器端执行
            if (level.isClientSide) return;

            // 获取玩家的持久化数据
            CompoundTag persistentData = player.getPersistentData();

            // 获取当前游戏时间和上次触发时间
            long currentTime = level.getGameTime();
            long lastTriggerTime = persistentData.getLong("Yuangaung_LastTrigger");

            // 判断是否满足触发间隔
            if (currentTime - lastTriggerTime >= TRIGGER_INTERVAL) {
                // 更新上次触发时间
                persistentData.putLong("Yuangaung_LastTrigger", currentTime);

                // 执行技能效果
                TheBrokenSword.doSlash(player, 12f, 1.6f);
            }
        }
    }
}