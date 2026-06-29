package architecture.ego_curios.events.registry

import architecture.ego_curios.util.EgoCuriosUtil
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent

/**
 * 注册能力
 */
@EventBusSubscriber(modid = EgoCuriosUtil.ID)
object CapabilityRegistry {

	@SubscribeEvent
	fun registerHighest(event: RegisterCapabilitiesEvent) {
	}
}
