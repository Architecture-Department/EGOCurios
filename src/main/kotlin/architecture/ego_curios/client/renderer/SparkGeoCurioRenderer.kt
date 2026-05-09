package architecture.ego_curios.client.renderer

import architecture.ego_curios.CurioAnimatedAccessoryInfo
import architecture.ego_curios.client.renderer.CuriosRenderer.Companion.getEquipmentSlot
import cn.solarmoon.spark_core.animation.renderer.IGeoRenderer
import cn.solarmoon.spark_core.animation.renderer.layer.RenderLayer
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext

open class SparkGeoCurioRenderer : GeoArmorRenderer<ItemStack, CurioAnimatedAccessoryInfo<*>>(),
	IGeoRenderer<ItemStack, CurioAnimatedAccessoryInfo<*>>, CuriosRenderer {
	override val layers: MutableList<RenderLayer<ItemStack, CurioAnimatedAccessoryInfo<*>>> = mutableListOf()
	protected var slotContext: SlotContext? = null

	override fun <T : LivingEntity?, M : EntityModel<T?>?> render(
		stack: ItemStack,
		slotContext: SlotContext,
		matrixStack: PoseStack,
		renderLayerParent: RenderLayerParent<T, M>,
		renderTypeBuffer: MultiBufferSource,
		light: Int,
		limbSwing: Float,
		limbSwingAmount: Float,
		partialTicks: Float,
		ageInTicks: Float,
		netHeadYaw: Float,
		headPitch: Float
	) {
		val entityModel = renderLayerParent.getModel()
		if (entityModel !is HumanoidModel<*>) {
			return
		}
		val entity = slotContext.entity
		val animatable = entity.appurtenanceInfoMap["curio_" + slotContext.identifier]
		if (animatable !is CurioAnimatedAccessoryInfo<*>) return
		val slot = getEquipmentSlot(slotContext)
		prepForRender(
			slotContext,
			animatable,
			entity,
			stack,
			slot,
			entityModel,
			bufferSource,
			partialTick,
			limbSwing,
			limbSwingAmount,
			netHeadYaw,
			headPitch
		)
		render(animatable, partialTicks, matrixStack, renderTypeBuffer, light)
	}

	fun prepForRender(
		slotContext: SlotContext?,
		animatable: CurioAnimatedAccessoryInfo<*>,
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
		this.slotContext = slotContext
		super.prepForRender(
			animatable,
			entity,
			stack,
			slot,
			baseModel,
			bufferSource,
			partialTick,
			limbSwing,
			limbSwingAmount,
			netHeadYaw,
			headPitch
		)
	}

	override fun doPostRenderCleanup() {
		slotContext = null
		super.doPostRenderCleanup()
	}
}