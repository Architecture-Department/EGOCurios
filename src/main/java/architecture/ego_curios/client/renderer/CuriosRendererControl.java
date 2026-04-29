package architecture.ego_curios.client.renderer;

import architecture.ego_curios.common.item.EgoCurioItem;
import architecture.ego_curios.core.EGOCuriosConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CuriosRendererControl<T extends Item & GeoItem> implements ICurioRenderer {
	protected final AnimatableInstanceCache animatableInstanceCache;
	protected final GeoArmorRenderer<T> renderer;
	protected final T curioItem;

	public CuriosRendererControl(EgoCurioItem curioItem) {
		//noinspection unchecked
		this((T) curioItem, new GeoArmorRenderer<>((GeoModel<T>) curioItem.getModel()));
	}

	public CuriosRendererControl(T curioItem, GeoArmorRenderer<T> renderer) {
		this.curioItem = curioItem;
		this.animatableInstanceCache = curioItem.getAnimatableInstanceCache();
		this.renderer = renderer;
	}

	public static @NotNull EquipmentSlot getEquipmentSlot(final SlotContext slotContext) {
		return switch (slotContext.identifier()) {
			case EGOCuriosConstants.EGO_CURIOS_HEADWEAR, EGOCuriosConstants.EGO_CURIOS_HEAD,
				 EGOCuriosConstants.EGO_CURIOS_HINDBRAIN, EGOCuriosConstants.EGO_CURIOS_EYE,
				 EGOCuriosConstants.EGO_CURIOS_FACE, EGOCuriosConstants.EGO_CURIOS_CHEEK,
				 EGOCuriosConstants.EGO_CURIOS_MASK, EGOCuriosConstants.EGO_CURIOS_MOUTH -> EquipmentSlot.HEAD;
			case EGOCuriosConstants.EGO_CURIOS_NECK, EGOCuriosConstants.EGO_CURIOS_BROOCH,
				 EGOCuriosConstants.EGO_CURIOS_LEFT_BACK, EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK ->
					EquipmentSlot.CHEST;
			case EGOCuriosConstants.EGO_CURIOS_HAND, EGOCuriosConstants.EGO_CURIOS_GLOVE -> EquipmentSlot.MAINHAND;
			default -> EquipmentSlot.BODY;
		};
	}

	@Override
	public <LE extends LivingEntity, M extends EntityModel<LE>> void render(
		ItemStack stack,
		SlotContext slotContext,
		PoseStack matrixStack,
		RenderLayerParent<LE, M> renderLayerParent,
		MultiBufferSource renderTypeBuffer,
		int light,
		float limbSwing,
		float limbSwingAmount,
		float partialTicks,
		float ageInTicks,
		float netHeadYaw,
		float headPitch
	) {
		M entityModel = renderLayerParent.getModel();
		if (!(entityModel instanceof HumanoidModel<?> humanoidModel)) {
			return;
		}

		var renderer = this.renderer;
		// 预推送当前渲染状态机
		if (renderer instanceof GeoCuriosRenderer<T> geoCuriosRenderer) {
			geoCuriosRenderer.prepForRender(slotContext, slotContext.entity(), stack, getEquipmentSlot(slotContext), humanoidModel, renderTypeBuffer, partialTicks, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
		} else {
			renderer.prepForRender(slotContext.entity(), stack, getEquipmentSlot(slotContext), humanoidModel, renderTypeBuffer, partialTicks, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
		}
		// 进行渲染
		renderer.defaultRender(matrixStack, this.curioItem, renderTypeBuffer, null, null, netHeadYaw, partialTicks, light);
	}
}
