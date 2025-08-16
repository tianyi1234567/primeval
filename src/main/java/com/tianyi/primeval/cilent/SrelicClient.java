package com.tianyi.primeval.cilent;

import com.tianyi.primeval.cilent.render.*;
import com.tianyi.primeval.registry.PLEntiteRegristrys;
import mods.flammpfeil.slashblade.client.renderer.entity.DriveRenderer;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.tianyi.primeval.Primeval.MODID;

//客户端渲染，不填就崩
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT,modid = MODID)
public class SrelicClient {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PLEntiteRegristrys.BIGDrive, BigDriveRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.bl, DriveRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.wd, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.zs, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.hr, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.yy, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.sp, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.qj, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.pe, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.ro, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.su, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.sx, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.xysd, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.cbsd, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.xrt, SummonedSwordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.yaex, YanexRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.bigm, BigSumonswordRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.swplis, YanexRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.TBS,TheBrokenSwordRender::new);
        event.registerEntityRenderer(PLEntiteRegristrys.aata,RangeSlashRender::new);
        event.registerEntityRenderer(PLEntiteRegristrys.ceo, DriveRenderer::new);
        event.registerEntityRenderer(PLEntiteRegristrys.supde, SuperdriveRenderer::new);
    }
}
