# oop-sealed-hierarchy

## 概要

割引ポリシーを題材に、Java 17 の `sealed interface` と `permits` 句による閉じた型階層を実装した4ファイル構成のサンプルです。FUL が `sealed` / `final` といった Java 17+ 固有の修飾子を解析し、`instanceof` パターンマッチングを含むサービスクラスを正確にドキュメント化できるかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 4 |
| クラス / インターフェース数 | 4（`DiscountPolicy` sealed interface、`FlatDiscountPolicy`、`RateDiscountPolicy`、`DiscountService`） |
| 公開メソッド数 | 6（`applyTo` ×2、コンストラクタ ×2、`calculate`、`describe`） |
| 総行数（空行含む） | 62 |

## 複雑度の特徴

- `sealed interface` + `permits` 句で実装クラスを2つに限定した閉じた型階層
- `FlatDiscountPolicy` と `RateDiscountPolicy` はいずれも `final class` で継承チェーンを封鎖
- `RateDiscountPolicy` コンストラクタにパーセント範囲チェック（`IllegalArgumentException`）
- `DiscountService.describe()` で `instanceof` パターンマッチング（Java 16+）による型ごとの分岐
- `DiscountService.calculate()` に null / 非正値ガード節
- Java 17+ 固有の言語機能（`sealed`、`permits`）

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `sealed interface` と `permits` 句の解析精度。JavaParser / Spoon が封じられた型階層をどう表現するかの差異確認。`instanceof` パターンマッチングの依存解決 |
| DOCUMENT | `sealed interface` の型制約（`permits FlatDiscountPolicy, RateDiscountPolicy`）が JavaDoc に反映されるか。各 `final class` の実装責務の説明品質 |
| REPORT   | Java 17 機能を含むコードベースのクオリティゲート評価。`describe()` の分岐数スコアリング確認 |
