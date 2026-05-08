package architecture.ego_curios

import architecture.resonator_combat_framework.api.appurtenance.AnimAppurtenanceInfo
import cn.solarmoon.spark_core.animation.anim.AnimController
import cn.solarmoon.spark_core.animation.model.ModelController
import cn.solarmoon.spark_core.animation.model.ModelIndex
import com.jme3.bullet.objects.PhysicsRigidBody
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class CurioAnimAppurtenanceInfo<O>(
	owner: O,
	animatable: ItemStack,
	defaultModelIndex: ModelIndex
) : AnimAppurtenanceInfo<ItemStack, O>(owner, animatable, defaultModelIndex)
	where O : LivingEntity {

	override fun addPhysicsCollision(name: String, body: PhysicsRigidBody) {
		super.addPhysicsCollision("curio_$name", body)
	}

	override fun removePhysicsCollision(name: String) {
		super.removePhysicsCollision("curio_$name")
	}
}
