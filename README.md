# ECサイトWebアプリケーション
このリポジトリは、ECサイトのWebアプリケーションのコードを含んでいます。

# 概要
このアプリケーションは、企業間向けECサイトに仕入れ業務に必要な機能を提供しています。主な機能には、以下が含まれます。
- ユーザーの登録、閲覧、編集
- 商品の登録、閲覧、編集、購入
- 注文の管理

# 共通事項
- ECサイトへの情報の入力は、マウス及びキーボード、スマートフォンから行う。
- ブラウザはChromeを推奨とする。<br>
  Chromeのみで動作を確認済みだが、古いブラウザ（internet explorer）などはJavaScriptが対応していない可能性があるため、動作を保証することはできません。
- スマートフォンからも使用すると想定される為、スマートフォンでも見やすいデザインを採用しており、画面の自動調整が可能です

# 制約事項
- ユーザー、商品の検索処理は対象外とする。
- ユーザー、商品の削除処理は対象外とする。
- 商品の購入後の決済処理、購入取り消し、税額計算は対象外とする。

# 技術スタック
- フロントエンド：HTML5、CSS、JavaScript、jQuery、BootStrap、Thymeleaf
- バックエンド：Java18、SpringBoot、SpringSecurity、Mybatis、MySQL、lombok、Validation、JUnit、DbUnit ...etc<br>
※ その他技術スタックは、build.gradle 参照

# 使用方法
このアプリケーションを実行するためには、以下の手順に従ってください。
1. Chromeブラウザで下記URLにアクセスして、アプリケーションが正常に動作していることを確認します。<br>
- [http://aws-infra-meron.com:8080/login](http://aws-infra-meron.com:8080/login) 
※ログインパスワードは別途連携

# 認証権限について
ログイン機能を実装し、認証権限に合わせて認可された操作を行う。
- 管理者権限（ADMIN）<br>
  管理者は、ECサイトにログインするユーザーを登録、閲覧、編集できる。<br>
- メーカー権限（MAKER）<br>
  メーカーは、出店企業（店舗）に向けて商品を販売できる。
- 店舗権限（SHOP）<br>
  店舗は、商品をメーカーから買う（仕入れる）ことができる。
- 共通認可<br>
  管理者、メーカー、店舗は、メニュー画面を閲覧できる。<br>
  管理者、メーカー、店舗は、メーカーが登録（出品）した商品を閲覧できる。
## 権限ごとの仕入れ業務の表示画面一覧

| 画面/権限    | ADMIN | MAKER | SHOP | 画面URL 例                                       | 備考                     |
|:---------|:------|:------|:-----|:----------------------------------------------|:-----------------------|
| ログイン画面   | 〇     | 〇     | 〇    |http://aws-infra-meron.com:8080/login                   | ログイン画面は権限認証しなくても表示できる  |
| メニュー画面   | 〇     | 〇     | 〇    |http://aws-infra-meron.com:8080                         | 権限ごとの画面リンクが表示される       |
| ユーザ登録画面  | 〇     | -     | -    |http://aws-infra-meron.com:8080/users/creationForm      | ADMINのみユーザーの登録ができる     |
| ユーザー一覧画面 | 〇     | -     | -    |http://aws-infra-meron.com:8080/users?limit=10&offset=0 | ADMINのみユーザー一覧画面の閲覧ができる |
| ユーザー編集画面 | 〇     | -     | -    |http://aws-infra-meron.com:8080/users/update/1          | ADMINのみユーザーの編集ができる     |
| 商品登録画面   | -     | 〇     | -    |http://aws-infra-meron.com:8080/items/creationForm      | MAKERのみ商品の登録ができる       |
| 商品一覧画面   | 〇     | 〇     | 〇    |http://aws-infra-meron.com:8080/items?limit=10&offset=0 | 商品の一覧画面が閲覧できる          |
| 商品詳細画面   | 〇     | -     | -    |http://aws-infra-meron.com:8080/items/discription/1     | ADMINのみ商品の詳細が閲覧できる     |
| 商品編集画面   | -     | 〇     | -    |http://aws-infra-meron.com:8080/items/update/1          | MAKERのみ商品の編集ができる       | 
| 商品購入画面   | -     | -     | 〇    |http://aws-infra-meron.com:8080/items/buy/1             | SHOPのみ商品の購入ができる        |
| 購入確認画面   | -     | -     | 〇    |http://aws-infra-meron.com:8080/items/confirm/1         | SHOPのみ商品の購入確認画面が閲覧できる  |
| 購入完了画面   | -     | -     | 〇    |http://aws-infra-meron.com:8080/items/complete/1        | SHOPのみ商品の購入完了画面が閲覧できる  |

# 納品物
ソースコード以外の納品物は下記とする
- [要件定義](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/要件定義.docx)
- [ユースケース図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/UseCaseDiagram/ユースケース図.pdf)
- シーケンス図は、NotionAIを使用してMarmed.jsで自動生成されました。<br>
  [シーケンス図(3層アーキテクチャ)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/3層アーキテクチャのシーケンス図.jpg)<br>
  [シーケンス図(ADMIN権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/ADMIN権限でログインした場合のシーケンス図.jpg)<br>
  [シーケンス図(MAKER権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/MAKER権限でログインした場合のシーケンス図.jpg)<br>
  [シーケンス図(SHOP権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/SHOP権限でログインした場合のシーケンス図.jpg)<br>
- [画面遷移図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/BasicDesign/画面遷移図.png)
- 画面設計は、Figmaを使用した。<br>
  [画面設計書(ADMIN権限)](https://www.figma.com/file/fADbDWFmmOfNlBWR4xG4qW/ecSite%EF%BC%88ADMIN%E6%A8%A9%E9%99%90%EF%BC%89?t=CIMYWvw98TBdHq4u-0)<br>
  [画面設計書(MAKER権限)](https://www.figma.com/file/Qa5LKJ8cT4rCVd7HChNL7I/ecSite%EF%BC%88MAKER%E6%A8%A9%E9%99%90%EF%BC%89?node-id=0%3A1&t=AQkwm8VQszcS8Gc0-1)<br>
  [画面設計書(SHOP権限)](https://www.figma.com/file/ezQ4wmZp4bOBQFp5qMHTLN/ecSite%EF%BC%88SHOP%E6%A8%A9%E9%99%90%EF%BC%89?t=AQkwm8VQszcS8Gc0-1)<br>
- [API定義書](http://localhost:61112/swaggerui.html?project=b920a53b&filename=C:/Users/farno/ecsite/src/main/resources/api-schema.yaml&renderer=0)
- [DB設計書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/DatabaseDesign/DB設計.xlsx)
- [単体試験実施状況](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/3.Implementation/UnitTest/JUNITテストコードの範囲.xlsx)
- [試験項目書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/4.Verification/SystemTest/総合テスト仕様書.xlsx)