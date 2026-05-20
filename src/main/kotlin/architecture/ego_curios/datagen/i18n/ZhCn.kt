package architecture.ego_curios.datagen.i18n

import architecture.ego_curios.common.item.EgoCurioItem
import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.core.EGOCuriosConstants
import architecture.ego_curios.init.EGOCuriosItems
import architecture.ego_curios.init.tag.CuriosItemTags
import architecture.goldenboughs_lib.datagen.i18n.DatagenI18n
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.neoforged.fml.loading.FMLEnvironment
import net.neoforged.neoforge.registries.DeferredHolder
import java.util.function.Supplier

class ZhCn(output: PackOutput) : DatagenI18n(output, EGOCurios.ID, "zh_cn") {

	companion object {
		private val ITEMS: MutableMap<Supplier<out Item>, String> = HashMap()
		private val MAP: MutableMap<String, String> = HashMap()

		@JvmStatic
		fun addI18nText(zhCn: String, key: String) {
			if (!FMLEnvironment.production) {
				MAP[key] = zhCn
			}
		}

		@JvmStatic
		fun addI18nItemText(zhName: String, deferredItem: Supplier<out Item>) {
			if (!FMLEnvironment.production) {
				ITEMS[deferredItem] = zhName
			}
		}
	}

	override fun addTranslations() {
		addPackDescription(EGOCurios.ID, "E.G.O.饰品")
		addItemList(ITEMS)
		MAP.forEach { (key, value) -> add(key, value) }

		// 饰品描述
		EGOCuriosItems.REGISTRY.entries.stream()
			.map(DeferredHolder<*, *>::get)
			.filter { it is EgoCurioItem }
			.map { it as EgoCurioItem }
			.map { it.getAndClearTooltipsI18nMap() }
			.forEach { map -> map.forEach { (key, value) -> add(key, value) } }

		addCurios(EGOCuriosConstants.EGO_CURIOS, "饰品", "E.G.O.饰品")
		addCurios(EGOCuriosConstants.EGO_CURIOS_HEADWEAR, "头饰", "E.G.O.饰品-头饰")
		addCurios(EGOCuriosConstants.EGO_CURIOS_HEAD, "头部", "E.G.O.饰品-头部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_HINDBRAIN, "后脑", "E.G.O.饰品-后脑")
		addCurios(EGOCuriosConstants.EGO_CURIOS_EYE, "眼部", "E.G.O.饰品-眼部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_FACE, "面部", "E.G.O.饰品-面部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_CHEEK, "脸颊", "E.G.O.饰品-脸颊")
		addCurios(EGOCuriosConstants.EGO_CURIOS_MASK, "口罩", "E.G.O.饰品-口罩")
		addCurios(EGOCuriosConstants.EGO_CURIOS_MOUTH, "口部", "E.G.O.饰品-口部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_NECK, "颈部", "E.G.O.饰品-颈部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_BROOCH, "胸针", "E.G.O.饰品-胸针")
		addCurios(EGOCuriosConstants.EGO_CURIOS_HAND, "手部", "E.G.O.饰品-手部")
		addCurios(EGOCuriosConstants.EGO_CURIOS_GLOVE, "手套", "E.G.O.饰品-手套")
		addCurios(EGOCuriosConstants.EGO_CURIOS_LEFT_BACK, "左背", "E.G.O.饰品-左背")
		addCurios(EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK, "右背", "E.G.O.饰品-右背")

		//region tag标签
		add(CuriosItemTags.EGO_CURIOS, "E.G.O.饰品")
		add(CuriosItemTags.EGO_CURIOS_HEADWEAR, "E.G.O.饰品-头饰")
		add(CuriosItemTags.EGO_CURIOS_HEAD, "E.G.O.饰品-头部")
		add(CuriosItemTags.EGO_CURIOS_HINDBRAIN, "E.G.O.饰品-后脑")
		add(CuriosItemTags.EGO_CURIOS_EYE, "E.G.O.饰品-眼部")
		add(CuriosItemTags.EGO_CURIOS_FACE, "E.G.O.饰品-面部")
		add(CuriosItemTags.EGO_CURIOS_CHEEK, "E.G.O.饰品-脸颊")
		add(CuriosItemTags.EGO_CURIOS_MASK, "E.G.O.饰品-口罩")
		add(CuriosItemTags.EGO_CURIOS_MOUTH, "E.G.O.饰品-口部")
		add(CuriosItemTags.EGO_CURIOS_NECK, "E.G.O.饰品-颈部")
		add(CuriosItemTags.EGO_CURIOS_BROOCH, "E.G.O.饰品-胸针")
		add(CuriosItemTags.EGO_CURIOS_HAND, "E.G.O.饰品-手部")
		add(CuriosItemTags.EGO_CURIOS_GLOVE, "E.G.O.饰品-手套")
		add(CuriosItemTags.EGO_CURIOS_BACK, "E.G.O.饰品-背后")
		//endregion
	}
}
