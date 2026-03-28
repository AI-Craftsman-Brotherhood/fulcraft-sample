# ecommerce-order

## 概要

18ファイル・5パッケージで構成される、ECサイトの注文処理ワークフローのサンプルプロジェクトです。複数のデザインパターン（Observer、Strategy、Factory Method、Sealed Hierarchy）とモダンJavaの機能（record、sealed interface、パターンマッチング）を、レイヤー構造のパッケージ設計で組み合わせています。FULがクロスパッケージの依存関係解析、さまざまな複雑度レベル、フルパイプライン（ANALYZE → DOCUMENT → REPORT → EXPLORE）実行を現実的なコードベースで正しく処理できるかを検証するための包括的なターゲットです。

## ソース構成

| 項目 | 値 |
|------|-----|
| パッケージ数 | 5 (`model`, `stock`, `discount`, `shipping`, `service`) |
| ファイル数 | 18 |
| クラス / インターフェース / レコード / Enum | 19（ネストされたレコード `DiscountSummary` 1件を含む） |
| パブリックメソッド数 | 約50 |
| 総行数（空行含む） | 1,004 |

### パッケージ内訳

| パッケージ | ファイル数 | 行数 | 役割 |
|-----------|-----------|------|------|
| `model` | 5 | 300 | ドメインモデル層（record、enum、集約ルート） |
| `stock` | 3 | 147 | 在庫管理（Observer パターン） |
| `discount` | 5 | 279 | 割引計算（Sealed 階層 + Strategy + パターンマッチング） |
| `shipping` | 4 | 147 | 送料計算（Strategy + Factory Method） |
| `service` | 1 | 131 | オーケストレーションファサード（他の全パッケージに依存） |

## パッケージ依存グラフ

```
service ──→ model       (直接参照)
   │──→ stock       (在庫確認)
   │──→ discount    (割引計算)
   └──→ shipping    (送料計算)

discount ──→ model      (Money, Order, OrderItem)
shipping ──→ model      (Money, Order)
stock    ──→ model      (直接参照なし、String の商品コードを使用)
```

- `model` は他のパッケージに依存しない（最下層）
- `service` は全パッケージに依存する（最上層）
- `stock`、`discount`、`shipping` は `model` のみに依存する（中間層）
- パッケージ間の循環依存なし

## 複雑度の特性

- **高複雑度メソッド**: `CouponDiscount.calculate()` は8以上の分岐を持つ（日付範囲チェック、最低金額、最低アイテム数、上限ロジック）— FULの品質ゲート閾値をトリガーするよう意図的に設計
- **ガード句を持つ集約ルート**: `Order` は状態遷移のバリデーション（`confirm()`、`ship()`、`cancel()`）を `requireStatus()` ヘルパーで集約
- **ファサードオーケストレーション**: `OrderService.placeOrder()` は在庫確認 → 割引 → 送料 → 確定 → 在庫減少を順次チェーン
- **網羅的switchを伴うSealed階層**: `DiscountCalculator.toSummary()` は `DiscountPolicy` の全3 permits に対してパターンマッチングを使用
- **Observerの通知ループ**: `StockManager.setStock()` はガード句（nullチェック、空文字チェック、変更なしスキップ）付きでオブザーバーを走査
- **不変値オブジェクト**: `Money` record は全ての算術演算で新しいインスタンスを返す
- **抽象メソッドを持つEnum**: `OrderStatus.canTransitionTo()` は定数ごとの遷移ルールを定義

## 使用しているJava言語機能

| 機能 | 使用箇所 |
|------|---------|
| record (Java 16+) | `Money`, `Product`, `OrderItem`, `DiscountSummary` |
| sealed interface (Java 17+) | `DiscountPolicy` |
| パターンマッチングswitch (Java 21+) | `DiscountCalculator.toSummary()` |
| switch式 (Java 14+) | `ShippingMethodFactory.create()` |
| 抽象メソッドを持つenum | `OrderStatus` |
| インターフェースのdefaultメソッド | `StockObserver.observerName()` |
| Stream API | `Order.subtotal()`, `Order.itemCount()`, `DiscountCalculator` |
| `List.copyOf()` / `Set.copyOf()` | `Order`, `LowStockAlert`（防御的コピー） |
| コンパクトコンストラクタでのバリデーション | `Money`, `Product`, `OrderItem` |

## FUL機能カバレッジ

| FUL機能 | 本サンプルでの検証ポイント |
|---------|--------------------------|
| ANALYZE（依存グラフ） | 5パッケージにまたがるクロスパッケージ依存解決。`service` から他全パッケージへの放射状依存。`discount` ↔ `shipping` 間の偽陽性循環依存がないことの確認 |
| ANALYZE（複雑度） | `CouponDiscount.calculate()` のサイクロマティック複雑度スコアリング精度（期待値8+）。`OrderService.placeOrder()` の最大ネスト深度。パッケージ間の複雑度分布比較 |
| ANALYZE（sealed/permits） | パッケージをまたいだ `sealed interface DiscountPolicy permits ...` の型解決。パターンマッチングswitchの網羅性チェック |
| ANALYZE（デッドコード） | 本サンプル内に呼び出し元がない `StockObserver.observerName()` defaultメソッドがフラグされるか |
| DOCUMENT | コンパクトコンストラクタを持つrecordのJavaDoc生成。Sealed階層メンバーの説明品質。`OrderService` ファサードの責務とオーケストレーションフローの文書化 |
| REPORT | パッケージごとの品質メトリクス比較。18クラスにわたる複雑度分布ヒートマップ。レイヤー間の依存密度分析 |
| EXPLORE | マルチパッケージプロジェクトでのフルパイプライン実行。パッケージ境界をまたいだ注文処理ワークフローのインタラクティブな探索 |
