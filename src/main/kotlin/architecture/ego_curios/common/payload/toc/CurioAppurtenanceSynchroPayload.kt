package architecture.ego_curios.common.payload.toc

import architecture.ego_curios.common.payload.toc.SlotContextExpand.Companion.toExpand
import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.util.getStackInSlot
import architecture.goldenboughs_lib.util.PayloadUtil
import architecture.resonator_combat_framework.common.payload.toc.AppurtenanceSynchroPayload
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.neoforged.neoforge.network.handling.IPayloadContext
import top.theillusivec4.curios.api.SlotContext
import architecture.ego_curios.core.EGOCuriosConstants

class CurioAppurtenanceSynchroPayload(
	entityId: Int,
	executeType: Byte,
	val slotContext: SlotContextExpand
) : AppurtenanceSynchroPayload(entityId, executeType) {

	override fun work(context: IPayloadContext, player: Player) {
		val entity = player.level().getEntity(entityId) as? LivingEntity ?: return
		val (identifier, _, index, _, _) = slotContext
		val itemStack = entity.getStackInSlot(identifier, index)
		val map = mapOf(
			"slotContext" to slotContext
		)
//		val iAppurtenanceExecute = itemStack.item as? IAppurtenanceExecute ?: return
//		when (executeType.toInt()) {
//			0 -> iAppurtenanceExecute.remove(entity, itemStack, map)
//			1 -> iAppurtenanceExecute.add(entity, itemStack, map)
//		}
	}

	override fun type(): Type<out CustomPacketPayload?> = TYPE

	companion object {
		@JvmStatic
		fun send(
			add: Boolean,
			slotContext: SlotContext
		) {
			PayloadUtil.sendToPlayersTrackingEntityAndSelf(
				slotContext.entity,
				CurioAppurtenanceSynchroPayload(
					slotContext.entity.id,
					add.toByte(),
					slotContext.toExpand()
				)
			)
		}

		@JvmStatic
		val TYPE: Type<CurioAppurtenanceSynchroPayload> =
			Type(EGOCuriosConstants.modRl("curio_appurtenance_synchro_payload"))

		@JvmStatic
		val STREAM_CODEC: StreamCodec<ByteBuf, CurioAppurtenanceSynchroPayload> =
			StreamCodec.composite(
				ByteBufCodecs.INT, CurioAppurtenanceSynchroPayload::entityId,
				ByteBufCodecs.BYTE, CurioAppurtenanceSynchroPayload::executeType,
				SlotContextExpand.STREAM_CODEC, CurioAppurtenanceSynchroPayload::slotContext,
				CurioAppurtenanceSynchroPayload::newOf
			)

		@JvmStatic
		private fun newOf(
			entityId: Int,
			executeType: Byte,
			slotContext: SlotContextExpand
		): CurioAppurtenanceSynchroPayload =
			CurioAppurtenanceSynchroPayload(entityId, executeType, slotContext)

		@JvmStatic
		private fun Boolean.toByte(): Byte = if (this) 1 else 0
	}
}