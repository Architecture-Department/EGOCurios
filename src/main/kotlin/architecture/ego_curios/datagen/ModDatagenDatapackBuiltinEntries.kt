package architecture.ego_curios.datagen

import architecture.ego_curios.util.EGOCuriosUtil
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import java.util.concurrent.CompletableFuture

/**
 * 创建一个数据包内置条目
 */
class ModDatagenDatapackBuiltinEntries(
	output: PackOutput,
	registries: CompletableFuture<HolderLookup.Provider>,
	datapackEntriesBuilder: RegistrySetBuilder
) : DatapackBuiltinEntriesProvider(output, registries, datapackEntriesBuilder, setOf(EGOCuriosUtil.ID))
