package com.jaysydney.Custom.client;

import com.jaysydney.Custom.enetities.EntityHerobrine;
import com.jaysydney.HerobrineComedyMod;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;



public class HerobrineRenderer extends MobEntityRenderer<EntityHerobrine, HerobrineRenderState, HerobrineModel> {

    public HerobrineRenderer(EntityRendererFactory.Context context) {
        super(context, new HerobrineModel(context.getPart(HerobrineModel.HEROBRINE)), 0.5f);
    }


    @Override
    public void render(HerobrineRenderState state, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public HerobrineRenderState createRenderState() {
        return new HerobrineRenderState();
    }

    @Override
    public void updateRenderState(EntityHerobrine livingEntity, HerobrineRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }

    @Override
    public Identifier getTexture(HerobrineRenderState state) {
        return Identifier.of(HerobrineComedyMod.MOD_ID,"textures/entity/herobrine.png");
    }
}
