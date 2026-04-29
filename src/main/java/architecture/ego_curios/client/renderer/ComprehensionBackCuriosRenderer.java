package architecture.ego_curios.client.renderer;

import architecture.ego_curios.common.item.ComprehensionBackCurioItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import top.theillusivec4.curios.api.SlotContext;

public class ComprehensionBackCuriosRenderer extends GeoCuriosRenderer<ComprehensionBackCurioItem> {
	protected GeoBone leftUpperTentacleRoot = null;
	protected GeoBone leftMiddleTentacleRoot = null;
	protected GeoBone leftLowerTentacleRoot = null;

	protected GeoBone rightUpperTentacleRoot = null;
	protected GeoBone rightMiddleTentacleRoot = null;
	protected GeoBone rightLowerTentacleRoot = null;

	public ComprehensionBackCuriosRenderer(ComprehensionBackCurioItem armorItem) {
		super(armorItem);
	}

	@Override
	protected void grabRelevantBones(BakedGeoModel bakedModel) {
		super.grabRelevantBones(bakedModel);
		// 因为模型搞反了，所以这里需要搞反
		rightUpperTentacleRoot = bakedModel.getBone("left_upper_tentacle_root").orElse(null);
		rightMiddleTentacleRoot = bakedModel.getBone("left_middle_tentacle_root").orElse(null);
		rightLowerTentacleRoot = bakedModel.getBone("left_lower_tentacle_root").orElse(null);
		leftUpperTentacleRoot = bakedModel.getBone("right_upper_tentacle_root").orElse(null);
		leftMiddleTentacleRoot = bakedModel.getBone("right_middle_tentacle_root").orElse(null);
		leftLowerTentacleRoot = bakedModel.getBone("right_lower_tentacle_root").orElse(null);
	}

	@Override
	public void prepForRender(SlotContext slotContext, Entity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> baseModel, MultiBufferSource bufferSource, float partialTick, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		super.prepForRender(slotContext, entity, stack, slot, baseModel, bufferSource, partialTick, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
//		switch (slotContext.identifier()) {
//			case EGOCuriosConstants.EGO_CURIOS_LEFT_BACK -> {
//				setBoneVisible(rightUpperTentacleRoot, false);
//				setBoneVisible(rightMiddleTentacleRoot, false);
//				setBoneVisible(rightLowerTentacleRoot, false);
//			}
//			case EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> {
//				setBoneVisible(leftUpperTentacleRoot, false);
//				setBoneVisible(leftMiddleTentacleRoot, false);
//				setBoneVisible(leftLowerTentacleRoot, false);
//			}
//		}
	}

	@Override
	public void doPostRenderCleanup() {
		super.doPostRenderCleanup();
		setBoneVisible(rightUpperTentacleRoot, true);
		setBoneVisible(rightMiddleTentacleRoot, true);
		setBoneVisible(rightLowerTentacleRoot, true);
		setBoneVisible(leftUpperTentacleRoot, true);
		setBoneVisible(leftMiddleTentacleRoot, true);
		setBoneVisible(leftLowerTentacleRoot, true);
	}
}
