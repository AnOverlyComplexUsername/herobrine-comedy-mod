package com.example.entityProperties;

import com.jaysydney.Custom.enetities.HerobrineRaidEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class HerobrineRaidModel extends PigEntityModel {
    public HerobrineRaidModel(ModelPart root, Function<Identifier, RenderLayer> layerFactory) {
        super(root);
    }

}
