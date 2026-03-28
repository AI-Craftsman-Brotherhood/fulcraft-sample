# oop-abstraction

## 概要

Template Method パターンを抽象クラスで実現した価格計算サンプルです。FUL の解析対象として、抽象クラスと具象サブクラスの継承階層、`final` メソッドによるロック、`protected` フックメソッドによる拡張点をどう検出・文書化するかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 2 |
| クラス / インターフェース数 | 2（abstract class 1、concrete class 1） |
| 公開メソッド数 | 3（`calculateFinalPrice`, `discountAmount`, `minimumPrice`） |
| 総行数（空行含む） | 43 |

## 複雑度の特徴

- `calculateFinalPrice` に `if` 分岐と `Math.max` による下限チェックが含まれ、1 メソッド内に複数の条件分岐が集中する
- `abstract` メソッド `discountAmount` はサブクラスで実装されるため、静的解析時に動的ディスパッチとして扱われる
- `HolidayPriceCalculator` のコンストラクタに範囲バリデーション (`if` 分岐 + `throw`) が存在する
- 継承が 1 階層のみで、循環依存はなく、クラス間の依存はシンプルな親子関係のみ
- `protected` / `final` のアクセス修飾子が複数混在し、Spoon ベースの解析で修飾子の組み合わせを正確に抽出できるか確認できる

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | 抽象クラス・`final` メソッド・`abstract` メソッドの検出精度。`--engine javaparser` と `spoon` の結果比較 |
| DOCUMENT | `abstract` メソッドに対して実装の意図を補うコメントを LLM が生成できるか。`protected` フックの役割説明の質 |
| REPORT   | シンプルな継承構造でのサイクロマティック複雑度ベースラインの確認。`--max-cyclomatic` 閾値の動作確認 |
