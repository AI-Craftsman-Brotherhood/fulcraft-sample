# oop-observer-pattern

## 概要

在庫管理を題材に Observer パターンを実装した4ファイル構成のサンプルです。インターフェース・Subject クラス・2種類の Observer 実装という典型的なパターン構造を持ち、FUL がクラス間の依存グラフとインターフェース実装関係を正確にトレースできるかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 4 |
| クラス / インターフェース数 | 4（`InventoryObserver`、`InventorySubject`、`LowStockObserver`、`StockChangeCounter`） |
| 公開メソッド数 | 8（`onStockChanged` ×3、`addObserver`、`removeObserver`、`currentStock`、`setStock`、`lowStockItems`、`updateCount`） |
| 総行数（空行含む） | 91 |

## 複雑度の特徴

- 1インターフェース + 3クラスの依存グラフ（Subject → Observer の一方向通知）
- `InventorySubject.setStock()` に複数のガード節（null チェック・空白チェック・変化なしスキップ）が集中し、分岐が多い
- `for` ループで複数オブザーバーへの通知を反復
- `LowStockObserver` は条件分岐で `Set` への追加・削除を制御
- `Set.copyOf()` による防御的コピーで内部状態を保護
- `String.isBlank()` 使用（Java 11+）

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `InventoryObserver` を実装する2クラスの依存解決。`setStock()` の分岐数（cyclomatic complexity）のスコアリング精度 |
| DOCUMENT | インターフェースメソッド `onStockChanged` と各実装クラスの JavaDoc 生成。パラメータ名（`itemCode`, `previousStock`, `currentStock`）の説明品質 |
| REPORT   | Observer パターンの複数実装クラスに対するクラス別品質レポートの出力形式確認 |
