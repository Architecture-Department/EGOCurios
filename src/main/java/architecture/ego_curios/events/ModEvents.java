package architecture.ego_curios.events;

import architecture.ego_curios.core.EGOCurios;
import architecture.resonator_combat_framework.api.event.AddGeckoLibCachePathEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = EGOCurios.ID)
public final class ModEvents {
	@SubscribeEvent()
	public static void addGeckoLibCachePathEvent(AddGeckoLibCachePathEvent event) {
//		event.addAnimationPath(EGOCurios.modRlText("animation"));
//		event.addModelPath(EGOCurios.modRlText("geo_model"));
	}
}
