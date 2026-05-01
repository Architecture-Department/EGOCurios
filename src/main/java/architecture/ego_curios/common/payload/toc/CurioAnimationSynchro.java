package architecture.ego_curios.common.payload.toc;

import architecture.goldenboughs_lib.common.payload.api.ToClientPayload;
import architecture.goldenboughs_lib.core.GoldenBoughsLib;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public class CurioAnimationSynchro implements ToClientPayload {
	public static final Type<CurioAnimationSynchro> TYPE = new Type<>(GoldenBoughsLib.modRl("curio_animation_synchro"));
//	public static final StreamCodec<ByteBuf, CurioAnimationSynchro> STREAM_CODEC = StreamCodec.composite(
//		ByteBufCodecs.INT, CurioAnimationSynchro::attackStrengthTicker,
//		CurioAnimationSynchro::new);

	@Override
	public void work(Player player) {

	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
