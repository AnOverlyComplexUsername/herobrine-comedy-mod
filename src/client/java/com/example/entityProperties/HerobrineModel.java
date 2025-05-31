package com.example.entityProperties;

import com.jaysydney.Custom.enetities.EntityHerobrine;

import com.jaysydney.HerobrineComedyMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class HerobrineModel extends EntityModel<HerobrineRenderState> {
    public static final EntityModelLayer HEROBRINE = new EntityModelLayer(Identifier.of(HerobrineComedyMod.MOD_ID,"herobrine"), "main");
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
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.headwear = this.root.getChild("headwear");
        this.body = this.root.getChild("body");
        this.jacket = this.root.getChild("jacket");
        this.left_arm = this.root.getChild("left_arm");
        this.left_sleeve = this.root.getChild("left_sleeve");
        this.right_arm = this.root.getChild("right_arm");
        this.right_sleeve = this.root.getChild("right_sleeve");
        this.left_leg = this.root.getChild("left_leg");
        this.left_pants = this.root.getChild("left_pants");
        this.right_leg = this.root.getChild("right_leg");
        this.right_pants = this.root.getChild("right_pants");

    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, -24.0F, 0.0F));

        ModelPartData headwear = root.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.rotation(0.0F, -24.0F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, -24.0F, 0.0F));

        ModelPartData jacket = root.addChild("jacket", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.rotation(0.0F, -24.0F, 0.0F));

        ModelPartData left_arm = root.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(5.0F, -22.0F, 0.0F));

        ModelPartData left_sleeve = root.addChild("left_sleeve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.rotation(5.0F, -22.0F, 0.0F));

        ModelPartData right_arm = root.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(-5.0F, -22.0F, 0.0F));

        ModelPartData right_sleeve = root.addChild("right_sleeve", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.rotation(-5.0F, -22.0F, 0.0F));

        ModelPartData left_leg = root.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(2.0F, -12.0F, 0.0F));

        ModelPartData left_pants = root.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.rotation(2.0F, -12.0F, 0.0F));

        ModelPartData right_leg = root.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(-2.0F, -12.0F, 0.0F));

        ModelPartData right_pants = root.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.rotation(-2.0F, -12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(HerobrineRenderState state){
        super.setAngles(state);
        this.setHeadAngles(state.relativeHeadYaw, state.pitch);
        //this.animateWalking(MantisAnimations.ANIM_MANTIS_WALK, state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2f, 2.5f);
        //to be added with walking animation
        this.animate(state.idleAnimationState, HerobrineAnimations.ANIM_HEROBRINE_IDLE, state.age, 1f);
    }

    //allows head movement
    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

}
