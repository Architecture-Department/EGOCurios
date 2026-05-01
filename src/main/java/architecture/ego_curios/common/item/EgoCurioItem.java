package architecture.ego_curios.common.item;

import architecture.ego_curios.client.model.GeoCurioModel;
import architecture.ego_curios.client.renderer.GeoCuriosRenderer;
import architecture.goldenboughs_lib.api.virtue.VirtueAttributeModifier;
import architecture.goldenboughs_lib.api.world.item.IEgoItem;
import architecture.goldenboughs_lib.init.LibDataComponents;
import architecture.goldenboughs_lib.util.RationalityUtil;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * E.G.O饰品
 *
 * @author Dusttt & 小尽(WangXiaoJin)
 */
public class EgoCurioItem extends Item implements ICurioItem, GeoItem, IEgoItem {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private final List<Component> tooltips = new ArrayList<>();
	private final VirtueAttributeModifier virtueAddAttribute;
	private final boolean isEnderMask;

	//region ########## 请不要使用这些变量，这些仅用于生成国际化文本 ###################
	@Nullable
	@ApiStatus.Internal
	private Map<String, String> tooltipsI18nMap = new LinkedHashMap<>();
	@Nullable
	@ApiStatus.Internal
	private List<Function<String, MutableComponent>> tooltipsComponent;
	@Nullable
	@ApiStatus.Internal
	private List<String> tooltipsI18n;
	//endregion

	public EgoCurioItem(Builder<? extends EgoCurioItem> egoCurioBuilder) {
		super(egoCurioBuilder.properties.component(LibDataComponents.IS_RESTRAIN, false)
			.stacksTo(1)
			.fireResistant());
		this.virtueAddAttribute = egoCurioBuilder.virtueAddAttribute.build();
		this.isEnderMask = egoCurioBuilder.isEnderMask;
		this.tooltipsI18n = egoCurioBuilder.tooltips;
		this.tooltipsComponent = egoCurioBuilder.tooltipsComponent;
	}

	@Override
	public void onUnequip(final SlotContext slotContext, final ItemStack newStackInSlot, final ItemStack stackBeingUnequipped) {
		ICurioItem.super.onUnequip(slotContext, newStackInSlot, stackBeingUnequipped);
		curioDataUpdate(slotContext, newStackInSlot);
		if (slotContext.entity() instanceof Player player) {
			RationalityUtil.restrictValue(player);
		}
	}

	@Override
	public void onEquip(final SlotContext slotContext, final ItemStack previousStack, final ItemStack stackBeingEquipped) {
		ICurioItem.super.onEquip(slotContext, previousStack, stackBeingEquipped);
		curioDataUpdate(slotContext, previousStack);
		if (slotContext.entity() instanceof Player player) {
			RationalityUtil.restrictValue(player);
		}
	}

	public void curioDataUpdate(SlotContext slotContext, ItemStack newStackInSlot) {
		if (!(slotContext.entity().level() instanceof ServerLevel serverLevel)) {
			return;
		}
		curioDataUpdate(serverLevel, newStackInSlot);
	}

	protected void curioDataUpdate(ServerLevel serverLevel, ItemStack newStackInSlot) {
		GeoItem.getOrAssignId(newStackInSlot, serverLevel);
	}

	@Override
	protected @NotNull String getOrCreateDescriptionId() {
		String itemDescriptionId = super.getOrCreateDescriptionId();
		if (this.tooltipsI18n == null) {
			return itemDescriptionId;
		}

		AtomicInteger tooltipComponentIndex = new AtomicInteger();
		this.tooltipsI18n.forEach(tooltipValue -> {
			int index = tooltipComponentIndex.get();
			tooltipComponentIndex.incrementAndGet();
			String key = itemDescriptionId + ".tooltip." + index;
			if (!FMLEnvironment.production && this.tooltipsI18nMap != null) {
				this.tooltipsI18nMap.put(key, tooltipValue);
			}

			if (this.tooltipsComponent != null) {
				this.tooltips.add(this.tooltipsComponent.get(index).apply(key));
			}
		});

		if (FMLEnvironment.production && this.tooltipsI18nMap != null) {
			this.tooltipsI18nMap = null;
		}

		this.tooltipsComponent = null;
		this.tooltipsI18n = null;
		return itemDescriptionId;
	}

	@Override
	public List<Component> getSlotsTooltip(final List<Component> originalTooltips, final TooltipContext tooltipContext, final ItemStack itemStack) {
		List<Component> mutableTooltip = new ArrayList<>(originalTooltips);
		mutableTooltip.addAll(this.tooltips);
		return ICurioItem.super.getSlotsTooltip(mutableTooltip, tooltipContext, itemStack);
	}

	@Override
	public boolean canEquipFromUse(SlotContext slotContext, ItemStack itemStack) {
		return true;
	}

	@Override
	public boolean isEnderMask(final SlotContext slotContext, final EnderMan endermanEntity, final ItemStack itemStack) {
		return isEnderMask;
	}

