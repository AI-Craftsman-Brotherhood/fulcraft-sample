# oop-enum-behavior

## 概要

`enum` 定数ごとに抽象メソッドの実装を持たせることで振る舞いを分岐させるパターンのサンプルです。FUL の解析対象として、`enum` 内の匿名クラスボディ・`abstract` メソッド・`switch` 式を含む構造を静的解析・文書化ツールがどう扱うかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 2 |
| クラス / インターフェース数 | 2（enum 1、class 1） |
| 公開メソッド数 | 4（`discountPercent` × 3 定数分の実装 + `discountedPrice` + `badgeLabel`） |
| 総行数（空行含む） | 47 |

## 複雑度の特徴

- `MembershipLevel` の各 enum 定数（`REGULAR`, `SILVER`, `GOLD`）が匿名クラスボディで `discountPercent()` を実装しており、1 ファイルに 3 つの匿名クラスが存在する
- `enum` 内に `abstract` メソッド宣言があり、JavaParser / Spoon がこれをどう AST に変換するかの確認ポイント
- `LoyaltyService.badgeLabel` が `switch` 式（Java 14+ の arrow syntax）を使っており、網羅性チェックが必要な分岐構造
- `LoyaltyService.discountedPrice` に `price <= 0 || level == null` という複合条件のガード節がある
- `MembershipLevel` → `LoyaltyService` の依存はなく、`LoyaltyService` が `MembershipLevel` を参照する一方向依存

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `enum` 内の匿名クラスボディとその `abstract` メソッド実装の検出。`switch` 式の分岐数が複雑度スコアに反映されるかの確認 |
| DOCUMENT | `enum` 定数ごとの振る舞いに対する JavaDoc 生成品質。匿名クラスボディ内のメソッドへの説明生成の動作確認 |
| REPORT   | `switch` 式の網羅性と複雑度スコアリングの関係確認。`--max-cyclomatic` 閾値の適切な設定値の検討材料として活用 |
