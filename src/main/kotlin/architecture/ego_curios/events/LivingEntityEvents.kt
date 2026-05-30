package architecture.ego_curios.events

import architecture.ego_curios.core.EGOCuriosConstants
import architecture.ego_curios.init.EGOCuriosAttachments
import net.minecraft.world.entity.LivingEntity
import net.neoforged.bus.api.EventPriority
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.tick.EntityTickEvent

@EventBusSubscriber(modid = EGOCuriosConstants.ID)
object LivingEntityEvents {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	fun tickPost(event: EntityTickEvent.Post) {
		val entity = event.entity
		if (entity.isAlive && entity is LivingEntity) {
			val comprehensionBackCurioItemAttackLogic = entity.getExistingDataOrNull(EGOCuriosAttachments.ATTACK_LOGIC_HOLDER)
			comprehensionBackCurioItemAttackLogic?.tick()
		}
	}
}
