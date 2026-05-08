package architecture.ego_curios.client.renderer

import cn.solarmoon.spark_core.animation.IAnimatable
import cn.solarmoon.spark_core.animation.model.ModelPose
import cn.solarmoon.spark_core.animation.model.origin.OBone
import cn.solarmoon.spark_core.animation.model.origin.OCube
import cn.solarmoon.spark_core.animation.model.origin.OMesh
import cn.solarmoon.spark_core.animation.model.origin.OModel
import cn.solarmoon.spark_core.animation.renderer.IGeoRenderer
import cn.solarmoon.spark_core.animation.renderer.layer.RenderLayer
import cn.solarmoon.spark_core.animation.renderer.tmpM3
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.renderer.MultiBufferSource

interface IGeoRendererExpand<T, S> : IGeoRenderer<T, S> where S : IAnimatable<T> {
	// TODO 未满足要求
	override val layers: MutableList<RenderLayer<T, S>>

	/** 渲染入口*/
	override fun render(
		animatable: S,
		partialTick: Float,
		poseStack: PoseStack,
		bufferSource: MultiBufferSource,
		packedLight: Int
	) {
		preRender(animatable, partialTick, poseStack, bufferSource, packedLight)
		defaultRender(
			animatable,
			partialTick,
			poseStack,
			bufferSource,
			packedLight,
			getOverlay(animatable, partialTick),
			getColor(animatable, partialTick)
		)
		doPostRenderCleanup()
	}

	/** 渲染前*/
	fun preRender(
		animatable: S,
		partialTick: Float,
		poseStack: PoseStack,
		bufferSource: MultiBufferSource,
		packedLight: Int
	) {
	}

	/** 渲染*/
	fun defaultRender(
		animatable: S,
		partialTick: Float,
		poseStack: PoseStack,
		bufferSource: MultiBufferSource,
		packedLight: Int,
		overlay: Int,
		color: Int,
	) {
		val buffer = bufferSource.getBuffer(getRenderType(animatable))
		poseStack.pushPose()
		poseStack.mulPose(animatable.getWorldPositionMatrix(partialTick))
		animatable.modelController.model?.let {
			renderModel(it.origin, it.pose, poseStack, buffer, packedLight, overlay, color, partialTick)
		}
		layers.forEach {
			it.render(animatable, partialTick, poseStack, bufferSource, packedLight, -1)
		}
		poseStack.popPose()
	}

	/** 模型渲染 */
	fun renderModel(
		model: OModel,
		modelPose: ModelPose,
		poseStack: PoseStack,
		buffer: VertexConsumer,
		packedLight: Int,
		packedOverlay: Int,
		color: Int,
		partialTick: Float,
		force: Boolean = false
	) {
		model.bones.forEach { (name, bone) ->
			renderBones(name, bone, modelPose, poseStack, buffer, packedLight, packedOverlay, color, partialTick)
		}
	}

	/** 渲染骨骼 */
	fun renderBones(
		name: String,
		bone: OBone,
		pose: ModelPose,
		poseStack: PoseStack,
		buffer: VertexConsumer,
		packedLight: Int,
		packedOverlay: Int,
		color: Int,
		partialTick: Float,
		force: Boolean = false
	) {
		bone.tmpM4.identity()
		tmpM3.identity()
		bone.applyTransformWithParents(pose, bone.tmpM4, partialTick)
		poseStack.pushPose()
		poseStack.mulPose(bone.tmpM4)
		// 渲染所有cubes
		for (cube in bone.cubes) {
			renderCubes(cube, poseStack, buffer, packedLight, packedOverlay, color, force)
		}

		// 渲染mesh（可为null）
		renderMesh(bone.mesh, bone, buffer, packedLight, packedOverlay, color, partialTick, force)
		poseStack.popPose()
	}

	/** 渲染块 */
	fun renderCubes(
		cube: OCube,
		poseStack: PoseStack,
		buffer: VertexConsumer,
		packedLight: Int,
		packedOverlay: Int,
		color: Int,
		force: Boolean
	) {
		cube.renderVertexes(
			poseStack,
			buffer,
			packedLight,
			packedOverlay,
			color,
			force
		)
	}

	/** 渲染网格 */
	fun renderMesh(
		mesh: OMesh?,
		bone: OBone,
		buffer: VertexConsumer,
		packedLight: Int,
		packedOverlay: Int,
		color: Int,
		partialTick: Float,
		force: Boolean
	) {
		mesh?.renderVertexes(bone.tmpM4, tmpM3, buffer, packedLight, packedOverlay, color, partialTick, force)
	}

	// 渲染结束
	fun doPostRenderCleanup() {
	}
}
