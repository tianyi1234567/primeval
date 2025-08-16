package com.tianyi.primeval.mclib.Utils.SlashBlade;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;


public class SlashEffectUtils {
    public static boolean hasSpecialEffect(ItemStack stack, String effect) {
        CompoundTag tag = stack.getOrCreateTag(); // 获取或创建NBT标签

        if (tag.contains("bladeState")) { // 检查是否存在ForgeCaps标签
            CompoundTag forgeCaps = tag.getCompound("bladeState");

            if (forgeCaps.contains("SpecialEffects")) { // 检查SpecialEffects标签
                ListTag specialEffects = forgeCaps.getList("SpecialEffects", 8); // 8表示String类型
                for (int i = 0; i < specialEffects.size(); i++) {
                    String currentEffect = specialEffects.getString(i);
                    if (effect.equals(currentEffect)) {
                        return true; // 找到了指定的特殊效果
                    }
                }
            }
        }
        return false; // 没有找到指定的特殊效果
    }
    public static int addslashbladesaDamage(LivingEntity player,int damageex) {
        ItemStack stack= player.getMainHandItem();
        CompoundTag tag = stack.getOrCreateTag(); // 获取或创建NBT标签
        double add=damageex*(
                5+ addslashbladesaDamageKey(player)
        );
        return (int)add;
    }
    public static int addslashbladesaDamageKey(LivingEntity player) {
        if (player.getHealth() > 3) {
            player.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC_KILL)), 0);
            player.setHealth(player.getHealth() - 1);
        } else {
            player.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC_KILL)), 1);
        }
        return 0;
    }
}