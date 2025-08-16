package com.tianyi.primeval.cilent.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.entity.AreaAttackEnity;
import com.tianyi.primeval.entity.BigDriveEnity;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.awt.*;
@OnlyIn(Dist.CLIENT)

public  class RangeSlashRender <T extends AreaAttackEnity> extends EntityRenderer<T>
{
    static private final ResourceLocation modelLocation = new ResourceLocation(SlashBlade.MODID,
            "model/util/slashdim.obj");
    static private final ResourceLocation textureLocation = new ResourceLocation(SlashBlade.MODID,
            "model/util/slashdim.png");
    public static final ResourceLocation defaultTexture = new ResourceLocation("slashblade:model/util/ss.png");
    public static ResourceLocation getTextureLoc() {
        return (textureLocation);
    }
    public RangeSlashRender(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);

    }

    @Override
    public ResourceLocation getTextureLocation(T t) {
        return getTextureLoc();
    }

    @Override
    public void render(T entity, float p_114486_, float p_114487_, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {

            WavefrontObject model ;
            int tickCount = entity.getTick();
                float sas = (float) entity.getAttackRange()  *0.002f;
                matrixStack.translate(0,0,0);
                matrixStack.scale(sas, sas,sas);
                model = BladeModelManager.getInstance().getModel(modelLocation);
                BladeRenderState.setCol(Color.BLACK.getRGB(), false);
                BladeRenderState.renderOverrided(ItemStack.EMPTY, model, "base", defaultTexture,
                        matrixStack, bufferIn, packedLightIn);

        }
    }
}
