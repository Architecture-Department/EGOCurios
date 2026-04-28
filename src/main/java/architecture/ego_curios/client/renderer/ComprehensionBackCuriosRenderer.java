package architecture.ego_curios.client.renderer;

import architecture.ego_curios.common.item.ComprehensionBackCurioItem;
import architecture.ego_curios.core.EGOCuriosConstants;
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
		leftUpperTentacleRoot = bakedModel.getBone("left_upper_tentacle_root").orElse(null);
		leftMiddleTentacleRoot = bakedModel.getBone("left_middle_tentacle_root").orElse(null);
		leftLowerTentacleRoot = bakedModel.getBone("left_lower_tentacle_root").orElse(null);
		rightUpperTentacleRoot = bakedModel.getBone("right_upper_tentacle_root").orElse(null);
		rightMiddleTentacleRoot = bakedModel.getBone("right_middle_tentacle_root").orElse(null);
		rightLowerTentacleRoot = bakedModel.getBone("right_lower_tentacle_root").orElse(null);
	}

	@Override
	public void prepForRender(SlotContext slotContext, Entity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> baseModel, MultiBufferSource bufferSource, float partialTick, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		super.prepForRender(slotContext, entity, stack, slot, baseModel, bufferSource, partialTick, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
		switch (slotContext.identifier()) {
			case EGOCuriosConstants.EGO_CURIOS_LEFT_BACK -> {
				rightUpperTentacleRoot.setHidden(true);
				rightMiddleTentacleRoot.setHidden(true);
				rightLowerTentacleRoot.setHidden(true);
			}
			case EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> {
				leftUpperTentacleRoot.setHidden(true);
				leftMiddleTentacleRoot.setHidden(true);
				leftLowerTentacleRoot.setHidden(true);
			}
		}
	}
}
