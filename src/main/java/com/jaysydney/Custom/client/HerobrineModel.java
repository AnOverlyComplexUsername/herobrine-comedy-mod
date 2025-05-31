package com.jaysydney.Custom.client;

import com.jaysydney.Custom.enetities.EntityHerobrine;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class HerobrineModel extends EntityModel<EntityRenderState> {
    private final ModelPart root;

    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart jacket;
    private final ModelPart left_arm;
    private final ModelPart left_sleeve;
    private final ModelPart right_arm;
    private final ModelPart right_sleeve;
    private final ModelPart left_leg;
    private final ModelPart left_pants;
    private final ModelPart right_leg;
    private final ModelPart right_pants;

    public HerobrineModel(ModelPart root) {
        super(root);//this probably doesn't work??
        this.root = root;
        this.head = root.getChild("head");
        this.headwear = root.getChild("headwear");
        this.body = root.getChild("body");
        this.jacket = root.getChild("jacket");
        this.left_arm = root.getChild("left_arm");
        this.left_sleeve = root.getChild("left_sleeve");
        this.right_arm = root.getChild("right_arm");
        this.right_sleeve = root.getChild("right_sleeve");
        this.left_leg = root.getChild("left_leg");
        this.left_pants = root.getChild("left_pants");
        this.right_leg = root.getChild("right_leg");
        this.right_pants = root.getChild("right_pants");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0)
            .cuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("headwear", ModelPartBuilder.create().uv(32, 0)
            .cuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8, new Dilation(0.5F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16)
            .cuboid(-4.0F, 0.0F, -2.0F, 8, 12, 4, new Dilation(0.0F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("jacket", ModelPartBuilder.create().uv(16, 32)
            .cuboid(-4.0F, 0.0F, -2.0F, 8, 12, 4, new Dilation(0.25F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48)
            .cuboid(-1.0F, -2.0F, -2.0F, 4, 12, 4, new Dilation(0.0F)),
            ModelTransform.of(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));


        modelPartData.addChild("left_sleeve", ModelPartBuilder.create().uv(48, 48)
            .cuboid(-1.0F, -2.0F, -2.0F, 4, 12, 4, new Dilation(0.25F)),
            ModelTransform.of(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16)
            .cuboid(-3.0F, -2.0F, -2.0F, 4, 12, 4, new Dilation(0.0F)),
            ModelTransform.of(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("right_sleeve", ModelPartBuilder.create().uv(40, 32)
            .cuboid(-3.0F, -2.0F, -2.0F, 4, 12, 4, new Dilation(0.25F)),
            ModelTransform.of(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48)
            .cuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, new Dilation(0.0F)),
            ModelTransform.of(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("left_pants", ModelPartBuilder.create().uv(0, 48)
            .cuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, new Dilation(0.25F)),
            ModelTransform.of(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));


        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16)
            .cuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, new Dilation(0.0F)),
            ModelTransform.of(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));


        modelPartData.addChild("right_pants", ModelPartBuilder.create().uv(0, 32)
            .cuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, new Dilation(0.25F)),
            ModelTransform.of(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    //@Override
    public void setAngles(EntityHerobrine entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
}

    //@Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        headwear.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        jacket.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_sleeve.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_sleeve.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        left_pants.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        right_pants.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
