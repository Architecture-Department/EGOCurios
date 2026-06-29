package architecture.ego_curios.events.registry.client

import architecture.ego_curios.client.renderer.GeoCurioRenderer
import architecture.ego_curios.common.item.EgoCurioItem
import architecture.ego_curios.util.EgoCuriosUtil
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.registries.DeferredItem
import top.theillusivec4.curios.api.client.CuriosRendererRegistry
import java.util.function.Function

@EventBusSubscriber(modid = EgoCuriosUtil.ID, value = [Dist.CLIENT])
object CurioRenderersRegistrar {
	private val RENDERER_MAP: MutableMap<DeferredItem<EgoCurioItem>, Function<EgoCurioItem, GeoCurioRenderer<EgoCurioItem>>> =
		HashMap()

	/**
	 * 注册饰品渲染
	 */
	@SubscribeEvent
	fun onClientSetup(event: FMLClientSetupEvent) {
		for (entry in RENDERER_MAP) {
			val egoCurioItem = entry.key.get()
			CuriosRendererRegistry.register(egoCurioItem) { entry.value.apply(egoCurioItem) }
		}
		// CuriosRendererRegistry.register(EGOCuriosItems.COMPREHENSION_BACK.asItem(), SparkGeoCurioRenderer::new)
		// CuriosRendererRegistry.register(EGOCuriosItems.TEST.asItem(), SparkGeoCurioRenderer::new)
	}

	@JvmStatic
	fun addRenderer(item: DeferredItem<EgoCurioItem>, renderer: Function<EgoCurioItem, GeoCurioRenderer<EgoCurioItem>>) {
		RENDERER_MAP[item] = renderer
	}
}
