# oop-state-pattern

## 概要

注文ワークフローを題材に State パターンを実装した5ファイル構成のサンプルです。1インターフェース・1 Context クラス・3つの具象 State クラスが相互に参照し合う構造を持ち、FUL がパターン内の循環的依存（State ↔ Context）とパッケージプライベートメソッドをどう解析・表現するかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 5 |
| クラス / インターフェース数 | 5（`OrderState`、`OrderWorkflow`、`DraftOrderState`、`PaidOrderState`、`ShippedOrderState`） |
| 公開メソッド数 | 9（`name` ×3、`pay` ×3、`ship` ×3、`currentState`、`pay`/`ship` on Workflow） |
| 総行数（空行含む） | 80 |

## 複雑度の特徴

- Context（`OrderWorkflow`）と State 実装クラスが相互参照する双方向依存
- `transitionTo()` がパッケージプライベートスコープで定義され、アクセス制御がパターン構造の一部を成す
- 各 State クラスで許可されない操作（支払前の出荷・最終状態の操作）をノーオペレーションで実装
- 状態遷移が State クラス内部（`pay()` / `ship()`）で `new XxxOrderState()` インスタンス生成として表現される
- `transitionTo()` の null ガード節
- 3状態（DRAFT → PAID → SHIPPED）の一方向有限ステートマシン

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | Context ↔ State 間の循環参照の検出精度。パッケージプライベートメソッド `transitionTo()` がクラス依存グラフに含まれるか。Spoon / JavaParser の差異確認 |
| DOCUMENT | ノーオペレーション実装（`// Final state.` コメントのみ）に対して LLM がどのような JavaDoc を生成するか。状態遷移先クラスへの言及の有無 |
| REPORT   | 多クラス間の依存密度に対するクオリティゲート評価。パターン構造における複雑度分散の確認 |
