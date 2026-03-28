# fulcraft-java-sample

[FUL](https://github.com/AI-Craftsman-Brotherhood/fulcraft) の E2E フィクスチャとして使用する、ケースごとの Java サンプルプロジェクト集です。

## 目的

FUL のテスト生成動作を検証・実演するための、小規模で焦点を絞った Java サンプルを提供します。

## ケース一覧

### basic

| ケース | 説明 |
|--------|------|
| [simple-java](simple-java/) | 四則演算とゼロ除算例外 |
| [basic-collections](basic-collections/) | 在庫管理における HashMap / List 操作 |
| [basic-encapsulation](basic-encapsulation/) | private final フィールドと入力バリデーション |
| [basic-exception](basic-exception/) | try / catch / finally と例外型の使い分け |
| [basic-if-switch](basic-if-switch/) | if-else 連鎖と switch 式（Java 14+） |
| [basic-inheritance](basic-inheritance/) | インターフェース実装と割引率バリデーション |
| [basic-loops](basic-loops/) | for-each、while + break、do-while パターン |

### oop

| ケース | 説明 |
|--------|------|
| [oop-abstraction](oop-abstraction/) | abstract / final フックを使った Template Method パターン |
| [oop-annotation](oop-annotation/) | カスタムアノテーション定義とランタイムリフレクション |
| [oop-builder-pattern](oop-builder-pattern/) | 必須 / 任意パラメータを分離するネスト Builder |
| [oop-composition](oop-composition/) | 集約ルートによる has-a 関係 |
| [oop-default-method](oop-default-method/) | 共通ロジックを提供するインターフェース default メソッド |
| [oop-enum-behavior](oop-enum-behavior/) | 定数ごとの抽象メソッドを持つ enum |
| [oop-factory-method](oop-factory-method/) | フォーマッタ選択のための Factory Method パターン |
| [oop-generics](oop-generics/) | 型境界付きパラメータ `<T extends Identifiable>` |
| [oop-nested-class](oop-nested-class/) | 内部データモデルとしての static ネストクラス |
| [oop-observer-pattern](oop-observer-pattern/) | 在庫変動通知のための Observer パターン |
| [oop-polymorphism](oop-polymorphism/) | インターフェースによる実行時チャネル切り替え |
| [oop-record](oop-record/) | コンパクトコンストラクタでバリデーションを行う Java 16+ record |
| [oop-sealed-hierarchy](oop-sealed-hierarchy/) | パターンマッチングを使った sealed interface（Java 17+） |
| [oop-state-pattern](oop-state-pattern/) | 注文ワークフロー遷移のための State パターン |
| [oop-strategy-pattern](oop-strategy-pattern/) | 配送料計算のための Strategy パターン |

### comprehensive

| ケース | 説明 |
|--------|------|
| [ecommerce-order](ecommerce-order/) | 18ファイル・5パッケージ構成の EC 注文ワークフロー。Observer、Strategy、Factory Method、Sealed Hierarchy、record、パターンマッチングを横断的に使用 |

## 使い方

各ディレクトリは独立したサンプルプロジェクトです。
`javac` で直接コンパイルするか、ケースディレクトリを IDE にインポートして使用できます。

## FUL との関係

本リポジトリは [FUL](https://github.com/AI-Craftsman-Brotherhood/fulcraft) プロジェクトのコンパニオンサンプルセットとして管理されています。
ライセンスおよび報告ポリシーは FUL 本体リポジトリに準拠します。

## ライセンス

本プロジェクトは **FUL License (Proprietary / Source Available)** の下で公開されています。
詳細は [LICENSE](LICENSE) を参照してください。

### ライセンス概要
- 個人 / 学生 / 非商用利用は許可されています。
- 明示的な書面による許可がない限り、商用利用は禁止されています。
- 改変および再配布は禁止されています。

商用ライセンスまたは明示的な許可について：
- Email: support@craftsman-bro.com
- Web: https://craftsman-bro.com/en/contact/

## コントリビューション

外部からのプルリクエストは受け付けていません。
バグ報告は以下の Issue トラッカーからお願いします：
- https://github.com/AI-Craftsman-Brotherhood/fulcraft-sample/issues/new

詳細は [CONTRIBUTING.md](CONTRIBUTING.md) を参照してください。

## セキュリティ

脆弱性の報告は [SECURITY.md](SECURITY.md) に記載のチャネルを通じてお願いします。
