package architecture.ego_curios.common.payload.toc

import architecture.ego_curios.api.getStackInSlot
import architecture.ego_curios.core.EGOCurios
import architecture.goldenboughs_lib.util.PayloadUtil
import architecture.resonator_combat_framework.api.IAppurtenanceExecute
import architecture.resonator_combat_framework.common.payload.toc.AppurtenanceSynchroPayload
import io.netty.buffer.ByteBuf
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import kotlin.String

class CurioAppurtenanceSynchroPayload(
	entityId: Int,
	executeType: Byte,
	var slotIdentifier: String,
	var slotIndex: Int,
	nbt: CompoundTag = CompoundTag()
) : AppurtenanceSynchroPayload(entityId, executeType, nbt) {

	override fun work(player: Player) {
		val entity = player.level().getEntity(entityId) as? LivingEntity ?: return
		val itemStack = entity.getStackInSlot(slotIndex)

		val iAppurtenanceExecute = itemStack?.item as? IAppurtenanceExecute ?: return
		nbt.putInt("slotIndex", slotIndex)
		nbt.putString("identifier", slotIdentifier)
		when (executeType.toInt()) {
			0 -> iAppurtenanceExecute.remove(entity, itemStack, nbt)
			1 -> iAppurtenanceExecute.add(entity, itemStack, nbt)
		}
	}

	override fun type(): Type<out CustomPacketPayload?> = TYPE

	companion object {
		@JvmOverloads
		@JvmStatic
		fun send(
			entity: LivingEntity,
			add: Boolean,
			slotIdentifier: String,
			slotIndex: Int,
			nbt: CompoundTag = CompoundTag()
		) {

			PayloadUtil.sendToPlayersTrackingEntityAndSelf(
				entity,
				CurioAppurtenanceSynchroPayload(
					entity.id, add.toByte(), slotIdentifier, slotIndex, nbt
				)
			)
		}

		@JvmStatic
		val TYPE: Type<CurioAppurtenanceSynchroPayload> = Type(EGOCurios.modRl("curio_appurtenance_aynchro_payload"))

		@JvmStatic
		val STREAM_CODEC: StreamCodec<ByteBuf, CurioAppurtenanceSynchroPayload> =
			StreamCodec.composite(
				ByteBufCodecs.INT, CurioAppurtenanceSynchroPayload::entityId,
				ByteBufCodecs.BYTE, CurioAppurtenanceSynchroPayload::executeType,
				ByteBufCodecs.STRING_UTF8, CurioAppurtenanceSynchroPayload::slotIdentifier,
				ByteBufCodecs.INT, CurioAppurtenanceSynchroPayload::slotIndex,
				COMPOUND_TAG_STREAM_CODEC, CurioAppurtenanceSynchroPayload::nbt,
				CurioAppurtenanceSynchroPayload::newOf
			)

		@JvmStatic
		private fun newOf(
			entityId: Int,
			executeType: Byte,
			slotIdentifier: String,
			slotIndex: Int,
			nbt: CompoundTag = CompoundTag()
		): CurioAppurtenanceSynchroPayload =
			CurioAppurtenanceSynchroPayload(entityId, executeType, slotIdentifier, slotIndex, nbt)

		@JvmStatic
		private fun Boolean.toByte(): Byte = if (this) 1 else 0
	}
}