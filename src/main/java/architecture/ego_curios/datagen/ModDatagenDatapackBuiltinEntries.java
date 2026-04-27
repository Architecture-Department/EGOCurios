package architecture.ego_curios.datagen;

import architecture.ego_curios.core.EGOCurios;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 创建一个数据包内置条目
 */
public final class ModDatagenDatapackBuiltinEntries extends DatapackBuiltinEntriesProvider {

	public ModDatagenDatapackBuiltinEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, RegistrySetBuilder datapackEntriesBuilder) {
		super(output, registries, datapackEntriesBuilder, Set.of(EGOCurios.ID));
	}
}
