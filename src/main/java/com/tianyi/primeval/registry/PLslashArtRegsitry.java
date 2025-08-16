package com.tianyi.primeval.registry;

import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.tianyi.primeval.Primeval.MODID;

public class PLslashArtRegsitry {//sa注册
    public static final DeferredRegister<SlashArts> SLASH_ARTS =
            DeferredRegister.create(SlashArts.REGISTRY_KEY, MODID);
    public static final RegistryObject<SlashArts> BIG_DRIVE;
    public static final RegistryObject<SlashArts> WIND_IS_VLOWING;
    public static final RegistryObject<SlashArts> XUE_YU;
    public static final RegistryObject<SlashArts> HUANYING_GAI;
    public static final RegistryObject<SlashArts> ERTIAN;
    public static final RegistryObject<SlashArts> LIANZHAN;
    public static final RegistryObject<SlashArts> LIANZHANB;
    public static final RegistryObject<SlashArts> SILIE;
    public static final RegistryObject<SlashArts> SILIEEX;
    public static final RegistryObject<SlashArts> YINHUAL;
    public static final RegistryObject<SlashArts> JIXIAN;
    public static final RegistryObject<SlashArts> SHUXIAN;
    public static final RegistryObject<SlashArts> YUMAO;
    public static final RegistryObject<SlashArts> TAICHU;
    public static final RegistryObject<SlashArts> GUANGLAN;
    public static final RegistryObject<SlashArts> DAJIAN;
    public static final RegistryObject<SlashArts> XUEREN;
    public static final RegistryObject<SlashArts> DYFS;
    public static final RegistryObject<SlashArts> XUANDRIVE;
    public static final RegistryObject<SlashArts> CI_JI;
    public static final RegistryObject<SlashArts> QIXING;
    public static final RegistryObject<SlashArts> DPRO;
    public static final RegistryObject<SlashArts> QIXINGEX;
    public static final RegistryObject<SlashArts> BREAK_SKY;
    public static final RegistryObject<SlashArts> PXKKL;
    public static final RegistryObject<SlashArts> SANZHZHU;

    static {
        BIG_DRIVE=SLASH_ARTS.register("big_drive",()->new SlashArts((e)->PLComboRegsitry.BIGDRIVE_VERTICAL.getId()));//命名需要puliic相同
        WIND_IS_VLOWING=SLASH_ARTS.register("wind_is_blowing",()->new SlashArts((e)->PLComboRegsitry.WIND_IS_VLOWING.getId()));
        XUE_YU=SLASH_ARTS.register("xueyu",()->new SlashArts((e)->PLComboRegsitry.XUE_YU.getId()));
        HUANYING_GAI=SLASH_ARTS.register("huanyinggai",()->new SlashArts((e)->PLComboRegsitry.HUANYING_GAI.getId()));
        ERTIAN=SLASH_ARTS.register("ertian",()->new SlashArts((e)->PLComboRegsitry.ERTIAN.getId()));
        LIANZHAN=SLASH_ARTS.register("lianzhan",()->new SlashArts((e)->PLComboRegsitry.LIANZHAN.getId()));
        LIANZHANB=SLASH_ARTS.register("lianzhanb",()->new SlashArts((e)->PLComboRegsitry.LIANZHANB.getId()));
        SILIE=SLASH_ARTS.register("silie",()->new SlashArts((e)->PLComboRegsitry.SILIE.getId()));
        SILIEEX=SLASH_ARTS.register("silieex",()->new SlashArts((e)->PLComboRegsitry.SILIEEX.getId()));
        YINHUAL=SLASH_ARTS.register("yinghual",()->new SlashArts((e)->PLComboRegsitry.YINHUAL.getId()));
        JIXIAN=SLASH_ARTS.register("jixian",()->new SlashArts((e)->PLComboRegsitry.JIXIAN.getId()));
        SHUXIAN=SLASH_ARTS.register("shuixian",()->new SlashArts((e)->PLComboRegsitry.SHUXIAN.getId()));
        YUMAO=SLASH_ARTS.register("yumao",()->new SlashArts((e)->PLComboRegsitry.YUMAO.getId()));
        TAICHU=SLASH_ARTS.register("taichu",()->new SlashArts((e)->PLComboRegsitry.TAICHU.getId()));
        GUANGLAN=SLASH_ARTS.register("guanglan",()->new SlashArts((e)->PLComboRegsitry.GUANGLAN.getId()));
        DAJIAN=SLASH_ARTS.register("dajian",()->new SlashArts((e)->PLComboRegsitry.DAJIAN.getId()));
        XUEREN=SLASH_ARTS.register("xueren",()->new SlashArts((e)->PLComboRegsitry.XUEREN.getId()));
        DYFS=SLASH_ARTS.register("dyfs",()->new SlashArts((e)->PLComboRegsitry.DYFS.getId()));
        XUANDRIVE=SLASH_ARTS.register("xuandrive",()->new SlashArts((e)->PLComboRegsitry.XUANDRIVE.getId()));
        CI_JI=SLASH_ARTS.register("ciji",()->new SlashArts((e)->PLComboRegsitry.CI_JI.getId()));
        QIXING=SLASH_ARTS.register("qixing",()->new SlashArts((e)->PLComboRegsitry.QIXING.getId()));
        DPRO=SLASH_ARTS.register("dpro",()->new SlashArts((e)->PLComboRegsitry.DPRO.getId()));
        QIXINGEX=SLASH_ARTS.register("qixingex",()->new SlashArts((e)->PLComboRegsitry.QIXINGEX.getId()));
        BREAK_SKY=SLASH_ARTS.register("break_sky",()->new SlashArts((e)->PLComboRegsitry.BREAK_SKY.getId()));
        PXKKL=SLASH_ARTS.register("pxkkl",()->new SlashArts((e)->PLComboRegsitry.PXKKL.getId()));
        SANZHZHU=SLASH_ARTS.register("sanzhzhu",()->new SlashArts((e)->PLComboRegsitry.SANZHZHU.getId()));
    }
}