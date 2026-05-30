package architecture.ego_curios.common.item

import architecture.ego_curios.common.payload.toc.CurioAppurtenanceSynchroPayload
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext

class TestEgoCurioItem(egoCurioBuilder: Builder<out EgoCurioItem>) : EgoCurioItem(egoCurioBuilder) {

	override fun onUnequip(slotContext: SlotContext, newStackInSlot: ItemStack, stackBeingUnequipped: ItemStack) {
		super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped)
		CurioAppurtenanceSynchroPayload.send(false, slotContext)
	}

	override fun onEquip(slotContext: SlotContext, previousStack: ItemStack, stackBeingEquipped: ItemStack) {
		super.onEquip(slotContext, previousStack, stackBeingEquipped)
		CurioAppurtenanceSynchroPayload.send(true, slotContext)
	}

	// @Override
	fun add(entity: Entity, itemStack: ItemStack, map: Map<String, *>) {
		if (entity !is LivingEntity) return
		val slotContext = ComprehensionBackCurioItem.getSlotContext(map) ?: return
		// livingEntity.getAppurtenanceInfoMap().put("curio_" + slotContext.getIdentifier(), new CurioAnimatedAccessoryInfo<>(
		//     livingEntity,
		//     itemStack,
		//     new ModelIndex("curio", EGOCuriosConstants.modRl("test"))
		// ))
	}

	// @Override
	fun remove(entity: Entity, itemStack: ItemStack, map: Map<String, *>) {
		if (entity !is LivingEntity) return
		val slotContext = ComprehensionBackCurioItem.getSlotContext(map) ?: return
		// entity.getAppurtenanceInfoMap().remove("curio_" + slotContext.getIdentifier())
	}
}
