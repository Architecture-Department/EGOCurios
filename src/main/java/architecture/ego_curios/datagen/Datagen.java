package architecture.ego_curios.datagen;

import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.datagen.i18n.ZhCn;
import architecture.ego_curios.datagen.tag.DatagenBlockTag;
import architecture.ego_curios.datagen.tag.DatagenItemTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * 数据生成主类
 */
@EventBusSubscriber(modid = EGOCurios.ID)
public final class Datagen {
	@SubscribeEvent
	public static void gatherData(@NotNull GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> completableFuture = event.getLookupProvider();

		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		// 服务端数据生成|
		buildServer(event, generator, new ModDatagenDatapackBuiltinEntries(output, completableFuture, new RegistrySetBuilder()));
		buildServer(event, generator, new DatagenCuriosTest(output, existingFileHelper, completableFuture));

		DatagenBlockTag datagenBlockTag = new DatagenBlockTag(output, completableFuture, existingFileHelper);
		buildServer(event, generator, datagenBlockTag);
		buildServer(event, generator, new DatagenItemTag(output, completableFuture, datagenBlockTag.contentsGetter(), existingFileHelper));

		// 客户端数据生成
		buildClient(event, generator, new ZhCn(output));
		buildClient(event, generator, new DatagenItemModel(output, existingFileHelper));
	}

	private static <T extends DataProvider> @NotNull T buildClient(
		@NotNull GatherDataEvent event,
		@NotNull DataGenerator generator,
		T provider
	) {
		return generator.addProvider(event.includeClient(), provider);
	}

	private static <T extends DataProvider> @NotNull T buildServer(
		@NotNull GatherDataEvent event,
		@NotNull DataGenerator generator,
		T provider
	) {
		return generator.addProvider(event.includeServer(), provider);
	}
}
