package architecture.ego_curios.events.registry

import architecture.ego_curios.common.payload.toc.CurioAppurtenanceSynchroPayload
import architecture.ego_curios.util.EgoCuriosUtil
import architecture.goldenboughs_lib.api.payload.ToClientPayload
import architecture.goldenboughs_lib.api.payload.ToServerAndClientPayload
import architecture.goldenboughs_lib.api.payload.ToServerPayload
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler
import net.neoforged.neoforge.network.registration.PayloadRegistrar

@EventBusSubscriber(modid = EgoCuriosUtil.ID)
object PayloadRegistry {
	@SubscribeEvent
	fun register(event: RegisterPayloadHandlersEvent) {
		val registrar = event.registrar("1.0")
		// 接收来自服务端和客户端的数据 发送到 客户端和服务端

		// 接收来自服务端的数据 发送到 客户端
		playToClient(registrar, CurioAppurtenanceSynchroPayload.TYPE, CurioAppurtenanceSynchroPayload.STREAM_CODEC)

		// 接收来自客户端的数据 发送到 服务端

		EgoCuriosUtil.LOGGER.info("Registering payloads finish")
	}

	private fun <T : ToServerAndClientPayload> playToServerAndClient(
		registrar: PayloadRegistrar,
		type: CustomPacketPayload.Type<T>,
		reader: StreamCodec<in RegistryFriendlyByteBuf, T>
	): PayloadRegistrar {
		return registrar.playBidirectional(
			type, reader,
			DirectionalPayloadHandler(ToServerAndClientPayload::handle, ToServerAndClientPayload::handle)
		)
	}

	private fun <T : ToServerPayload> playToServer(
		registrar: PayloadRegistrar,
		type: CustomPacketPayload.Type<T>,
		reader: StreamCodec<in RegistryFriendlyByteBuf, T>
	): PayloadRegistrar {
		return registrar.playToServer(type, reader, ToServerAndClientPayload::handle)
	}

	private fun <T : ToClientPayload> playToClient(
		registrar: PayloadRegistrar,
		type: CustomPacketPayload.Type<T>,
		reader: StreamCodec<in RegistryFriendlyByteBuf, T>
	): PayloadRegistrar {
		return registrar.playToClient(type, reader, ToServerAndClientPayload::handle)
	}
}
