package architecture.ego_curios.common.item

import architecture.ego_curios.api.AttackLogicHolder
import architecture.ego_curios.common.payload.toc.CurioAppurtenanceSynchroPayload
import architecture.ego_curios.common.payload.toc.SlotContextExpand
import architecture.ego_curios.init.EGOCuriosAttachments
import architecture.ego_curios.util.EGOCuriosUtil
import architecture.goldenboughs_lib.mixed.geckolib.IAnimationController
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.SingletonGeoAnimatable
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import top.theillusivec4.curios.api.SlotContext

/**
 * 理解 后背 饰品
 */
class ComprehensionBackCurioItem(egoCurioBuilder: Builder<ComprehensionBackCurioItem>) :
	EgoCurioItem(egoCurioBuilder) {

	init {
		SingletonGeoAnimatable.registerSyncedAnimatable(this)
	}

	override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
		super.registerControllers(controllers)
		val idle = RawAnimation.begin().thenPlay("idle")
		val animationHandler = AnimationController.AnimationStateHandler<ComprehensionBackCurioItem> { state ->
			if (state.isCurrentAnimationStage("idle")) {
				PlayState.CONTINUE
			} else {
				if (state.controller.tryTriggerAnimation("idle")) PlayState.CONTINUE else PlayState.STOP
			}
		}

		// TODO 依旧模型的命名反了

		val left_upper = AnimationController(this, "left_upper", 6, animationHandler)
		IAnimationController.of(left_upper).`goldenboughs_lib$enabledBones`(
			"right_upper_tentacle_root",
			"right_upper_tentacle",
			"right_upper_tentacle2",
			"right_upper_tentacle3",
			"right_upper_tentacle4",
			"right_upper_tentacle5",
			"right_upper_tentacle6",
			"right_upper_tentacle7"
		)
		left_upper
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_upper_attack", RawAnimation.begin().thenPlay("left_upper_attack"))
		controllers.add(left_upper)

		val right_upper = AnimationController(this, "right_upper", 6, animationHandler)
		IAnimationController.of(right_upper).`goldenboughs_lib$enabledBones`(
			"left_upper_tentacle_root",
			"left_upper_tentacle",
			"left_upper_tentacle2",
			"left_upper_tentacle3",
			"left_upper_tentacle4",
			"left_upper_tentacle5",
			"left_upper_tentacle6",
			"left_upper_tentacle7"
		)
		right_upper
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_upper_attack", RawAnimation.begin().thenPlay("right_upper_attack"))
		controllers.add(right_upper)

		val left_middle = AnimationController(this, "left_middle", 6, animationHandler)
		IAnimationController.of(left_middle).`goldenboughs_lib$enabledBones`(
			"right_middle_tentacle_root",
			"right_middle_tentacle",
			"right_middle_tentacle2",
			"right_middle_tentacle3",
			"right_middle_tentacle4",
			"right_middle_tentacle5",
			"right_middle_tentacle6",
			"right_middle_tentacle7"
		)
		left_middle
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_middle_attack", RawAnimation.begin().thenPlay("left_middle_attack"))
		controllers.add(left_middle)

		val right_middle = AnimationController(this, "right_middle", 6, animationHandler)
		IAnimationController.of(right_middle).`goldenboughs_lib$enabledBones`(
			"left_middle_tentacle_root",
			"left_middle_tentacle",
			"left_middle_tentacle2",
			"left_middle_tentacle3",
			"left_middle_tentacle4",
			"left_middle_tentacle5",
			"left_middle_tentacle6",
			"left_middle_tentacle7"
		)
		right_middle
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_middle_attack", RawAnimation.begin().thenPlay("right_middle_attack"))
		controllers.add(right_middle)

		val left_lower = AnimationController(this, "left_lower", 6, animationHandler)
		IAnimationController.of(left_lower).`goldenboughs_lib$enabledBones`(
			"right_lower_tentacle_root",
			"right_lower_tentacle",
			"right_lower_tentacle2",
			"right_lower_tentacle3",
			"right_lower_tentacle4",
			"right_lower_tentacle5",
			"right_lower_tentacle6",
			"right_lower_tentacle7"
		)
		left_lower
			.triggerableAnim("idle", idle)
			.triggerableAnim("left_lower_attack", RawAnimation.begin().thenPlay("left_lower_attack"))
		controllers.add(left_lower)

		val right_lower = AnimationController(this, "right_lower", 6, animationHandler)
		IAnimationController.of(right_lower).`goldenboughs_lib$enabledBones`(
			"left_lower_tentacle_root",
			"left_lower_tentacle",
			"left_lower_tentacle2",
			"left_lower_tentacle3",
			"left_lower_tentacle4",
			"left_lower_tentacle5",
			"left_lower_tentacle6",
			"left_lower_tentacle7"
		)
		right_lower
			.triggerableAnim("idle", idle)
			.triggerableAnim("right_lower_attack", RawAnimation.begin().thenPlay("right_lower_attack"))
		controllers.add(right_lower)
	}

	override fun onUnequip(slotContext: SlotContext, newStackInSlot: ItemStack, stackBeingUnequipped: ItemStack) {
		super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped)
		val entity = slotContext.entity()
		if (entity.level() !is ServerLevel) {
			return
		}

		CurioAppurtenanceSynchroPayload.send(false, slotContext)

		val identifier = slotContext.identifier()
		removeAppurtenance(entity, identifier)

		val data = entity.getData(EGOCuriosAttachments.ATTACK_LOGIC_HOLDER)
		when (identifier) {
			EGOCuriosUtil.EGO_CURIOS_LEFT_BACK -> data.remove(EGOCuriosUtil.modRl("$descriptionId.left_back"))
			EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK -> data.remove(EGOCuriosUtil.modRl("$descriptionId.right_back"))
		}
	}

	override fun onEquip(slotContext: SlotContext, previousStack: ItemStack, stackBeingEquipped: ItemStack) {
		super.onEquip(slotContext, previousStack, stackBeingEquipped)

		val entity = slotContext.entity()
		if (entity.level() !is ServerLevel) {
			return
		}

		CurioAppurtenanceSynchroPayload.send(true, slotContext)

		val identifier = slotContext.identifier()
		addAppurtenance(entity, stackBeingEquipped, identifier)

		val data = entity.getData(EGOCuriosAttachments.ATTACK_LOGIC_HOLDER)
		when (identifier) {
			EGOCuriosUtil.EGO_CURIOS_LEFT_BACK ->
				data.add(EGOCuriosUtil.modRl("$descriptionId.left_back"), AttackLogic(entity, stackBeingEquipped, true))

			EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK ->
				data.add(EGOCuriosUtil.modRl("$descriptionId.right_back"), AttackLogic(entity, stackBeingEquipped, false))
		}
	}

	// @Override
	fun remove(entity: Entity, itemStack: ItemStack, map: Map<String, *>) {
		if (entity !is LivingEntity) return

		val slotContext = getSlotContext(map) ?: return

		removeAppurtenance(entity, slotContext.identifier)
	}

	fun removeAppurtenance(entity: Entity, identifier: String) {
		// entity.getAppurtenanceInfoMap().remove("curio_" + identifier)
	}

	fun addAppurtenance(livingEntity: LivingEntity, itemStack: ItemStack, identifier: String) {
		// livingEntity.getAppurtenanceInfoMap().put("curio_" + identifier, new CurioAnimatedAccessoryInfo<>(
		//     livingEntity,
		//     itemStack,
		//     new ModelIndex("curio", EGOCuriosConstants.modRl("comprehension_back"))
		// ))
	}

	// @Override
	fun add(entity: Entity, itemStack: ItemStack, map: Map<String, *>) {
		if (entity !is LivingEntity) return

		val slotContext = getSlotContext(map) ?: return

		addAppurtenance(entity, itemStack, slotContext.identifier)
	}

	companion object {
		fun getSlotContext(map: Map<String, *>): SlotContextExpand? {
			return map["slotContext"] as? SlotContextExpand
		}
	}

	class AttackLogic(
		private val entity: LivingEntity,
		private val itemStack: ItemStack,
		private val isLeft: Boolean
	) : AttackLogicHolder.IAttackLogic {
		private var lastHurtByMob: LivingEntity? = null
		private var lastHurtMob: LivingEntity? = null
		private var tickCount: Int = 0
		private var upperTickCount: Int = 0
		private var middleTickCount: Int = 0
		private var lowerTickCount: Int = 0

		override fun tick() {
			if (tickCount % 10 == 0) {
				lastHurtByMob = isTarget(entity.lastHurtByMob)
				lastHurtMob = isTarget(entity.lastHurtMob)
			}
			val random = entity.random

			tickCount++

			if (upperTickCount > 0) {
				upperTickCount--
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					upperTickCount = getUpperTickCount(random)
					upperAttack()
				}
			}

			if (middleTickCount > 0) {
				middleTickCount--
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					middleTickCount = getUpperTickCount(random)
					middleAttack()
				}
			}

			if (lowerTickCount > 0) {
				lowerTickCount--
			} else {
				if (random.nextBoolean() && getTarget() != null) {
					lowerTickCount = getUpperTickCount(random)
					lowerAttack()
				}
			}
		}

		fun upperAttack() {
			play(
				if (isLeft) "left_upper" else "right_upper",
				if (isLeft) "left_upper_attack" else "right_upper_attack"
			)
		}

		fun middleAttack() {
			play(
				if (isLeft) "left_middle" else "right_middle",
				if (isLeft) "left_middle_attack" else "right_middle_attack"
			)
		}

		fun lowerAttack() {
			play(
				if (isLeft) "left_lower" else "right_lower",
				if (isLeft) "left_lower_attack" else "right_lower_attack"
			)
		}

		private fun play(controllerName: String, animationName: String) {
			val item: Item = itemStack.item
			GeoItem.getId(itemStack)
			if (item !is GeoItem || entity.level() !is ServerLevel) {
				return
			}

			val serverLevel = entity.level() as ServerLevel
			val id = GeoItem.getOrAssignId(itemStack, serverLevel)
			(item as GeoItem).triggerArmorAnim(entity, id, controllerName, animationName)
		}

		private fun isTarget(entity: LivingEntity?): LivingEntity? {
			if (entity == null || !entity.isAlive || entity.isRemoved) {
				return null
			}
			return entity
		}

		fun getTarget(): LivingEntity? {
			if (isTarget(lastHurtByMob) != null && distanceToSqr(lastHurtByMob!!)) {
				return lastHurtByMob
			}

			if (isTarget(lastHurtMob) != null && distanceToSqr(lastHurtMob!!)) {
				return lastHurtMob
			}

			return null
		}

		private fun distanceToSqr(entity: LivingEntity): Boolean {
			return entity.distanceToSqr(entity) < 2.6f
		}

		companion object {
			private fun getUpperTickCount(random: RandomSource): Int {
				return random.nextInt(40, 60)
			}
		}
	}
}
