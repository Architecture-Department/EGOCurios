package architecture.ego_curios.datagen

import architecture.ego_curios.core.EGOCurios
import architecture.ego_curios.init.EGOCuriosItems
import architecture.goldenboughs_lib.util.client.DatagenItemModelUtil.withExistingParent
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import architecture.ego_curios.core.EGOCuriosConstants

/**
 * 物品模型数据生成器
 * 用于为模组中的物品生成对应的模型文件
 */
class DatagenItemModel(output: PackOutput, existingFileHelper: ExistingFileHelper) :
	ItemModelProvider(output, EGOCuriosConstants.ID, existingFileHelper) {

	override fun registerModels() {
		withExistingParent("item/curio/", EGOCuriosItems.REGISTRY)
	}
}
