package architecture.ego_curios

import architecture.resonator_combat_framework.api.appurtenance.AnimatedAccessoryInfo
import cn.solarmoon.spark_core.animation.model.ModelIndex
import com.jme3.bullet.objects.PhysicsRigidBody
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import org.joml.Matrix4f

open class CurioAnimatedAccessoryInfo<O>(
	owner: O,
	val itemStack: ItemStack,
	defaultModelIndex: ModelIndex
) : AnimatedAccessoryInfo<ItemStack, O>(owner, itemStack, defaultModelIndex)
	where O : LivingEntity {

	override fun addPhysicsCollision(name: String, body: PhysicsRigidBody) {
		super.addPhysicsCollision("curio_$name", body)
	}

	override fun removePhysicsCollision(name: String) {
		super.removePhysicsCollision("curio_$name")
	}

	override fun getWorldPositionMatrix(partialTicks: Number): Matrix4f {
		return Matrix4f()/*
			.translate(owner.getPosition(partialTicks.toFloat()).toVector3f())
			.rotateY(PI.toFloat() - owner.getPreciseBodyRotation(partialTicks.toFloat()).toRadians())*/
	}
}
