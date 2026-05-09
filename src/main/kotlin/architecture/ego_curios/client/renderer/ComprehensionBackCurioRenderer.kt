package architecture.ego_curios.client.renderer

import architecture.ego_curios.common.item.ComprehensionBackCurioItem
import architecture.ego_curios.core.EGOCuriosConstants
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.cache.`object`.GeoBone
import software.bernie.geckolib.model.GeoModel
import top.theillusivec4.curios.api.SlotContext

class ComprehensionBackCurioRenderer(model: GeoModel<ComprehensionBackCurioItem?>?) :
	GeoCurioRenderer<ComprehensionBackCurioItem?>(model) {
	var leftUpperTentacleRoot: GeoBone? = null
	var leftMiddleTentacleRoot: GeoBone? = null
	var leftLowerTentacleRoot: GeoBone? = null

	var rightUpperTentacleRoot: GeoBone? = null
	var rightMiddleTentacleRoot: GeoBone? = null
	var rightLowerTentacleRoot: GeoBone? = null

	override fun grabRelevantBones(bakedModel: BakedGeoModel) {
		super.grabRelevantBones(bakedModel)
		// 因为模型搞反了，所以这里需要搞反
		// TODO 依旧模型的命名反了
		rightUpperTentacleRoot = bakedModel.getBone("left_upper_tentacle_root").orElse(null)
		rightMiddleTentacleRoot = bakedModel.getBone("left_middle_tentacle_root").orElse(null)
		rightLowerTentacleRoot = bakedModel.getBone("left_lower_tentacle_root").orElse(null)
		leftUpperTentacleRoot = bakedModel.getBone("right_upper_tentacle_root").orElse(null)
		leftMiddleTentacleRoot = bakedModel.getBone("right_middle_tentacle_root").orElse(null)
		leftLowerTentacleRoot = bakedModel.getBone("right_lower_tentacle_root").orElse(null)
	}

	override fun prepForRender(
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
		super.prepForRender(
			slotContext,
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
		when (slotContext.identifier()) {
			EGOCuriosConstants.EGO_CURIOS_LEFT_BACK -> {
				setBoneVisible(rightUpperTentacleRoot, false)
				setBoneVisible(rightMiddleTentacleRoot, false)
				setBoneVisible(rightLowerTentacleRoot, false)
			}

			EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> {
				setBoneVisible(leftUpperTentacleRoot, false)
				setBoneVisible(leftMiddleTentacleRoot, false)
				setBoneVisible(leftLowerTentacleRoot, false)
			}
		}
	}

	override fun doPostRenderCleanup() {
		super.doPostRenderCleanup()
		setBoneVisible(rightUpperTentacleRoot, true)
		setBoneVisible(rightMiddleTentacleRoot, true)
		setBoneVisible(rightLowerTentacleRoot, true)
		setBoneVisible(leftUpperTentacleRoot, true)
		setBoneVisible(leftMiddleTentacleRoot, true)
		setBoneVisible(leftLowerTentacleRoot, true)
	}
}
