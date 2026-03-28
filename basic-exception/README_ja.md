# basic-exception

## 概要

`try-catch-finally` ブロックと複数の例外型を組み合わせた `SafeCalculator` を解析対象とするサンプルである。例外フローが複数経路に分岐する実装に対して、FUL の複雑度計測と LLM によるドキュメント生成が例外仕様を正確に捉えられるかを検証できる。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 1 |
| クラス / インターフェース数 | 1 |
| 公開メソッド数 | 2 |
| 総行数（空行含む） | 26 |

## 複雑度の特徴

- `parseAndDivide` は `try` ブロック内でのメソッド呼び出し、`NumberFormatException` / `IllegalArgumentException` の 2 catch 節、`finally` ブロックを持つ
- 例外送出（`divide`）と例外捕捉（`parseAndDivide`）がクラス内で分離しており、メソッド間の制御フロー連鎖が発生する
- `finally` は no-op ながらサイクロマティック複雑度の計上対象になりうる境界ケース
- 継承なし、外部依存なし

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `try-catch-finally` 各節を例外フローとして複雑度にカウントする実装差異の検証、`--engine` 間の計上値比較 |
| DOCUMENT | 複数の例外型と戻り値の意味（0 / -1 の使い分け）を LLM が `@throws` および `@return` に正確に反映できるかの確認 |
| REPORT   | `--complexity-strategy split` 適用時に `parseAndDivide` が分割対象として検出されるかの閾値動作検証 |
