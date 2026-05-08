@file:Suppress("unused")

package architecture.ego_curios.api

import com.google.common.collect.Multimap
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.level.Level
import top.theillusivec4.curios.api.CuriosApi
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.SlotResult
import top.theillusivec4.curios.api.type.ISlotType
import top.theillusivec4.curios.api.type.capability.ICurio
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler
import java.util.*
import java.util.function.Predicate

/**
 * 获取指定世界上已注册的槽位类型（如果存在）。
 *
 * @param id    槽位类型标识符
 * @param level 槽位类型所在的世界
 * @return 已注册的槽位类型，如果不存在则返回空
 */
fun Level.getSlot(id: String?): Optional<ISlotType> {
	return CuriosApi.getSlot(id, this)
}

/**
 * 获取指定端上已注册的槽位类型（如果存在）。
 *
 * @param id       槽位类型标识符
 * @param isClient true表示客户端槽位，false表示服务端槽位
 * @return 已注册的槽位类型，如果不存在则返回空
 */
fun getSlot(id: String?, isClient: Boolean): Optional<ISlotType> {
	return CuriosApi.getSlot(id, isClient)
}

/**
 * 获取指定世界上所有已注册的槽位类型。
 *
 * @param level 槽位类型所在的世界
 * @return 已注册的槽位类型
 */
fun Level.getSlots(): MutableMap<String, ISlotType> {
	return CuriosApi.getSlots(this)
}

/**
 * 获取指定端上所有已注册的槽位类型。
 *
 * @param isClient true表示客户端槽位，false表示服务端槽位
 * @return 已注册的槽位类型
 */
fun getSlots(isClient: Boolean): MutableMap<String, ISlotType> {
	return CuriosApi.getSlots(isClient)
}

/**
 * 获取指定世界上提供给玩家实体的所有已注册槽位类型。
 *
 * @param level 槽位类型所在的世界
 * @return 提供给玩家实体的槽位类型
 */
fun Level.getPlayerSlots(): MutableMap<String, ISlotType> {
	return CuriosApi.getPlayerSlots(this)
}

/**
 * 获取指定端上提供给玩家实体的所有已注册槽位类型。
 *
 * @param isClient true表示客户端槽位，false表示服务端槽位
 * @return 提供给玩家实体的槽位类型
 */
fun getPlayerSlots(isClient: Boolean): MutableMap<String, ISlotType> {
	return CuriosApi.getPlayerSlots(isClient)
}

/**
 * 获取提供给玩家实体的所有已注册槽位类型。
 *
 * @param player 槽位类型对应的 [Player]
 * @return 提供给玩家实体的槽位类型
 */
fun Player?.getPlayerSlots(): MutableMap<String, ISlotType> {
	return CuriosApi.getPlayerSlots(this)
}

/**
 * 获取提供给实体的所有已注册槽位类型。
 *
 * @param livingEntity 槽位类型对应的 [LivingEntity]
 * @return 提供给实体的槽位类型
 */
fun LivingEntity?.getEntitySlots(): MutableMap<String, ISlotType> {
	return CuriosApi.getEntitySlots(this)
}

/**
 * 获取指定世界上提供给实体类型的所有已注册槽位类型。
 *
 * @param type  槽位类型对应的实体类型
 * @param level 槽位类型所在的世界
 * @return 提供给实体类型的槽位类型
 */
fun Level.getEntitySlots(type: EntityType<*>?): MutableMap<String, ISlotType> {
	return CuriosApi.getEntitySlots(type, this)
}

/**
 * 获取指定世界上提供给实体类型的所有已注册槽位类型。
 *
 * @param this  槽位类型对应的实体类型
 * @param level 槽位类型所在的世界
 * @return 提供给实体类型的槽位类型
 */
fun EntityType<*>?.getEntitySlots(level: Level): MutableMap<String, ISlotType> {
	return CuriosApi.getEntitySlots(this, level)
}

/**
 * 获取提供给实体类型的所有已注册槽位类型。
 *
 * @param this     槽位类型对应的实体类型
 * @param isClient true表示客户端槽位，false表示服务端槽位
 * @return 提供给实体的槽位类型
 */
fun EntityType<*>.getEntitySlots(isClient: Boolean): MutableMap<String, ISlotType> {
	return CuriosApi.getEntitySlots(this, isClient)
}

/**
 * 获取指定 ItemStack 和世界的所有已注册槽位类型。
 *
 * @param stack ItemStack 对应的槽位类型
 * @param level ItemStack 所在的世界
 * @return 指定 ItemStack 的槽位类型
 */
fun Level.getItemStackSlots(stack: ItemStack?): MutableMap<String, ISlotType> {
	return CuriosApi.getItemStackSlots(stack, this)
}

/**
 * 获取指定 ItemStack 和世界的所有已注册槽位类型。
 *
 * @param this  ItemStack 对应的槽位类型
 * @param level ItemStack 所在的世界
 * @return 指定 ItemStack 的槽位类型
 */
