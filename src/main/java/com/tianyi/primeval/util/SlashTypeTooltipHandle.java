package com.tianyi.primeval.util;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mod.EventBusSubscriber
public class SlashTypeTooltipHandle {

    public static Map<ResourceLocation, String> slashTypesTooltip = new HashMap<>();


    @SubscribeEvent
    public static void reload(AddReloadListenerEvent event){
        slashTypesTooltip.clear();
//        slashTypesTooltip.put(
//                ResourceLocation.parse("rough_blade:starry_galaxy"),
//                "rough_blade_slash_type_fate"
//        );
//        slashTypesTooltip.put(
//                ResourceLocation.parse("rough_blade:the_waning_moon"),
//                "rough_blade_slash_type_truth"
//        );
    }
    public static boolean hasTranslation(ResourceLocation se){
        return slashTypesTooltip.containsKey(se);
    }
    public static String getTranslation(ResourceLocation se){
        return slashTypesTooltip.get(se);
    }
    public static List<Component> getTooltip(ResourceLocation se){
        return TooltipUtil.sprit(Component.translatable(SlashTypeTooltipHandle.getTranslation(se)));
    }
}