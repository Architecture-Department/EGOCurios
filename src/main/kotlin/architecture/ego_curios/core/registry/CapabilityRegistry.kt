package architecture.ego_curios.core.registry

import architecture.ego_curios.core.EGOCurios
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import architecture.ego_curios.core.EGOCuriosConstants

/**
 * 注册能力
 */
@EventBusSubscriber(modid = EGOCuriosConstants.ID)
object CapabilityRegistry {

	@SubscribeEvent
	fun registerHighest(event: RegisterCapabilitiesEvent) {
	}
}
