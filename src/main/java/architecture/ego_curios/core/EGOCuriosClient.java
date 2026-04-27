package architecture.ego_curios.core;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = EGOCurios.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = EGOCurios.ID, value = Dist.CLIENT)
public final class EGOCuriosClient {
	public EGOCuriosClient(ModContainer container) {
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
	}

	@SubscribeEvent
	static void onClientSetup(FMLClientSetupEvent event) {
		EGOCurios.LOGGER.info("HELLO FROM CLIENT SETUP");
		EGOCurios.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
	}
}
