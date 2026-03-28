# oop-record

## 概要

請求書（`Invoice`）と明細行（`InvoiceLine`）を題材に、Java 16 の `record` を使った値オブジェクト設計を示すサンプルです。FUL が `record` 宣言を通常クラスと区別して解析・ドキュメント生成できるか、またコンパクトコンストラクタを含む特殊な構文をどう扱うかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 2 |
| クラス / インターフェース数 | 2（`Invoice` クラス、`InvoiceLine` レコード） |
| 公開メソッド数 | 5（`addLine`、`lineCount`、`totalAmount`、`subtotal`、コンパクトコンストラクタ） |
| 総行数（空行含む） | 45 |

## 複雑度の特徴

- `record` 宣言（`InvoiceLine`）と通常クラス（`Invoice`）が混在する構成
- `InvoiceLine` のコンパクトコンストラクタに複数の `if` による入力バリデーション（`IllegalArgumentException` スロー）
- `Invoice.totalAmount()` で `for-each` ループによるリスト集計
- `Invoice.addLine()` に null ガード節
- `record` が自動生成するアクセサ（`itemName()`、`unitPrice()`、`quantity()`）と明示的に定義したメソッド（`subtotal()`）の混在
- Java 16+ 固有の言語機能（`record` キーワード）

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `record` 宣言を JavaParser / Spoon が型として認識し、自動生成アクセサをメソッドとして列挙できるか。コンパクトコンストラクタの分岐数スコアリング |
| DOCUMENT | `record` コンポーネント（`itemName`、`unitPrice`、`quantity`）への自動 JavaDoc 生成品質。コンパクトコンストラクタ内バリデーションルールの説明 |
| REPORT   | `record` を含むコードベースのクオリティゲート評価。バリデーション付きコンストラクタの複雑度がレポートに正しく反映されるか |
