# oop-strategy-pattern

## 概要

配送料計算を題材に Strategy パターンを実装した4ファイル構成のサンプルです。コンストラクタインジェクションと実行時の戦略切り替え（`setShippingStrategy()`）を併せ持つ Context クラスを含み、FUL が戦略インターフェースとその実装クラスの依存関係・分岐ロジックを解析・ドキュメント化できるかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 4 |
| クラス / インターフェース数 | 4（`ShippingStrategy`、`StandardShippingStrategy`、`ExpressShippingStrategy`、`CheckoutService`） |
| 公開メソッド数 | 6（`shippingFee` ×2、コンストラクタ1、`setShippingStrategy`、`totalWithShipping`） |
| 総行数（空行含む） | 56 |

## 複雑度の特徴

- 1インターフェース・2実装クラス・1 Context クラスの標準的な Strategy 構造
- `StandardShippingStrategy.shippingFee()` に条件分岐2つ（`itemCount <= 0` ガード＋三項演算子による送料無料判定）
- `ExpressShippingStrategy.shippingFee()` に条件分岐1つ（`itemCount <= 0` ガード）＋算術式による動的料金計算
- `CheckoutService` がコンストラクタインジェクションと `setShippingStrategy()` セッターの両方で戦略を保持
- `totalWithShipping()` に `Math.max(0, ...)` による非負ガード
- 実装クラス間でアルゴリズム（固定料金+個数課金 vs 閾値判定）が明確に異なり、LLM 説明の差異が出やすい

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `ShippingStrategy` を実装する2クラスの依存解決。`setShippingStrategy()` セッターによるランタイム差し替えが静的解析でどう表現されるか |
| DOCUMENT | 算術式ベースの料金計算（`1200 + (itemCount * 80)`）と閾値ベースの料金計算（`subtotal >= 5000 ? 0 : 500`）に対して LLM がビジネスルールを正確に説明できるか |
| REPORT   | 両 Strategy クラスの cyclomatic complexity スコアの比較。`--max-cyclomatic` しきい値および `--complexity-strategy` オプションの動作確認 |
