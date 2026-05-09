package architecture.ego_curios.client.renderer

import architecture.ego_curios.client.renderer.CuriosRenderer.Companion.getEquipmentSlot
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer
import top.theillusivec4.curios.api.SlotContext

open class GeoCurioRenderer<T>(model: GeoModel<T?>?) : GeoArmorRenderer<T?>(model),
	CuriosRenderer where T : Item?, T : GeoItem? {
	protected var slotContext: SlotContext? = null

	open fun prepForRender(
		slotContext: SlotContext,
		entity: Entity,
		stack: ItemStack,
		slot: EquipmentSlot,
		baseModel: HumanoidModel<*>,
		bufferSource: MultiBufferSource,
		partialTick: Float,
		limbSwing: Float,
		limbSwingAmount: Float,
		netHeadYaw: Float,
		headPitch: Float
	) {
		this.slotContext = slotContext
		super.prepForRender(
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

	override fun <LE : LivingEntity, M : EntityModel<LE?>> render(
		stack: ItemStack,
		slotContext: SlotContext,
		matrixStack: PoseStack,
		renderLayerParent: RenderLayerParent<LE, M>,
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
		// 预推送当前渲染状态机
		val entity = slotContext.entity()
		val equipmentSlot = getEquipmentSlot(slotContext)
		prepForRender(
			slotContext,
			entity,
			stack,
			equipmentSlot,
			entityModel,
			renderTypeBuffer,
			partialTicks,
			limbSwing,
			limbSwingAmount,
			netHeadYaw,
			headPitch
		)
		// 进行渲染
		val item = stack.item as T
		defaultRender(matrixStack, item, renderTypeBuffer, null, null, netHeadYaw, partialTicks, light)
	}
}
