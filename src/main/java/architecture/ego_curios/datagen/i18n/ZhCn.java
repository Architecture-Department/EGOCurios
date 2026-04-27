package architecture.ego_curios.datagen.i18n;

import architecture.ego_curios.common.item.EgoCurioItem;
import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.init.EGOCurioItems;
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
		super(output, "zh_cn");
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
		EGOCurioItems.REGISTRY.getEntries().stream()
			.map(DeferredHolder::get)
			.filter(EgoCurioItem.class::isInstance)
			.map(EgoCurioItem.class::cast)
			.map(EgoCurioItem::getAndClearTooltipsI18nMap)
			.forEach(map -> map.forEach(this::add));

		//region tag标签
		add(CuriosItemTags.EGO_CURIOS, "E.G.O饰品");
		add(CuriosItemTags.EGO_CURIOS_HEADWEAR, "E.G.O饰品-头饰");
		add(CuriosItemTags.EGO_CURIOS_HEAD, "E.G.O饰品-头部");
		add(CuriosItemTags.EGO_CURIOS_HINDBRAIN, "E.G.O饰品-后脑");
		add(CuriosItemTags.EGO_CURIOS_EYE, "E.G.O饰品-眼部");
		add(CuriosItemTags.EGO_CURIOS_FACE, "E.G.O饰品-面部");
		add(CuriosItemTags.EGO_CURIOS_CHEEK, "E.G.O饰品-脸颊");
		add(CuriosItemTags.EGO_CURIOS_MASK, "E.G.O饰品-口罩");
		add(CuriosItemTags.EGO_CURIOS_MOUTH, "E.G.O饰品-口部");
		add(CuriosItemTags.EGO_CURIOS_NECK, "E.G.O饰品-颈部");
		add(CuriosItemTags.EGO_CURIOS_BROOCH, "E.G.O饰品-胸针");
		add(CuriosItemTags.EGO_CURIOS_HAND, "E.G.O饰品-手部");
		add(CuriosItemTags.EGO_CURIOS_GLOVE, "E.G.O饰品-手套");
		add(CuriosItemTags.EGO_CURIOS_BACK, "E.G.O饰品-背后");
		//endregion
	}
}
