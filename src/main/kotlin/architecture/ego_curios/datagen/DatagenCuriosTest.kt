package architecture.ego_curios.datagen

import architecture.ego_curios.util.EgoCuriosUtil
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
) : CuriosDataProvider(EgoCuriosUtil.ID, output, fileHelper, registries) {

	override fun generate(registries: HolderLookup.Provider, fileHelper: ExistingFileHelper) {
		createSlot(EgoCuriosUtil.EGO_CURIOS, EgoCuriosUtil.EGO_CURIOS_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_HEADWEAR, EgoCuriosUtil.EGO_CURIOS_HEADWEAR_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_HEAD, EgoCuriosUtil.EGO_CURIOS_HEAD_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_HINDBRAIN, EgoCuriosUtil.EGO_CURIOS_HINDBRAIN_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_EYE, EgoCuriosUtil.EGO_CURIOS_EYE_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_FACE, EgoCuriosUtil.EGO_CURIOS_FACE_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_CHEEK, EgoCuriosUtil.EGO_CURIOS_CHEEK_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_MASK, EgoCuriosUtil.EGO_CURIOS_MASK_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_MOUTH, EgoCuriosUtil.EGO_CURIOS_MOUTH_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_NECK, EgoCuriosUtil.EGO_CURIOS_NECK_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_BROOCH, EgoCuriosUtil.EGO_CURIOS_BROOCH_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_HAND, EgoCuriosUtil.EGO_CURIOS_HAND_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_GLOVE, EgoCuriosUtil.EGO_CURIOS_GLOVE_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_LEFT_BACK, EgoCuriosUtil.EGO_CURIOS_LEFT_BACK_VALIDATOR)
		createSlot(EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK, EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK_VALIDATOR)

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
			EgoCuriosUtil.EGO_CURIOS_HEADWEAR,
			EgoCuriosUtil.EGO_CURIOS_HEAD,
			EgoCuriosUtil.EGO_CURIOS_HINDBRAIN,
			EgoCuriosUtil.EGO_CURIOS_EYE,
			EgoCuriosUtil.EGO_CURIOS_FACE,
			EgoCuriosUtil.EGO_CURIOS_CHEEK,
			EgoCuriosUtil.EGO_CURIOS_MASK,
			EgoCuriosUtil.EGO_CURIOS_MOUTH,
			EgoCuriosUtil.EGO_CURIOS_NECK,
			EgoCuriosUtil.EGO_CURIOS_BROOCH,
			EgoCuriosUtil.EGO_CURIOS_HAND,
			EgoCuriosUtil.EGO_CURIOS_GLOVE,
			EgoCuriosUtil.EGO_CURIOS_LEFT_BACK,
			EgoCuriosUtil.EGO_CURIOS_RIGHT_BACK
		)
	}

	fun createSlot(nameID: String, icon: String, validator: ResourceLocation): ISlotData {
		return createSlot(nameID, validator).icon(EgoCuriosUtil.modRl(icon))
	}
}
