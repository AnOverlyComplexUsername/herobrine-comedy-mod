package com.example.entityProperties;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.state.PigEntityRenderState;

public class HerobrineRaidRenderer extends PigEntityRenderer {

    public HerobrineRaidRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public PigEntityRenderState createRenderState() {
        return new PigEntityRenderState();
    }


}
