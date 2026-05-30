package architecture.ego_curios.init

import architecture.ego_curios.core.EGOCuriosConstants
import architecture.ego_curios.core.EGOCuriosConstants.modRegister
import architecture.ego_curios.datagen.i18n.ZhCn
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.BiFunction
import java.util.function.Supplier

/**
 * 创造模式物品栏
 */
object EGOCuriosCreativeModeTabs {
	@JvmField
	val REGISTRY: DeferredRegister<CreativeModeTab> = modRegister<CreativeModeTab>(BuiltInRegistries.CREATIVE_MODE_TAB)

	@JvmField
	val EGO_CURIOS: DeferredHolder<CreativeModeTab, CreativeModeTab> = register(
		"ego_curios", "E.G.O饰品"
	) { name: String, zhCn: String ->
		createCreativeModeTab(
			name,
			zhCn,
			{ parameters, output ->
				addRegistryItem(EGOCuriosItems.REGISTRY, output)
			},
			{ EGOCuriosItems.BENEDICTION.get().defaultInstance })
	}

	private fun register(
		name: String, zhCn: String, builder: BiFunction<String, String, CreativeModeTab.Builder>
	): DeferredHolder<CreativeModeTab, CreativeModeTab> {
		return REGISTRY.register(name) { -> builder.apply(name, zhCn).build() }
	}

	private fun createCreativeModeTab(
		name: String,
		zhCn: String,
		displayItemsGenerator: CreativeModeTab.DisplayItemsGenerator,
		icon: Supplier<ItemStack>,
		withTabsBefore: ResourceKey<CreativeModeTab>
	): CreativeModeTab.Builder {
		return createCreativeModeTab(name, zhCn, displayItemsGenerator, icon).withTabsBefore(withTabsBefore)
	}

	private fun createCreativeModeTab(
		name: String, zhCn: String, displayItemsGenerator: CreativeModeTab.DisplayItemsGenerator, icon: Supplier<ItemStack>
	): CreativeModeTab.Builder {
		return createCreativeModeTab(name, zhCn, displayItemsGenerator).icon(icon)
	}

	private fun createCreativeModeTab(
		name: String, zhCn: String, displayItemsGenerator: CreativeModeTab.DisplayItemsGenerator
	): CreativeModeTab.Builder {
		val key = "itemGroup.${EGOCuriosConstants.ID}.$name"
		ZhCn.addI18nText(zhCn, key)
		return CreativeModeTab.builder().title(Component.translatable(key)).displayItems(displayItemsGenerator)
	}

	private fun addRegistryItem(registry: DeferredRegister.Items, output: CreativeModeTab.Output) {
		registry.entries.forEach { output.accept(it.get()) }
	}
}
