package architecture.ego_curios.init.tag

import architecture.ego_curios.core.EGOCurios.modRl
import architecture.goldenboughs_lib.util.LibUtil.rlOf
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object CuriosItemTags {
	/**
	 * E.G.O饰品
	 */
	@JvmField
	val EGO_CURIOS: TagKey<Item> = createTag("ego/curio")

	/**
	 * E.G.O饰品 头饰
	 */
	@JvmField
	val EGO_CURIOS_HEADWEAR: TagKey<Item> = createTag("ego/curio/headwear")

	/**
	 * E.G.O饰品 头部
	 */
	@JvmField
	val EGO_CURIOS_HEAD: TagKey<Item> = createTag("ego/curio/head")

	/**
	 * E.G.O饰品 后脑
	 */
	@JvmField
	val EGO_CURIOS_HINDBRAIN: TagKey<Item> = createTag("ego/curio/hindbrain")

	/**
	 * E.G.O饰品 眼部
	 */
	@JvmField
	val EGO_CURIOS_EYE: TagKey<Item> = createTag("ego/curio/eye")

	/**
	 * E.G.O饰品 脸
	 */
	@JvmField
	val EGO_CURIOS_FACE: TagKey<Item> = createTag("ego/curio/face")

	/**
	 * E.G.O饰品 脸颊
	 */
	@JvmField
	val EGO_CURIOS_CHEEK: TagKey<Item> = createTag("ego/curio/cheek")

	/**
	 * E.G.O饰品 口罩
	 */
	@JvmField
	val EGO_CURIOS_MASK: TagKey<Item> = createTag("ego/curio/mask")

	/**
	 * E.G.O饰品 口部
	 */
	@JvmField
	val EGO_CURIOS_MOUTH: TagKey<Item> = createTag("ego/curio/mouth")

	/**
	 * E.G.O饰品 颈部
	 */
	@JvmField
	val EGO_CURIOS_NECK: TagKey<Item> = createTag("ego/curio/neck")

	/**
	 * E.G.O饰品 胸针
	 */
	@JvmField
	val EGO_CURIOS_BROOCH: TagKey<Item> = createTag("ego/curio/brooch")

	/**
	 * E.G.O饰品 手部
	 */
	@JvmField
	val EGO_CURIOS_HAND: TagKey<Item> = createTag("ego/curio/hand")

	/**
	 * E.G.O饰品 手套
	 */
	@JvmField
	val EGO_CURIOS_GLOVE: TagKey<Item> = createTag("ego/curio/glove")

	/**
	 * E.G.O饰品 背后
	 */
	@JvmField
	val EGO_CURIOS_BACK: TagKey<Item> = createTag("ego/curio/back")

	private fun createTag(name: String): TagKey<Item> {
		return createTag(modRl(name))
	}

	private fun createTag(location: ResourceLocation): TagKey<Item> {
		return ItemTags.create(location)
	}

	private fun createCTag(name: String): TagKey<Item> {
		return createTag(rlOf("c", name))
	}

	private fun createMcTag(name: String): TagKey<Item> {
		return createTag(ResourceLocation.withDefaultNamespace(name))
	}
}
