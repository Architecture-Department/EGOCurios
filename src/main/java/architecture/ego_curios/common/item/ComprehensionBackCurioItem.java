package architecture.ego_curios.common.item;

import architecture.ego_curios.api.AttackLogicHolder;
import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.core.EGOCuriosConstants;
import architecture.ego_curios.init.EGOCuriosAttachments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
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
		GeoItem.registerSyncedAnimatable(this);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		super.registerControllers(controllers);
		controllers.add(new AnimationController<>(this, "idle", 4, (state) -> {
			if (state.isCurrentAnimationStage("idle")) {
				return PlayState.CONTINUE;
			}
			return state.getController().tryTriggerAnimation("idle") ? PlayState.CONTINUE : PlayState.STOP;
		}).triggerableAnim("idle", RawAnimation.begin().thenPlay("idle")));

		controllers.add(new AnimationController<>(this, "attack", 4, (state) -> {
//			if (state.animationTick > 5) {
//				return state.setAndContinue(RawAnimation.begin().thenPlay("left_upper_attack"));
//			}
			return PlayState.STOP;
		})
			.triggerableAnim("left_upper_attack", RawAnimation.begin().thenPlay("left_upper_attack"))
			.triggerableAnim("left_middle_attack", RawAnimation.begin().thenPlay("left_middle_attack"))
			.triggerableAnim("left_lower_attack", RawAnimation.begin().thenPlay("left_lower_attack"))
			.triggerableAnim("right_upper_attack", RawAnimation.begin().thenPlay("right_upper_attack"))
			.triggerableAnim("right_middle_attack", RawAnimation.begin().thenPlay("right_middle_attack"))
			.triggerableAnim("right_lower_attack", RawAnimation.begin().thenPlay("right_lower_attack")));
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
			return random.nextInt(35, 50);
//			return 5;
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
			play(isLeft ? "left_upper_attack" : "right_upper_attack");
		}

		public void middleAttack() {
			play(isLeft ? "left_middle_attack" : "right_middle_attack");
		}

		public void lowerAttack() {
			play(isLeft ? "left_lower_attack" : "right_lower_attack");
		}

		private void play(String animation) {
			Item item = itemStack.getItem();
			if (!(item instanceof GeoItem geoItem) || !(entity.level() instanceof ServerLevel serverLevel)) {
				return;
			}

			geoItem.triggerAnim(entity, GeoItem.getOrAssignId(itemStack, serverLevel), "attack", animation);
			geoItem.stopTriggeredAnim(entity, GeoItem.getOrAssignId(itemStack, serverLevel), "attack", animation);
			geoItem.triggerArmorAnim(entity, GeoItem.getOrAssignId(itemStack, serverLevel), "attack", animation);
			geoItem.stopTriggeredArmorAnim(entity, GeoItem.getOrAssignId(itemStack, serverLevel), "attack", animation);
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
