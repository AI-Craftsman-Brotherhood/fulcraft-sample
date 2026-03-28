# oop-default-method

## 概要

インターフェースの `default` メソッドで共通ロジックを提供し、実装クラスの実装量を最小化する設計パターンのサンプルです。FUL の解析対象として、`default` メソッドを含むインターフェース・その実装クラス・利用サービスの 3 層構造を静的解析・文書化ツールがどう処理するかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 3 |
| クラス / インターフェース数 | 3（interface 1、class 2） |
| 公開メソッド数 | 5（`publish`, `publishUserEvent`, `events`, `login`、`InMemoryEventPublisher.publish`） |
| 総行数（空行含む） | 48 |

## 複雑度の特徴

- `EventPublisher.publishUserEvent` は `default` メソッドに `if` 分岐と早期 `return` を含み、インターフェース内での制御フローの扱いを確認できる
- `UserActionService` はコンストラクタインジェクションで `EventPublisher` インターフェースに依存し、`InMemoryEventPublisher` との具体的な結合がない設計
- `InMemoryEventPublisher` は `publish` のみを実装し、`publishUserEvent` は `default` から継承するため、解析エンジンが継承メソッドをどう扱うかが確認ポイントとなる
- `List.copyOf()` による防御的コピーがあり、可変内部状態の漏洩防止パターンが含まれる
- クラス間依存の方向は `UserActionService` → `EventPublisher` ← `InMemoryEventPublisher` の形で、インターフェースを中心にした菱形構造

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `default` メソッドのインターフェース内検出と、実装クラスへの継承メソッドの扱い。`--engine spoon` でのインターフェース解析精度 |
| DOCUMENT | `default` メソッドへの JavaDoc 生成品質。実装クラス (`InMemoryEventPublisher`) に継承されたメソッドの説明生成の動作確認 |
| REPORT   | インターフェース内の `default` メソッドが複雑度計算の対象に含まれるかの確認。`--complexity-strategy` の適用範囲確認 |
