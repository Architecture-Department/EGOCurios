package architecture.ego_curios.datagen

import architecture.ego_curios.util.EGOCuriosUtil
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.data.ExistingFileHelper
import top.theillusivec4.curios.api.CuriosDataProvider
import top.theillusivec4.curios.api.type.capability.ICurio
import top.theillusivec4.curios.api.type.data.IEntitiesData
import top.theillusivec4.curios.api.type.data.ISlotData
import java.util.concurrent.CompletableFuture

class DatagenCuriosTest(
	output: PackOutput,
	fileHelper: ExistingFileHelper,
	registries: CompletableFuture<HolderLookup.Provider>
) : CuriosDataProvider(EGOCuriosUtil.ID, output, fileHelper, registries) {

	override fun generate(registries: HolderLookup.Provider, fileHelper: ExistingFileHelper) {
		createSlot(EGOCuriosUtil.EGO_CURIOS, EGOCuriosUtil.EGO_CURIOS_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_HEADWEAR, EGOCuriosUtil.EGO_CURIOS_HEADWEAR_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_HEAD, EGOCuriosUtil.EGO_CURIOS_HEAD_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_HINDBRAIN, EGOCuriosUtil.EGO_CURIOS_HINDBRAIN_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_EYE, EGOCuriosUtil.EGO_CURIOS_EYE_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_FACE, EGOCuriosUtil.EGO_CURIOS_FACE_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_CHEEK, EGOCuriosUtil.EGO_CURIOS_CHEEK_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_MASK, EGOCuriosUtil.EGO_CURIOS_MASK_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_MOUTH, EGOCuriosUtil.EGO_CURIOS_MOUTH_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_NECK, EGOCuriosUtil.EGO_CURIOS_NECK_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_BROOCH, EGOCuriosUtil.EGO_CURIOS_BROOCH_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_HAND, EGOCuriosUtil.EGO_CURIOS_HAND_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_GLOVE, EGOCuriosUtil.EGO_CURIOS_GLOVE_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_LEFT_BACK, EGOCuriosUtil.EGO_CURIOS_LEFT_BACK_VALIDATOR)
		createSlot(EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK, EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK_VALIDATOR)

		createSimpleEntities("player")
	}

	fun createSlot(nameId: String, validator: ResourceLocation): ISlotData {
		return super.createSlot(nameId)
			.dropRule(ICurio.DropRule.ALWAYS_KEEP)
			.addValidator(validator)
			.addCosmetic(true)
	}

	fun createSimpleEntities(nameID: String): IEntitiesData {
		return createEntities(nameID).addPlayer().addSlots(
			EGOCuriosUtil.EGO_CURIOS_HEADWEAR,
			EGOCuriosUtil.EGO_CURIOS_HEAD,
			EGOCuriosUtil.EGO_CURIOS_HINDBRAIN,
			EGOCuriosUtil.EGO_CURIOS_EYE,
			EGOCuriosUtil.EGO_CURIOS_FACE,
			EGOCuriosUtil.EGO_CURIOS_CHEEK,
			EGOCuriosUtil.EGO_CURIOS_MASK,
			EGOCuriosUtil.EGO_CURIOS_MOUTH,
			EGOCuriosUtil.EGO_CURIOS_NECK,
			EGOCuriosUtil.EGO_CURIOS_BROOCH,
			EGOCuriosUtil.EGO_CURIOS_HAND,
			EGOCuriosUtil.EGO_CURIOS_GLOVE,
			EGOCuriosUtil.EGO_CURIOS_LEFT_BACK,
			EGOCuriosUtil.EGO_CURIOS_RIGHT_BACK
		)
	}

	fun createSlot(nameID: String, icon: String, validator: ResourceLocation): ISlotData {
		return createSlot(nameID, validator).icon(EGOCuriosUtil.modRl(icon))
	}
}
