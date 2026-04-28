package architecture.ego_curios.datagen.i18n;

import architecture.ego_curios.common.item.EgoCurioItem;
import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.core.EGOCuriosConstants;
import architecture.ego_curios.init.EGOCuriosItems;
import architecture.ego_curios.init.tag.CuriosItemTags;
import architecture.goldenboughs_lib.datagen.i18n.DatagenI18n;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@ApiStatus.Internal
public final class ZhCn extends DatagenI18n {
	private static final Map<Supplier<? extends Item>, String> ITEMS = new HashMap<>();
	private static final Map<String, String> MAP = new HashMap<>();

	public ZhCn(final PackOutput output) {
		super(output, EGOCurios.ID, "zh_cn");
	}

	public static void addI18nText(String zhCn, String key) {
		if (!FMLEnvironment.production) {
			ZhCn.MAP.put(key, zhCn);
		}
	}

	public static void addI18nItemText(String zhName, Supplier<? extends Item> deferredItem) {
		if (!FMLEnvironment.production) {
			ZhCn.ITEMS.put(deferredItem, zhName);
		}
	}

	@Override
	protected void addTranslations() {
		addPackDescription(EGOCurios.ID, "E.G.O.饰品");
		addItemList(ITEMS);
		MAP.forEach(this::add);

		// 饰品描述
		EGOCuriosItems.REGISTRY.getEntries().stream()
			.map(DeferredHolder::get)
			.filter(EgoCurioItem.class::isInstance)
			.map(EgoCurioItem.class::cast)
			.map(EgoCurioItem::getAndClearTooltipsI18nMap)
			.forEach(map -> map.forEach(this::add));

		addCurios(EGOCuriosConstants.EGO_CURIOS, "饰品", "E.G.O.饰品");
		addCurios(EGOCuriosConstants.EGO_CURIOS_HEADWEAR, "头饰", "E.G.O.饰品-头饰");
		addCurios(EGOCuriosConstants.EGO_CURIOS_HEAD, "头部", "E.G.O.饰品-头部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_HINDBRAIN, "后脑", "E.G.O.饰品-后脑");
		addCurios(EGOCuriosConstants.EGO_CURIOS_EYE, "眼部", "E.G.O.饰品-眼部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_FACE, "面部", "E.G.O.饰品-面部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_CHEEK, "脸颊", "E.G.O.饰品-脸颊");
		addCurios(EGOCuriosConstants.EGO_CURIOS_MASK, "口罩", "E.G.O.饰品-口罩");
		addCurios(EGOCuriosConstants.EGO_CURIOS_MOUTH, "口部", "E.G.O.饰品-口部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_NECK, "颈部", "E.G.O.饰品-颈部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_BROOCH, "胸针", "E.G.O.饰品-胸针");
		addCurios(EGOCuriosConstants.EGO_CURIOS_HAND, "手部", "E.G.O.饰品-手部");
		addCurios(EGOCuriosConstants.EGO_CURIOS_GLOVE, "手套", "E.G.O.饰品-手套");
		addCurios(EGOCuriosConstants.EGO_CURIOS_LEFT_BACK, "左背", "E.G.O.饰品-左背");
		addCurios(EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK, "右背", "E.G.O.饰品-右背");

		//region tag标签
		add(CuriosItemTags.EGO_CURIOS, "E.G.O.饰品");
		add(CuriosItemTags.EGO_CURIOS_HEADWEAR, "E.G.O.饰品-头饰");
		add(CuriosItemTags.EGO_CURIOS_HEAD, "E.G.O.饰品-头部");
		add(CuriosItemTags.EGO_CURIOS_HINDBRAIN, "E.G.O.饰品-后脑");
		add(CuriosItemTags.EGO_CURIOS_EYE, "E.G.O.饰品-眼部");
		add(CuriosItemTags.EGO_CURIOS_FACE, "E.G.O.饰品-面部");
		add(CuriosItemTags.EGO_CURIOS_CHEEK, "E.G.O.饰品-脸颊");
		add(CuriosItemTags.EGO_CURIOS_MASK, "E.G.O.饰品-口罩");
		add(CuriosItemTags.EGO_CURIOS_MOUTH, "E.G.O.饰品-口部");
		add(CuriosItemTags.EGO_CURIOS_NECK, "E.G.O.饰品-颈部");
		add(CuriosItemTags.EGO_CURIOS_BROOCH, "E.G.O.饰品-胸针");
		add(CuriosItemTags.EGO_CURIOS_HAND, "E.G.O.饰品-手部");
		add(CuriosItemTags.EGO_CURIOS_GLOVE, "E.G.O.饰品-手套");
		add(CuriosItemTags.EGO_CURIOS_BACK, "E.G.O.饰品-背后");
		//endregion
	}
}
