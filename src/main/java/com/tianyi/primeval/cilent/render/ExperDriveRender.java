package com.tianyi.primeval.cilent.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.entity.xeper.ExperDriveEntity;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;


public class ExperDriveRender <T extends ExperDriveEntity> extends EntityRenderer<T> {

    private static final ResourceLocation TEXTURE = Primeval.prefix("model/util/exper_diver.png");
    private static final ResourceLocation MODEL = Primeval.prefix("model/util/exper_diver.obj");

    public ExperDriveRender(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {return null;}
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {
            float lifetime = entity.getLifetime();
            double deathTime = (double)lifetime;
            double baseAlpha = Math.min(deathTime, (double)Math.max(0.0F, lifetime - (float)entity.tickCount)) / deathTime;
            baseAlpha = Math.max((double)0.0F, -Math.pow(baseAlpha - (double)1.0F, (double)4.0F) + (double)0.75F);
            matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot())));
            matrixStack.mulPose(Axis.XP.rotationDegrees(entity.getRotX()));
            matrixStack.mulPose(Axis.YP.rotationDegrees(entity.getRotY()));
            float scale = 0.015F;
            matrixStack.scale(scale, scale, scale);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            int color = entity.getColor() & 16777215;
            int alpha = (255 & (int)((double)255.0F * baseAlpha)) << 24;
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);
            BladeRenderState.setCol(color | alpha);
            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "base", TEXTURE, matrixStack, bufferIn, packedLightIn);
        }

    }
}
