package architecture.ego_curios.core.registry.client;

import architecture.ego_curios.client.renderer.GeoCuriosRenderer;
import architecture.ego_curios.common.item.EgoCurioItem;
import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.init.EGOCuriosItems;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.ApiStatus;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(modid = EGOCurios.ID, value = Dist.CLIENT)
public final class CurioRenderersRegistrar {
	@ApiStatus.Internal
	private static final Map<DeferredItem<EgoCurioItem>, Function<EgoCurioItem, GeoCuriosRenderer<EgoCurioItem>>> RENDERER_MAP = new HashMap<>();

	/**
	 * 注册饰品渲染
	 */
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		for (DeferredHolder<Item, ? extends Item> itemDeferredHolder : EGOCuriosItems.REGISTRY.getEntries()) {
			if (!(itemDeferredHolder.get() instanceof EgoCurioItem egoCurioItem)) {
				continue;
			}
			@SuppressWarnings("SuspiciousMethodCalls")
			var function = RENDERER_MAP.get(itemDeferredHolder);
			if (function == null) {
				return;
			}
			CuriosRendererRegistry.register(egoCurioItem, () -> function.apply(egoCurioItem));
		}
	}

	public static void addRenderer(DeferredItem<EgoCurioItem> item, Function<EgoCurioItem, GeoCuriosRenderer<EgoCurioItem>> renderer) {
		if (!FMLEnvironment.dist.isClient()) {
			return;
		}
		RENDERER_MAP.put(item, renderer);
	}
}
