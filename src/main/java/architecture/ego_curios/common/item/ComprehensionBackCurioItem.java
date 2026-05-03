package architecture.ego_curios.common.item;

import architecture.ego_curios.api.AttackLogicHolder;
import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.core.EGOCuriosConstants;
import architecture.ego_curios.init.EGOCuriosAttachments;
import architecture.goldenboughs_lib.mixed.geckolib.IAnimationController;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import top.theillusivec4.curios.api.SlotContext;

/**
 * 理解 后背 饰品
 */
public class ComprehensionBackCurioItem extends EgoCurioItem {
	public ComprehensionBackCurioItem(Builder<ComprehensionBackCurioItem> egoCurioBuilder) {
		super(egoCurioBuilder);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		super.registerControllers(controllers);
		RawAnimation idle = RawAnimation.begin().thenPlay("idle");
		AnimationController.AnimationStateHandler<ComprehensionBackCurioItem> animationHandler = (state) -> {
			if (state.isCurrentAnimationStage("idle")) {
				return PlayState.CONTINUE;
			}
			return state.getController().tryTriggerAnimation("idle") ? PlayState.CONTINUE : PlayState.STOP;
		};

		// TODO 依旧模型的命名反了

		var left_upper = new AnimationController<>(this, "left_upper", 6, animationHandler);
		IAnimationController.of(left_upper).goldenboughs_lib$enabledBones(
			"right_upper_tentacle_root",
			"right_upper_tentacle",
			"right_upper_tentacle2",
			"right_upper_tentacle3",
			"right_upper_tentacle4",
			"right_upper_tentacle5",
			"right_upper_tentacle6",
			"right_upper_tentacle7");
		left_upper
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_upper_attack", RawAnimation.begin().thenPlay("left_upper_attack"));
		controllers.add(left_upper);

		var right_upper = new AnimationController<>(this, "right_upper", 6, animationHandler);
		IAnimationController.of(right_upper).goldenboughs_lib$enabledBones(
			"left_upper_tentacle_root",
			"left_upper_tentacle",
			"left_upper_tentacle2",
			"left_upper_tentacle3",
			"left_upper_tentacle4",
			"left_upper_tentacle5",
			"left_upper_tentacle6",
			"left_upper_tentacle7");
		right_upper
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_upper_attack", RawAnimation.begin().thenPlay("right_upper_attack"));
		controllers.add(right_upper);

		var left_middle = new AnimationController<>(this, "left_middle", 6, animationHandler);
		IAnimationController.of(left_middle).goldenboughs_lib$enabledBones(
			"right_middle_tentacle_root",
			"right_middle_tentacle",
			"right_middle_tentacle2",
			"right_middle_tentacle3",
			"right_middle_tentacle4",
			"right_middle_tentacle5",
			"right_middle_tentacle6",
			"right_middle_tentacle7");
		left_middle
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_middle_attack", RawAnimation.begin().thenPlay("left_middle_attack"));
		controllers.add(left_middle);

		var right_middle = new AnimationController<>(this, "right_middle", 6, animationHandler);
		IAnimationController.of(right_middle).goldenboughs_lib$enabledBones(
			"left_middle_tentacle_root",
			"left_middle_tentacle",
			"left_middle_tentacle2",
			"left_middle_tentacle3",
			"left_middle_tentacle4",
			"left_middle_tentacle5",
			"left_middle_tentacle6",
			"left_middle_tentacle7");
		right_middle
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_middle_attack", RawAnimation.begin().thenPlay("right_middle_attack"));
		controllers.add(right_middle);

		var left_lower = new AnimationController<>(this, "left_lower", 6, animationHandler);
		IAnimationController.of(left_lower).goldenboughs_lib$enabledBones(
			"right_lower_tentacle_root",
			"right_lower_tentacle",
			"right_lower_tentacle2",
			"right_lower_tentacle3",
			"right_lower_tentacle4",
			"right_lower_tentacle5",
			"right_lower_tentacle6",
			"right_lower_tentacle7");
		left_lower
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_lower_attack", RawAnimation.begin().thenPlay("left_lower_attack"));
		controllers.add(left_lower);

		var right_lower = new AnimationController<>(this, "right_lower", 6, animationHandler);
		IAnimationController.of(right_lower).goldenboughs_lib$enabledBones(
			"left_lower_tentacle_root",
			"left_lower_tentacle",
			"left_lower_tentacle2",
			"left_lower_tentacle3",
			"left_lower_tentacle4",
			"left_lower_tentacle5",
			"left_lower_tentacle6",
			"left_lower_tentacle7");
		right_lower
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_lower_attack", RawAnimation.begin().thenPlay("right_lower_attack"));
		controllers.add(right_lower);
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStackInSlot, ItemStack stackBeingUnequipped) {
		super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped);
		LivingEntity entity = slotContext.entity();
		if (!(entity.level() instanceof ServerLevel serverLevel)) {
			return;
		}

