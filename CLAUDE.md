# E.G.O.Curios

E.G.O. 饰品模组。基于 Curios API 的 15 槽位饰品系统，含 ~50 件 E.G.O. 饰品。

## 项目结构

```
src/main/kotlin/architecture/ego_curios/
├── core/
│   ├── EGOCurios.kt          — @Mod("ego_curios") 入口
│   ├── EGOCuriosClient.kt    — 客户端入口
│   ├── EGOCuriosConstants.kt — 15 个饰品槽位常量 + 物品集合
│   └── registry/
│       ├── CurioRegistry.kt          — 槽位验证器注册
│       ├── CapabilityRegistry.kt     — 能力注册
│       ├── PayloadRegistry.kt        — 网络包注册
│       └── client/CurioRenderersRegistrar.kt — 渲染器注册
├── api/
│   └── AttackLogicHolder.kt  — 实体攻击逻辑 Attachment
├── common/
│   ├── item/
│   │   ├── EgoCurioItem.kt           — 饰品基类 (ICurioItem + IEgoItem + GeoItem)
│   │   ├── ComprehensionBackCurioItem.kt — 理解/背后饰品（触手动画+攻击逻辑）
│   │   └── TestEgoCurioItem.kt       — 测试饰品
│   └── payload/toc/
│       ├── CurioAppurtenanceSynchroPayload.kt — 饰品外观同步包
│       └── SlotContextExpand.kt      — SlotContext 序列化
├── client/renderer/
│   ├── GeoCurioRenderer.kt           — GeckoLib 饰品渲染器基类
│   └── ComprehensionBackCurioRenderer.kt — 理解饰品渲染器（8触手骨骼控制）
├── init/
│   ├── EGOCuriosItems.kt     — ~50 件饰品注册
│   ├── EGOCuriosAttachments.kt — ATTACK_LOGIC_HOLDER 注册
│   ├── EGOCuriosCreativeModeTabs.kt
│   └── tag/CuriosItemTags.kt — 槽位物品标签
├── events/
│   ├── LivingEntityEvents.kt — EntityTick 驱动攻击逻辑
│   └── ModEvents.kt          — AddGeckoLibCachePathEvent
├── datagen/                  — 数据生成（物品模型/标签/i18n/Curios槽位定义）
└── util/
    └── CuriosUtil.kt         — Curios API 扩展函数
```

## 15 个饰品槽位

| 槽位 | ID                      |
|----|-------------------------|
| 通用 | `ego_curios`            |
| 头饰 | `ego_curios_headwear`   |
| 头部 | `ego_curios_head`       |
| 后脑 | `ego_curios_hindbrain`  |
| 眼部 | `ego_curios_eye`        |
| 面部 | `ego_curios_face`       |
| 脸颊 | `ego_curios_cheek`      |
| 面具 | `ego_curios_mask`       |
| 口部 | `ego_curios_mouth`      |
| 颈部 | `ego_curios_neck`       |
| 胸针 | `ego_curios_brooch`     |
| 手部 | `ego_curios_hand`       |
| 手套 | `ego_curios_glove`      |
| 左背 | `ego_curios_left_back`  |
| 右背 | `ego_curios_right_back` |

## 关键类

- `EgoCurioItem` — 饰品基类，Builder 模式配置四美德属性（fortitude/prudence/temperance/justice）+ 工具提示
- `ComprehensionBackCurioItem` — 最复杂饰品，8 条触手骨骼动画 + AttackLogic 定时攻击
- `GeoCurioRenderer<T>` — GeckoLib 饰品渲染桥接（GeoArmorRenderer + Curios ICurioRenderer）
- `CurioRegistry` — 15 槽位验证器（互斥逻辑：左右背槽不能装备同一物品）

## 上游依赖

- **GoldenBoughsLib** — IEgoItem, VirtueAttributeModifier, LcDamageType, GeoCurioModel, RationalityUtil
- **ResonatorCombatFramework** — AppurtenanceSynchroPayload, ModelUtil, AddGeckoLibCachePathEvent
- **Curios API** (external)
