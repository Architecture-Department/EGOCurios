package architecture.ego_curios.events.registry

import architecture.ego_curios.common.payload.toc.CurioAppurtenanceSynchroPayload
import architecture.ego_curios.util.EgoCuriosUtil
import architecture.goldenboughs_lib.events.registry.PayloadRegistry.playToClient
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

@EventBusSubscriber(modid = EgoCuriosUtil.ID)
object PayloadRegistry {
	@SubscribeEvent
	fun register(event: RegisterPayloadHandlersEvent) {
		val registrar = event.registrar("1.0")
		// 接收来自服务端和客户端的数据 发送到 客户端和服务端

		// 接收来自服务端的数据 发送到 客户端
		registrar.playToClient(CurioAppurtenanceSynchroPayload.TYPE, CurioAppurtenanceSynchroPayload.STREAM_CODEC)

		// 接收来自客户端的数据 发送到 服务端

		EgoCuriosUtil.LOGGER.info("Registering payloads finish")
	}
}
