package architecture.ego_curios.events.registry

import architecture.ego_curios.init.EgoCuriosItems
import architecture.ego_curios.init.tag.EgoCuriosItemTags
import architecture.ego_curios.util.EgoCuriosUtil
import architecture.ego_curios.util.getCuriosInventory
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import top.theillusivec4.curios.api.CuriosApi

/**
 * 饰品
 */
object CurioRegistry {
	@JvmStatic
	fun registry() {
		createValidators(EgoCuriosUtil.EGO_CURIOS_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS)
		createValidators(EgoCuriosUtil.EGO_CURIOS_HEADWEAR_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_HEADWEAR)
		createValidators(EgoCuriosUtil.EGO_CURIOS_HEAD_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_HEAD)
		createValidators(EgoCuriosUtil.EGO_CURIOS_HINDBRAIN_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_HINDBRAIN)
		createValidators(EgoCuriosUtil.EGO_CURIOS_EYE_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_EYE)
		createValidators(EgoCuriosUtil.EGO_CURIOS_FACE_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_FACE)
		createValidators(EgoCuriosUtil.EGO_CURIOS_CHEEK_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_CHEEK)
		createValidators(EgoCuriosUtil.EGO_CURIOS_MASK_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_MASK)
		createValidators(EgoCuriosUtil.EGO_CURIOS_MOUTH_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_MOUTH)
		createValidators(EgoCuriosUtil.EGO_CURIOS_NECK_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_NECK)
		createValidators(EgoCuriosUtil.EGO_CURIOS_BROOCH_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_BROOCH)
		createValidators(EgoCuriosUtil.EGO_CURIOS_HAND_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_HAND)
		createValidators(EgoCuriosUtil.EGO_CURIOS_GLOVE_VALIDATOR, EgoCuriosItemTags.EGO_CURIOS_GLOVE)
		CuriosApi.registerCurioPredicate(EgoCuriosUtil.EGO_CURIOS_LEFT_BACK_VALIDATOR) { slotResult ->
			val stack = slotResult.stack
			val item = stack.item
			// TODO 扩展成tag的形式
			if (item == EgoCuriosItems.COMPREHENSION_BACK.get()) {
				return@registerCurioPredicate true
			}

			if (!stack.`is`(EgoCuriosItemTags.EGO_CURIOS_BACK)) {
				return@registerCurioPredicate false
			}

			val curiosInventory = slotResult.slotContext().entity().getCuriosInventory()
			if (curiosInventory.isEmpty) {
				return@registerCurioPredicate false
			}

			for (a in curiosInventory.get().findCurios(item)) {
				if (a.slotContext().identifier() == EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK) {
					return@registerCurioPredicate false
				}
			}
			true
		}
		CuriosApi.registerCurioPredicate(EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK_VALIDATOR) { slotResult ->
			val stack = slotResult.stack
			val item = stack.item
			// TODO 扩展成tag的形式
			if (item == EgoCuriosItems.COMPREHENSION_BACK.get()) {
				return@registerCurioPredicate true
			}

			if (!stack.`is`(EgoCuriosItemTags.EGO_CURIOS_BACK)) {
				return@registerCurioPredicate false
			}

			val curiosInventory = slotResult.slotContext().entity().getCuriosInventory()
			if (curiosInventory.isEmpty) {
				return@registerCurioPredicate false
			}

			for (a in curiosInventory.get().findCurios(item)) {
				if (a.slotContext().identifier() == EgoCuriosUtil.EGO_CURIOS_LEFT_BACK) {
					return@registerCurioPredicate false
				}
			}
			true
		}
	}

	private fun createValidators(name: ResourceLocation, tagKey: TagKey<Item>) {
		CuriosApi.registerCurioPredicate(name) { slotResult -> slotResult.stack.`is`(tagKey) }
	}
}
