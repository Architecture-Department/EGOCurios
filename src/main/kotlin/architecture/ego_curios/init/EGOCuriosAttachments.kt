package architecture.ego_curios.init

import architecture.ego_curios.api.AttackLogicHolder
import architecture.ego_curios.util.EGOCuriosUtil.modRegister
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.attachment.IAttachmentHolder
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import org.jetbrains.annotations.Contract
import java.util.function.Function
import java.util.function.Supplier

object EGOCuriosAttachments {
	@JvmField
	val REGISTRY: DeferredRegister<AttachmentType<*>> =
		modRegister<AttachmentType<*>>(NeoForgeRegistries.ATTACHMENT_TYPES)

	@JvmField
	val ATTACK_LOGIC_HOLDER: DeferredHolder<AttachmentType<*>, AttachmentType<AttackLogicHolder>> =
		register(
			"attack_logic_holder", AttachmentType.builder { holder ->
				require(holder is LivingEntity) { "Attachment holder must be a LivingEntity" }
				AttackLogicHolder(holder)
			}
		)

	private fun <T> registerPlayer(
		name: String, defaultValue: Function<Player, T>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return registerPlayer(name, defaultValue) { it }
	}

	private fun <T> registerPlayer(
		name: String,
		defaultValue: Function<Player, T>,
		builder: Function<AttachmentType.Builder<T>, AttachmentType.Builder<T>>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return register(name) {
			builder.apply(AttachmentType.builder { holder ->
				instanceofPlayer(defaultValue, holder, name)
			}).build()
		}
	}

	private fun <T> register(
		name: String, builder: Supplier<AttachmentType<T>>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return REGISTRY.register(name, builder)
	}

	private fun <T> registerEntity(
		name: String, defaultValue: Function<Entity, T>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return registerEntity(name, defaultValue) { builder: AttachmentType.Builder<T> -> builder }
	}

	private fun <T> registerEntity(
		name: String,
		defaultValue: Function<Entity, T>,
		builder: Function<AttachmentType.Builder<T>, AttachmentType.Builder<T>>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return register<T>(name) {
			builder.apply(AttachmentType.builder { holder ->
				instanceofEntity<T>(defaultValue, holder, name)
			}).build()
		}
	}

	@Contract("_, null, _ -> fail")
	private fun <T> instanceofPlayer(
		defaultValue: Function<Player, T>, holder: IAttachmentHolder, name: String
	): T {
		assert(holder is Player) { "$name can only be attached to a player" }
		return defaultValue.apply(holder as Player)
	}

	@Contract("_, null, _ -> fail")
	private fun <T> instanceofEntity(
		defaultValue: Function<Entity, T>, holder: IAttachmentHolder, name: String
	): T {
		assert(holder is Entity) { "$name can only be attached to an entity" }
		return defaultValue.apply(holder as Entity)
	}

	private fun <T> register(
		name: String, builder: AttachmentType.Builder<T>
	): DeferredHolder<AttachmentType<*>, AttachmentType<T>> {
		return register(name) { builder.build() }
	}
}
