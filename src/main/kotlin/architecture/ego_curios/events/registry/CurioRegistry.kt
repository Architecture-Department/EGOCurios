package architecture.ego_curios.events.registry

import architecture.ego_curios.init.EGOCuriosItems
import architecture.ego_curios.init.tag.CuriosItemTags
import architecture.ego_curios.util.EGOCuriosUtil
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
		createValidators(EGOCuriosUtil.EGO_CURIOS_VALIDATOR, CuriosItemTags.EGO_CURIOS)
		createValidators(EGOCuriosUtil.EGO_CURIOS_HEADWEAR_VALIDATOR, CuriosItemTags.EGO_CURIOS_HEADWEAR)
		createValidators(EGOCuriosUtil.EGO_CURIOS_HEAD_VALIDATOR, CuriosItemTags.EGO_CURIOS_HEAD)
		createValidators(EGOCuriosUtil.EGO_CURIOS_HINDBRAIN_VALIDATOR, CuriosItemTags.EGO_CURIOS_HINDBRAIN)
		createValidators(EGOCuriosUtil.EGO_CURIOS_EYE_VALIDATOR, CuriosItemTags.EGO_CURIOS_EYE)
		createValidators(EGOCuriosUtil.EGO_CURIOS_FACE_VALIDATOR, CuriosItemTags.EGO_CURIOS_FACE)
		createValidators(EGOCuriosUtil.EGO_CURIOS_CHEEK_VALIDATOR, CuriosItemTags.EGO_CURIOS_CHEEK)
		createValidators(EGOCuriosUtil.EGO_CURIOS_MASK_VALIDATOR, CuriosItemTags.EGO_CURIOS_MASK)
		createValidators(EGOCuriosUtil.EGO_CURIOS_MOUTH_VALIDATOR, CuriosItemTags.EGO_CURIOS_MOUTH)
		createValidators(EGOCuriosUtil.EGO_CURIOS_NECK_VALIDATOR, CuriosItemTags.EGO_CURIOS_NECK)
		createValidators(EGOCuriosUtil.EGO_CURIOS_BROOCH_VALIDATOR, CuriosItemTags.EGO_CURIOS_BROOCH)
		createValidators(EGOCuriosUtil.EGO_CURIOS_HAND_VALIDATOR, CuriosItemTags.EGO_CURIOS_HAND)
		createValidators(EGOCuriosUtil.EGO_CURIOS_GLOVE_VALIDATOR, CuriosItemTags.EGO_CURIOS_GLOVE)
		CuriosApi.registerCurioPredicate(EGOCuriosUtil.EGO_CURIOS_LEFT_BACK_VALIDATOR) { slotResult ->
			val stack = slotResult.stack
			val item = stack.item
			// TODO 扩展成tag的形式
			if (item == EGOCuriosItems.COMPREHENSION_BACK.get()) {
				return@registerCurioPredicate true
			}

			if (!stack.`is`(CuriosItemTags.EGO_CURIOS_BACK)) {
				return@registerCurioPredicate false
			}

			val curiosInventory = slotResult.slotContext().entity().getCuriosInventory()
			if (curiosInventory.isEmpty) {
				return@registerCurioPredicate false
			}

			for (a in curiosInventory.get().findCurios(item)) {
				if (a.slotContext().identifier() == EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK) {
					return@registerCurioPredicate false
				}
			}
			true
		}
		CuriosApi.registerCurioPredicate(EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK_VALIDATOR) { slotResult ->
			val stack = slotResult.stack
			val item = stack.item
			// TODO 扩展成tag的形式
			if (item == EGOCuriosItems.COMPREHENSION_BACK.get()) {
				return@registerCurioPredicate true
			}

			if (!stack.`is`(CuriosItemTags.EGO_CURIOS_BACK)) {
				return@registerCurioPredicate false
			}

			val curiosInventory = slotResult.slotContext().entity().getCuriosInventory()
			if (curiosInventory.isEmpty) {
				return@registerCurioPredicate false
			}

			for (a in curiosInventory.get().findCurios(item)) {
				if (a.slotContext().identifier() == EGOCuriosUtil.EGO_CURIOS_LEFT_BACK) {
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
