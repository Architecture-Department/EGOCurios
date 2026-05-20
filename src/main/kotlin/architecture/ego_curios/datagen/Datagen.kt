package architecture.ego_curios.datagen

import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.datagen.i18n.ZhCn
import architecture.ego_curios.datagen.tag.DatagenBlockTag
import architecture.ego_curios.datagen.tag.DatagenItemTag
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.data.DataGenerator
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.data.event.GatherDataEvent
import java.util.concurrent.CompletableFuture

/**
 * 数据生成主类
 */
@EventBusSubscriber(modid = EGOCurios.ID)
object Datagen {
	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val output = generator.packOutput
		val completableFuture = event.lookupProvider

		val existingFileHelper = event.existingFileHelper
		// 服务端数据生成
		buildServer(event, generator, ModDatagenDatapackBuiltinEntries(output, completableFuture, RegistrySetBuilder()))
		buildServer(event, generator, DatagenCuriosTest(output, existingFileHelper, completableFuture))

		val datagenBlockTag = DatagenBlockTag(output, completableFuture, existingFileHelper)
		buildServer(event, generator, datagenBlockTag)
		buildServer(
			event,
			generator,
			DatagenItemTag(output, completableFuture, datagenBlockTag.contentsGetter(), existingFileHelper)
		)

		// 客户端数据生成
		buildClient(event, generator, ZhCn(output))
		buildClient(event, generator, DatagenItemModel(output, existingFileHelper))
	}

	private fun <T : DataProvider> buildClient(event: GatherDataEvent, generator: DataGenerator, provider: T): T {
		return generator.addProvider(event.includeClient(), provider)
	}

	private fun <T : DataProvider> buildServer(event: GatherDataEvent, generator: DataGenerator, provider: T): T {
		return generator.addProvider(event.includeServer(), provider)
	}
}
