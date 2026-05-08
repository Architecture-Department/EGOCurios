package architecture.ego_curios.client.renderer

import architecture.ego_curios.core.EGOCuriosConstants
import net.minecraft.world.entity.EquipmentSlot
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.ICurioRenderer

interface CuriosRenderer : ICurioRenderer {
	companion object {
		@JvmStatic
		fun getEquipmentSlot(slotContext: SlotContext): EquipmentSlot {
			return getEquipmentSlot(slotContext.identifier())
		}

		@JvmStatic
		fun getEquipmentSlot(slotId: String): EquipmentSlot {
			if (isHead(slotId)) {
				return EquipmentSlot.HEAD
			}
			if (isNeck(slotId)) {
				return EquipmentSlot.CHEST
			}
			// 需要修改以区分主副手
			if (isHand(slotId)) {
				return EquipmentSlot.MAINHAND
			}
			return EquipmentSlot.BODY
		}

		@JvmStatic
		fun isHead(slotId: String): Boolean {
			return when (slotId) {
				EGOCuriosConstants.EGO_CURIOS_HEADWEAR,
				EGOCuriosConstants.EGO_CURIOS_HEAD,
				EGOCuriosConstants.EGO_CURIOS_HINDBRAIN,
				EGOCuriosConstants.EGO_CURIOS_EYE,
				EGOCuriosConstants.EGO_CURIOS_FACE,
				EGOCuriosConstants.EGO_CURIOS_CHEEK,
				EGOCuriosConstants.EGO_CURIOS_MASK,
				EGOCuriosConstants.EGO_CURIOS_MOUTH -> true

				else -> false
			}
		}

		@JvmStatic
		fun isNeck(slotId: String): Boolean {
			return when (slotId) {
				EGOCuriosConstants.EGO_CURIOS_NECK,
				EGOCuriosConstants.EGO_CURIOS_BROOCH,
				EGOCuriosConstants.EGO_CURIOS_LEFT_BACK,
				EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> true

				else -> false
			}
		}

		@JvmStatic
		fun isHand(slotId: String): Boolean {
			return when (slotId) {
				EGOCuriosConstants.EGO_CURIOS_HAND,
				EGOCuriosConstants.EGO_CURIOS_GLOVE -> true

				else -> false
			}
		}
	}
}
