# oop-generics

## 概要

型境界付きジェネリクスを使った汎用インメモリリポジトリのサンプルです。FUL の解析対象として、型パラメータ (`T extends Identifiable`) を含むクラス定義・インターフェース型境界・ジェネリクスを使ったコレクション操作を静的解析・文書化ツールがどう処理するかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 3 |
| クラス / インターフェース数 | 3（interface 1、class 2） |
| 公開メソッド数 | 5（`id`、`save`、`findById`、`size`、`name`） |
| 総行数（空行含む） | 52 |

## 複雑度の特徴

- `MemoryRepository<T extends Identifiable>` は型パラメータを持つクラスであり、JavaParser / Spoon が型境界を AST からどう抽出するかの確認ポイント
- `MemoryRepository.save` に `entity == null || entity.id() == null || entity.id().isBlank()` の複合条件ガード節がある
- `findById` は `HashMap.get` の委譲のみで分岐なし、`size` も 1 行返却のみであり、複雑度の低いメソッドのスコアリング基準確認に使える
- `Identifiable` はメソッドを 1 つだけ持つ最小インターフェースであり、型境界定義の最シンプルな形として解析対象になる
- クラス間依存は `Product` → `Identifiable` と `MemoryRepository` → `Identifiable` の 2 方向で、`Product` と `MemoryRepository` に直接の依存関係はない

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | ジェネリクスの型パラメータと型境界 (`T extends Identifiable`) の検出精度。`HashMap<String, T>` のような型引数を含むフィールド型の解析 |
| DOCUMENT | ジェネリクスクラスの型パラメータに対する JavaDoc 生成品質（`@param <T>` の説明）。型境界の役割説明の正確さ |
| REPORT   | 単純実装メソッド (`findById`, `size`) を含む複雑度スコア分布。ジェネリクス構造が品質ゲート評価に影響するかの確認 |
