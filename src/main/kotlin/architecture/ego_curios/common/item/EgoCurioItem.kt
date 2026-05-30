package architecture.ego_curios.common.item

import architecture.ego_curios.client.renderer.GeoCurioRenderer
import architecture.goldenboughs_lib.api.world.item.IEgoItem
import architecture.goldenboughs_lib.client.model.curio.GeoCurioModel
import architecture.goldenboughs_lib.init.LibDataComponentTypes
import architecture.goldenboughs_lib.module.rationality.util.RationalityUtil.restrictRationalityValue
import architecture.goldenboughs_lib.module.virtue.api.VirtueAttributeModifier
import com.google.common.collect.Multimap
import net.minecraft.client.model.HumanoidModel
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.monster.EnderMan
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.neoforged.fml.loading.FMLEnvironment
import org.jetbrains.annotations.ApiStatus
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.CuriosRendererRegistry
import top.theillusivec4.curios.api.type.capability.ICurioItem
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.UnaryOperator

/**
 * E.G.O饰品
 *
 * @author Dusttt & 小尽(WangXiaoJin)
 */
class EgoCurioItem(egoCurioBuilder: Builder<out EgoCurioItem>) : Item(
	egoCurioBuilder.properties.component(LibDataComponentTypes.IS_RESTRAIN, false)
		.stacksTo(1)
		.fireResistant()
), ICurioItem, GeoItem, IEgoItem {

	private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
	private val tooltips: MutableList<Component> = ArrayList()
	private val virtueAddAttribute: VirtueAttributeModifier = egoCurioBuilder.virtueAddAttribute.build()
	private val isEnderMask: Boolean = egoCurioBuilder.isEnderMask

	//region ########## 请不要使用这些变量，这些仅用于生成国际化文本 ###################
	@ApiStatus.Internal
	private var tooltipsI18nMap: MutableMap<String, String>? = LinkedHashMap()

	@ApiStatus.Internal
	private var tooltipsComponent: MutableList<Function<String, MutableComponent>>? = null

	@ApiStatus.Internal
	private var tooltipsI18n: MutableList<String>? = null
	//endregion

	init {
		tooltipsI18n = egoCurioBuilder.tooltips
		tooltipsComponent = egoCurioBuilder.tooltipsComponent
	}

	override fun onUnequip(slotContext: SlotContext, newStackInSlot: ItemStack, stackBeingUnequipped: ItemStack) {
		super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped)
		// curioDataUpdate(slotContext, newStackInSlot)
		val entity = slotContext.entity()
		if (entity is Player) {
			entity.restrictRationalityValue()
		}
	}

	override fun onEquip(slotContext: SlotContext, previousStack: ItemStack, stackBeingEquipped: ItemStack) {
		super.onEquip(slotContext, previousStack, stackBeingEquipped)
		// curioDataUpdate(slotContext, previousStack)
		val entity = slotContext.entity()
		if (entity is Player) {
			entity.restrictRationalityValue()
		}
	}

	fun curioDataUpdate(slotContext: SlotContext, newStackInSlot: ItemStack) {
		val serverLevel = slotContext.entity().level() as? ServerLevel ?: return
		curioDataUpdate(serverLevel, newStackInSlot)
	}

	protected fun curioDataUpdate(serverLevel: ServerLevel, newStackInSlot: ItemStack) {
		GeoItem.getOrAssignId(newStackInSlot, serverLevel)
	}

	override fun getOrCreateDescriptionId(): String {
		val itemDescriptionId = super.getOrCreateDescriptionId()
		if (tooltipsI18n == null) {
			return itemDescriptionId
		}

		var tooltipComponentIndex = 0
		tooltipsI18n!!.forEach { tooltipValue ->
			val index = tooltipComponentIndex++
			val key = "$itemDescriptionId.tooltip.$index"
			if (!FMLEnvironment.production && tooltipsI18nMap != null) {
				tooltipsI18nMap!![key] = tooltipValue
			}

			if (tooltipsComponent != null) {
				tooltips.add(tooltipsComponent!![index].apply(key))
			}
		}

		if (FMLEnvironment.production && tooltipsI18nMap != null) {
			tooltipsI18nMap = null
		}

		tooltipsComponent = null
		tooltipsI18n = null
		return itemDescriptionId
	}

	override fun getSlotsTooltip(
		tooltips: List<Component?>?,
		context: TooltipContext?,
		stack: ItemStack?
	): List<Component?>? {
		val mutableTooltip = ArrayList(tooltips ?: arrayListOf())
		mutableTooltip.addAll(this.tooltips)
		return super.getSlotsTooltip(mutableTooltip, context, stack)
	}

	override fun canEquipFromUse(slotContext: SlotContext, itemStack: ItemStack): Boolean = true

	override fun isEnderMask(slotContext: SlotContext, endermanEntity: EnderMan, itemStack: ItemStack): Boolean =
		isEnderMask

	/**
	 * 属性加成
	 */
	override fun getAttributeModifiers(
		slotContext: SlotContext,
		attributeId: ResourceLocation,
		itemStack: ItemStack
	): Multimap<Holder<Attribute>, AttributeModifier> {
		val attributeModifiers = super.getAttributeModifiers(slotContext, attributeId, itemStack)
		val entity = slotContext.entity()
		entity ?: return attributeModifiers
		attributeModifiers.putAll(virtueAddAttribute.getAttributeModifiers(entity, attributeId, itemStack))
		return attributeModifiers
	}

	override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
	}

	override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache

	@ApiStatus.Internal
	fun getAndClearTooltipsI18nMap(): MutableMap<String, String>? {
		val map = tooltipsI18nMap ?: return null
		tooltipsI18nMap = null
		return map
	}

	override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
		consumer.accept(object : GeoRenderProvider {
			private var renderer: GeoArmorRenderer<*>? = null
			private var isInitialized = false

			override fun <T : LivingEntity?> getGeoArmorRenderer(
				livingEntity: T?,
				itemStack: ItemStack,
				equipmentSlot: EquipmentSlot?,
				original: HumanoidModel<T>?
			): HumanoidModel<*>? {
				if (!isInitialized) {
					val iCurioRenderer = CuriosRendererRegistry.getRenderer(itemStack.item).orElse(null)
					if (iCurioRenderer is GeoArmorRenderer<*>) {
						renderer = iCurioRenderer
					}
					isInitialized = true
				}
				return renderer
			}
		})
	}

	companion object {
		@JvmStatic
		fun <T : EgoCurioItem> of(): Builder<T> = Builder()
	}

	open class Builder<T : EgoCurioItem> {
		internal val virtueAddAttribute = VirtueAttributeModifier.Builder()
		internal val tooltips = ArrayList<String>()
		internal val tooltipsComponent = ArrayList<Function<String, MutableComponent>>()

		@JvmField
		var model: GeoModel<T>? = null

		@JvmField
		var curiosRenderer: Function<GeoModel<T>, Function<T, GeoCurioRenderer<T>>>? = null

		internal var isEnderMask = false
			private set

		var properties: Properties = Properties()
			private set

		/**
		 * 勇气
		 *
		 * @param maxHealth 最大生命值
		 */
		fun fortitude(maxHealth: Int): Builder<T> {
			virtueAddAttribute.fortitude(maxHealth)
			return this
		}

		/**
		 * 谨慎
		 *
		 * @param maxRationality 最大理智
		 */
		fun prudence(maxRationality: Int): Builder<T> {
			virtueAddAttribute.prudence(maxRationality)
			return this
		}

		/**
		 * 自律
		 *
		 * @param blockBreakSpeed 挖掘速度
		 * @param attackKnockback 攻击击退
		 * @param workSuccessRate 工作成功率
		 * @param workSpeed       工作速度
		 */
		fun temperance(blockBreakSpeed: Int, attackKnockback: Int, workSuccessRate: Int, workSpeed: Int): Builder<T> {
			virtueAddAttribute.temperance(blockBreakSpeed, attackKnockback, workSuccessRate, workSpeed)
			return this
		}

		/**
		 * 自律
		 *
		 * @param blockBreakSpeed 挖掘速度
		 * @param attackKnockback 攻击击退
		 * @param workValue       工作成功率，工作速度
		 */
		fun temperance(blockBreakSpeed: Int, attackKnockback: Int, workValue: Int): Builder<T> {
			virtueAddAttribute.temperance(blockBreakSpeed, attackKnockback, workValue)
			return this
		}

		/**
		 * 自律
		 */
		fun temperance(value: Int): Builder<T> {
			virtueAddAttribute.temperance(value)
			return this
		}

		/**
		 * 正义
		 *
		 * @param movementSpeed 移动速度
		 * @param swimSpeed     游泳速度
		 * @param attackSpeed   攻击速度
		 */
		fun justice(movementSpeed: Int, swimSpeed: Int, attackSpeed: Int): Builder<T> {
			virtueAddAttribute.justice(movementSpeed, swimSpeed, attackSpeed)
			return this
		}

		/**
		 * 正义
		 *
		 * @param speed       移动速度，游泳速度
		 * @param attackSpeed 攻击速度
		 */
		fun justice(speed: Int, attackSpeed: Int): Builder<T> {
			virtueAddAttribute.justice(speed, attackSpeed)
			return this
		}

		/**
		 * 正义
		 */
		fun justice(value: Int): Builder<T> {
			virtueAddAttribute.justice(value)
			return this
		}

		fun model(modelRl: ResourceLocation): Builder<T> {
			return model(GeoCurioModel(modelRl))
		}

		fun model(model: GeoModel<T>): Builder<T> {
			this.model = model
			renderer { GeoCurioRenderer(it) }
			return this
		}

		fun renderer(curiosRenderer: Function<GeoModel<T>, GeoCurioRenderer<T>>): Builder<T> {
			this.curiosRenderer = Function { model -> Function { item -> curiosRenderer.apply(model) } }
			return this
		}

		fun properties(properties: Properties): Builder<T> {
			this.properties = properties
			return this
		}

		/**
		 * 启用类似南瓜头的屏蔽末影人对视的作用
		 */
		fun enderMask(): Builder<T> {
			isEnderMask = true
			return this
		}

		fun addTooltip(zhCn: String): Builder<T> {
			tooltips.add(zhCn)
			tooltipsComponent.add(Function { key -> Component.translatable(key) })
			return this
		}

		fun addTooltip(zhCn: String, component: UnaryOperator<MutableComponent>): Builder<T> {
			tooltips.add(zhCn)
			tooltipsComponent.add(Function { key -> component.apply(Component.translatable(key)) })
			return this
		}

		fun addTooltip(zhCn: String, style: Style): Builder<T> {
			tooltips.add(zhCn)
			tooltipsComponent.add(Function { key -> Component.translatable(key).setStyle(style) })
			return this
		}
	}
}
