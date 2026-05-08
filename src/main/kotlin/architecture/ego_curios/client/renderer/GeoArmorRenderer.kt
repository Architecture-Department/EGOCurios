package architecture.ego_curios.client.renderer

import cn.solarmoon.spark_core.animation.IAnimatable
import cn.solarmoon.spark_core.animation.model.BonePose
import cn.solarmoon.spark_core.animation.model.ModelInstance
import cn.solarmoon.spark_core.animation.model.ModelPose
import cn.solarmoon.spark_core.animation.model.origin.OBone
import cn.solarmoon.spark_core.animation.model.origin.OModel
import cn.solarmoon.spark_core.animation.renderer.layer.RenderLayer
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack

open class GeoArmorRenderer<T, S> : IGeoRendererExpand<T, S> where S : IAnimatable<T> {
	val boneInfoExpand: MutableMap<String, OBoneInfo> = mutableMapOf()
	override val layers: MutableList<RenderLayer<T, S>> = mutableListOf()

	var animatable: S? = null

	var lastModelInstance: ModelInstance? = null

	var isFine: Boolean = false

	var bipedHead: OBone? = null
	var armorHead: OBone? = null

	var bipedBody: OBone? = null
	var armorPants: OBone? = null
	var armorBody: OBone? = null

	var bipedRightArm: OBone? = null
	var armorRightArm: OBone? = null
	var armorFineRightArm: OBone? = null

	var bipedLeftArm: OBone? = null
	var armorLeftArm: OBone? = null
	var armorFineLeftArm: OBone? = null

	var bipedRightLeg: OBone? = null
	var armorRightLeg: OBone? = null
	var armorRightBoot: OBone? = null

	var bipedLeftLeg: OBone? = null
	var armorLeftLeg: OBone? = null
	var armorLeftBoot: OBone? = null

	var baseHumanoidModel: HumanoidModel<*>? = null
	var baseLastModelInstance: ModelInstance? = null

	var currentEntity: Entity? = null
	var currentStack: ItemStack? = null
	var currentSlot: EquipmentSlot? = null
	var bufferSource: MultiBufferSource? = null
	var partialTick: Float = 0f
	var limbSwing: Float = 0f
	var limbSwingAmount: Float = 0f
	var netHeadYaw: Float = 0f
	var headPitch: Float = 0f

	fun getModel(): OModel? {
		return lastModelInstance?.origin
	}

	fun getModelInstance(): ModelInstance? {
		return lastModelInstance
	}

	fun getBone(name: String, model: OModel?): OBone? {
		return model?.bones[name]
	}

	fun getBoneInfo(boneName: String): OBoneInfo? {
		return boneInfoExpand[boneName]
	}

	fun getOrAddBone(name: String, model: OModel?): OBone? {
		boneInfoExpand[name]?.let { return model?.bones[name] }

		val bone = model?.bones[name] ?: return null
		lastModelInstance?.pose?.bonePoses[name]?.let {
			boneInfoExpand[name] = OBoneInfo(bone)
		}
		return bone
	}

	fun setAllBonesVisible(visible: Boolean) {
		boneInfoExpand.values.forEach { it.visible = visible }
	}

	protected fun grabRelevantBones(bakedModelInstance: ModelInstance?) {
		if (this.lastModelInstance === bakedModelInstance) return

		this.lastModelInstance = bakedModelInstance
		lastModelInstance?.origin?.bones?.forEach { (_, v) ->
			boneInfoExpand[v.name] = OBoneInfo(v)
		}
		val model: OModel = getModel() ?: return

		bipedHead = getOrAddBone("bipedHead", model)
		armorHead = getOrAddBone("armorHead", model)

		bipedBody = getOrAddBone("bipedBody", model)
		armorPants = getOrAddBone("armorPants", model)
		armorBody = getOrAddBone("armorBody", model)

		bipedRightArm = getOrAddBone("bipedRightArm", model)
		armorRightArm = getOrAddBone("armorRightArm", model)
		armorFineRightArm = getOrAddBone("armorFineRightArm", model)

		bipedLeftArm = getOrAddBone("bipedLeftArm", model)
		armorLeftArm = getOrAddBone("armorLeftArm", model)
		armorFineLeftArm = getOrAddBone("armorFineLeftArm", model)

		bipedRightLeg = getOrAddBone("bipedRightLeg", model)
		armorRightLeg = getOrAddBone("armorRightLeg", model)
		armorRightBoot = getOrAddBone("armorRightBoot", model)

		bipedLeftLeg = getOrAddBone("bipedLeftLeg", model)
		armorLeftLeg = getOrAddBone("armorLeftLeg", model)
		armorLeftBoot = getOrAddBone("armorLeftBoot", model)
	}

	protected fun applyBoneVisibilityBySlot(currentSlot: EquipmentSlot) {
		setAllBonesVisible(false)

		val renderer = this@GeoArmorRenderer
		baseHumanoidModel?.run {
			when (currentSlot) {
				EquipmentSlot.HEAD -> {
					renderer.bipedHead.setRecursionVisible(head.visible)
				}

				EquipmentSlot.CHEST -> {
					renderer.bipedBody.setRecursionVisible(body.visible)

					if (!isFine) {
						renderer.bipedRightArm.setRecursionVisible(rightArm.visible)
					} else {
						renderer.bipedRightArm.setRecursionVisible(rightArm.visible)
					}
				}

				EquipmentSlot.LEGS -> {
					renderer.bipedRightLeg.setRecursionVisible(rightLeg.visible)
				}

				EquipmentSlot.FEET -> {
					renderer.bipedLeftLeg.setRecursionVisible(rightLeg.visible)
				}

				else -> {}
			}
		}
	}

