package com.tianyi.primeval.util;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class PLSpecialEffectsTooltipHandler {
    
    /**
     * 检查指定的特殊效果是否有对应的翻译文本
     * @param se 特殊效果的ResourceLocation
     * @return 如果有对应的翻译文本则返回true，否则返回false
     */
    @OnlyIn(Dist.CLIENT)
    public static boolean hasTranslation(ResourceLocation se) {
        // 检查是否是我们的模组中的特殊效果
        if (!se.getNamespace().equals("primeval")) {
            return false;
        }
        
        // 构造翻译键
        String translationKey = "special_effect.primeval." + se.getPath();
        
        // 检查语言文件中是否存在对应的键
        return I18n.exists(translationKey);
    }
    
    /**
     * 获取指定特殊效果的工具提示文本
     * @param se 特殊效果的ResourceLocation
     * @return 工具提示文本列表
     */
    @OnlyIn(Dist.CLIENT)
    public static List<Component> getTooltip(ResourceLocation se) {
        List<Component> tooltip = new ArrayList<>();
        
        String translationKey = "special_effect.primeval." + se.getPath();
        MutableComponent component = Component.translatable(translationKey);
        tooltip.add(component);
        
        return tooltip;
    }
}