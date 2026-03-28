# oop-factory-method

## 概要

Factory Method パターンでフォーマット種別に応じた `ReportFormatter` 実装を切り替えるサンプルです。FUL の解析対象として、インターフェース・複数の実装クラス・静的ファクトリクラス・利用サービスが連携する 5 クラス構成の依存グラフと、インターフェース抽象化が文書化にどう影響するかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 5 |
| クラス / インターフェース数 | 5（interface 1、class 4） |
| 公開メソッド数 | 5（`format` × 3、`create`、`createMonthlyReport`） |
| 総行数（空行含む） | 54 |

## 複雑度の特徴

- `ReportFormatterFactory.create` に `if ("json".equalsIgnoreCase(format))` 分岐があり、フォーマット種別判定の単純な条件分岐が存在する
- `ReportFormatterFactory` はプライベートコンストラクタ + `static` メソッドのみのユーティリティクラスパターンであり、インスタンス化禁止設計の解析確認に使える
- `JsonReportFormatter` と `TextReportFormatter` はどちらも `format` を 1 行で実装する最低複雑度のクラスであり、実装クラスのサイクロマティック複雑度の下限確認に適している
- `ReportService` は `ReportFormatterFactory` と `ReportFormatter` インターフェースのみに依存し、具象クラスへの直接依存がない
- 5 クラスにわたる依存グラフ（`ReportService` → `ReportFormatterFactory` → `JsonReportFormatter` / `TextReportFormatter`、`ReportService` → `ReportFormatter` ← 実装クラス）が FUL の依存解析で正確に再現されるかを確認できる

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | インターフェースを頂点とする実装クラス群の依存グラフ生成。`equalsIgnoreCase` を使った条件分岐の解析。`--engine composite` での複数エンジン結果統合確認 |
| DOCUMENT | `ReportFormatter` インターフェースと各実装クラスへの JavaDoc の一貫性。ファクトリメソッドの選択ロジックに対する説明生成品質 |
| REPORT   | 複数クラスにまたがる品質ゲート評価の結果確認。実装クラスごとの複雑度スコア分布の可視化 |
