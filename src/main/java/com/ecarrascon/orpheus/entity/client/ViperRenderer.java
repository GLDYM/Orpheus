package com.ecarrascon.orpheus.entity.client;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ViperRenderer extends MobRenderer<ViperEntity, ViperModel<ViperEntity>> {
    public ViperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ViperModel<>(pContext.bakeLayer(ModelLayers.VIPER_LAYER)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(ViperEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Orpheus.MOD_ID, "textures/entity/vipera_ammodytes.png");
    }

    @Override
    public void render(ViperEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
