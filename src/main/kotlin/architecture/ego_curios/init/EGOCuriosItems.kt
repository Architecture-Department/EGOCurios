package architecture.ego_curios.init

import architecture.ego_curios.client.renderer.ComprehensionBackCurioRenderer
import architecture.ego_curios.client.renderer.GeoCurioRenderer
import architecture.ego_curios.common.item.ComprehensionBackCurioItem
import architecture.ego_curios.common.item.EgoCurioItem
import architecture.ego_curios.common.item.TestEgoCurioItem
import architecture.ego_curios.core.EGOCuriosConstants
import architecture.ego_curios.core.EGOCuriosConstants.modRl
import architecture.ego_curios.datagen.i18n.ZhCn
import architecture.ego_curios.events.registry.client.CurioRenderersRegistrar
import architecture.goldenboughs_lib.module.lc_damage.api.LcDamageType
import net.minecraft.network.chat.Style
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Function

// TODO 可能需要给饰品添加等级
object EGOCuriosItems {
	@JvmField
	val REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(EGOCuriosConstants.ID)

	@JvmField
	val TEST: DeferredItem<EgoCurioItem> = REGISTRY.register(
		"test_curios"
	) { -> TestEgoCurioItem(EgoCurioItem.Builder()) }

	//region 头饰
	// TODO 受到精神伤害时，减少5%的精神损耗。
	@JvmField
	val SYRINX: DeferredItem<EgoCurioItem> = register(
		"syrinx_curios", "泣婴", CuriosType.HEADWEAR, ::EgoCurioItem
	) {
		it.fortitude(-2).prudence(-2).temperance(0).justice(6)
			.addTooltip("受到精神伤害时，减少5%的精神损耗。")
	}

	@JvmField
	val LAMP: DeferredItem<EgoCurioItem> = register(
		"lamp_curios", "目灯", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(3).prudence(0).temperance(3).justice(6) }

	@JvmField
	val HORNET: DeferredItem<EgoCurioItem> = register(
		"hornet_curios", "黄蜂", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(2).prudence(3).temperance(0).justice(0) }

	@JvmField
	val LAETITIA: DeferredItem<EgoCurioItem> = register(
		"laetitia_curios", "蕾蒂希娅", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(0).prudence(4).temperance(0).justice(0) }

	@JvmField
	val HEAVEN: DeferredItem<EgoCurioItem> = register(
		"heaven_curios", "穿刺极乐", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(4).prudence(0).temperance(2).justice(0) }

	@JvmField
	val DIFFRACTION: DeferredItem<EgoCurioItem> = register(
		"diffraction_curios", "虚无衍射体", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(0).prudence(6).temperance(0).justice(0) }

	// TODO 每受到一次不会导致死亡或恐慌的伤害，都有8%的概率免疫此次伤害。
	@JvmField
	val DISCORD: DeferredItem<EgoCurioItem> = register(
		"discord_curios", "不和", CuriosType.HEADWEAR, ::EgoCurioItem
	) {
		it.fortitude(-10).prudence(-10).temperance(0).justice(20)
			.addTooltip("每受到一次不会导致死亡或恐慌的伤害，都有8%的概率免疫此次伤害。")
	}

	// TODO 装备全套“粉红军备”E.G.O时，E.G.O武器“粉红军备”的攻击力将提高15%。
	@JvmField
	val PINK: DeferredItem<EgoCurioItem> = register(
		"pink_curios", "粉红军备", CuriosType.HEADWEAR, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(5).temperance(0).justice(0, 5)
			.addTooltip("装备全套“粉红军备”E.G.O时，E.G.O武器“粉红军备”的攻击力将提高15%。")
	}

	@JvmField
	val HYPOCRISY: DeferredItem<EgoCurioItem> = register(
		"hypocrisy_curios", "伪善", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(3).prudence(3).temperance(0).justice(0) }

