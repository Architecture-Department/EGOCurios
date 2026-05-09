package architecture.ego_curios.common.item;

import architecture.ego_curios.CurioAnimatedAccessoryInfo;
import architecture.ego_curios.common.payload.toc.CurioAppurtenanceSynchroPayload;
import architecture.ego_curios.common.payload.toc.SlotContextExpand;
import architecture.ego_curios.core.EGOCurios;
import architecture.resonator_combat_framework.api.IAppurtenanceExecute;
import cn.solarmoon.spark_core.animation.model.ModelIndex;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Map;

public class TestEgoCurioItem extends EgoCurioItem implements IAppurtenanceExecute {
	public TestEgoCurioItem(Builder<? extends EgoCurioItem> egoCurioBuilder) {
		super(egoCurioBuilder);
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStackInSlot, ItemStack stackBeingUnequipped) {
		super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped);
		CurioAppurtenanceSynchroPayload.send(false, slotContext);
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack previousStack, ItemStack stackBeingEquipped) {
		super.onEquip(slotContext, previousStack, stackBeingEquipped);
		CurioAppurtenanceSynchroPayload.send(true, slotContext);
	}

	@Override
	public void add(@NotNull Entity entity, @NotNull ItemStack itemStack, @NotNull Map<@NotNull String, ?> map) {
		if (!(entity instanceof LivingEntity livingEntity)) return;
		SlotContextExpand slotContext = ComprehensionBackCurioItem.getSlotContext(map);
		if (slotContext == null) {
			return;
		}
		livingEntity.getAppurtenanceInfoMap().put("curio_" + slotContext.getIdentifier(), new CurioAnimatedAccessoryInfo<>(
			livingEntity,
			itemStack,
			new ModelIndex("curio", EGOCurios.modRl("test"))
		));
	}

	@Override
	public void remove(@NotNull Entity entity, @NotNull ItemStack itemStack, @NotNull Map<@NotNull String, ?> map) {
		if (!(entity instanceof LivingEntity livingEntity)) return;
		SlotContextExpand slotContext = ComprehensionBackCurioItem.getSlotContext(map);
		if (slotContext == null) {
			return;
		}
		entity.getAppurtenanceInfoMap().remove("curio_" + slotContext.getIdentifier());
	}
}
