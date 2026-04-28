package architecture.ego_curios.events;

import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.init.EGOCuriosAttachments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = EGOCurios.ID)
public final class LivingEntityEvents {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void tickPost(EntityTickEvent.Post event) {
		Entity entity = event.getEntity();
		if (entity.isAlive()) {
			if (entity instanceof LivingEntity) {
				var comprehensionBackCurioItemAttackLogic = entity.getExistingDataOrNull(EGOCuriosAttachments.COMPREHENSION_BACK_CURIO_ITEM_ATTACK_LOGIC);
				if (comprehensionBackCurioItemAttackLogic != null) {
					comprehensionBackCurioItemAttackLogic.tick();
				}
			}
		}
	}
}
