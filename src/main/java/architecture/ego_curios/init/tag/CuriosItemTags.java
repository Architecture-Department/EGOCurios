package architecture.ego_curios.init.tag;

import architecture.ego_curios.core.EGOCurios;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class CuriosItemTags {
	/**
	 * E.G.O饰品
	 */
	public static final TagKey<Item> EGO_CURIOS = createTag("ego/curios");
	/**
	 * E.G.O饰品 头饰
	 */
	public static final TagKey<Item> EGO_CURIOS_HEADWEAR = createTag("ego/curios/headwear");
	/**
	 * E.G.O饰品 头部
	 */
	public static final TagKey<Item> EGO_CURIOS_HEAD = createTag("ego/curios/head");
	/**
	 * E.G.O饰品 后脑
	 */
	public static final TagKey<Item> EGO_CURIOS_HINDBRAIN = createTag("ego/curios/hindbrain");
	/**
	 * E.G.O饰品 眼部
	 */
	public static final TagKey<Item> EGO_CURIOS_EYE = createTag("ego/curios/eye");
	/**
	 * E.G.O饰品 脸
	 */
	public static final TagKey<Item> EGO_CURIOS_FACE = createTag("ego/curios/face");
	/**
	 * E.G.O饰品 脸颊
	 */
	public static final TagKey<Item> EGO_CURIOS_CHEEK = createTag("ego/curios/cheek");
	/**
	 * E.G.O饰品 口罩
	 */
	public static final TagKey<Item> EGO_CURIOS_MASK = createTag("ego/curios/mask");
	/**
	 * E.G.O饰品 口部
	 */
	public static final TagKey<Item> EGO_CURIOS_MOUTH = createTag("ego/curios/mouth");
	/**
	 * E.G.O饰品 颈部
	 */
	public static final TagKey<Item> EGO_CURIOS_NECK = createTag("ego/curios/neck");
	/**
	 * E.G.O饰品 胸针
	 */
	public static final TagKey<Item> EGO_CURIOS_BROOCH = createTag("ego/curios/brooch");
	/**
	 * E.G.O饰品 手部
	 */
	public static final TagKey<Item> EGO_CURIOS_HAND = createTag("ego/curios/hand");
	/**
	 * E.G.O饰品 手套
	 */
	public static final TagKey<Item> EGO_CURIOS_GLOVE = createTag("ego/curios/glove");
	/**
	 * E.G.O饰品 背后
	 */
	public static final TagKey<Item> EGO_CURIOS_BACK = createTag("ego/curios/back");

	private static TagKey<Item> createTag(String name) {
		return createTag(EGOCurios.modRl(name));
	}

	private static TagKey<Item> createTag(ResourceLocation location) {
		return ItemTags.create(location);
	}

	private static TagKey<Item> createCTag(String name) {
		return createTag(ResourceLocation.fromNamespaceAndPath("c", name));
	}

	private static TagKey<Item> createMcTag(String name) {
		return createTag(ResourceLocation.withDefaultNamespace(name));
	}
}
