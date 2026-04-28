package architecture.ego_curios.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashSet;
import java.util.Set;

public final class EGOCuriosConstants {
	public static final String EGO_CURIOS = "ego_curios";
	public static final String EGO_CURIOS_HEADWEAR = "ego_curios_headwear";
	public static final String EGO_CURIOS_HEAD = "ego_curios_head";
	public static final String EGO_CURIOS_HINDBRAIN = "ego_curios_hindbrain";
	public static final String EGO_CURIOS_EYE = "ego_curios_eye";
	public static final String EGO_CURIOS_FACE = "ego_curios_face";
	public static final String EGO_CURIOS_CHEEK = "ego_curios_cheek";
	public static final String EGO_CURIOS_MASK = "ego_curios_mask";
	public static final String EGO_CURIOS_MOUTH = "ego_curios_mouth";
	public static final String EGO_CURIOS_NECK = "ego_curios_neck";
	public static final String EGO_CURIOS_BROOCH = "ego_curios_brooch";
	public static final String EGO_CURIOS_HAND = "ego_curios_hand";
	public static final String EGO_CURIOS_GLOVE = "ego_curios_glove";
	public static final String EGO_CURIOS_LEFT_BACK = "ego_curios_left_back";
	public static final String EGO_CURIOS_RIGHT_BACK = "ego_curios_right_back";

	public static final ResourceLocation EGO_CURIOS_VALIDATOR = createTagId(EGO_CURIOS);
	public static final ResourceLocation EGO_CURIOS_HEADWEAR_VALIDATOR = createTagId(EGO_CURIOS_HEADWEAR);
	public static final ResourceLocation EGO_CURIOS_HEAD_VALIDATOR = createTagId(EGO_CURIOS_HEAD);
	public static final ResourceLocation EGO_CURIOS_HINDBRAIN_VALIDATOR = createTagId(EGO_CURIOS_HINDBRAIN);
	public static final ResourceLocation EGO_CURIOS_EYE_VALIDATOR = createTagId(EGO_CURIOS_EYE);
	public static final ResourceLocation EGO_CURIOS_FACE_VALIDATOR = createTagId(EGO_CURIOS_FACE);
	public static final ResourceLocation EGO_CURIOS_CHEEK_VALIDATOR = createTagId(EGO_CURIOS_CHEEK);
	public static final ResourceLocation EGO_CURIOS_MASK_VALIDATOR = createTagId(EGO_CURIOS_MASK);
	public static final ResourceLocation EGO_CURIOS_MOUTH_VALIDATOR = createTagId(EGO_CURIOS_MOUTH);
	public static final ResourceLocation EGO_CURIOS_NECK_VALIDATOR = createTagId(EGO_CURIOS_NECK);
	public static final ResourceLocation EGO_CURIOS_BROOCH_VALIDATOR = createTagId(EGO_CURIOS_BROOCH);
	public static final ResourceLocation EGO_CURIOS_HAND_VALIDATOR = createTagId(EGO_CURIOS_HAND);
	public static final ResourceLocation EGO_CURIOS_GLOVE_VALIDATOR = createTagId(EGO_CURIOS_GLOVE);
	public static final ResourceLocation EGO_CURIOS_LEFT_BACK_VALIDATOR = createTagId(EGO_CURIOS_LEFT_BACK);
	public static final ResourceLocation EGO_CURIOS_RIGHT_BACK_VALIDATOR = createTagId(EGO_CURIOS_RIGHT_BACK);

	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_HEADWEAR_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_HEAD_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_HINDBRAIN_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_EYE_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_FACE_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_CHEEK_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_MASK_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_MOUTH_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_NECK_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_BROOCH_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_HAND_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_GLOVE_SET = new HashSet<>();
	public static final Set<DeferredItem<? extends Item>> EGO_CURIOS_BACK_SET = new HashSet<>();

	private static String createId(String name) {
		return EGOCurios.modRlText(name);
	}

	private static ResourceLocation createTagId(String name) {
		return EGOCurios.modRl(name + "_tag");
	}
}
