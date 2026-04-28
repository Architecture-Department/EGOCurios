package architecture.ego_curios.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;

public class GeoCuriosRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> {
	protected SlotContext slotContext;

	public GeoCuriosRenderer(T armorItem) {
		super(armorItem);
	}

	public GeoCuriosRenderer(GeoModel<T> model) {
		super(model);
	}

	public void prepForRender(SlotContext slotContext, Entity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> baseModel, MultiBufferSource bufferSource,
	                          float partialTick, float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.slotContext = slotContext;
		super.prepForRender(entity, stack, slot, baseModel, bufferSource, partialTick, limbSwing, limbSwingAmount, netHeadYaw, headPitch);
	}

	@Override
	public void doPostRenderCleanup() {
		slotContext = null;
		super.doPostRenderCleanup();
	}
}