	@JvmField
	val ADORATION: DeferredItem<EgoCurioItem> = register(
		"adoration_curios", "爱慕", CuriosType.HEADWEAR, ::EgoCurioItem
	) { it.fortitude(5).prudence(10).temperance(-5).justice(6) }

	//endregion
	//region 头
	@JvmField
	val STANDARD_TRAINING_EGO: DeferredItem<EgoCurioItem> = register(
		"standard_training_ego_curios", "教学用E.G.O", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(2).prudence(2).temperance(0).justice(0) }

	// TODO 对异想体“一罪与百善”进行工作的成功率提高10%
	@JvmField
	val PENITENCE: DeferredItem<EgoCurioItem> = register(
		"penitence_curios", "忏悔", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(2).temperance(0).justice(0)
			.addTooltip("对异想体“一罪与百善”进行工作的成功率提高10%")
	}

	@JvmField
	val IN_THE_NAME_OF_LOVE_AND_HATE: DeferredItem<EgoCurioItem> = register(
		"in_the_name_of_love_and_hate_curios", "以爱与恨之名", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.model(modRl("in_the_name_of_love_and_hate"))
			.fortitude(0).prudence(0).temperance(2).justice(4)
	}

	// TODO 沟通工作的成功率提高3%
	@JvmField
	val BEAR_PAWS: DeferredItem<EgoCurioItem> = register(
		"bear_paws_curios", "熊熊抱", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(0).prudence(4).temperance(0).justice(0) }

	@JvmField
	val HORN: DeferredItem<EgoCurioItem> = register(
		"horn_curios", "犄角", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(2).prudence(2).temperance(0).justice(0) }

	@JvmField
	val CHRISTMAS: DeferredItem<EgoCurioItem> = register(
		"christmas_curios", "悲惨圣诞", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(-4).prudence(8).temperance(0).justice(6) }

