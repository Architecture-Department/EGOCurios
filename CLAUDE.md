# E.G.O.Curios

Mod ID: `ego_curios`

饰品模组。基于 Curios API 的 15 槽位饰品系统，含 ~50 件 E.G.O. 饰品。

## 包结构

- `core/` — `EGOCurios.kt`(@Mod), `EGOCuriosClient.kt`, 槽位常量, 注册表
- `api/` — `AttackLogicHolder` 实体攻击逻辑 Attachment
- `common/item/` — `EgoCurioItem` 饰品基类, `ComprehensionBackCurioItem` 等
- `common/payload/` — 饰品外观同步包
- `client/renderer/` — `GeoCurioRenderer`, 饰品渲染器
- `init/` — 物品/Attachment/创造模式标签/槽位标签注册
- `events/` — 事件监听器（LivingEntityEvents, ModEvents）
- `datagen/` — 数据生成（物品模型、标签、i18n、Curios槽位定义）
- `util/` — Curios API 扩展函数

## 15 槽位

通用、头饰、头部、后脑、眼部、面部、脸颊、面具、口部、颈部、胸针、手部、手套、左背、右背。左右背槽互斥。

## 依赖

- **GoldenBoughsLib** — IEgoItem, VirtueAttributeModifier, LcDamageType
- **ResonatorCombatFramework** — 网络同步包, ModelUtil
- **Curios API** (外部)

由 ImaginaryCraft 模块 jarJar 聚合。
