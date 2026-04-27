package architecture.ego_curios.core.registry;

import architecture.ego_curios.datagen.DatagenCuriosTest;
import architecture.ego_curios.init.tag.CuriosItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

import static top.theillusivec4.curios.api.CuriosApi.registerCurioPredicate;

/**
 * 饰品
 */
public final class CurioRegistry {
	public static void registry() {
		createValidators(DatagenCuriosTest.EGO_CURIOS_VALIDATOR, CuriosItemTags.EGO_CURIOS);
		createValidators(DatagenCuriosTest.EGO_CURIOS_HEADWEAR_VALIDATOR, CuriosItemTags.EGO_CURIOS_HEADWEAR);
		createValidators(DatagenCuriosTest.EGO_CURIOS_HEAD_VALIDATOR, CuriosItemTags.EGO_CURIOS_HEAD);
		createValidators(DatagenCuriosTest.EGO_CURIOS_HINDBRAIN_VALIDATOR, CuriosItemTags.EGO_CURIOS_HINDBRAIN);
		createValidators(DatagenCuriosTest.EGO_CURIOS_EYE_VALIDATOR, CuriosItemTags.EGO_CURIOS_EYE);
		createValidators(DatagenCuriosTest.EGO_CURIOS_FACE_VALIDATOR, CuriosItemTags.EGO_CURIOS_FACE);
		createValidators(DatagenCuriosTest.EGO_CURIOS_CHEEK_VALIDATOR, CuriosItemTags.EGO_CURIOS_CHEEK);
		createValidators(DatagenCuriosTest.EGO_CURIOS_MASK_VALIDATOR, CuriosItemTags.EGO_CURIOS_MASK);
		createValidators(DatagenCuriosTest.EGO_CURIOS_MOUTH_VALIDATOR, CuriosItemTags.EGO_CURIOS_MOUTH);
		createValidators(DatagenCuriosTest.EGO_CURIOS_NECK_VALIDATOR, CuriosItemTags.EGO_CURIOS_NECK);
		createValidators(DatagenCuriosTest.EGO_CURIOS_BROOCH_VALIDATOR, CuriosItemTags.EGO_CURIOS_BROOCH);
		createValidators(DatagenCuriosTest.EGO_CURIOS_HAND_VALIDATOR, CuriosItemTags.EGO_CURIOS_HAND);
		createValidators(DatagenCuriosTest.EGO_CURIOS_GLOVE_VALIDATOR, CuriosItemTags.EGO_CURIOS_GLOVE);
		registerCurioPredicate(DatagenCuriosTest.EGO_CURIOS_LEFT_BACK_VALIDATOR,
			(slotResult) -> {
				if (!slotResult.stack().is(CuriosItemTags.EGO_CURIOS_BACK)) {
					return false;
				}

				var curiosInventory = CuriosApi.getCuriosInventory(slotResult.slotContext().entity());
				if (curiosInventory.isEmpty()) {
					return false;
				}

				var item = slotResult.stack().getItem();
				for (var a : curiosInventory.get().findCurios(item)) {
					if (a.slotContext().identifier().equals(DatagenCuriosTest.EGO_CURIOS_RIGHT_BACK)) {
						return false;
					}
				}
				return true;
			});
		registerCurioPredicate(DatagenCuriosTest.EGO_CURIOS_RIGHT_BACK_VALIDATOR,
			(slotResult) -> {
				if (!slotResult.stack().is(CuriosItemTags.EGO_CURIOS_BACK)) {
					return false;
				}

				var curiosInventory = CuriosApi.getCuriosInventory(slotResult.slotContext().entity());
				if (curiosInventory.isEmpty()) {
					return false;
				}

				var item = slotResult.stack().getItem();
				for (var a : curiosInventory.get().findCurios(item)) {
					if (a.slotContext().identifier().equals(DatagenCuriosTest.EGO_CURIOS_LEFT_BACK)) {
						return false;
					}
				}
				return true;
			});
	}

	private static void createValidators(ResourceLocation name, TagKey<Item> tagKey) {
		registerCurioPredicate(name, (slotResult) -> slotResult.stack().is(tagKey));
	}
}
