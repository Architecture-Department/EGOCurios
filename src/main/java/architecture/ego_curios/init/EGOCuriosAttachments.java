package architecture.ego_curios.init;

import architecture.ego_curios.api.AttackLogicHolder;
import architecture.ego_curios.core.EGOCurios;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

public final class EGOCuriosAttachments {
	public static final DeferredRegister<AttachmentType<?>> REGISTRY = EGOCurios.modRegister(NeoForgeRegistries.ATTACHMENT_TYPES);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<AttackLogicHolder>> ATTACK_LOGIC_HOLDER = register("attack_logic_holder",
		AttachmentType.builder((attachmentHolder) -> {
			if (!(attachmentHolder instanceof LivingEntity livingEntity)) {
				throw new IllegalArgumentException("Attachment holder must be a LivingEntity");
			}
			return new AttackLogicHolder(livingEntity);
		}));

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> registerPlayer(
		final String name,
		final Function<Player, T> defaultValue
	) {
		return registerPlayer(name, defaultValue, builder -> builder);
	}

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> registerPlayer(
		final String name,
		final Function<Player, T> defaultValue,
		final @NotNull Function<AttachmentType.Builder<T>, AttachmentType.Builder<T>> builder
	) {
		return register(name, builder.apply(AttachmentType.builder(holder ->
			instanceofPlayer(defaultValue, holder, name)))::build);
	}

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(
		final String name,
		final Supplier<AttachmentType<T>> builder
	) {
		return EGOCuriosAttachments.REGISTRY.register(name, builder);
	}

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> registerEntity(
		final String name,
		final Function<Entity, T> defaultValue
	) {
		return registerEntity(name, defaultValue, builder -> builder);
	}

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> registerEntity(
		final String name,
		final Function<Entity, T> defaultValue,
		final @NotNull Function<AttachmentType.Builder<T>, AttachmentType.Builder<T>> builder
	) {
		return register(name, builder.apply(AttachmentType.builder(holder ->
			instanceofEntity(defaultValue, holder, name)))::build);
	}

	@Contract("_, null, _ -> fail")
	private static <T> T instanceofPlayer(final @NotNull Function<Player, T> defaultValue, final IAttachmentHolder holder, final String name) {
		assert holder instanceof Player : name + " can only be attached to a player";
		return defaultValue.apply((Player) holder);
	}

	@Contract("_, null, _ -> fail")
	private static <T> T instanceofEntity(final @NotNull Function<Entity, T> defaultValue, final IAttachmentHolder holder, final String name) {
		assert holder instanceof Entity : name + " can only be attached to an entity";
		return defaultValue.apply((Entity) holder);
	}

	private static <T> @NotNull DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(
		final String name,
		final AttachmentType.@NotNull Builder<T> builder
	) {
		return register(name, builder::build);
	}
}
