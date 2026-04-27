package architecture.ego_curios.init;

import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.datagen.i18n.ZhCn;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * 创造模式物品栏
 */
public final class EGOCuriosCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = EGOCurios.modRegister(BuiltInRegistries.CREATIVE_MODE_TAB);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EGO_CURIOS = register(
		"ego_curios", "E.G.O饰品", (name, zhCn) ->
			createCreativeModeTab(name, zhCn, (parameters, output) ->
				addRegistryItem(EGOCurioItems.REGISTRY, output), () ->
				EGOCurioItems.BENEDICTION.get().getDefaultInstance()));

	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
		String name,
		String zhCn,
		BiFunction<String, String, CreativeModeTab.Builder> builder
	) {
		return EGOCuriosCreativeModeTabs.REGISTRY.register(name, builder.apply(name, zhCn)::build);
	}

	private static CreativeModeTab.Builder createCreativeModeTab(
		String name,
		String zhCn,
		CreativeModeTab.DisplayItemsGenerator displayItemsGenerator,
		Supplier<ItemStack> icon,
		ResourceKey<CreativeModeTab> withTabsBefore
	) {
		return createCreativeModeTab(name, zhCn, displayItemsGenerator, icon)
			.withTabsBefore(withTabsBefore);
	}

	private static CreativeModeTab.Builder createCreativeModeTab(
		String name,
		String zhCn,
		CreativeModeTab.DisplayItemsGenerator displayItemsGenerator,
		Supplier<ItemStack> icon
	) {
		return createCreativeModeTab(name, zhCn, displayItemsGenerator)
			.icon(icon);
	}

	private static CreativeModeTab.Builder createCreativeModeTab(
		String name,
		String zhCn,
		CreativeModeTab.DisplayItemsGenerator displayItemsGenerator
	) {
		String key = "itemGroup." + EGOCurios.ID + "." + name;
		ZhCn.addI18nText(zhCn, key);
		return CreativeModeTab.builder()
			.title(Component.translatable(key))
			.displayItems(displayItemsGenerator);
	}

	private static void addRegistryItem(DeferredRegister.Items registry, CreativeModeTab.Output output) {
		registry.getEntries().forEach(entry -> output.accept(entry.get()));
	}
}
