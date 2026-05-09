package architecture.ego_curios.client.renderer;

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
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;

public class GeoCurioRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> implements CuriosRenderer {
	protected SlotContext slotContext;

	public GeoCurioRenderer(GeoModel<T> model) {
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
		LivingEntity entity = slotContext.entity();
		EquipmentSlot equipmentSlot = CuriosRenderer.getEquipmentSlot(slotContext);
		prepForRender(slotContext, entity, stack, equipmentSlot, humanoidModel, renderTypeBuffer, partialTicks, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
		// 进行渲染
		//noinspection unchecked
		T item = (T) stack.getItem();
		defaultRender(matrixStack, item, renderTypeBuffer, null, null, netHeadYaw, partialTicks, light);
	}
}
