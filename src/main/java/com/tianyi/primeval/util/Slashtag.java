package com.tianyi.primeval.util;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

//替换nbt的轮子
public class Slashtag {

    /**
     * 检测并替换手持物品的NBT数据
     * @param player 玩家对象
     * @param nbtKey 要检测的NBT键
     * @param originalValue 原始值
     * @param newValue 要替换的新值
     * @return 是否成功替换了NBT数据
     */
    public static boolean checkAndReplaceNBT(Player player, String nbtKey, String originalValue, String newValue) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }

        CompoundTag tag;
        if (stack.hasTag()) {
            tag = stack.getTag();
        } else {
            tag = new CompoundTag();
            stack.setTag(tag);
        }

        if (tag.contains(nbtKey) && originalValue.equals(tag.getString(nbtKey))) {
            tag.putString(nbtKey, newValue);
            stack.setTag(tag);
            return true;
        }

        return false;
    }
    @Deprecated
    public static boolean setNBT(Player player, String nbtKey, String value) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }

        return stack.getCapability(ItemSlashBlade.BLADESTATE).map(state -> {
            if ("TextureName".equals(nbtKey)) {
                state.setTexture(new ResourceLocation(value));
                return true;
            } else if ("SpecialAttackType".equals(nbtKey)) {
                return setNBTDirect(stack, nbtKey, value);
            }
            return false;
        }).orElse(false);
    }
    
    /**
     * 直接修改 NBT 数据（ForgeCaps->Parent 和 bladeState）
     * @param stack 物品堆栈
     * @param nbtKey NBT键
     * @param value NBT值
     * @return 是否成功修改
     */
    private static boolean setNBTDirect(ItemStack stack, String nbtKey, String value) {
        CompoundTag nbt = stack.getOrCreateTag();
        
        // 修改 ForgeCaps->Parent 中的值
        CompoundTag forgeCaps;
        if (nbt.contains("ForgeCaps") && nbt.get("ForgeCaps") instanceof CompoundTag) {
            forgeCaps = nbt.getCompound("ForgeCaps");
        } else {
            forgeCaps = new CompoundTag();
            nbt.put("ForgeCaps", forgeCaps);
        }
        
        CompoundTag parent;
        if (forgeCaps.contains("Parent") && forgeCaps.get("Parent") instanceof CompoundTag) {
            parent = forgeCaps.getCompound("Parent");
        } else {
            parent = new CompoundTag();
            forgeCaps.put("Parent", parent);
        }
        
        parent.putString(nbtKey, value);
        
        // 修改 bladeState 中的值（为了防止tag自我欺骗修改）
        if (nbt.contains("bladeState") && nbt.get("bladeState") instanceof CompoundTag) {
            CompoundTag bladeState = nbt.getCompound("bladeState");
            bladeState.putString(nbtKey, value);
        }
        
        return true;
    }
    
    /**
     * 直接设置手持物品的TextureName
     * @param player 玩家对象
     * @param textureName 纹理名称（ResourceLocation格式，如 "primeval:model/tianyi/duandai_ye.png"）
     * @return 是否成功设置了纹理
     */
    public static boolean setTextureName(Player player, String textureName) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }

        //setTexture接口
        return stack.getCapability(ItemSlashBlade.BLADESTATE).map(state -> {
            state.setTexture(new ResourceLocation(textureName));
            return true;
        }).orElse(false);
    }
    
    /**
     * 直接设置手持物品的SpecialAttackType
     * @param player 玩家对象
     * @param attackType 攻击类型（ResourceLocation格式，如 "primeval:duandai_ye"）
     * @return 是否成功设置了攻击类型
     */
    public static boolean setSpecialAttackType(Player player, String attackType) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }

        //虽然不知道为什么SA的接口要叫setSlashArtsKey，但是已经找到正确的接口了
        return stack.getCapability(ItemSlashBlade.BLADESTATE).map(state -> {
            state.setSlashArtsKey(new ResourceLocation(attackType));
            return true;
        }).orElse(false);
    }
    public static boolean setSpecialEffects(Player player, String effectIds) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return false;
        }

        String[] effects = effectIds.split(",");
        ListTag effectList = new ListTag();
        
        for (String effect : effects) {
            String trimmedEffect = effect.trim();
            if (!trimmedEffect.isEmpty()) {
                effectList.add(net.minecraft.nbt.StringTag.valueOf(trimmedEffect));
            }
        }

        CompoundTag nbt = stack.getOrCreateTag();
        if (nbt.contains("bladeState") && nbt.get("bladeState") instanceof CompoundTag) {
            CompoundTag bladeState = nbt.getCompound("bladeState");
            bladeState.put("SpecialEffects", effectList);
        }

        return stack.getCapability(ItemSlashBlade.BLADESTATE).map(state -> {
            state.setSpecialEffects(effectList);
            return true;
        }).orElse(false);
    }
}