fun ItemStack?.getItemStackSlots(level: Level): MutableMap<String, ISlotType> {
	return CuriosApi.getItemStackSlots(this, level)
}

/**
 * 获取指定 ItemStack 和端的所有已注册槽位类型。
 *
 * @param this     ItemStack 对应的槽位类型
 * @param isClient true表示客户端槽位，false表示服务端槽位
 * @return 指定 ItemStack 的槽位类型
 */
fun ItemStack?.getItemStackSlots(isClient: Boolean): MutableMap<String, ISlotType> {
	return CuriosApi.getItemStackSlots(this, isClient)
}

/**
 * 获取指定 ItemStack 和实体的所有已注册槽位类型。
 *
 * @param stack        ItemStack 对应的槽位类型
 * @param livingEntity 拥有槽位类型的实体
 * @return 指定 ItemStack 和实体的槽位类型
 */
fun LivingEntity.getItemStackSlots(stack: ItemStack?): MutableMap<String, ISlotType> {
	return CuriosApi.getItemStackSlots(stack, this)
}

/**
 * 获取指定 ItemStack 和实体的所有已注册槽位类型。
 *
 * @param this         ItemStack 对应的槽位类型
 * @param livingEntity 拥有槽位类型的实体
 * @return 指定 ItemStack 和实体的槽位类型
 */
fun ItemStack?.getItemStackSlots(livingEntity: LivingEntity): MutableMap<String, ISlotType> {
	return CuriosApi.getItemStackSlots(this, livingEntity)
}

/**
 * 获取附加到 [ItemStack] 上的 curio 能力的 [Optional]。
 *
 * @param this 要获取 curio 能力的 [ItemStack]
 * @return curio 能力的 [Optional]
 */
fun ItemStack?.getCurio(): Optional<ICurio> {
	return CuriosApi.getCurio(this)
}

/**
 * 获取附加到实体上的 curio 库存能力的 [Optional]。
 *
 * @param this 要获取 curio 库存能力的 [LivingEntity]
 * @return curio 库存能力的 [Optional]
 */
fun LivingEntity?.getCuriosInventory(): Optional<ICuriosItemHandler> {
	return CuriosApi.getCuriosInventory(this)
}

fun LivingEntity?.getStackInSlot(index: Int): ItemStack? {
	return getCuriosInventory().orElse(null)?.equippedCurios?.getStackInSlot(index)
}

/**
 * 检查 ItemStack 对于特定的堆叠和槽位上下文是否有效。
 *
 * @param this  正在检查 ItemStack 的槽位上下文
 * @param stack 待检查的 ItemStack
 * @return 如果 ItemStack 对槽位有效则返回 true，否则返回 false
 */
fun SlotContext?.isStackValid(stack: ItemStack?): Boolean {
	return CuriosApi.isStackValid(this, stack)
}

/**
 * 检索 ItemStack 的属性修饰符映射。
 * <br></br>
 * 注意：只有标识符保证存在于槽位上下文中。对于 ItemStack 可能不在 curio 槽位中的情况，
 * 例如检索物品工具提示时，索引为 -1 且佩戴者可能为 null。
 *
 * @param this  关于 ItemStack 已装备或可能装备的槽位的上下文
 * @param id    槽位唯一标识符
 * @param stack 待检查的 ItemStack
 * @return 属性修饰符映射
 */
fun SlotContext?.getAttributeModifiers(
	id: ResourceLocation?,
	stack: ItemStack?
): Multimap<Holder<Attribute?>?, AttributeModifier?> {
	return CuriosApi.getAttributeModifiers(this, id, stack)
}

/**
 * 检索 ItemStack 的属性修饰符映射。
 * <br></br>
 * 注意：只有标识符保证存在于槽位上下文中。对于 ItemStack 可能不在 curio 槽位中的情况，
 * 例如检索物品工具提示时，索引为 -1 且佩戴者可能为 null。
 *
 * @param this        待检查的 ItemStack
 * @param id          槽位唯一标识符
 * @param slotContext 关于 ItemStack 已装备或可能装备的槽位的上下文
 * @return 属性修饰符映射
 */
fun ItemStack?.getAttributeModifiers(
	id: ResourceLocation?,
	slotContext: SlotContext?
): Multimap<Holder<Attribute?>?, AttributeModifier?> {
	return CuriosApi.getAttributeModifiers(slotContext, id, this)
}

/**
 * 向指定的属性映射添加槽位修饰符。
 *
 * @param map        属性到属性修饰符的 [Multimap]
 * @param identifier 要添加修饰符的槽位标识符
 * @param id         与修饰符关联的标识符
 * @param amount     修饰符的数值
 * @param operation  修饰符的操作类型
 */
fun addSlotModifier(
	map: Multimap<Holder<Attribute?>?, AttributeModifier?>?,
	identifier: String?,
	id: ResourceLocation?,
	amount: Double,
	operation: AttributeModifier.Operation?
) {
	CuriosApi.addSlotModifier(map, identifier, id, amount, operation)
}

