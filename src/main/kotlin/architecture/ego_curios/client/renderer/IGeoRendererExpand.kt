package architecture.ego_curios.client.renderer

import cn.solarmoon.spark_core.animation.IAnimatable
import cn.solarmoon.spark_core.animation.model.ModelInstance
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
import net.minecraft.client.renderer.RenderType
import org.joml.Matrix4f

interface IGeoRendererExpand<T, S> : IGeoRenderer<T, S> where S : IAnimatable<T> {

	var entityRenderTranslations: Matrix4f
	var modelRenderTranslations: Matrix4f

	var animatable: S?

	var lastModelInstance: ModelInstance?

	var partialTick: Float

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
		poseStack.pushPose()
		val model = getModel(animatable)
		val renderType = getRenderType(animatable)
		val buffer = getBuffer(bufferSource, renderType)
		val packedOverlay = getOverlay(animatable, partialTick)
		val renderColor = getColor(animatable, partialTick)
		preRender(
			poseStack,
			animatable,
			model,
			bufferSource,
			buffer,
			false,
			partialTick,
			packedLight,
			packedOverlay,
			renderColor
		)
		if (firePreRenderEvent(
				poseStack,
				model,
				bufferSource,
				partialTick,
				packedLight
			)
		) {
			preApplyRenderLayers(
				poseStack,
				animatable,
				model,
				renderType,
				bufferSource,
				buffer,
				partialTick,
				packedLight,
				packedOverlay
			)
			actuallyRender(
				poseStack,
				animatable,
				model,
				renderType,
				bufferSource,
				buffer,
				false,
				partialTick,
				packedLight,
				packedOverlay,
				renderColor
			)
			applyRenderLayers(
				poseStack,
				animatable,
				model,
				renderType,
				bufferSource,
				buffer,
				partialTick,
				packedLight,
				packedOverlay,
				renderColor
			)
			postRender(
				poseStack,
				animatable,
				model,
				bufferSource,
				buffer,
				false,
				partialTick,
				packedLight,
				packedOverlay,
				renderColor
			)
			firePostRenderEvent(
				poseStack,
				model,
				bufferSource,
				partialTick,
				packedLight
			)
		}
		poseStack.popPose()
		renderFinal(
			poseStack,
			animatable,
			model,
			bufferSource,
			buffer,
			partialTick,
			packedLight,
			packedOverlay,
			renderColor
		)
		doPostRenderCleanup()
	}

	/**
	 * Re-renders the provided [BakedGeoModel] using the existing [GeoRenderer]
	 * 
	 * 
	 * Usually you'd use this for rendering alternate [RenderType] layers or for sub-model rendering whilst inside a [GeoRenderLayer] or similar
	 */
	fun reRender(
		model: BakedGeoModel, poseStack: PoseStack, bufferSource: MultiBufferSource, animatable: T?,
		renderType: RenderType?, buffer: VertexConsumer?, partialTick: Float,
		packedLight: Int, packedOverlay: Int, colour: Int
	) {
		poseStack.pushPose()
		preRender(
			poseStack,
			animatable,
			model,
			bufferSource,
			buffer!!,
			true,
			partialTick,
			packedLight,
			packedOverlay,
			colour
		)
		actuallyRender(
			poseStack,
			animatable,
			model,
			renderType,
			bufferSource,
			buffer,
			true,
			partialTick,
			packedLight,
			packedOverlay,
			colour
		)
		postRender(
			poseStack,
			animatable,
			model,
			bufferSource,
			buffer,
			true,
			partialTick,
			packedLight,
			packedOverlay,
			colour
		)
		poseStack.popPose()
	}

	fun getBuffer(
		bufferSource: MultiBufferSource,
		renderType: RenderType
	): VertexConsumer = bufferSource.getBuffer(renderType)

	fun getModel(animatable: S): ModelInstance? = animatable.modelController.model

	fun firePostRenderEvent(
		poseStack: PoseStack,
		model: ModelInstance?,
		bufferSource: MultiBufferSource,
		partialTick: Float,
		packedLight: Int
	) {
	}

	fun postRender(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer,
		isReRender: Boolean,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int,
		renderColor: Int
	) {
		bufferSource ?: return
		this@IGeoRendererExpand.animatable ?: return
		renderType ?: return
		vertexConsumer ?: return

		poseStack.pushPose()
		poseStack.mulPose(this@IGeoRendererExpand.animatable!!.getWorldPositionMatrix(this@IGeoRendererExpand.partialTick))


		poseStack.popPose()
	}

	/** 渲染前*/
	fun preRender(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer,
		isReRender: Boolean,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int,
		colour: Int
	) {
		this.animatable = animatable
		this.lastModelInstance = model
		this.partialTick = partialTick
	}

	fun applyRenderLayers(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		renderType: RenderType?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer?,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int,
		colour: Int
	) {
		layers.forEach {
			it.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay)
		}
	}


	fun actuallyRender(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		renderType: RenderType?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer?,
		isReRender: Boolean,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int,
		colour: Int
	) {
		var buffer = buffer
		if (buffer == null) {
			if (renderType == null) return

			buffer = bufferSource.getBuffer(renderType)
		}

		updateAnimatedTextureFrame(animatable)

		renderModel(
			model.origin,
			model.pose,
			poseStack,
			buffer,
			packedLight,
			packedOverlay,
			colour,
			partialTick
		)
	}

	fun updateAnimatedTextureFrame(animatable: S) {
	}

	fun preApplyRenderLayers(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		renderType: RenderType?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer?,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int
	) {
	}

	fun firePreRenderEvent(
		poseStack: PoseStack,
		model: ModelInstance?,
		bufferSource: MultiBufferSource,
		partialTick: Float,
		packedLight: Int
	): Boolean

	fun renderFinal(
		poseStack: PoseStack,
		animatable: S,
		model: ModelInstance?,
		bufferSource: MultiBufferSource,
		buffer: VertexConsumer,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int,
		renderColor: Int
	) {
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
		mesh?.renderVertexes(
			bone.tmpM4,
			tmpM3,
			buffer,
			packedLight,
			packedOverlay,
			color,
			partialTick,
			force
		)
	}

	// 渲染结束
	fun doPostRenderCleanup() {
	}

	fun scaleModelForRender(
		widthScale: Float,
		heightScale: Float,
		poseStack: PoseStack,
		animatable: S?,
		model: ModelInstance?,
		isReRender: Boolean,
		partialTick: Float,
		packedLight: Int,
		packedOverlay: Int
	) {
		if (isReRender || !(widthScale != 1f || heightScale != 1f)) return
		poseStack.scale(widthScale, heightScale, widthScale)
	}
}
