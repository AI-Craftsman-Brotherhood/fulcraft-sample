# oop-annotation

## 概要

カスタムアノテーション定義・付与・リフレクション読み取りを組み合わせた監査メタデータ処理サンプルです。FUL の解析対象として、`@interface` 宣言・`@Retention`/`@Target` メタアノテーション・リフレクション API を使ったメタデータ収集ロジックをどの程度静的解析できるかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 3 |
| クラス / インターフェース数 | 3（annotation 1、class 2） |
| 公開メソッド数 | 4（`auditedActions`, `createOrder`, `cancelOrder`, `health`） |
| 総行数（空行含む） | 54 |

## 複雑度の特徴

- `AuditMetadataReader.auditedActions` は `for` ループと `if` 分岐を含み、リフレクション API を呼び出す処理が 1 メソッドに集中する
- `@Retention(RetentionPolicy.RUNTIME)` と `@Target(ElementType.METHOD)` を持つカスタムアノテーション定義が存在し、アノテーション宣言の静的解析対象となる
- `AuditedOrderService` の各メソッドに `null` / `isBlank` ガード節が重複して現れる
- クラス間依存は `AuditMetadataReader` → `AuditedAction`（アノテーション型参照）と `AuditedOrderService` → `AuditedAction`（付与）の 2 方向で分離されている
- リフレクション API (`getDeclaredMethods`, `getAnnotation`) は静的解析では動的呼び出しとして扱われるため、エンジンごとの解析差分が現れやすい

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `@interface` 宣言の検出とアノテーション付きメソッドの一覧化。`--engine spoon` がアノテーションメタ情報を正確に抽出できるかの確認 |
| DOCUMENT | アノテーション定義ファイル (`AuditedAction`) に対する JavaDoc 生成の品質。リフレクションを使ったメタデータ読み取りロジックへの説明生成 |
| REPORT   | `for` ループ＋`if` 分岐の複雑度スコアリング。`--complexity-strategy` オプションの動作確認用ベースケース |
