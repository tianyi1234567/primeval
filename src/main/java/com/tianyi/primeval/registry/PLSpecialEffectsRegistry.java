package com.tianyi.primeval.registry;

import com.tianyi.primeval.registry.specialeffects.*;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.tianyi.primeval.Primeval.MODID;
//这里是SE注册表
public class PLSpecialEffectsRegistry {

    public static final DeferredRegister<SpecialEffect> REGISTRY_KEY2;//这玩意是最重要的
    public static final RegistryObject<SpecialEffect> PRIMEVALT;
    public static final RegistryObject<SpecialEffect> YINYANGJIAN;
    public static final RegistryObject<SpecialEffect> PRIMEVALTY;
    public static final RegistryObject<SpecialEffect> SHENFENG;
    public static final RegistryObject<SpecialEffect> YINGYU;
    public static final RegistryObject<SpecialEffect> XINGHUN;
    public static final RegistryObject<SpecialEffect> YUANMENG;
    public static final RegistryObject<SpecialEffect> DUAN;
    public static final RegistryObject<SpecialEffect> TAICHU;
    public static final RegistryObject<SpecialEffect> WEATERPRO;
    public static final RegistryObject<SpecialEffect> SHIDAI;
    public static final RegistryObject<SpecialEffect> ATTOW;
    public static final RegistryObject<SpecialEffect> DIVERX;
    public static final RegistryObject<SpecialEffect> XUEYUX;
    public static final RegistryObject<SpecialEffect> CLWEOR;
    public static final RegistryObject<SpecialEffect> HUASEOR;
    public static final RegistryObject<SpecialEffect> HUTI;
    public static final RegistryObject<SpecialEffect> DUANEX;
    public static final RegistryObject<SpecialEffect> SHIXUE;
    public static final RegistryObject<SpecialEffect> TUGUITU;
    public static final RegistryObject<SpecialEffect> YUANGUANG;
    public static final RegistryObject<SpecialEffect> KUNAN;

    public static final RegistryObject<SpecialEffect> DUANZHOU;
    public static final RegistryObject<SpecialEffect> DUANYE;

    public static final RegistryObject<SpecialEffect> ZHONGYAN;

    public PLSpecialEffectsRegistry() {
    }

    static {
        REGISTRY_KEY2 = DeferredRegister.create(SpecialEffect.REGISTRY_KEY, MODID);
        PRIMEVALT= REGISTRY_KEY2.register("primevalt", Primevalt::new);//后面的不要和name一样
        YINYANGJIAN= REGISTRY_KEY2.register("yinyangjian", Yinyangjian::new);
        PRIMEVALTY= REGISTRY_KEY2.register("promevalty", Primevalty::new);
        SHENFENG= REGISTRY_KEY2.register("shenfeng", Shenfeng::new);
        YINGYU= REGISTRY_KEY2.register("yinyu", Yingyu::new);
        XINGHUN= REGISTRY_KEY2.register("xinghun", Xinghun::new);
        YUANMENG= REGISTRY_KEY2.register("yuanmeng", Yuanmeng::new);
        DUAN= REGISTRY_KEY2.register("duan", Duan::new);
        TAICHU= REGISTRY_KEY2.register("taichu", Taichu::new);
        WEATERPRO= REGISTRY_KEY2.register("weaterpro", Weaterpro::new);
        SHIDAI= REGISTRY_KEY2.register("shidai", Shidai::new);
        ATTOW= REGISTRY_KEY2.register("attow", Attow::new);
        DIVERX= REGISTRY_KEY2.register("diverx", Diverx::new);
        XUEYUX= REGISTRY_KEY2.register("xueyux", Xueyux::new);
        CLWEOR= REGISTRY_KEY2.register("clweor", Clweor::new);
        HUASEOR= REGISTRY_KEY2.register("huasword", Huasword::new);
        HUTI= REGISTRY_KEY2.register("huti", Huti::new);
        DUANEX= REGISTRY_KEY2.register("duanex", Duanex::new);
        SHIXUE= REGISTRY_KEY2.register("shixue", Shixue::new);
        TUGUITU= REGISTRY_KEY2.register("tuguitu", Tuguitu::new);
        YUANGUANG= REGISTRY_KEY2.register("yuangaung", Yuangaung::new);
        KUNAN= REGISTRY_KEY2.register("kunan", Kunan::new);
        DUANZHOU= REGISTRY_KEY2.register("duanzhou", Duanzhou::new);
        DUANYE= REGISTRY_KEY2.register("duanye", Duanye::new);

        ZHONGYAN= REGISTRY_KEY2.register("zhongyan", Zhongyan::new);
    }
}
