package architecture.ego_curios.datagen

import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.core.EGOCuriosConstants
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
) : CuriosDataProvider(EGOCuriosConstants.ID, output, fileHelper, registries) {

	override fun generate(registries: HolderLookup.Provider, fileHelper: ExistingFileHelper) {
		createSlot(EGOCuriosConstants.EGO_CURIOS, EGOCuriosConstants.EGO_CURIOS_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_HEADWEAR, EGOCuriosConstants.EGO_CURIOS_HEADWEAR_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_HEAD, EGOCuriosConstants.EGO_CURIOS_HEAD_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_HINDBRAIN, EGOCuriosConstants.EGO_CURIOS_HINDBRAIN_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_EYE, EGOCuriosConstants.EGO_CURIOS_EYE_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_FACE, EGOCuriosConstants.EGO_CURIOS_FACE_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_CHEEK, EGOCuriosConstants.EGO_CURIOS_CHEEK_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_MASK, EGOCuriosConstants.EGO_CURIOS_MASK_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_MOUTH, EGOCuriosConstants.EGO_CURIOS_MOUTH_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_NECK, EGOCuriosConstants.EGO_CURIOS_NECK_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_BROOCH, EGOCuriosConstants.EGO_CURIOS_BROOCH_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_HAND, EGOCuriosConstants.EGO_CURIOS_HAND_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_GLOVE, EGOCuriosConstants.EGO_CURIOS_GLOVE_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_LEFT_BACK, EGOCuriosConstants.EGO_CURIOS_LEFT_BACK_VALIDATOR)
		createSlot(EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK, EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK_VALIDATOR)

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
			EGOCuriosConstants.EGO_CURIOS_HEADWEAR,
			EGOCuriosConstants.EGO_CURIOS_HEAD,
			EGOCuriosConstants.EGO_CURIOS_HINDBRAIN,
			EGOCuriosConstants.EGO_CURIOS_EYE,
			EGOCuriosConstants.EGO_CURIOS_FACE,
			EGOCuriosConstants.EGO_CURIOS_CHEEK,
			EGOCuriosConstants.EGO_CURIOS_MASK,
			EGOCuriosConstants.EGO_CURIOS_MOUTH,
			EGOCuriosConstants.EGO_CURIOS_NECK,
			EGOCuriosConstants.EGO_CURIOS_BROOCH,
			EGOCuriosConstants.EGO_CURIOS_HAND,
			EGOCuriosConstants.EGO_CURIOS_GLOVE,
			EGOCuriosConstants.EGO_CURIOS_LEFT_BACK,
			EGOCuriosConstants.EGO_CURIOS_RIGHT_BACK
		)
	}

	fun createSlot(nameID: String, icon: String, validator: ResourceLocation): ISlotData {
		return createSlot(nameID, validator).icon(EGOCuriosConstants.modRl(icon))
	}
}