		String identifier = slotContext.identifier();
		AttackLogicHolder data = entity.getData(EGOCuriosAttachments.ATTACK_LOGIC_HOLDER);
		switch (identifier) {
			case EGOCuriosConstants.EGO_CURIOS_LEFT_BACK -> data.remove(EGOCurios.modRl(getDescriptionId() + ".left_back"));
			case EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK -> data.remove(EGOCurios.modRl(getDescriptionId() + ".right_back"));
		}
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack previousStack, ItemStack stackBeingEquipped) {
		super.onEquip(slotContext, previousStack, stackBeingEquipped);
		LivingEntity entity = slotContext.entity();
		if (!(entity.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		String identifier = slotContext.identifier();
		AttackLogicHolder data = entity.getData(EGOCuriosAttachments.ATTACK_LOGIC_HOLDER);
		switch (identifier) {
			case EGOCuriosConstants.EGO_CURIOS_LEFT_BACK ->
				data.add(EGOCurios.modRl(getDescriptionId() + ".left_back"), new AttackLogic(entity, stackBeingEquipped, true));
			case EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK ->
				data.add(EGOCurios.modRl(getDescriptionId() + ".right_back"), new AttackLogic(entity, stackBeingEquipped, false));
		}
	}

	public static class AttackLogic implements AttackLogicHolder.IAttackLogic {
		private final LivingEntity entity;
		private final ItemStack itemStack;
		private final boolean isLeft;
		private @Nullable LivingEntity lastHurtByMob;
		private @Nullable LivingEntity lastHurtMob;
		private int tickCount;
		private int upperTickCount;
		private int middleTickCount;
		private int lowerTickCount;

		public AttackLogic(LivingEntity entity, ItemStack itemStack, boolean isLeft) {
			this.entity = entity;
			this.itemStack = itemStack;
			this.isLeft = isLeft;
		}

		private static int getUpperTickCount(RandomSource random) {
			return random.nextInt(40, 60);
		}

		@Override
		public void tick() {
			if (tickCount % 10 == 0) {
				lastHurtByMob = isTarget(entity.getLastHurtByMob());
				lastHurtMob = isTarget(entity.getLastHurtMob());
			}
			RandomSource random = entity.getRandom();

			tickCount++;

			if (upperTickCount > 0) {
				upperTickCount--;
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					upperTickCount = getUpperTickCount(random);
					upperAttack();
				}
			}

			if (middleTickCount > 0) {
				middleTickCount--;
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					middleTickCount = getUpperTickCount(random);
					middleAttack();
				}
			}

			if (lowerTickCount > 0) {
				lowerTickCount--;
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					lowerTickCount = getUpperTickCount(random);
					lowerAttack();
				}
			}
		}

		public void upperAttack() {
			play(isLeft ? "left_upper" : "right_upper",
				isLeft ? "left_upper_attack" : "right_upper_attack");
		}

		public void middleAttack() {
			play(isLeft ? "left_middle" : "right_middle",
				isLeft ? "left_middle_attack" : "right_middle_attack");
		}

		public void lowerAttack() {
			play(isLeft ? "left_lower" : "right_lower",
				isLeft ? "left_lower_attack" : "right_lower_attack");
		}

		private void play(String controllerName, String animationName) {
			Item item = itemStack.getItem();
			GeoItem.getId(itemStack);
			if (!(item instanceof GeoItem geoItem) || !(entity.level() instanceof ServerLevel serverLevel)) {
				return;
			}

			long id = GeoItem.getOrAssignId(itemStack, serverLevel);
			geoItem.triggerArmorAnim(entity, id, controllerName, animationName);
		}

		private LivingEntity isTarget(LivingEntity entity) {
			if (entity == null || !entity.isAlive() || entity.isRemoved()) {
				return null;
			}
			return entity;
		}

		public @Nullable LivingEntity getTarget() {
			if (isTarget(lastHurtByMob) != null && distanceToSqr(lastHurtByMob)) {
				return lastHurtByMob;
			}

			if (isTarget(lastHurtMob) != null && distanceToSqr(lastHurtMob)) {
				return lastHurtMob;
			}

			return null;
		}

		private boolean distanceToSqr(LivingEntity entity) {
			return entity.distanceToSqr(entity) < 2.6f;
		}
	}
}
