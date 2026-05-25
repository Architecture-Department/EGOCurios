package architecture.ego_curios.datagen.tag

import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.core.EGOCuriosConstants
import architecture.ego_curios.init.EGOCuriosItems
import architecture.ego_curios.init.tag.CuriosItemTags
import architecture.goldenboughs_lib.init.tag.LibItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.registries.DeferredItem
import java.util.concurrent.CompletableFuture

class DatagenItemTag(
	output: PackOutput,
	lookupProvider: CompletableFuture<HolderLookup.Provider>,
	blockTags: CompletableFuture<TagLookup<Block>>,
	existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, lookupProvider, blockTags, EGOCuriosConstants.ID, existingFileHelper) {

	override fun addTags(provider: HolderLookup.Provider) {
		addSet(CuriosItemTags.EGO_CURIOS_HEADWEAR, EGOCuriosConstants.EGO_CURIOS_HEADWEAR_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_HEAD, EGOCuriosConstants.EGO_CURIOS_HEAD_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_HINDBRAIN, EGOCuriosConstants.EGO_CURIOS_HINDBRAIN_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_EYE, EGOCuriosConstants.EGO_CURIOS_EYE_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_FACE, EGOCuriosConstants.EGO_CURIOS_FACE_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_CHEEK, EGOCuriosConstants.EGO_CURIOS_CHEEK_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_MASK, EGOCuriosConstants.EGO_CURIOS_MASK_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_MOUTH, EGOCuriosConstants.EGO_CURIOS_MOUTH_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_NECK, EGOCuriosConstants.EGO_CURIOS_NECK_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_BROOCH, EGOCuriosConstants.EGO_CURIOS_BROOCH_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_HAND, EGOCuriosConstants.EGO_CURIOS_HAND_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_GLOVE, EGOCuriosConstants.EGO_CURIOS_GLOVE_SET)
			.add(EGOCuriosItems.TEST.get())
		addSet(CuriosItemTags.EGO_CURIOS_BACK, EGOCuriosConstants.EGO_CURIOS_BACK_SET)
			.add(EGOCuriosItems.TEST.get())
		tag(CuriosItemTags.EGO_CURIOS).addTags(
			CuriosItemTags.EGO_CURIOS_HEADWEAR,
			CuriosItemTags.EGO_CURIOS_CHEEK,
			CuriosItemTags.EGO_CURIOS_HEAD,
			CuriosItemTags.EGO_CURIOS_HINDBRAIN,
			CuriosItemTags.EGO_CURIOS_EYE,
			CuriosItemTags.EGO_CURIOS_FACE,
			CuriosItemTags.EGO_CURIOS_MASK,
			CuriosItemTags.EGO_CURIOS_MOUTH,
			CuriosItemTags.EGO_CURIOS_NECK,
			CuriosItemTags.EGO_CURIOS_BROOCH,
			CuriosItemTags.EGO_CURIOS_HAND,
			CuriosItemTags.EGO_CURIOS_GLOVE,
			CuriosItemTags.EGO_CURIOS_BACK
		)

		tag(LibItemTags.EGO)
			.addTag(CuriosItemTags.EGO_CURIOS)
	}

	@Suppress("UNCHECKED_CAST")
	private fun addSet(tag: TagKey<Item>, set: Set<DeferredItem<out Item>>): IntrinsicTagAppender<Item> {
		return tag(tag).add(*set.stream().map { it.get() }.toArray { arrayOfNulls<Item>(it) })
	}
}
