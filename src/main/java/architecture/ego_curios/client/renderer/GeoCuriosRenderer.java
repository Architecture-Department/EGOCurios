package architecture.ego_curios.client.renderer;

import architecture.ego_curios.core.EGOCuriosConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class GeoCuriosRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> implements ICurioRenderer {
	protected SlotContext slotContext;

	public GeoCuriosRenderer(GeoModel<T> model) {
		super(model);
	}

	public void prepForRender(
		SlotContext slotContext, Entity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> baseModel,
		MultiBufferSource bufferSource,
		float partialTick, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch
	) {
		this.slotContext = slotContext;
		super.prepForRender(entity, stack, slot, baseModel, bufferSource, partialTick, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	@Override
	public void doPostRenderCleanup() {
		slotContext = null;
		super.doPostRenderCleanup();
	}

	public static @NotNull EquipmentSlot getEquipmentSlot(final SlotContext slotContext) {
		return switch (slotContext.identifier()) {
			case EGOCuriosConstants.EGO_CURIOS_HEADWEAR,
			     EGOCuriosConstants.EGO_CURIOS_HEAD,
			     EGOCuriosConstants.EGO_CURIOS_HINDBRAIN,
			     EGOCuriosConstants.EGO_CURIOS_EYE,
			     EGOCuriosConstants.EGO_CURIOS_FACE,
			     EGOCuriosConstants.EGO_CURIOS_CHEEK,
			     EGOCuriosConstants.EGO_CURIOS_MASK,
			     EGOCuriosConstants.EGO_CURIOS_MOUTH -> EquipmentSlot.HEAD;

			case EGOCuriosConstants.EGO_CURIOS_NECK,
			     EGOCuriosConstants.EGO_CURIOS_BROOCH,
			     EGOCuriosConstants.EGO_CURIOS_LEFT_BACK,
			     EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> EquipmentSlot.CHEST;

			case EGOCuriosConstants.EGO_CURIOS_HAND,
			     EGOCuriosConstants.EGO_CURIOS_GLOVE -> EquipmentSlot.MAINHAND;

			default -> EquipmentSlot.BODY;
		};
	}

	@Override
	public <LE extends LivingEntity, M extends EntityModel<LE>> void render(
		ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<LE, M> renderLayerParent,
		MultiBufferSource renderTypeBuffer,
		int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch
	) {
		M entityModel = renderLayerParent.getModel();
		if (!(entityModel instanceof HumanoidModel<?> humanoidModel)) {
			return;
		}
		// 预推送当前渲染状态机
		prepForRender(slotContext, slotContext.entity(), stack, getEquipmentSlot(slotContext), humanoidModel, renderTypeBuffer, partialTicks, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
		// 进行渲染
		//noinspection unchecked
		defaultRender(matrixStack, (T) stack.getItem(), renderTypeBuffer, null, null, netHeadYaw, partialTicks, light);
	}
}
