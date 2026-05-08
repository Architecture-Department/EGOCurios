package architecture.ego_curios.common.payload.toc

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import top.theillusivec4.curios.api.SlotContext
import java.util.*

data class SlotContextExpand(
	val identifier: String,
	val entityUUID: UUID,
	val index: Int,
	val cosmetic: Boolean,
	val visible: Boolean
) {
	companion object {
		@JvmStatic
		fun SlotContext.toExpand() = SlotContextExpand(identifier, entity.uuid, index, cosmetic, visible)

		@JvmStatic
		val STREAM_CODEC: StreamCodec<ByteBuf, SlotContextExpand> =
			StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8, SlotContextExpand::identifier,
				UUIDUtil.STREAM_CODEC, SlotContextExpand::entityUUID,
				ByteBufCodecs.INT, SlotContextExpand::index,
				ByteBufCodecs.BOOL, SlotContextExpand::cosmetic,
				ByteBufCodecs.BOOL, SlotContextExpand::visible,
				SlotContextExpand::newOf
			)

		@JvmStatic
		val CODEC: MapCodec<SlotContextExpand> = RecordCodecBuilder.mapCodec { thisOptionsInstance ->
			thisOptionsInstance.group(
				Codec.STRING.fieldOf("identifier").forGetter(SlotContextExpand::identifier),
				UUIDUtil.CODEC.fieldOf("entityUUID").forGetter(SlotContextExpand::entityUUID),
				Codec.INT.fieldOf("index").forGetter(SlotContextExpand::index),
				Codec.BOOL.fieldOf("cosmetic").forGetter(SlotContextExpand::cosmetic),
				Codec.BOOL.fieldOf("visible").forGetter(SlotContextExpand::visible),
			).apply(thisOptionsInstance, SlotContextExpand::newOf)
		}

		@JvmStatic
		fun newOf(
			identifier: String,
			entityUUID: UUID,
			index: Int,
			cosmetic: Boolean,
			visible: Boolean
		): SlotContextExpand {
			return SlotContextExpand(
				identifier,
				entityUUID,
				index,
				cosmetic,
				visible
			)
		}
	}
}