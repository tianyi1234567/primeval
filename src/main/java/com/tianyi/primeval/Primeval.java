package com.tianyi.primeval;

import com.mojang.logging.LogUtils;
import com.tianyi.primeval.registry.*;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Primeval.MODID)
public class Primeval {
    public static ResourceLocation prefix(String path) {

        return new ResourceLocation(MODID, path);
    }
    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(MODID, path);
    }

    // Define mod id in a common place for everything to reference
    public static final String MODID = "primeval";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public Primeval() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        PLSpecialEffectsRegistry.REGISTRY_KEY2.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::register);
        CREATIVE_MODE_TABS.register(modEventBus);
        ParticleRegistry.register(modEventBus);
        //注册
        PLComboRegsitry.COMBO_STATES.register(modEventBus);
        PLslashArtRegsitry.SLASH_ARTS.register(modEventBus);




        //        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> Primeval = CREATIVE_MODE_TABS.register("primeval_slashblade",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.primeval.primeval_slashblade")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.slashblade);
                        stack.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(s -> {
                            s.setModel(new ResourceLocation(MODID, "model/tianyi/yinyangpo.obj"));
                            s.setTexture(new ResourceLocation(MODID, "model/tianyi/yinyang.png"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                        fillBlades(tabData);
                    })
                    .build());


    @OnlyIn(Dist.CLIENT)
    private static void fillBlades(CreativeModeTab.Output output) {
        if (Minecraft.getInstance().getConnection() != null) {
            BladeModelManager.getClientSlashBladeRegistry()
                    .entrySet().stream()
                    // 步骤1：过滤空值
                    .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                    // 步骤2：解析字符串为ResourceLocation并过滤命名空间
                    .filter(entry -> {
                        ResourceLocation loc = ResourceLocation.tryParse(entry.getKey().location().toString());
                        return loc != null && loc.getNamespace().equals(MODID);
                    })
                    // 步骤3：按字符串键排序
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        LOGGER.info("Registering Slashblade: {}", entry.getKey());
                        output.accept(entry.getValue().getBlade());
                    });
        }
    }
    public void register(RegisterEvent event) {
        PLEntiteRegristrys.register(event);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
            workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }
    @Mod.EventBusSubscriber
    public static class CommonEvents{
        @SubscribeEvent
        public static void tick(TickEvent.ServerTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
                workQueue.forEach(work -> {
                    work.setValue(work.getValue() - 1);
                    if (work.getValue() == 0)
                        actions.add(work);
                });
                actions.forEach(e -> e.getKey().run());
                workQueue.removeAll(actions);
            }
        }

    }
    // 在附属模组主类注册事件
    @SubscribeEvent
    public static void onStunApplied(LivingAttackEvent event) {
        // 检测到眩晕施加时取消
        if (event.getSource().getMsgId().equals("stun")) {
            event.setCanceled(true);
        }
    }
}