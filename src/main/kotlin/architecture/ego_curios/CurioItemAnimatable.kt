package architecture.ego_curios

import architecture.ego_curios.api.getItemStackSlots
import cn.solarmoon.spark_core.animation.ItemAnimatable
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotContext

class CurioItemAnimatable<T>(
	itemStack: ItemStack,
	animLevel: Level
) : ItemAnimatable(itemStack, animLevel) where T : Item {
}