	/**
	 * 属性加成
	 */
	@Override
	public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation attributeId, ItemStack itemStack) {
		Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = ICurioItem.super.getAttributeModifiers(slotContext, attributeId, itemStack);
		attributeModifiers.putAll(this.virtueAddAttribute.getAttributeModifiers(slotContext.entity(), attributeId, itemStack));
		return attributeModifiers;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@ApiStatus.Internal
	public Map<String, String> getAndClearTooltipsI18nMap() {
		Map<String, String> map = this.tooltipsI18nMap;
		if (map == null) {
			return null;
		}
		this.tooltipsI18nMap = null;
		return map;
	}

	@Override
	public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
		consumer.accept(new GeoRenderProvider() {
			private GeoArmorRenderer<?> renderer;
			private boolean isInitialized;

			@Nullable
			@Override
			public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
				if (!isInitialized) {
					ICurioRenderer iCurioRenderer = CuriosRendererRegistry.getRenderer(itemStack.getItem()).orElse(null);
					if (iCurioRenderer instanceof GeoArmorRenderer<?> geoArmorRenderer) {
						renderer = geoArmorRenderer;
					}
					isInitialized = true;
				}

				return this.renderer;
			}
		});
	}

	public static class Builder<T extends EgoCurioItem> {
		private final VirtueAttributeModifier.Builder virtueAddAttribute = new VirtueAttributeModifier.Builder();
		private final List<String> tooltips = new ArrayList<>();
		private final List<Function<String, MutableComponent>> tooltipsComponent = new ArrayList<>();
		private boolean isEnderMask;
		private Properties properties = new Properties();
		public @Nullable GeoCurioModel<T> model;
		public @Nullable Function<GeoCurioModel<T>, Function<T, GeoCuriosRenderer<T>>> curiosRenderer;

		public Builder() {
		}

		/**
		 * 勇气
		 *
		 * @param maxHealth 最大生命值
		 */
		public Builder<T> fortitude(int maxHealth) {
			this.virtueAddAttribute.fortitude(maxHealth);
			return this;
		}

		/**
		 * 谨慎
		 *
		 * @param maxRationality 最大理智
		 */
		public Builder<T> prudence(int maxRationality) {
			this.virtueAddAttribute.prudence(maxRationality);
			return this;
		}


		/**
		 * 自律
		 *
		 * @param blockBreakSpeed 挖掘速度
		 * @param attackKnockback 攻击击退
		 * @param workSuccessRate 工作成功率
		 * @param workSpeed       工作速度
		 */
		public Builder<T> temperance(int blockBreakSpeed, int attackKnockback, int workSuccessRate, int workSpeed) {
			this.virtueAddAttribute.temperance(blockBreakSpeed, attackKnockback, workSuccessRate, workSpeed);
			return this;
		}

		/**
		 * 自律
		 *
		 * @param blockBreakSpeed 挖掘速度
		 * @param attackKnockback 攻击击退
		 * @param workValue       工作成功率，工作速度
		 */
		public Builder<T> temperance(int blockBreakSpeed, int attackKnockback, int workValue) {
			this.virtueAddAttribute.temperance(blockBreakSpeed, attackKnockback, workValue);
			return this;
		}

		/**
		 * 自律
		 */
		public Builder<T> temperance(int value) {
			this.virtueAddAttribute.temperance(value);
			return this;
		}

		/**
		 * 正义
		 *
		 * @param movementSpeed 移动速度
		 * @param swimSpeed     游泳速度
		 * @param attackSpeed   攻击速度
		 */
		public Builder<T> justice(int movementSpeed, int swimSpeed, int attackSpeed) {
			this.virtueAddAttribute.justice(movementSpeed, swimSpeed, attackSpeed);
			return this;
		}

		/**
		 * 正义
		 *
		 * @param speed       移动速度，游泳速度
		 * @param attackSpeed 攻击速度
		 */
		public Builder<T> justice(int speed, int attackSpeed) {
			this.virtueAddAttribute.justice(speed, attackSpeed);
			return this;
		}

		/**
		 * 正义
		 */
		public Builder<T> justice(int value) {
			this.virtueAddAttribute.justice(value);
			return this;
		}

		public Builder<T> model(ResourceLocation modelRl) {
			return model(new GeoCurioModel<>(modelRl));
		}

		public Builder<T> model(GeoCurioModel<T> model) {
			this.model = model;
			renderer(GeoCuriosRenderer::new);
			return this;
		}

		public Builder<T> renderer(Function<GeoCurioModel<T>, GeoCuriosRenderer<T>> curiosRenderer) {
			this.curiosRenderer = model1 -> item1 -> curiosRenderer.apply(model1);
			return this;
		}

		public Builder<T> properties(Properties properties) {
			this.properties = properties;
			return this;
		}

		/**
		 * 启用类似南瓜头的屏蔽末影人对视的作用
		 */
		public Builder<T> enderMask() {
			this.isEnderMask = true;
			return this;
		}

		public Builder<T> addTooltip(String zhCn) {
			this.tooltips.add(zhCn);
			this.tooltipsComponent.add(Component::translatable);
			return this;
		}

		public Builder<T> addTooltip(String zhCn, UnaryOperator<MutableComponent> component) {
			this.tooltips.add(zhCn);
			this.tooltipsComponent.add((key) -> component.apply(Component.translatable(key)));
			return this;
		}

		public Builder<T> addTooltip(String zhCn, Style style) {
			this.tooltips.add(zhCn);
			this.tooltipsComponent.add((key) -> Component.translatable(key).setStyle(style));
			return this;
		}
	}
}
