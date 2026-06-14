package architecture.ego_curios.datagen

import architecture.ego_curios.init.EGOCuriosItems
import architecture.ego_curios.util.EGOCuriosUtil
import architecture.goldenboughs_lib.util.datagen.ItemModelUtil.withExistingParent
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

/**
 * 物品模型数据生成器
 * 用于为模组中的物品生成对应的模型文件
 */
class DatagenItemModel(output: PackOutput, existingFileHelper: ExistingFileHelper) :
	ItemModelProvider(output, EGOCuriosUtil.ID, existingFileHelper) {

	override fun registerModels() {
		withExistingParent(pathPrefix = "item/curio/", registry = EGOCuriosItems.REGISTRY)
	}
}
