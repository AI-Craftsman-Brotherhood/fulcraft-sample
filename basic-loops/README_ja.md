# basic-loops

## 概要

`for-each`・`while`・`do-while` の 3 種類のループ構造を 1 クラスに集約した `LoopService` を解析対象とするサンプルである。ループ構文ごとの複雑度計上の差異と、`break` を含む早期終了フローを FUL が静的解析でどのように扱うかを検証できる。

## ソース構成

| 項目 | 値 |
|------|-----|
| ファイル数 | 1 |
| クラス / インターフェース数 | 1 |
| 公開メソッド数 | 3 |
| 総行数（空行含む） | 45 |

## 複雑度の特徴

- `sum`: `for-each` ループ + null ガードのシンプル構成
- `countPositiveUntilZero`: `while` ループ内に `break`（終端検出）と `if`（正値判定）の 2 分岐が存在し、3 メソッド中で複雑度が最も高い
- `countdownSteps`: `do-while` ループで必ず 1 回実行される構造、`start <= 0` 入力でのエッジケース動作が解析対象となる
- 3 メソッドが異なるループ構文を使い分けており、`--engine` 間での複雑度計上の一貫性を比較する対象として適している

## FUL 機能との対応

| FUL 機能 | このサンプルでの検証ポイント |
|----------|--------------------------|
| ANALYZE  | `for-each` / `while` / `do-while` それぞれのループに対するサイクロマティック複雑度計上の差異検証、`break` の制御フロー追跡 |
| DOCUMENT | `break` による早期終了条件と `do-while` の事後条件を LLM が JavaDoc に正確に記述できるかの確認 |
| REPORT   | `countPositiveUntilZero` が `--complexity-strategy warn` / `skip` / `split` の各戦略でどのカテゴリに分類されるかの動作確認 |
