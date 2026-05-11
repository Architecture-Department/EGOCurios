package architecture.ego_curios.core

import architecture.ego_curios.core.registry.CurioRegistry
import architecture.ego_curios.init.EGOCuriosAttachments
import architecture.ego_curios.init.EGOCuriosCreativeModeTabs
import architecture.ego_curios.init.EGOCuriosItems
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.server.ServerStartingEvent
import net.neoforged.neoforge.registries.DeferredRegister
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.jetbrains.annotations.Contract
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(EGOCurios.ID)
@EventBusSubscriber
object EGOCurios {
	const val ID: String = "ego_curios"
	const val NAME: String = "E.G.O.Curios"

	@JvmField
	val LOGGER: Logger = LogManager.getLogger(ID)

	init {
		val modContainer = LOADING_CONTEXT.activeContainer
		val modBus = MOD_BUS

		EGOCuriosCreativeModeTabs.REGISTRY.register(modBus)
		EGOCuriosItems.REGISTRY.register(modBus)
		EGOCuriosAttachments.REGISTRY.register(modBus)
		CurioRegistry.registry()
	}

	@SubscribeEvent
	fun onServerStarting(event: ServerStartingEvent) {
		LOGGER.info("HELLO from server starting")
	}

	@JvmStatic
	@Contract("_ -> new")
	fun modRl(name: String): ResourceLocation {
		return ResourceLocation.fromNamespaceAndPath(ID, name)
	}

	@JvmStatic
	@Contract(pure = true)
	fun modRlText(name: String): String {
		return "$ID:$name"
	}

	@JvmStatic
	fun <T> modRegister(registry: Registry<T>): DeferredRegister<T> {
		return DeferredRegister.create<T>(registry, ID)
	}

	@JvmStatic
	fun <T> modRegister(registry: ResourceKey<Registry<T>>): DeferredRegister<T> {
		return DeferredRegister.create<T>(registry, ID)
	}
}
