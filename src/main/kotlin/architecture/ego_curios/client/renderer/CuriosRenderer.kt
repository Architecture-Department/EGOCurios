package architecture.ego_curios.client.renderer

import architecture.ego_curios.util.EgoCuriosUtil
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
				EgoCuriosUtil.EGO_CURIOS_HEADWEAR,
				EgoCuriosUtil.EGO_CURIOS_HEAD,
				EgoCuriosUtil.EGO_CURIOS_HINDBRAIN,
				EgoCuriosUtil.EGO_CURIOS_EYE,
				EgoCuriosUtil.EGO_CURIOS_FACE,
				EgoCuriosUtil.EGO_CURIOS_CHEEK,
				EgoCuriosUtil.EGO_CURIOS_MASK,
				EgoCuriosUtil.EGO_CURIOS_MOUTH -> true

				else -> false
			}
		}

		@JvmStatic
		fun isNeck(slotId: String): Boolean {
			return when (slotId) {
				EgoCuriosUtil.EGO_CURIOS_NECK,
				EgoCuriosUtil.EGO_CURIOS_BROOCH,
				EgoCuriosUtil.EGO_CURIOS_LEFT_BACK,
				EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK -> true

				else -> false
			}
		}

		@JvmStatic
		fun isHand(slotId: String): Boolean {
			return when (slotId) {
				EgoCuriosUtil.EGO_CURIOS_HAND,
				EgoCuriosUtil.EGO_CURIOS_GLOVE -> true

				else -> false
			}
		}
	}
}
