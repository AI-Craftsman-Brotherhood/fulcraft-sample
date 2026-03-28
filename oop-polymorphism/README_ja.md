# oop-polymorphism

## 概要

通知送信を題材にインターフェースによるポリモーフィズムを実装した4ファイル構成のサンプルです。1インターフェース・2実装クラス・1サービスクラスという最小構成で、FUL が動的ディスパッチの抽象化境界とクラス間依存を解析できるかを検証するターゲットとして機能します。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 4 |
| クラス / インターフェース数 | 4（`NotificationChannel`、`EmailChannel`、`SmsChannel`、`NotificationService`） |
| 公開メソッド数 | 5（`deliver` ×2、`switchChannel`、`sendWelcome`、コンストラクタ1） |
| 総行数（空行含む） | 49 |

## 複雑度の特徴

- 最小限の継承階層（インターフェース1つ・実装2つ）で依存解析のベースライン
- `NotificationService` がコンストラクタインジェクションと `switchChannel()` の両方で依存を保持・切り替え
- `sendWelcome()` に null / blank チェックのガード節
- 実装クラス（`EmailChannel`、`SmsChannel`）は分岐なしのフラットなメソッド構造
- クラス全体のサイクロマティック複雑度は非常に低い（ベースラインサンプルとして有用）

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `NotificationChannel` を実装する2クラスの型解決。`--engine` オプション（composite / javaparser / spoon）間での依存グラフの一致確認 |
| DOCUMENT | インターフェースの抽象メソッド `deliver` と各実装の JavaDoc 生成。戻り値フォーマット（`"EMAIL:..."` / `"SMS:..."`）の説明品質 |
| REPORT   | 低複雑度コードベースに対するクオリティゲート結果の確認。`--max-cyclomatic` のしきい値に引っかからないことの検証 |
