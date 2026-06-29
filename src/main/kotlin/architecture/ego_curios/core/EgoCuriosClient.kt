package architecture.ego_curios.core

import architecture.ego_curios.util.EgoCuriosUtil
import net.minecraft.client.Minecraft
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import thedarkcolour.kotlinforforge.neoforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(value = EgoCuriosUtil.ID, dist = [Dist.CLIENT])
@EventBusSubscriber(modid = EgoCuriosUtil.ID, value = [Dist.CLIENT])
object EgoCuriosClient {
	init {
		val modContainer = LOADING_CONTEXT.activeContainer
		val modBus = MOD_BUS

		modContainer.registerExtensionPoint(
			IConfigScreenFactory::class.java,
			IConfigScreenFactory(::ConfigurationScreen)
		)
	}

	@SubscribeEvent
	fun onClientSetup(event: FMLClientSetupEvent) {
		EgoCuriosUtil.LOGGER.info("HELLO FROM CLIENT SETUP")
		EgoCuriosUtil.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().user.name)
	}
}
