package com.ecarrascon.orpheus.entity.client;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.ecarrascon.orpheus.entity.animations.AnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ViperModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart viperaammodytes;
	private final ModelPart head;


	public ViperModel(ModelPart root) {
		this.viperaammodytes = root.getChild("viperaammodytes");
		this.head = viperaammodytes.getChild("body").getChild("head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition viperaammodytes = partdefinition.addOrReplaceChild("viperaammodytes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition body = viperaammodytes.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 2).addBox(-0.75F, -0.3125F, -1.025F, 2.0F, 2.4F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.25F, -2.3875F, 0.025F));

		PartDefinition horn_r1 = head.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(6, 17).addBox(-0.6F, -0.575F, -0.5F, 1.0F, 1.15F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 0.3125F, 0.025F, 0.0F, 0.0F, -0.48F));

		PartDefinition horn_r2 = head.addOrReplaceChild("horn_r2", CubeListBuilder.create().texOffs(7, 0).addBox(-0.5F, -0.575F, -0.6F, 1.0F, 1.15F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, -0.2875F, 0.025F, 0.0F, 0.0F, 0.3491F));

		PartDefinition one = body.addOrReplaceChild("one", CubeListBuilder.create().texOffs(8, 4).addBox(-1.0F, -1.25F, -1.2F, 2.0F, 2.75F, 2.4F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.5F, 0.0F));

		PartDefinition two = body.addOrReplaceChild("two", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.25F, -1.55F, 2.0F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.25F, 0.0F));

		PartDefinition three = body.addOrReplaceChild("three", CubeListBuilder.create().texOffs(14, 7).addBox(-1.0F, -1.075F, -1.4F, 2.0F, 2.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.325F, 0.0F));

		PartDefinition four = body.addOrReplaceChild("four", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.125F, -1.8F, 2.0F, 2.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.125F, 0.0F));

		PartDefinition six = body.addOrReplaceChild("six", CubeListBuilder.create().texOffs(10, 0).addBox(2.0F, -2.4F, -1.9F, 2.0F, 2.4F, 2.2F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition seven = body.addOrReplaceChild("seven", CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, -2.5F, -2.05F, 2.0F, 2.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eight = body.addOrReplaceChild("eight", CubeListBuilder.create().texOffs(10, 14).addBox(6.0F, -2.35F, -1.7F, 2.0F, 2.35F, 2.1F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nine = body.addOrReplaceChild("nine", CubeListBuilder.create().texOffs(7, 9).addBox(8.0F, -2.25F, -1.8F, 2.0F, 2.25F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ten = body.addOrReplaceChild("ten", CubeListBuilder.create().texOffs(16, 12).addBox(7.0F, -1.0F, -1.55F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, 0.0F));

		PartDefinition tail_two = body.addOrReplaceChild("tail_two", CubeListBuilder.create().texOffs(0, 17).addBox(7.0F, -0.5F, -0.9F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -0.5F, 0.0F));

		PartDefinition tail_one = body.addOrReplaceChild("tail_one", CubeListBuilder.create().texOffs(16, 0).addBox(7.0F, -0.55F, -1.25F, 2.0F, 1.1F, 1.3F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -0.55F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(AnimationDefinitions.VIPERAAMMODYTES_WALK, limbSwing, limbSwingAmount, 3, 4);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return viperaammodytes;
	}
}
