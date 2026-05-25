package architecture.ego_curios.events

import architecture.ego_curios.core.EGOCurios
import architecture.resonator_combat_framework.event.AddGeckoLibCachePathEvent
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import architecture.ego_curios.core.EGOCuriosConstants

@EventBusSubscriber(modid = EGOCuriosConstants.ID)
object ModEvents {
	@SubscribeEvent
	fun addGeckoLibCachePathEvent(event: AddGeckoLibCachePathEvent) {
		// event.addAnimationPath(EGOCuriosConstants.modRlText("animation"))
		// event.addModelPath(EGOCuriosConstants.modRlText("geo_model"))
	}
}