	protected fun applyBaseTransformations(baseModel: HumanoidModel<*>?) {
		baseModel ?: return

		if (this.bipedHead != null) {
			val headPart = baseModel.head
			bipedHead?.matchModelPartRot(headPart)
		}

		if (this.bipedHead != null) {
			val bodyPart = baseModel.body
			bipedBody?.matchModelPartRot(bodyPart)
		}

		if (this.bipedRightArm != null) {
			val rightArmPart = baseModel.rightArm
			bipedRightArm?.matchModelPartRot(rightArmPart)
		}

		if (this.bipedLeftArm != null) {
			val leftArmPart = baseModel.leftArm
			bipedLeftArm?.matchModelPartRot(leftArmPart)
		}

		if (this.bipedRightLeg != null) {
			val rightLegPart = baseModel.rightLeg
			bipedRightLeg?.matchModelPartRot(rightLegPart)
		}

		if (this.bipedLeftLeg != null) {
			val leftLegPart = baseModel.leftLeg
			bipedRightLeg?.matchModelPartRot(leftLegPart)
		}
	}

	fun prepForRender(
		animatable: S,
		entity: Entity?,
		stack: ItemStack,
		slot: EquipmentSlot?,
		baseModel: HumanoidModel<*>?,
		bufferSource: MultiBufferSource?,
		partialTick: Float,
		limbSwing: Float,
		limbSwingAmount: Float,
		netHeadYaw: Float,
		headPitch: Float
	) {
		this.baseHumanoidModel = baseModel
		this.currentEntity = entity
		this.currentStack = stack
		this.animatable = animatable
		this.currentSlot = slot
		this.bufferSource = bufferSource
		this.partialTick = partialTick
		this.limbSwing = limbSwing
		this.limbSwingAmount = limbSwingAmount
		this.netHeadYaw = netHeadYaw
		this.headPitch = headPitch
	}

	override fun preRender(
		animatable: S,
		partialTick: Float,
		poseStack: PoseStack,
		bufferSource: MultiBufferSource,
		packedLight: Int
	) {
//		this.entityRenderTranslations = Matrix4f(poseStack.last().pose())
		grabRelevantBones(animatable.modelController.model)
		applyBaseTransformations(this.baseHumanoidModel)
		// 缩放
//		scaleModelForBaby(poseStack, animatable, partialTick, isReRender)
//		scaleModelForRender(this.scaleWidth, this.scaleHeight, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay)

		currentSlot?.let { applyBoneVisibilityBySlot(it) }
		super.preRender(animatable, partialTick, poseStack, bufferSource, packedLight)
	}

	override fun renderBones(
		name: String,
		bone: OBone,
		pose: ModelPose,
		poseStack: PoseStack,
		buffer: VertexConsumer,
		packedLight: Int,
		packedOverlay: Int,
		color: Int,
		partialTick: Float,
		force: Boolean
	) {
		if (boneInfoExpand[name]?.visible ?: false) return
		super.renderBones(name, bone, pose, poseStack, buffer, packedLight, packedOverlay, color, partialTick, force)
	}

	override fun doPostRenderCleanup() {
		boneInfoExpand.clear()
		currentEntity = null
		currentStack = null
		currentSlot = null
		baseHumanoidModel = null
		animatable = null
		bufferSource = null
		partialTick = 0f
		limbSwing = 0f
		limbSwingAmount = 0f
		netHeadYaw = 0f
		headPitch = 0f

		isFine = false

		armorFineRightArm = null
		armorFineLeftArm = null
		armorPants = null
		armorHead = null
		armorBody = null
		armorRightArm = null
		armorLeftArm = null
		armorRightLeg = null
		armorLeftLeg = null
		armorRightBoot = null
		armorLeftBoot = null
	}

	fun OBone?.setVisible(visible: Boolean) {
		this ?: return
		boneInfoExpand[name]?.visible = visible
	}

	fun OBone?.setRecursionVisible(visible: Boolean) {
		this ?: return
		getChildren().forEach { it.setVisible(visible) }
	}

	fun OBone?.isVisible(): Boolean {
		this ?: return false
		return boneInfoExpand[name]?.visible ?: true
	}

	fun OBone?.getInfo(): OBoneInfo? {
		this ?: return null
		return boneInfoExpand[name]
	}

	fun OBone?.getPose(): BonePose? {
		this ?: return null
		return lastModelInstance?.pose?.bonePoses[name]
	}

	fun OBone?.matchModelPartRot(modelPart: ModelPart) {
		this ?: return
		tmpM4.run { rotateZYX(-modelPart.xRot, -modelPart.yRot, modelPart.zRot) }
	}

	fun OBone?.getChildren(): List<OBone> {
		this ?: return emptyList()
		return rootModel.bones.values.filter { it.parentName == this.name }
	}

	data class OBoneInfo(
		var bone: OBone,
		var visible: Boolean = false
	)
}