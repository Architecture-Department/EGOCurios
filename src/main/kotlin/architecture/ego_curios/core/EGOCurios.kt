package architecture.ego_curios.core

import architecture.ego_curios.core.EGOCuriosConstants.LOGGER
import architecture.ego_curios.core.registry.CurioRegistry
import architecture.ego_curios.init.EGOCuriosAttachments
import architecture.ego_curios.init.EGOCuriosCreativeModeTabs
import architecture.ego_curios.init.EGOCuriosItems
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.server.ServerStartingEvent
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(EGOCuriosConstants.ID)
@EventBusSubscriber
object EGOCurios {
	@SubscribeEvent
	fun onServerStarting(event: ServerStartingEvent) {
		LOGGER.info("HELLO from server starting")
	}

	init {
		val modContainer = LOADING_CONTEXT.activeContainer
		val modBus = MOD_BUS

		EGOCuriosCreativeModeTabs.REGISTRY.register(modBus)
		EGOCuriosItems.REGISTRY.register(modBus)
		EGOCuriosAttachments.REGISTRY.register(modBus)
		CurioRegistry.registry()
	}
}
