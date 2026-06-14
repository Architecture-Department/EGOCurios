package architecture.ego_curios.client.renderer

import architecture.ego_curios.util.EGOCuriosUtil
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
				EGOCuriosUtil.EGO_CURIOS_HEADWEAR,
				EGOCuriosUtil.EGO_CURIOS_HEAD,
				EGOCuriosUtil.EGO_CURIOS_HINDBRAIN,
				EGOCuriosUtil.EGO_CURIOS_EYE,
				EGOCuriosUtil.EGO_CURIOS_FACE,
				EGOCuriosUtil.EGO_CURIOS_CHEEK,
				EGOCuriosUtil.EGO_CURIOS_MASK,
				EGOCuriosUtil.EGO_CURIOS_MOUTH -> true

				else -> false
			}
		}

		@JvmStatic
		fun isNeck(slotId: String): Boolean {
			return when (slotId) {
				EGOCuriosUtil.EGO_CURIOS_NECK,
				EGOCuriosUtil.EGO_CURIOS_BROOCH,
				EGOCuriosUtil.EGO_CURIOS_LEFT_BACK,
				EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK -> true

				else -> false
			}
		}

		@JvmStatic
		fun isHand(slotId: String): Boolean {
			return when (slotId) {
				EGOCuriosUtil.EGO_CURIOS_HAND,
				EGOCuriosUtil.EGO_CURIOS_GLOVE -> true

				else -> false
			}
		}
	}
}
