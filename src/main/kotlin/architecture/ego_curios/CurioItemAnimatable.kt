package architecture.ego_curios

import cn.solarmoon.spark_core.animation.ItemAnimatable
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class CurioItemAnimatable<T>(
	itemStack: ItemStack,
	animLevel: Level
) : ItemAnimatable(itemStack, animLevel) where T : Item