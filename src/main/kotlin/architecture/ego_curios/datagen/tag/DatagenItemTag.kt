package architecture.ego_curios.datagen.tag

import architecture.ego_curios.init.EgoCuriosItems
import architecture.ego_curios.init.tag.EgoCuriosItemTags
import architecture.ego_curios.util.EgoCuriosUtil
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
) : ItemTagsProvider(output, lookupProvider, blockTags, EgoCuriosUtil.ID, existingFileHelper) {

	override fun addTags(provider: HolderLookup.Provider) {
		addSet(EgoCuriosItemTags.EGO_CURIOS_HEADWEAR, EgoCuriosUtil.EGO_CURIOS_HEADWEAR_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_HEAD, EgoCuriosUtil.EGO_CURIOS_HEAD_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_HINDBRAIN, EgoCuriosUtil.EGO_CURIOS_HINDBRAIN_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_EYE, EgoCuriosUtil.EGO_CURIOS_EYE_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_FACE, EgoCuriosUtil.EGO_CURIOS_FACE_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_CHEEK, EgoCuriosUtil.EGO_CURIOS_CHEEK_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_MASK, EgoCuriosUtil.EGO_CURIOS_MASK_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_MOUTH, EgoCuriosUtil.EGO_CURIOS_MOUTH_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_NECK, EgoCuriosUtil.EGO_CURIOS_NECK_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_BROOCH, EgoCuriosUtil.EGO_CURIOS_BROOCH_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_HAND, EgoCuriosUtil.EGO_CURIOS_HAND_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_GLOVE, EgoCuriosUtil.EGO_CURIOS_GLOVE_SET)
			.add(EgoCuriosItems.TEST.get())
		addSet(EgoCuriosItemTags.EGO_CURIOS_BACK, EgoCuriosUtil.EGO_CURIOS_BACK_SET)
			.add(EgoCuriosItems.TEST.get())
		tag(EgoCuriosItemTags.EGO_CURIOS).addTags(
			EgoCuriosItemTags.EGO_CURIOS_HEADWEAR,
			EgoCuriosItemTags.EGO_CURIOS_CHEEK,
			EgoCuriosItemTags.EGO_CURIOS_HEAD,
			EgoCuriosItemTags.EGO_CURIOS_HINDBRAIN,
			EgoCuriosItemTags.EGO_CURIOS_EYE,
			EgoCuriosItemTags.EGO_CURIOS_FACE,
			EgoCuriosItemTags.EGO_CURIOS_MASK,
			EgoCuriosItemTags.EGO_CURIOS_MOUTH,
			EgoCuriosItemTags.EGO_CURIOS_NECK,
			EgoCuriosItemTags.EGO_CURIOS_BROOCH,
			EgoCuriosItemTags.EGO_CURIOS_HAND,
			EgoCuriosItemTags.EGO_CURIOS_GLOVE,
			EgoCuriosItemTags.EGO_CURIOS_BACK
		)

		tag(LibItemTags.EGO)
			.addTag(EgoCuriosItemTags.EGO_CURIOS)
	}

	@Suppress("UNCHECKED_CAST")
	private fun addSet(tag: TagKey<Item>, set: Set<DeferredItem<out Item>>): IntrinsicTagAppender<Item> {
		return tag(tag).add(*set.stream().map { it.get() }.toArray { arrayOfNulls<Item>(it) })
	}
}
