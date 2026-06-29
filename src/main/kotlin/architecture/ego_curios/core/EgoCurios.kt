package architecture.ego_curios.core

import architecture.ego_curios.events.registry.CurioRegistry
import architecture.ego_curios.init.EgoCuriosAttachments
import architecture.ego_curios.init.EgoCuriosCreativeModeTabs
import architecture.ego_curios.init.EgoCuriosItems
import architecture.ego_curios.util.EgoCuriosUtil
import architecture.ego_curios.util.EgoCuriosUtil.LOGGER
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.server.ServerStartingEvent
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(EgoCuriosUtil.ID)
@EventBusSubscriber
object EgoCurios {
	@SubscribeEvent
	fun onServerStarting(event: ServerStartingEvent) {
		LOGGER.info("HELLO from server starting")
	}

	init {
		val modContainer = LOADING_CONTEXT.activeContainer
		val modBus = MOD_BUS

		EgoCuriosCreativeModeTabs.REGISTRY.register(modBus)
		EgoCuriosItems.REGISTRY.register(modBus)
		EgoCuriosAttachments.REGISTRY.register(modBus)
		CurioRegistry.registry()
	}
}
