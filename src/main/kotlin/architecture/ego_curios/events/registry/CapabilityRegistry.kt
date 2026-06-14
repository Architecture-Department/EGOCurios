package architecture.ego_curios.events.registry

import architecture.ego_curios.util.EGOCuriosUtil
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent

/**
 * 注册能力
 */
@EventBusSubscriber(modid = EGOCuriosUtil.ID)
object CapabilityRegistry {

	@SubscribeEvent
	fun registerHighest(event: RegisterCapabilitiesEvent) {
	}
}
