package com.tianyi.primeval.registry;

import com.google.common.base.CaseFormat;
import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.tianyi.primeval.Primeval.MODID;


public class PLEntiteRegristrys {
    //private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final ResourceLocation BIGDriveLoc = new ResourceLocation(Primeval.MODID,//写完检查cilent/render/SrelicClient文件是否有对应的实体渲染,不然等着炸客户端渲染吧你
            classToString(BigDriveEnity.class));
    public static EntityType<BigDriveEnity> BIGDrive;
    public static final ResourceLocation blowingLOC = new ResourceLocation(MODID,//注意检查Enity命名
            classToString(BlowingEnity.class));
    public static EntityType<BlowingEnity> bl;
    public static final ResourceLocation WindproEnityLOC = new ResourceLocation(MODID,
            classToString(WindproEnity.class));
    public static EntityType<WindproEnity> wd;
    public static final ResourceLocation ZheSheiEnityLOC = new ResourceLocation(MODID,
            classToString(ZhesheiEnity.class));
    public static EntityType<ZhesheiEnity> zs;
    public static final ResourceLocation HuanRenLOC = new ResourceLocation(MODID,
            classToString(HuanRenEnity.class));
    public static EntityType<HuanRenEnity> hr;
    public static final ResourceLocation YingYuLOC = new ResourceLocation(MODID,
            classToString(YingYuEnity.class));
    public static EntityType<YingYuEnity> yy;
    public static final ResourceLocation SunPROLOC = new ResourceLocation(MODID,
            classToString(SumonSwordPROEntity.class));
    public static EntityType<SumonSwordPROEntity> sp;
    public static final ResourceLocation QIJILOC = new ResourceLocation(MODID,
            classToString(QiJiEnity.class));
    public static EntityType<QiJiEnity> qj;
    public static final ResourceLocation PRODRIVE = new ResourceLocation(MODID,
            classToString(ProDriveEnity.class));
    public static EntityType<ProDriveEnity> pe;
    public static final ResourceLocation RAINPRO = new ResourceLocation(MODID,
            classToString(SwordRainEntity.class));
    public static EntityType<SwordRainEntity> ro;
    public static final ResourceLocation SUPS = new ResourceLocation(MODID,
            classToString(SumonSwordEntity.class));
    public static EntityType<SumonSwordEntity> su;
    public static final ResourceLocation SUPSEX = new ResourceLocation(MODID,
            classToString(SumonSwordEntityEX.class));
    public static EntityType<SumonSwordEntityEX> sx;
    public static final ResourceLocation XIWANGSWORD = new ResourceLocation(MODID,
            classToString(XiWangSwordEnity.class));
    public static EntityType<XiWangSwordEnity> xysd;
    public static final ResourceLocation CHIBANGSWORD = new ResourceLocation(MODID,
            classToString(ChiBangEnity.class));
    public static EntityType<ChiBangEnity> cbsd;
    public static final ResourceLocation YANEX = new ResourceLocation(MODID,
            classToString(YanexEnity.class));
    public static EntityType<YanexEnity> yaex;
    public static final ResourceLocation BIGSUM = new ResourceLocation(MODID,
            classToString(BigSumonswordEnity.class));
    public static EntityType<BigSumonswordEnity> bigm;
    public static final ResourceLocation XSOWRT = new ResourceLocation(MODID,
            classToString(XXXperswordEnity.class));
    public static EntityType<XXXperswordEnity> xrt;
    public static final ResourceLocation AATA = new ResourceLocation(MODID,
            classToString(AreaAttackEnity.class));
    public static EntityType<AreaAttackEnity> aata;
    public static EntityType<TheBreakSwordPlus> TBS;
    public static ResourceLocation  TBSLoc = new ResourceLocation(MODID, classToString(TheBreakSwordPlus.class));
    public static final ResourceLocation SWPLUS = new ResourceLocation(MODID,
            classToString(SummonedSwordPlus.class));
    public static EntityType<SummonedSwordPlus> swplis;
    public static final ResourceLocation CEO = new ResourceLocation(MODID,
            classToString(CEOEnity.class));
    public static EntityType<CEOEnity> ceo;
    public static final ResourceLocation SUPDE = new ResourceLocation(MODID,
            classToString(SuperdriveEntity.class));
    public static EntityType<SuperdriveEntity> supde;


    public static void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType < BigDriveEnity > entity = BIGDrive = EntityType.Builder.of(BigDriveEnity::new, MobCategory.MISC)//多个命名记得检查是都修改了对应的实体
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(BigDriveEnity::createInstance).build(BIGDriveLoc.toString());
                helper.register(BIGDriveLoc, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType < BlowingEnity > entity = bl = EntityType.Builder.of(BlowingEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(BlowingEnity::createInstance).build(blowingLOC.toString());
                helper.register(blowingLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType < WindproEnity > entity = wd = EntityType.Builder.of(WindproEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(WindproEnity::createInstance).build(WindproEnityLOC.toString());
                helper.register(WindproEnityLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType < ZhesheiEnity > entity = zs = EntityType.Builder.of(ZhesheiEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(ZhesheiEnity::createInstance).build(ZheSheiEnityLOC.toString());
                helper.register(ZheSheiEnityLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType < HuanRenEnity > entity = hr = EntityType.Builder.of(HuanRenEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(HuanRenEnity::createInstance).build(HuanRenLOC.toString());
                helper.register(HuanRenLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <YingYuEnity> entity = yy = EntityType.Builder.of(YingYuEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(YingYuEnity::createInstance).build(YingYuLOC.toString());
                helper.register(YingYuLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SumonSwordPROEntity> entity = sp = EntityType.Builder.of(SumonSwordPROEntity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SumonSwordPROEntity::createInstance).build(SunPROLOC.toString());
                helper.register(SunPROLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <QiJiEnity> entity = qj = EntityType.Builder.of(QiJiEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(QiJiEnity::createInstance).build(QIJILOC.toString());
                helper.register(QIJILOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <ProDriveEnity> entity = pe = EntityType.Builder.of(ProDriveEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(ProDriveEnity::createInstance).build(PRODRIVE.toString());
                helper.register(PRODRIVE, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SwordRainEntity> entity = ro = EntityType.Builder.of(SwordRainEntity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SwordRainEntity::createInstance).build(RAINPRO.toString());
                helper.register(RAINPRO, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SumonSwordEntity> entity = su = EntityType.Builder.of(SumonSwordEntity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SumonSwordEntity::createInstance).build(SUPS.toString());
                helper.register(SUPS, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SumonSwordEntityEX> entity = sx = EntityType.Builder.of(SumonSwordEntityEX::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SumonSwordEntityEX::createInstance).build(SUPSEX.toString());
                helper.register(SUPSEX, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <XiWangSwordEnity> entity = xysd = EntityType.Builder.of(XiWangSwordEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(XiWangSwordEnity::createInstance).build(XIWANGSWORD.toString());
                helper.register(XIWANGSWORD, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <ChiBangEnity> entity = cbsd = EntityType.Builder.of(ChiBangEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(ChiBangEnity::createInstance).build(CHIBANGSWORD.toString());
                helper.register(CHIBANGSWORD, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <YanexEnity> entity = yaex = EntityType.Builder.of(YanexEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(YanexEnity::createInstance).build(YANEX.toString());
                helper.register(YANEX, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <BigSumonswordEnity> entity = bigm = EntityType.Builder.of(BigSumonswordEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(BigSumonswordEnity::createInstance).build(BIGSUM.toString());
                helper.register(BIGSUM, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <XXXperswordEnity> entity = xrt = EntityType.Builder.of(XXXperswordEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(XXXperswordEnity::createInstance).build(XSOWRT.toString());
                helper.register(XSOWRT, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <AreaAttackEnity> entity = aata = EntityType.Builder.of(AreaAttackEnity::new, MobCategory.MISC)
                        .sized(0.1f, 0.1f).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(AreaAttackEnity::createInstance).build(AATA.toString());
                helper.register(AATA, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            EntityType<TheBreakSwordPlus> entity = TBS = EntityType.Builder
                    .of(TheBreakSwordPlus::new, MobCategory.MISC).sized(0.5F, 0.5F)
                    .setTrackingRange(4).setUpdateInterval(20)
                    .setCustomClientFactory(TheBreakSwordPlus::createInstance)
                    .build(TBSLoc.toString());
            helper.register(TBSLoc, entity);
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SummonedSwordPlus> entity = swplis = EntityType.Builder.of(SummonedSwordPlus::new, MobCategory.MISC)
                        .sized(0.1f, 0.1f).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SummonedSwordPlus::createInstance).build(SWPLUS.toString());
                helper.register(SWPLUS, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <CEOEnity> entity = ceo = EntityType.Builder.of(CEOEnity::new, MobCategory.MISC)
                        .sized(1.0f, 1.0f).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(CEOEnity::createInstance).build(CEO.toString());
                helper.register(CEO, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType <SuperdriveEntity> entity = supde = EntityType.Builder.of(SuperdriveEntity::new, MobCategory.MISC)
                        .sized(1.0f, 1.0f).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(SuperdriveEntity::createInstance).build(SUPDE.toString());
                helper.register(SUPDE, entity);
            }
        });
    }

    public static String classToString(Class<? extends Entity> entityClass)
    {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName())
                .replace("entity_", "");
    }
}
