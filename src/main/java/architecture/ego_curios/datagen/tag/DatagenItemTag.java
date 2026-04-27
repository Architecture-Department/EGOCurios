package architecture.ego_curios.datagen.tag;

import architecture.ego_curios.core.EGOCurios;
import architecture.ego_curios.core.EGOCuriosConstants;
import architecture.ego_curios.init.tag.CuriosItemTags;
import architecture.goldenboughs_lib.init.tag.LibItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public final class DatagenItemTag extends ItemTagsProvider {
	public DatagenItemTag(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider, final CompletableFuture<TagLookup<Block>> blockTags, final ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTags, EGOCurios.ID, existingFileHelper);
	}

	@Override
	protected void addTags(final HolderLookup.Provider provider) {
		addSet(CuriosItemTags.EGO_CURIOS_HEADWEAR, EGOCuriosConstants.EGO_CURIOS_HEADWEAR);
		addSet(CuriosItemTags.EGO_CURIOS_HEAD, EGOCuriosConstants.EGO_CURIOS_HEAD);
		addSet(CuriosItemTags.EGO_CURIOS_HINDBRAIN, EGOCuriosConstants.EGO_CURIOS_HINDBRAIN);
		addSet(CuriosItemTags.EGO_CURIOS_EYE, EGOCuriosConstants.EGO_CURIOS_EYE);
		addSet(CuriosItemTags.EGO_CURIOS_FACE, EGOCuriosConstants.EGO_CURIOS_FACE);
		addSet(CuriosItemTags.EGO_CURIOS_CHEEK, EGOCuriosConstants.EGO_CURIOS_CHEEK);
		addSet(CuriosItemTags.EGO_CURIOS_MASK, EGOCuriosConstants.EGO_CURIOS_MASK);
		addSet(CuriosItemTags.EGO_CURIOS_MOUTH, EGOCuriosConstants.EGO_CURIOS_MOUTH);
		addSet(CuriosItemTags.EGO_CURIOS_NECK, EGOCuriosConstants.EGO_CURIOS_NECK);
		addSet(CuriosItemTags.EGO_CURIOS_BROOCH, EGOCuriosConstants.EGO_CURIOS_BROOCH);
		addSet(CuriosItemTags.EGO_CURIOS_HAND, EGOCuriosConstants.EGO_CURIOS_HAND);
		addSet(CuriosItemTags.EGO_CURIOS_GLOVE, EGOCuriosConstants.EGO_CURIOS_GLOVE);
		addSet(CuriosItemTags.EGO_CURIOS_BACK, EGOCuriosConstants.EGO_CURIOS_BACK);
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
			CuriosItemTags.EGO_CURIOS_BACK);

		tag(LibItemTags.EGO)
			.addTag(CuriosItemTags.EGO_CURIOS);
	}

	private @NotNull IntrinsicTagAppender<Item> addSet(TagKey<Item> tag, @NotNull Set<DeferredItem<? extends Item>> set) {
		return tag(tag).add(set.stream().map(DeferredHolder::get).toArray(Item[]::new));
	}
}
