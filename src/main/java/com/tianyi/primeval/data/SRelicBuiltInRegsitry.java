package com.tianyi.primeval.data;

import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.registry.PLComboRegsitry;
import com.tianyi.primeval.registry.PLSpecialEffectsRegistry;
import com.tianyi.primeval.registry.PLslashArtRegsitry;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SRelicBuiltInRegsitry {
    //示例1
    //public static final ResourceKey<SlashBladeDefinition> START=register("none_blue");
    //示例2
    //public static final ResourceKey<SlashBladeDefinition> NONE=register("none_red");
    //大风来
    public static final ResourceKey<SlashBladeDefinition> WIND=register("big_wind");
    //光之舞
    public static final ResourceKey<SlashBladeDefinition> DANCE_LIGHT=register("dance_of_light");
    //寂虚之光
    public static final ResourceKey<SlashBladeDefinition> JIXU=register("jixu");
    //奇迹
    public static final ResourceKey<SlashBladeDefinition> MIRACLE=register("miracle");
    //墨萤_5形
    public static final ResourceKey<SlashBladeDefinition> MOYING=register("moying");
    //樱之舞
    public static final ResourceKey<SlashBladeDefinition> SUKURA=register("sakura_dance");
    //清风拂尘
    public static final ResourceKey<SlashBladeDefinition> STRONG_WIND=register("strong_wind");
    //星之坠
    public static final ResourceKey<SlashBladeDefinition> FALLING_STAR=register("the_falling_star");
    //空白
    public static final ResourceKey<SlashBladeDefinition> VOID=register("void");
    //阳
    public static final ResourceKey<SlashBladeDefinition> YANG=register("yang");
    //阴
    public static final ResourceKey<SlashBladeDefinition> YIN=register("yin");
    //阴阳纵横
    public static final ResourceKey<SlashBladeDefinition> YINYANG=register("yinyang");
    //血雨腥风
    public static final ResourceKey<SlashBladeDefinition> XUESE =register("xuese");
    //裁决
    public static final ResourceKey<SlashBladeDefinition> CAIJUE =register("caijue");
    //始源神录
    public static final ResourceKey<SlashBladeDefinition> SHIYUANEX =register("shiyuanex");
    //始源星
    public static final ResourceKey<SlashBladeDefinition> SHIYUAN =register("shiyuan");
    //基础原梦七件套
    public static final ResourceKey<SlashBladeDefinition> ZHENSHI =register("real");
    public static final ResourceKey<SlashBladeDefinition> LIFA =register("calendar_system");
    public static final ResourceKey<SlashBladeDefinition> YANYU =register("speak");
    public static final ResourceKey<SlashBladeDefinition> JIAZHI =register("value");
    public static final ResourceKey<SlashBladeDefinition> GUIZE =register("rule");
    public static final ResourceKey<SlashBladeDefinition> YIYI =register("significance");
    public static final ResourceKey<SlashBladeDefinition> ZUNYAN =register("dignity");
    //原梦 启示
    public static final ResourceKey<SlashBladeDefinition> REVE =register("revelation");
    //无形之水
    public static final ResourceKey<SlashBladeDefinition> WEATERK =register("weaterk");
    //折剑
    public static final ResourceKey<SlashBladeDefinition> ZHEJIAN =register("zhejian");
    //烈焰焚心
    public static final ResourceKey<SlashBladeDefinition> LIEYAN =register("lieyan");
    //希望有羽毛和翅膀
    public static final ResourceKey<SlashBladeDefinition> DAY =register("day");
    //源心一剑
    public static final ResourceKey<SlashBladeDefinition> DERIVE =register("derive");
    //血雨红樱
    public static final ResourceKey<SlashBladeDefinition> HONGY =register("hongy");
    //荒芜苦痛
    public static final ResourceKey<SlashBladeDefinition> HUANGWU =register("huangwu");
    //花之舞
    public static final ResourceKey<SlashBladeDefinition> HUAZHIWU =register("huazhiwu");
    //荧光—炫彩
    public static final ResourceKey<SlashBladeDefinition> XUANCAI =register("xuancai");
    //极难
    public static final ResourceKey<SlashBladeDefinition> JINAN =register("jinan");
    //至原刀——白楼
    public static final ResourceKey<SlashBladeDefinition> BAILOU =register("bailou");
    //至原刀——楼观
    public static final ResourceKey<SlashBladeDefinition> LOUGUAN =register("louguan");
    //双生原刃——白楼一现
    public static final ResourceKey<SlashBladeDefinition> BAILOUEX =register("bailouex");
    //双生原刃——楼观千变
    public static final ResourceKey<SlashBladeDefinition> LOUGUANEX =register("louguanex");
    //空白大花袄版
    public static final ResourceKey<SlashBladeDefinition> VOIDEX =register("viodex");
    //源初
    public static final ResourceKey<SlashBladeDefinition> PYQG =register("pyqg");
    //缎带
    public static final ResourceKey<SlashBladeDefinition> DUANDAI =register("duandai");
    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
        //bootstrap.register(START,
        //        new SlashBladeDefinition(Srelic.prefix("none_blue"),
        //                RenderDefinition.Builder.newInstance()
        //                        .textureName(Srelic.prefix("model/stairail/none_blue.png"))//贴图地址
        //                        .modelName(Srelic.prefix("model/stairail/none.obj"))//模型地址
        //                        .effectColor(2003199)//刀光颜色
        //                        .build(),
        //                PropertiesDefinition.Builder.newInstance()
        //                        .baseAttackModifier(15)//伤害
        //                        .defaultSwordType(List.of(SwordType.BEWITCHED))//刀的类型
        //                        .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())//这是SA
        //                        .addSpecialEffect(PLSpecialEffectsRegistry.FLAMEROSION.getId())//这是SE
        //                        .maxDamage(80)//耐久
        //                        .build(),
        //                List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),//附魔，最后一个不要打句号
        //                        new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
        //                        new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
        //                )));

        //bootstrap.register(NONE,
        //        new SlashBladeDefinition(Srelic.prefix("none_red"),
        //                RenderDefinition.Builder.newInstance()
        //                        .textureName(Srelic.prefix("model/stairail/none_red.jpg"))
        //                        .modelName(Srelic.prefix("model/stairail/none.obj"))
        //                        .effectColor(13504014)
        //                        .build(),
        //                PropertiesDefinition.Builder.newInstance()
        //                        .baseAttackModifier(21)
        //                        .defaultSwordType(List.of(SwordType.BEWITCHED))
        //                        .slashArtsType(SRslashArtRegsitry.FORDEADCRY.getId())
        //                        .maxDamage(80)
        //                        .build(),
        //                List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 9),
        //                        new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
        //                        new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 7),
        //                       new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 4),
        //                        new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1)
        //                )));
        bootstrap.register(WIND,
                new SlashBladeDefinition(Primeval.prefix("big_wind"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/strong_wind.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/dance_of_light.obj"))//模型地址
                                .effectColor(8058623)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))//刀的类型
                                .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())//SA
                                .maxDamage(1122)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 3),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.PROJECTILE_PROTECTION), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(DANCE_LIGHT,
                new SlashBladeDefinition(Primeval.prefix("dance_of_light"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/dance_of_light.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/dance_of_light.obj"))//模型地址
                                .effectColor(16187322)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.GUANGLAN.getId())//SA
                                .maxDamage(1024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(JIXU,
                new SlashBladeDefinition(Primeval.prefix("jixu"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/jixu.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/jixu.obj"))//模型地址
                                .effectColor(11908533)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(32)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.HUANYING_GAI.getId())//SA
                                .maxDamage(2222)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 2),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.PROJECTILE_PROTECTION), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(MIRACLE,
                new SlashBladeDefinition(Primeval.prefix("miracle"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/miracle.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/miracle.obj"))//模型地址
                                .effectColor(9961330)//颜色
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(56)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.BIGDRIVE_VERTICAL.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.PRIMEVALT.getId())//这是SE
                                .addSpecialEffect(PLSpecialEffectsRegistry.PRIMEVALTY.getId())
                                .maxDamage(2024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 20),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )));
        bootstrap.register(MOYING,
                new SlashBladeDefinition(Primeval.prefix("moying"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/moying.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/moying.obj"))//模型地址
                                .effectColor(15662966)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.LIANZHAN.getId())//SA
                                .maxDamage(10240)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 40),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.PROJECTILE_PROTECTION), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 2)
                        )));
        bootstrap.register(SUKURA,
                new SlashBladeDefinition(Primeval.prefix("sakura_dance"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/sakura_dance.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/sakura_dance.obj"))//模型地址
                                .effectColor(16759529)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.YINHUAL.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.YINGYU.getId())
                                .maxDamage(520)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 2)
                        )));
        bootstrap.register(STRONG_WIND,
                new SlashBladeDefinition(Primeval.prefix("strong_wind"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/strong_wind.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/strong_wind.obj"))//模型地址
                                .effectColor(8058623)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(24)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.WIND_IS_VLOWING.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHENFENG.getId())
                                .maxDamage(1444)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 12) ,//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLAST_PROTECTION), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)

                        )));
        bootstrap.register(FALLING_STAR,
                new SlashBladeDefinition(Primeval.prefix("the_falling_star"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/the_falling_star.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/the_falling_star.obj"))//模型地址
                                .effectColor(1744894)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(14)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.DYFS.getId())//SA
                                .maxDamage(888)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(VOID,
                new SlashBladeDefinition(Primeval.prefix("void"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/void.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/void.obj"))//模型地址
                                .effectColor(16774471)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3)//伤害
                                .defaultSwordType(List.of(SwordType.ENCHANTED))
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())//SA
                                .maxDamage(20240)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)//附魔（不要忘了最后一个不要写句号）
                        )));
        bootstrap.register(YANG,
                new SlashBladeDefinition(Primeval.prefix("yang"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/yang.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/yinyang.obj"))//模型地址
                                .effectColor(16777215)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.LIANZHANB.getId())//SA
                                .maxDamage(989)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(YIN,
                new SlashBladeDefinition(Primeval.prefix("yin"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/yin.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/yinyang.obj"))//模型地址
                                .effectColor(1644825)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.LIANZHAN.getId())//SA
                                .maxDamage(898)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(YINYANG,
                new SlashBladeDefinition(Primeval.prefix("yinyang"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/yinyang.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/yinyangpo.obj"))//模型地址
                                .effectColor(8487297)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(40)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.ERTIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.YINYANGJIAN.getId())//这是SE
                                .maxDamage(9898)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 11),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(XUESE,
                new SlashBladeDefinition(Primeval.prefix("xuese"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/xuese.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/xuese.obj"))//模型地址
                                .effectColor(16717848)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(24)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.XUE_YU.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIXUE.getId())//这是SE
                                .maxDamage(10000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )));
        bootstrap.register(CAIJUE,
                new SlashBladeDefinition(Primeval.prefix("caijue"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/caijue.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/caijue.obj"))//模型地址
                                .effectColor(166655)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.LIANZHANB.getId())//SA
                                .maxDamage(500)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10)//附魔（不要忘了最后一个不要写句号）
                        )));
        bootstrap.register(SHIYUANEX,
                new SlashBladeDefinition(Primeval.prefix("shiyuanex"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/shiyuanex.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/jixu.obj"))//模型地址
                                .effectColor(9917695)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(42)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.SILIEEX.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.XINGHUN.getId())
                                .maxDamage(7000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )));
        bootstrap.register(SHIYUAN,
                new SlashBladeDefinition(Primeval.prefix("shiyuan"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/shiyuan.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/shiyuan.obj"))//模型地址
                                .effectColor(9917695)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(22)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.SILIE.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.XINGHUN.getId())
                                .maxDamage(4000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),//附魔（不要忘了最后一个不要写句号）
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )));
        bootstrap.register(ZHENSHI,
                new SlashBladeDefinition(Primeval.prefix("real"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/real.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(15321344)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5)
                        )));
        bootstrap.register(LIFA,
                new SlashBladeDefinition(Primeval.prefix("calendar_system"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/calendar_system.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(3668536)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 3)
                        )));
        bootstrap.register(YANYU,
                new SlashBladeDefinition(Primeval.prefix("speak"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/speak.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(12815871)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )));
        bootstrap.register(JIAZHI,
                new SlashBladeDefinition(Primeval.prefix("value"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/value.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(2159579)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(GUIZE,
                new SlashBladeDefinition(Primeval.prefix("rule"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/rule.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(13301789)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5)
                        )));
        bootstrap.register(YIYI,
                new SlashBladeDefinition(Primeval.prefix("significance"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/significance.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(9633760)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 3)
                        )));
        bootstrap.register(ZUNYAN,
                new SlashBladeDefinition(Primeval.prefix("dignity"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/dignity.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(15204239)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(1000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3)
                        )));
        bootstrap.register(REVE,
                new SlashBladeDefinition(Primeval.prefix("revelation"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/revelation.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xi.obj"))//模型地址
                                .effectColor(16777215)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.JIXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.SHIDAI.getId())
                                .maxDamage(10000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(WEATERK,
                new SlashBladeDefinition(Primeval.prefix("weaterk"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/wearerk.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/wearerk.obj"))//模型地址
                                .effectColor(1886463)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(14)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.SHUXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.WEATERPRO.getId())
                                .maxDamage(4)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(ZHEJIAN,
                new SlashBladeDefinition(Primeval.prefix("zhejian"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/xmr.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/xmr.obj"))//模型地址
                                .effectColor(16777215)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(15)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.SHUXIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.ATTOW.getId())
                                .maxDamage(4000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(LIEYAN,
                new SlashBladeDefinition(Primeval.prefix("lieyan"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/lieyan.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/lieyan.obj"))//模型地址
                                .effectColor(16741888)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(16)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.YUMAO.getId())//SA
                                .maxDamage(2000)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_PROTECTION), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)

                        )));
        bootstrap.register(DAY,
                new SlashBladeDefinition(Primeval.prefix("day"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/xir/day.png"))//贴图地址
                                .modelName(Primeval.prefix("model/xir/day.obj"))//模型地址
                                .effectColor(16773651)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(35)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.TAICHU.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.YUANMENG.getId())
                                .maxDamage(77777)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.ALL_DAMAGE_PROTECTION), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 7)

                        )));
        bootstrap.register(DERIVE,
                new SlashBladeDefinition(Primeval.prefix("derive"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/derive.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/derive.obj"))//模型地址
                                .effectColor(49919)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(1)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.DAJIAN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.DIVERX.getId())
                                .addSpecialEffect(PLSpecialEffectsRegistry.CLWEOR.getId())
                                .maxDamage(180)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.INFINITY_ARROWS), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)

                        )));
        bootstrap.register(HONGY,
                new SlashBladeDefinition(Primeval.prefix("hongy"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/hongy.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/hongy.obj"))//模型地址
                                .effectColor(16731007)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(38)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.XUEREN.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.XUEYUX.getId())
                                .maxDamage(14444)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(HUANGWU,
                new SlashBladeDefinition(Primeval.prefix("huangwu"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/huangwu.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/huangwu.obj"))//模型地址
                                .effectColor(8092539)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(46)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.SANZHZHU.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.KUNAN.getId())
                                .maxDamage(26585)//耐久
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS  ), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 5)
                        )));
        bootstrap.register(HUAZHIWU,
                new SlashBladeDefinition(Primeval.prefix("huazhiwu"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/huazhiwu.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/huazhiwu.obj"))//模型地址
                                .effectColor(41215)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(23)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.YUMAO.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.DUAN.getId())
                                .addSpecialEffect(PLSpecialEffectsRegistry.CLWEOR.getId())
                                .maxDamage(16357)//耐久
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE ), 5)
                        )));
        bootstrap.register(XUANCAI,
                new SlashBladeDefinition(Primeval.prefix("xuancai"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/xuancai.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/xuancai.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.XUANDRIVE.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.CLWEOR.getId())
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(JINAN,
                new SlashBladeDefinition(Primeval.prefix("jinan"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/lou/jinan.png"))//贴图地址
                                .modelName(Primeval.prefix("model/lou/jinan.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.DPRO.getId())//SA
                                .maxDamage(1024)//耐久
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT  ), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE ), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(BAILOU,
                new SlashBladeDefinition(Primeval.prefix("bailou"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/lou/bailou.png"))//贴图地址
                                .modelName(Primeval.prefix("model/lou/bailou.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.QIXING.getId())//SA
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT  ), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE ), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING ), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS ), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING ), 1)
                        )));
        bootstrap.register(LOUGUAN,
                new SlashBladeDefinition(Primeval.prefix("louguan"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/lou/louguan.png"))//贴图地址
                                .modelName(Primeval.prefix("model/lou/louguan.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.DUAN.getId())
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING ), 1)
                        )));
        bootstrap.register(BAILOUEX,
                new SlashBladeDefinition(Primeval.prefix("bailouex"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/lou/bailou.png"))//贴图地址
                                .modelName(Primeval.prefix("model/lou/bailouex.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(28)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.QIXINGEX.getId())//SA
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT  ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE ), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING ), 3)
                        )));
        bootstrap.register(LOUGUANEX,
                new SlashBladeDefinition(Primeval.prefix("louguanex"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/lou/louguan.png"))//贴图地址
                                .modelName(Primeval.prefix("model/lou/louguanex.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(24)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.DUANEX.getId())
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING ), 5)
                        )));
        bootstrap.register(VOIDEX,
                new SlashBladeDefinition(Primeval.prefix("voidex"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/voidex.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/void.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())//SA
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT ), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING  ), 5)
                        )));
        bootstrap.register(PYQG,
                new SlashBladeDefinition(Primeval.prefix("pyqg"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/yuanshui/pyqg.png"))//贴图地址
                                .modelName(Primeval.prefix("model/yuanshui/pyqg.obj"))//模型地址
                                .effectColor(14614371)//颜色
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(45)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLslashArtRegsitry.BREAK_SKY.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.HUTI.getId())
                                .addSpecialEffect(PLSpecialEffectsRegistry.TUGUITU.getId())
                                .addSpecialEffect(PLSpecialEffectsRegistry.YUANGUANG.getId())
                                .maxDamage(10024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )));
        bootstrap.register(DUANDAI,
                new SlashBladeDefinition(Primeval.prefix("duandai"),//NBT名称
                        RenderDefinition.Builder.newInstance()
                                .textureName(Primeval.prefix("model/tianyi/duandai_zhou.png"))//贴图地址
                                .modelName(Primeval.prefix("model/tianyi/duandai.obj"))//模型地址
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(41)//伤害
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(PLComboRegsitry.DAZHOU.getId())//SA
                                .addSpecialEffect(PLSpecialEffectsRegistry.CLWEOR.getId())
                                .addSpecialEffect(PLSpecialEffectsRegistry.DUANZHOU.getId())
                                .maxDamage(4024)//耐久
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE ), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS ), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING ), 3)
                        )));
    }




    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Primeval.prefix(id));
    }
    public static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }
}
