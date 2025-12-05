package com.tianyi.primeval.registry.specialeffects;

import com.dinzeer.legendreliclib.lib.compat.slashblade.entity.swordrain.BaseSwordRainEntity;
import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.util.Drivesul;
import com.tianyi.primeval.util.SwordRainul;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Duanye extends SpecialEffect {
    // 用于追踪每个玩家的冷却时间和攻击计数
    private static final java.util.HashMap<java.util.UUID, Long> EFFECT_COOLDOWN = new java.util.HashMap<>();  // 冷却时间
    private static final java.util.HashMap<java.util.UUID, Integer> ATTACK_COUNT = new java.util.HashMap<>();    // 当前攻击次数
    private static final java.util.HashMap<java.util.UUID, Long> LAST_ATTACK_TICK = new java.util.HashMap<>();   // 上次攻击的游戏tick
    
    private static final long COOLDOWN_DURATION = 10L;   // 效果A冷却时长（tick）
    private static final int MAX_ATTACKS = 3;            // 最多连续3次
    private static final long ATTACK_INTERVAL = 5L;      // 每次攻击间隔（tick）
    public Duanye() {
        super(0, false, false);
    }
    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(PLSpecialEffectsRegistry.DUANYE.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }
            Player player = (Player) event.getUser();
            java.util.UUID playerId = player.getUUID();
            long gameTime = player.level().getGameTime();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(PLSpecialEffectsRegistry.YINYANGJIAN.get(), level)) {//第一次刀光
                SlashEffect.SakuraEnd.doSlash(player, event.getRoll() - 90F, Vec3.ZERO, false, false, 0.7);
            }
            // 检查冷却期
            if (EFFECT_COOLDOWN.containsKey(playerId)) {
                long lastTrigger = EFFECT_COOLDOWN.get(playerId);
                if (gameTime - lastTrigger < COOLDOWN_DURATION) {
                    return;
                }
            }
            
            // 触发效果A：开始连续攻击
            EFFECT_COOLDOWN.put(playerId, gameTime);
            ATTACK_COUNT.put(playerId, 0);
            LAST_ATTACK_TICK.put(playerId, gameTime);
        }
    }
    
    // 每个tick检查是否需要执行下一次攻击
    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.level().isClientSide) {
                return;
            }
            
            java.util.UUID playerId = player.getUUID();
            long gameTime = player.level().getGameTime();
            
            // 检查是否有待执行的攻击
            if (ATTACK_COUNT.containsKey(playerId)) {
                int currentCount = ATTACK_COUNT.get(playerId);
                long lastAttack = LAST_ATTACK_TICK.get(playerId);
                
                if (currentCount < MAX_ATTACKS && gameTime - lastAttack >= ATTACK_INTERVAL) {
                    // 执行一次攻击
                    AttackManager.doSlash(
                        player,
                        360.0F + 360.0F * player.getRandom().nextFloat(),
                        AttackManager.genRushOffset(player),
                        false,
                        false,
                        0.7F
                    );
                    
                    ATTACK_COUNT.put(playerId, currentCount + 1);
                    LAST_ATTACK_TICK.put(playerId, gameTime);
                } else if (currentCount >= MAX_ATTACKS) {
                    // 三次攻击已完成，清除追踪
                    ATTACK_COUNT.remove(playerId);
                    LAST_ATTACK_TICK.remove(playerId);
                }
            }
        }
    }
}
