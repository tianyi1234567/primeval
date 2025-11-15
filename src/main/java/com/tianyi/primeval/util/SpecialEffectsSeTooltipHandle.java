package com.tianyi.primeval.util;

import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mod.EventBusSubscriber
public class SpecialEffectsSeTooltipHandle {

    public static Map<ResourceLocation, String> specialEffectsSeTooltip = new HashMap<>();

    @SubscribeEvent
    public static void reload(AddReloadListenerEvent event){
        specialEffectsSeTooltip.clear();
        for (var se : PLSpecialEffectsRegistry.REGISTRY_KEY2.getEntries()){
            add(se.getId());
        }
//        specialEffectsSeTooltip.put(
//                PLSpecialEffectsRegistry.StarrySky.getId(),
//                "rough_blade_se_tooltip_starry_sky"
//        );
//        specialEffectsSeTooltip.put(
//                PLSpecialEffectsRegistry.The_Waning_Moon.getId(),
//                "rough_blade_se_tooltip_the_waning_moon"
//        );
    }
    public static void add(ResourceLocation se){
        specialEffectsSeTooltip.put(se, "primeval_se_tooltip_"+se.getPath());
    }
    public static boolean hasTranslation(ResourceLocation se){
        return specialEffectsSeTooltip.containsKey(se);
    }
    public static String getTranslation(ResourceLocation se){
        return specialEffectsSeTooltip.get(se);
    }
    public static List<Component> getTooltip(ResourceLocation se){
        return TooltipUtil.sprit(Component.translatable(SpecialEffectsSeTooltipHandle.getTranslation(se)));
    }
}