/**
 * 向 ItemStack 的标签数据添加槽位修饰符。
 *
 * @param this       要添加修饰符的 ItemStack
 * @param identifier 要添加修饰符的槽位标识符
 * @param id         与修饰符关联的标识符
 * @param amount     修饰符的数值
 * @param operation  修饰符的操作类型
 * @param slot       ItemStack 提供修饰符的槽位
 */
fun ItemStack?.addSlotModifier(
	identifier: String?,
	id: ResourceLocation?,
	amount: Double,
	operation: AttributeModifier.Operation?,
	slot: String?
) {
	CuriosApi.addSlotModifier(this, identifier, id, amount, operation, slot)
}

/**
 * 创建带有添加的槽位修饰符的 [ItemAttributeModifiers]。
 *
 * @param itemAttributeModifiers [ItemAttributeModifiers] 实例
 * @param identifier             要添加修饰符的槽位标识符
 * @param id                     与修饰符关联的标识符
 * @param amount                 修饰符的数值
 * @param operation              修饰符的操作类型
 * @param this                   提供修饰符的槽位组
 */
fun EquipmentSlotGroup?.withSlotModifier(
	itemAttributeModifiers: ItemAttributeModifiers?,
	identifier: String?,
	id: ResourceLocation?,
	amount: Double,
	operation: AttributeModifier.Operation?
): ItemAttributeModifiers {
	return CuriosApi.withSlotModifier(itemAttributeModifiers, identifier, id, amount, operation, this)
}

/**
 * 向 ItemStack 的标签数据添加属性修饰符。
 *
 * @param this      要添加修饰符的 ItemStack
 * @param attribute 要添加修饰符的属性
 * @param id        与修饰符关联的标识符
 * @param amount    修饰符的数值
 * @param operation 修饰符的操作类型
 * @param slot      ItemStack 提供修饰符的槽位
 */
fun ItemStack?.addModifier(
	attribute: Holder<Attribute?>?, id: ResourceLocation?,
	amount: Double, operation: AttributeModifier.Operation?,
	slot: String?
) {
	CuriosApi.addModifier(this, attribute, id, amount, operation, slot)
}

/**
 * 注册一个新的谓词，以 [ResourceLocation] 为键，用于决定哪些槽位分配给给定的 [ItemStack]。
 *
 * @param resourceLocation 验证器的唯一 [ResourceLocation]
 * @param predicate        为给定堆叠和 [SlotResult] 注册的谓词
 */
fun registerCurioPredicate(
	resourceLocation: ResourceLocation?,
	predicate: Predicate<SlotResult?>?
) {
	CuriosApi.registerCurioPredicate(resourceLocation, predicate)
}

/**
 * 获取现有的谓词（如果未找到则返回空），以 [ResourceLocation] 为键，用于决定哪些槽位分配给给定的 [ItemStack]。
 *
 * @param resourceLocation 验证器的唯一 [ResourceLocation]
 * @return 找到的 ResourceLocation 对应的谓词的 Optional，否则为空
 */
fun getCurioPredicate(
	resourceLocation: ResourceLocation?
): Optional<Predicate<SlotResult>> {
	return CuriosApi.getCurioPredicate(resourceLocation)
}

/**
 * 获取所有已注册的谓词，用于决定哪些槽位分配给给定的 [ItemStack]。
 *
 * @return 按 [ResourceLocation] 键入的已注册谓词映射
 */
fun getCurioPredicates(): MutableMap<ResourceLocation?, Predicate<SlotResult?>?> {
	return CuriosApi.getCurioPredicates()
}

/**
 * 评估一组谓词以确定给定的 [SlotResult] 是否是有效的分配。
 *
 * @param predicates   表示要迭代的谓词的 ResourceLocation 集合
 * @param this         包含 [SlotContext] 和 [ItemStack] 的 SlotResult
 * @return 如果任何谓词通过则返回 true，否则返回 false
 */
fun SlotResult?.testCurioPredicates(
	predicates: MutableSet<ResourceLocation?>?,
): Boolean {
	return CuriosApi.testCurioPredicates(predicates, this)
}

/**
 * 基于提供的 [SlotContext] 获取 [ResourceLocation]。
 *
 * @param this 作为 [ResourceLocation] 基础的 SlotContext
 * @return 基于 SlotContext 的 ResourceLocation
 */
fun SlotContext.getSlotId(): ResourceLocation {
	return CuriosApi.getSlotId(this)
}

/**
 * 执行通过 [&lt;][ItemStack.hurtAndBreak] 在 runnable 中使用的破坏行为
 * 
 * 这对于触发 curio 槽位中的破坏动画是必要的
 * 
 * 示例：{ stack.hurtAndBreak(amount, level entity, item -> CuriosApi.broadcastCurioBreakEvent(slotContext)); }
 *
 * @param this 关于 curio 所在槽位的上下文
 */
fun SlotContext?.broadcastCurioBreakEvent() {
	CuriosApi.broadcastCurioBreakEvent(this)
}