	@JvmField
	val FAINT_AROMA: DeferredItem<EgoCurioItem> = register(
		"faint_aroma_curios", "余香", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0)
			.prudence(0)
			.temperance(0, 2, 0, 2)
			.justice(0)
	}

	@JvmField
	val SHEEP_IS_CLOTHING: DeferredItem<EgoCurioItem> = register(
		"sheep_is_clothing_curios", "羊皮", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(9).prudence(-3).temperance(0).justice(0).addTooltip(
			"...这就是为什么我能在那匹狼饿着肚子的情况下安全回到这里！",
			Style.EMPTY.withColor(LcDamageType.PHYSICS.colourValue)
		)
	}

	@JvmField
	val INSPIRED_BRAVERY_BLUE: DeferredItem<EgoCurioItem> = register(
		"inspired_bravery_blue_curios", "内在勇气", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(0).justice(10)
			.addTooltip("勇气愈发强大，一切都成为可能！", Style.EMPTY.withColor(LcDamageType.PHYSICS.colourValue))
	}

	@JvmField
	val RECKLESS_FOOLISHNESS_BLUE: DeferredItem<EgoCurioItem> = register(
		"reckless_foolishness_blue_curios", "匹夫之勇", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(-5).temperance(0).justice(10)
			.addTooltip("充满勇气的战士时刻准备着冲锋陷阵！", Style.EMPTY.withColor(LcDamageType.PHYSICS.colourValue))
	}

	@JvmField
	val RECKLESS_FOOLISHNESS_ORANGE: DeferredItem<EgoCurioItem> = register(
		"reckless_foolishness_orange_curios", "匹夫之勇", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(-10).temperance(0).justice(15)
			.addTooltip("过度的勇气可能会铸成大错。", Style.EMPTY.withColor(LcDamageType.PHYSICS.colourValue))
	}

	@JvmField
	val RECKLESS_FOOLISHNESS_RED: DeferredItem<EgoCurioItem> = register(
		"reckless_foolishness_red_curios", "匹夫之勇", CuriosType.HEAD, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(-20).temperance(0).justice(20)
			.addTooltip("匹夫之勇，终将葬送一切。", Style.EMPTY.withColor(LcDamageType.PHYSICS.colourValue))
	}

	@JvmField
	val BLACK_SWAN: DeferredItem<EgoCurioItem> = register(
		"black_swan_curios", "黑天鹅", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(-4).prudence(-4).temperance(10).justice(0) }

	@JvmField
	val ENGULFING_DREAM: DeferredItem<EgoCurioItem> = register(
		"engulfing_dream_curios", "迷魂梦境", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(0).prudence(4).temperance(0).justice(0) }

	@JvmField
	val CHERRY_BLOSSOMS: DeferredItem<EgoCurioItem> = register(
		"cherry_blossoms_curios", "落樱", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(0).prudence(2).temperance(0).justice(2) }

	@JvmField
	val FEATHER_OF_HONOR: DeferredItem<EgoCurioItem> = register(
		"feather_of_honor_curios", "荣耀之羽", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(0).prudence(2).temperance(0).justice(4) }

	@JvmField
	val SO_CUTE: DeferredItem<EgoCurioItem> = register(
		"so_cute_curios", "超特么可爱！！！", CuriosType.HEAD, ::EgoCurioItem
	) { it.fortitude(4).prudence(0).temperance(-2).justice(0) }

	//endregion
	//region 后脑
	@JvmField
	val BENEDICTION: DeferredItem<EgoCurioItem> = register(
		"benediction_curios", "祝福", CuriosType.HINDBRAIN, ::EgoCurioItem
	) { it.fortitude(6).prudence(6).temperance(6).justice(6) }

	//endregion
	//region 眼
	// TODO 理解 的数值需要调整
	// TODO 实装效果
	// TODO 缺少模型
	@JvmField
	val COMPREHENSION_EYE: DeferredItem<EgoCurioItem> = register(
		"comprehension_eye_curios", "理解", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(2).prudence(2).temperance(2).justice(2) }

	@JvmField
	val SOLITUDE: DeferredItem<EgoCurioItem> = register(
		"solitude_curios", "孤独", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(3).justice(0) }

	// TODO 穿戴“沉默乐团”的E.G.O全套护甲时，能够吸收精神伤害。（免疫所有精神伤害并将之转化为恢复精神值）
	@JvmField
	val DA_CAPO: DeferredItem<EgoCurioItem> = register(
		"da_capo_curios", "Da Capo", CuriosType.EYE, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(4).justice(0)
			.addTooltip("穿戴“沉默乐团”的E.G.O全套护甲时，能够吸收精神伤害。（免疫所有精神伤害并将之转化为恢复精神值）")
	}

	// TODO 洞察工作的成功率提高3%
	@JvmField
	val GRINDER_MK4: DeferredItem<EgoCurioItem> = register(
		"grinder_mk4_curios", "粉碎机Mk4", CuriosType.EYE, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(4).justice(0)
			.addTooltip("洞察工作的成功率提高3%")
	}

	@JvmField
	val RED_EYES: DeferredItem<EgoCurioItem> = register(
		"red_eyes_curios", "赤瞳", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(3).justice(0) }

	// TODO 压迫工作的成功率提高6%
	@JvmField
	val JUSTITIA: DeferredItem<EgoCurioItem> = register(
		"justitia_curios", "正义裁决者", CuriosType.EYE, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(0).justice(6)
			.addTooltip("压迫工作的成功率提高6%").enderMask()
	}

	@JvmField
	val SMILE: DeferredItem<EgoCurioItem> = register(
		"smile_curios", "笑靥", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(5).prudence(5).temperance(0).justice(0).enderMask() }

	@JvmField
	val CENSORED: DeferredItem<EgoCurioItem> = register(
		"censored_curios", "CENSORED", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(10).temperance(0).justice(0).enderMask() }

	@JvmField
	val TODAY_IS_EXPRESSION: DeferredItem<EgoCurioItem> = register(
		"today_is_expression_curios", "此刻的神色", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(-2).temperance(4).justice(0).enderMask() }

	@JvmField
	val SOUND_OF_A_STAR: DeferredItem<EgoCurioItem> = register(
		"sound_of_a_star_curios", "新星之声", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(0).justice(10, 0).enderMask() }

	@JvmField
	val TOUGH: DeferredItem<EgoCurioItem> = register(
		"tough_curios", "谢顶之灾", CuriosType.EYE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(0).justice(2) }

	//endregion
	//region 面部
	@JvmField
	val COBALT_SCAR: DeferredItem<EgoCurioItem> = register(
		"cobalt_scar_curios", "郁蓝创痕", CuriosType.FACE, ::EgoCurioItem
	) { it.fortitude(4).prudence(0).temperance(0).justice(2) }

	//endregion
	//region 脸颊
	// TODO 生命值治疗效果提高5%
	@JvmField
	val MIMICRY: DeferredItem<EgoCurioItem> = register(
		"mimicry_curios", "拟态", CuriosType.CHEEK, ::EgoCurioItem
	) {
		it.fortitude(10).prudence(0).temperance(0).justice(0)
			.addTooltip("生命值治疗效果提高5%")
	}

	// TODO 受到精神伤害时，恢复伤害值20%的精神值，并暂时提高10点攻击速度。
	@JvmField
	val HARMONY: DeferredItem<EgoCurioItem> = register(
		"harmony_curios", "谐奏放射器", CuriosType.CHEEK, ::EgoCurioItem
	) {
		it.fortitude(8).prudence(-4).temperance(0).justice(0)
			.addTooltip("受到精神伤害时，恢复伤害值20%的精神值，并暂时提高10点攻击速度。")
	}

	@JvmField
	val THOSE_WHO_KNOW_THE_CRUELTY_OF_WINTER_AND_THE_AROMA_OF_ROSES: DeferredItem<EgoCurioItem> = register(
		"those_who_know_the_cruelty_of_winter_and_the_aroma_of_roses_curios",
		"我深知严冬的残酷...和玫瑰的芬芳...",
		CuriosType.CHEEK,
		::EgoCurioItem
	) {
		it.fortitude(6).prudence(6).temperance(0).justice(0)
			.addTooltip("玫瑰盛开...雪宫崩塌...", Style.EMPTY.withColor(LcDamageType.THE_SOUL.colourValue))
			.addTooltip("欢笑的人们不曾记得...", Style.EMPTY.withColor(LcDamageType.THE_SOUL.colourValue))
			.addTooltip("在那有位沉睡的美人...", Style.EMPTY.withColor(LcDamageType.THE_SOUL.colourValue))
	}

	@JvmField
	val THE_SWORD_SHARPENED_WITH_TEARS: DeferredItem<EgoCurioItem> = register(
		"the_sword_sharpened_with_tears_curios", "盈泪之剑", CuriosType.CHEEK, ::EgoCurioItem
	) { it.fortitude(0).prudence(2).temperance(0).justice(4) }

	//endregion
	//region 口罩
	@JvmField
	val REGRET: DeferredItem<EgoCurioItem> = register(
		"regret_curios", "悔恨", CuriosType.MASK, ::EgoCurioItem
	) { it.fortitude(2).prudence(2).temperance(0).justice(0) }

	@JvmField
	val CRIMSON_SCAR: DeferredItem<EgoCurioItem> = register(
		"crimson_scar_curios", "猩红创痕", CuriosType.MASK, ::EgoCurioItem
	) { it.fortitude(2).prudence(0).temperance(0).justice(4) }

	//endregion
	//region 口
	@JvmField
	val FOURTH_MATCH_FLAME: DeferredItem<EgoCurioItem> = register(
		"fourth_match_flame_curios", "终末火柴之光", CuriosType.MOUTH, ::EgoCurioItem
	) { it.fortitude(4).prudence(0).temperance(0).justice(0) }

	// TODO 持有“红舞鞋”E.G.O武器时，降低10点成功率和工作速度，提高10点攻击速度。
	@JvmField
	val SANGUINE_DESIRE: DeferredItem<EgoCurioItem> = register(
		"sanguine_desire_curios", "血之渴望", CuriosType.MOUTH, ::EgoCurioItem
	) {
		it.fortitude(4).prudence(0).temperance(0).justice(0)
			.addTooltip("持有“红舞鞋”E.G.O武器时，降低10点成功率和工作速度，提高10点攻击速度。")
	}

	@JvmField
	val SODA: DeferredItem<EgoCurioItem> = register(
		"soda_curios",
		"美味苏打",
		CuriosType.MOUTH,
		::EgoCurioItem,
	) { it.fortitude(2).prudence(0).temperance(0).justice(0) }


	// TODO 持有“魔弹”E.G.O武器时，提高3点最大与最小攻击力。
	@JvmField
	val MAGIC_BULLET: DeferredItem<EgoCurioItem> = register(
		"magic_bullet_curios", "魔弹", CuriosType.MOUTH, ::EgoCurioItem
	) {
		it.model(modRl("magic_bullet"))
			.fortitude(-5).prudence(-5).temperance(0).justice(10)
			.addTooltip("持有“魔弹”E.G.O武器时，提高3点最大与最小攻击力。")
	}

	@JvmField
	val ECSTASY: DeferredItem<EgoCurioItem> = register(
		"ecstasy_curios", "沉醉", CuriosType.MOUTH, ::EgoCurioItem
	) { it.fortitude(5).prudence(0).temperance(0).justice(0) }

	@JvmField
	val LANTERN: DeferredItem<EgoCurioItem> = register(
		"lantern_curios", "诱捕幻灯", CuriosType.MOUTH, ::EgoCurioItem
	) { it.fortitude(0).prudence(6).temperance(0).justice(0) }

	//endregion
	//region 颈
	// TODO 每隔一段时间为佩戴者恢复少量生命值。
	@JvmField
	val OUR_GALAXY: DeferredItem<EgoCurioItem> = register(
		"our_galaxy_curios", "小小银河", CuriosType.NECK, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(3).justice(0)
			.addTooltip("每隔一段时间为佩戴者恢复少量生命值。")
	}

	@JvmField
	val BEAK: DeferredItem<EgoCurioItem> = register(
		"beak_curios", "小喙", CuriosType.NECK, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(0).justice(2) }

	@JvmField
	val HARVEST: DeferredItem<EgoCurioItem> = register(
		"harvest_curios", "猎头长耙", CuriosType.NECK, ::EgoCurioItem
	) { it.fortitude(0).prudence(4).temperance(0).justice(0) }

	@JvmField
	val PLEASURE: DeferredItem<EgoCurioItem> = register(
		"pleasure_curios", "因乐癫狂", CuriosType.NECK, ::EgoCurioItem
	) { it.fortitude(0).prudence(10).temperance(-6).justice(0) }

	//endregion
	//region 胸针
	// TODO 穿戴“噪音”E.G.O护甲全套时，减少10点最高精神值并增加10点攻击速度。
	@JvmField
	val NOISE: DeferredItem<EgoCurioItem> = register(
		"noise_curios", "噪音", CuriosType.BROOCH, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(0).temperance(0).justice(2)
			.addTooltip("穿戴“噪音”E.G.O护甲全套时，减少10点最高精神值并增加10点攻击速度。")
	}

	@JvmField
	val LOGGING: DeferredItem<EgoCurioItem> = register(
		"logging_curios", "伐木者", CuriosType.BROOCH, ::EgoCurioItem
	) { it.fortitude(2).prudence(0).temperance(2).justice(0) }

	// TODO 持有“绿色枝干”E.G.O武器时，最大与最小攻击力会提高5点。
	@JvmField
	val GREEN_STEM: DeferredItem<EgoCurioItem> = register(
		"green_stem_curios", "绿色枝干", CuriosType.BROOCH, ::EgoCurioItem
	) {
		it.fortitude(0).prudence(6).temperance(0).justice(0)
			.addTooltip("持有“绿色枝干”E.G.O武器时，最大与最小攻击力会提高5点。")
	}

	@JvmField
	val FRAGMENTS_FROM_SOMEWHERE: DeferredItem<EgoCurioItem> = register(
		"fragments_from_somewhere_curios", "彼方的裂片", CuriosType.BROOCH, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(2).justice(0) }

	@JvmField
	val MOONLIGHT: DeferredItem<EgoCurioItem> = register(
		"moonlight_curios", "月光", CuriosType.BROOCH, ::EgoCurioItem
	) { it.fortitude(1).prudence(1).temperance(1).justice(1) }

	//endregion
	//region 手
	// TODO 本能工作的成功率提高6%
	@JvmField
	val GOLD_RUSH: DeferredItem<EgoCurioItem> = register(
		"gold_rush_curios", "闪金冲锋", CuriosType.HAND, ::EgoCurioItem
	) {
		it.fortitude(6).prudence(0).temperance(0).justice(0)
			.addTooltip("本能工作的成功率提高6%")
	}

	@JvmField
	val AMITA: DeferredItem<EgoCurioItem> = register(
		"amita_curios", "无量", CuriosType.HAND, ::EgoCurioItem
	) { it.fortitude(10).prudence(-4).temperance(0).justice(0) }

	//endregion
	//region 手套
	@JvmField
	val WRIST_CUTTER: DeferredItem<EgoCurioItem> = register(
		"wrist_cutter_curios", "割腕者", CuriosType.GLOVE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(2).justice(0) }

	@JvmField
	val SPORE: DeferredItem<EgoCurioItem> = register(
		"spore_curios", "荧光菌孢", CuriosType.GLOVE, ::EgoCurioItem
	) { it.fortitude(0).prudence(5).temperance(2).justice(0) }

	@JvmField
	val EXUVIAE: DeferredItem<EgoCurioItem> = register(
		"exuviae_curios", "脱落之皮", CuriosType.GLOVE, ::EgoCurioItem
	) { it.fortitude(5).prudence(2).temperance(0).justice(0) }

	@JvmField
	val GAZE: DeferredItem<EgoCurioItem> = register(
		"gaze_curios", "凝视", CuriosType.GLOVE, ::EgoCurioItem
	) { it.fortitude(4).prudence(0).temperance(0).justice(0) }

	@JvmField
	val WINGBEAT: DeferredItem<EgoCurioItem> = register(
		"wingbeat_curios", "翅振", CuriosType.GLOVE, ::EgoCurioItem
	) { it.fortitude(0).prudence(0).temperance(2).justice(0) }

	//endregion
	//region 背
	// TODO ALEPH等级的版本要区分效果
	// TODO 理解 的数值需要调整
	// TODO 实装效果
	@JvmField
	val COMPREHENSION_BACK: DeferredItem<ComprehensionBackCurioItem> = register<ComprehensionBackCurioItem>(
		"comprehension_back_curios", "理解", CuriosType.BACK, ::ComprehensionBackCurioItem
	) {
		it.model(modRl("comprehension_back"))
			.renderer(::ComprehensionBackCurioRenderer)
			.fortitude(2).prudence(2).temperance(2).justice(2)
	}

	@JvmField
	val SOLEMN_LAMENT: DeferredItem<EgoCurioItem> = register(
		"solemn_lament_curios", "圣宣", CuriosType.BACK, ::EgoCurioItem
	) { it.fortitude(2).prudence(2).temperance(2).justice(2) }

	@JvmField
	val THROUGH_THE_DARK_TWILIGHT: DeferredItem<EgoCurioItem> = register(
		"through_the_dark_twilight_curios", "破晓", CuriosType.BACK, ::EgoCurioItem
	) {
		it.fortitude(7).prudence(7).temperance(7).justice(7)
			.addTooltip("人们最终战胜了黄昏的黑暗，准备面对黎明的光辉。", Style.EMPTY.withColor(0xffcb1d))
			.addTooltip("而在那片昏暗的森林中，鸟儿的叽喳鸣唱依旧响彻着吗？", Style.EMPTY.withColor(0xffcb1d))
	}

	@JvmField
	val PARADISE_LOST: DeferredItem<EgoCurioItem> = register(
		"paradise_lost_curios", "失乐园", CuriosType.BACK, ::EgoCurioItem
	) {
		it.model(modRl("paradise_lost"))
			.fortitude(10).prudence(10).temperance(0).justice(10)
	}

	//endregion
	private fun <T : EgoCurioItem> register(
		entityType: EntityType<*>,
		zhName: String,
		type: CuriosType,
		item: Function<EgoCurioItem.Builder<T>, out T>,
		builder: Function<EgoCurioItem.Builder<T>, EgoCurioItem.Builder<T>>
	): DeferredItem<T> {
		return register(
			"%s_curios_%s".format(entityType.descriptionId, type), zhName, type, item, builder
		)
	}

	private fun <T : EgoCurioItem> register(
		id: String,
		zhName: String,
		type: CuriosType,
		builderFunction: Function<EgoCurioItem.Builder<T>, out T>,
		builder: Function<EgoCurioItem.Builder<T>, EgoCurioItem.Builder<T>>
	): DeferredItem<T> {
		val t = builder.apply(EgoCurioItem.of())
		val deferredItem = REGISTRY.register(id) { -> builderFunction.apply(t) }

		val curiosRenderer = t.curiosRenderer
		if (curiosRenderer != null) {
			CurioRenderersRegistrar.addRenderer(
				deferredItem as DeferredItem<EgoCurioItem>,
				curiosRenderer.apply(t.model!!) as Function<EgoCurioItem, GeoCurioRenderer<EgoCurioItem>>
			)
		}

		type.addCurio(deferredItem)
		ZhCn.addI18nItemText(zhName, deferredItem)

		return deferredItem
	}

	enum class CuriosType(val typeName: String, private val set: MutableSet<DeferredItem<out Item>>) {
		HEADWEAR("headwear", EGOCuriosConstants.EGO_CURIOS_HEADWEAR_SET),
		CHEEK("cheek", EGOCuriosConstants.EGO_CURIOS_CHEEK_SET),
		HEAD("head", EGOCuriosConstants.EGO_CURIOS_HEAD_SET),
		HINDBRAIN("hindbrain", EGOCuriosConstants.EGO_CURIOS_HINDBRAIN_SET),
		EYE("eye", EGOCuriosConstants.EGO_CURIOS_EYE_SET),
		FACE("face", EGOCuriosConstants.EGO_CURIOS_FACE_SET),
		MASK("mask", EGOCuriosConstants.EGO_CURIOS_MASK_SET),
		MOUTH("mouth", EGOCuriosConstants.EGO_CURIOS_MOUTH_SET),
		NECK("neck", EGOCuriosConstants.EGO_CURIOS_NECK_SET),
		BROOCH("brooch", EGOCuriosConstants.EGO_CURIOS_NECK_SET),
		HAND("hand", EGOCuriosConstants.EGO_CURIOS_HAND_SET),
		GLOVE("glove", EGOCuriosConstants.EGO_CURIOS_GLOVE_SET),
		BACK("back", EGOCuriosConstants.EGO_CURIOS_BACK_SET), ;

		fun addCurio(item: DeferredItem<out Item>) {
			this.set.add(item)
		}
	}
}
