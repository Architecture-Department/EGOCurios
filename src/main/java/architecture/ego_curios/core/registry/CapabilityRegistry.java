package architecture.ego_curios.core.registry;

import architecture.ego_curios.core.EGOCurios;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

/**
 * 注册能力
 */
@EventBusSubscriber(modid = EGOCurios.ID)
public final class CapabilityRegistry {

	@SubscribeEvent
	public static void registerHighest(RegisterCapabilitiesEvent event) {
	}
}
