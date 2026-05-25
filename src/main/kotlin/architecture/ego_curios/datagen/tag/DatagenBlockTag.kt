package architecture.ego_curios.datagen.tag

import architecture.ego_curios.core.EGOCurios
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture
import architecture.ego_curios.core.EGOCuriosConstants

class DatagenBlockTag(
	output: PackOutput,
	lookupProvider: CompletableFuture<HolderLookup.Provider>,
	existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, lookupProvider, EGOCuriosConstants.ID, existingFileHelper) {

	override fun addTags(provider: HolderLookup.Provider) {
	}
}
