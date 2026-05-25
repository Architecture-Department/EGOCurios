package architecture.ego_curios.core

import architecture.goldenboughs_lib.util.LibUtil.rlOf
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.registries.DeferredRegister
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.jetbrains.annotations.Contract
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem

object EGOCuriosConstants {
	const val ID: String = "ego_curios"
	const val NAME: String = "E.G.O.Curios"

	@JvmField
	val LOGGER: Logger = LogManager.getLogger(ID)

	@JvmStatic
	@Contract("_ -> new")
	fun modRl(name: String): ResourceLocation {
		return rlOf(ID, name)
	}

	@JvmStatic
	@Contract(pure = true)
	fun modRlText(name: String): String {
		return "$ID:$name"
	}

	@JvmStatic
	fun <T> modRegister(registry: Registry<T>): DeferredRegister<T> {
		return DeferredRegister.create<T>(registry, ID)
	}

	@JvmStatic
	fun <T> modRegister(registry: ResourceKey<Registry<T>>): DeferredRegister<T> {
		return DeferredRegister.create<T>(registry, ID)
	}

	const val EGO_CURIOS = "ego_curios"
	const val EGO_CURIOS_HEADWEAR = "ego_curios_headwear"
	const val EGO_CURIOS_HEAD = "ego_curios_head"
	const val EGO_CURIOS_HINDBRAIN = "ego_curios_hindbrain"
	const val EGO_CURIOS_EYE = "ego_curios_eye"
	const val EGO_CURIOS_FACE = "ego_curios_face"
	const val EGO_CURIOS_CHEEK = "ego_curios_cheek"
	const val EGO_CURIOS_MASK = "ego_curios_mask"
	const val EGO_CURIOS_MOUTH = "ego_curios_mouth"
	const val EGO_CURIOS_NECK = "ego_curios_neck"
	const val EGO_CURIOS_BROOCH = "ego_curios_brooch"
	const val EGO_CURIOS_HAND = "ego_curios_hand"
	const val EGO_CURIOS_GLOVE = "ego_curios_glove"
	const val EGO_CURIOS_LEFT_BACK = "ego_curios_left_back"
	const val EGO_CURIOS_RIGHT_BACK = "ego_curios_right_back"

	@JvmField
	val EGO_CURIOS_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS)

	@JvmField
	val EGO_CURIOS_HEADWEAR_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_HEADWEAR)

	@JvmField
	val EGO_CURIOS_HEAD_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_HEAD)

	@JvmField
	val EGO_CURIOS_HINDBRAIN_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_HINDBRAIN)

	@JvmField
	val EGO_CURIOS_EYE_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_EYE)

	@JvmField
	val EGO_CURIOS_FACE_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_FACE)

	@JvmField
	val EGO_CURIOS_CHEEK_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_CHEEK)

	@JvmField
	val EGO_CURIOS_MASK_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_MASK)

	@JvmField
	val EGO_CURIOS_MOUTH_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_MOUTH)

	@JvmField
	val EGO_CURIOS_NECK_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_NECK)

	@JvmField
	val EGO_CURIOS_BROOCH_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_BROOCH)

	@JvmField
	val EGO_CURIOS_HAND_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_HAND)

	@JvmField
	val EGO_CURIOS_GLOVE_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_GLOVE)

	@JvmField
	val EGO_CURIOS_LEFT_BACK_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_LEFT_BACK)

	@JvmField
	val EGO_CURIOS_RIGHT_BACK_VALIDATOR: ResourceLocation = createTagId(EGO_CURIOS_RIGHT_BACK)

	@JvmField
	val EGO_CURIOS_HEADWEAR_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_HEAD_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_HINDBRAIN_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_EYE_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_FACE_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_CHEEK_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_MASK_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_MOUTH_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_NECK_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_BROOCH_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_HAND_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_GLOVE_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	@JvmField
	val EGO_CURIOS_BACK_SET: MutableSet<DeferredItem<out Item>> = HashSet()

	private fun createId(name: String): String = modRlText(name)

	private fun createTagId(name: String): ResourceLocation = modRl(name + "_tag")
}
