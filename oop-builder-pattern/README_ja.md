# oop-builder-pattern

## 概要

静的内部 Builder クラスによるイミュータブル値オブジェクト生成パターンのサンプルです。FUL の解析対象として、ネストクラス構造・メソッドチェーン・コンストラクタバリデーションを含む設計を静的解析・文書化ツールがどう扱うかを検証できます。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 2 |
| クラス / インターフェース数 | 3（outer class 1、static nested class 1、service class 1） |
| 公開メソッド数 | 9（`userId`, `displayName`, `email`, `newsletterSubscribed`, `builder`, `displayName(String)`, `email(String)`, `newsletterSubscribed(boolean)`, `build`, `register`） |
| 総行数（空行含む） | 87 |

## 複雑度の特徴

- `UserProfile` が `static final class Builder` をネストしており、FUL がネストクラスをトップレベルクラスと区別して扱えるかを確認できる
- `Builder` コンストラクタに `if` バリデーション、各セッターメソッドに `null` / `isBlank` ガード節が分散している
- `displayName(String)` セッターは `null` チェックと `isBlank` チェックを AND 条件で組み合わせており、ネスト深度が浅い割に分岐が複数ある
- `UserProfileRegistrationService` は `Builder` を使うだけで独自ロジックを持たず、依存クラス数が少ないシンプルなコーディネーター役
- クラス間依存は `UserProfileRegistrationService` → `UserProfile` → `UserProfile.Builder` の線形チェーン

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | ネストクラス (`Builder`) のクラス構造検出と依存グラフへの組み込み方。`--engine javaparser` と `spoon` でのネストクラス扱いの差分確認 |
| DOCUMENT | `Builder` の各セッターメソッドへの引数説明・デフォルト値に関する JavaDoc 生成品質。`build()` メソッドの事後条件説明 |
| REPORT   | `final` クラス・`private` コンストラクタが品質ゲートに与える影響。イミュータブル設計の評価スコア確認 |
