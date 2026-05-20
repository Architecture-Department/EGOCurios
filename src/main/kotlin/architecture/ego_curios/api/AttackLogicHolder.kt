package architecture.ego_curios.api

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity

class AttackLogicHolder(val entity: LivingEntity) {
	private val map: MutableMap<ResourceLocation, IAttackLogic> = HashMap()

	fun tick() {
		if (map.isEmpty()) return
		for (attackLogic in map.values) {
			attackLogic.tick()
		}
	}

	fun add(id: ResourceLocation, attackLogic: IAttackLogic): IAttackLogic? = map.put(id, attackLogic)

	fun remove(id: ResourceLocation): IAttackLogic? = map.remove(id)

	fun get(id: ResourceLocation): IAttackLogic? = map[id]

	fun contains(id: ResourceLocation): Boolean = map.containsKey(id)

	interface IAttackLogic {
		fun tick()
	}
}
