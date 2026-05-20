package architecture.ego_curios.events

import architecture.ego_curios.core.EGOCurios
import architecture.resonator_combat_framework.event.AddGeckoLibCachePathEvent
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber

@EventBusSubscriber(modid = EGOCurios.ID)
object ModEvents {
	@SubscribeEvent
	fun addGeckoLibCachePathEvent(event: AddGeckoLibCachePathEvent) {
		// event.addAnimationPath(EGOCurios.modRlText("animation"))
		// event.addModelPath(EGOCurios.modRlText("geo_model"))
	}
}
