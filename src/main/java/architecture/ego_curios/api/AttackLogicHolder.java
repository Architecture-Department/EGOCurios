package architecture.ego_curios.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class AttackLogicHolder {
	private final Map<ResourceLocation, IAttackLogic> map = new HashMap<>();
	private final LivingEntity entity;

	public AttackLogicHolder(LivingEntity entity) {
		this.entity = entity;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public void tick() {
		if (map.isEmpty()) {
			return;
		}
		for (IAttackLogic attackLogic : map.values()) {
			attackLogic.tick();
		}
	}

	public IAttackLogic add(ResourceLocation id, IAttackLogic attackLogic) {
		return map.put(id, attackLogic);
	}

	public IAttackLogic remove(ResourceLocation id) {
		return map.remove(id);
	}

	public IAttackLogic get(ResourceLocation id) {
		return map.get(id);
	}

	public boolean contains(ResourceLocation id) {
		return map.containsKey(id);
	}

	public interface IAttackLogic {
		void tick();
	}
}
