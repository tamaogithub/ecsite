
# ECサイトアプリケーション概要
このアプリケーションは、企業間の商品の仕入れ業務に必要な機能を提供しています。<br>
このアプリケーションを実行するためには下記のURLにアクセスして下さい。<br>
[http://aws-infra-meron.com:8080/login](http://aws-infra-meron.com:8080/login) <br>
ログインパスワードは書類選考フォームにて連携

# 機能一覧
主な機能には、以下が含まれます。
- ユーザーのログイン
- メニューの表示
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
## フロントエンド
+ HTML5
  + Thymeleaf
+ CSS
  + BootStrap 5.2.3
+ JavaScript
  + jQuery 3.6.4

## バッグエンド
+ Java 17
+ Spring boot 2.7.1
  + Spring Web
  + Spring Security
  + Spring Validation
  + Spring Data JPA
+ mybatis 2.2.0
+ OpenAPI Generator 6.2.1 
+ Junit 4.13.2
+ dbunit 2.7.3
+ Mockito 4.2.0
+ jacoco 0.8.8
+ Lombok 1.18.26

## ビルドツール
+ Gradle

## インフラ
+ AWS
  + Amazon CloudWatch
  + IAM
  + Elastic IP
  + Route 53
  + EC2
    + Tomcat 9.0.60
    + Apache
  + RDS
    + MySQL

# 認証権限について
ログイン機能にて、認証権限に合わせて認可された操作を行う。
+ 管理者権限（ADMIN）
  + 管理者は、ECサイトにログイン可能なADMIN、MAKER、SHOPの何れかの権限を付与するユーザーを登録、閲覧、編集できる。
+ メーカー権限（MAKER）
  + メーカーは、出店企業（店舗）に向けて商品を販売できる。
+ 店舗権限（SHOP）
  + 店舗は、商品をメーカーから買う（仕入れる）ことができる。
+ 共通認可
  + 管理者、メーカー、店舗は、メニュー画面を閲覧できる。
  + 管理者、メーカー、店舗は、メーカーが登録（出品）した商品を閲覧できる。
## ユースケース図
![usecase](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/UseCaseDiagram/%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E5%9B%B3.png)

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

# インフラ構成図
![sample](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/BasicDesign/%E3%82%A4%E3%83%B3%E3%83%95%E3%83%A9%E6%A7%8B%E6%88%90%E5%9B%B3.png)

# ECサイト作成を経て学んだこと
+ アプリケーションの設計能力(画面設計、DB設計)
+ バリデーションを伴うCRUD機能の実装
+ 画像ファイルのアップロード機能の実装
+ JUnit,Mockitoを用いた単体テストの実施
+ AWSを使ったwebサーバ、DBサーバの構築

# 今後の課題
+ 機能の追加
  + 商品検索機能
  + 商品削除機能
  + 商品購入完了時の決算処理
  + 商品購入履歴画面の追加
+ コードのリファクタリング
  + 複数のクラスに記述している同じ処理を一つにまとめる
+ Dockerを用いた環境構築
+ CircleCIを用いたCI/CDパイプラインの構築

# 納品物
ソースコード以外の納品物は下記とする
- [要件定義](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/要件定義.docx)
- [ユースケース図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/UseCaseDiagram/%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E5%9B%B3.png)
- [インフラ構成図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/BasicDesign/%E3%82%A4%E3%83%B3%E3%83%95%E3%83%A9%E6%A7%8B%E6%88%90%E5%9B%B3.png)
- シーケンス図は、NotionAIを使用してMarmed.jsで自動生成した。<br>
  [シーケンス図(3層アーキテクチャ)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/3層アーキテクチャのシーケンス図.jpg)<br>
  [シーケンス図(ADMIN権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/ADMIN権限でログインした場合のシーケンス図.jpg)<br>
  [シーケンス図(MAKER権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/MAKER権限でログインした場合のシーケンス図.jpg)<br>
  [シーケンス図(SHOP権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/SHOP権限でログインした場合のシーケンス図.jpg)<br>
- 画面遷移図、画面設計は、Figmaを使用した。<br>
  [画面遷移図](https://www.figma.com/file/7824LcF2ygKRyJr0oqZvnS/%E7%94%BB%E9%9D%A2%E9%81%B7%E7%A7%BB%E5%9B%B3?type=design&node-id=0-1&t=ESMnaImNhzOx44Rk-0)<br>
  [画面設計書(ADMIN権限)](https://www.figma.com/file/fADbDWFmmOfNlBWR4xG4qW/ecSite%EF%BC%88ADMIN%E6%A8%A9%E9%99%90%EF%BC%89?t=CIMYWvw98TBdHq4u-0)<br>
  [画面設計書(MAKER権限)](https://www.figma.com/file/Qa5LKJ8cT4rCVd7HChNL7I/ecSite%EF%BC%88MAKER%E6%A8%A9%E9%99%90%EF%BC%89?node-id=0%3A1&t=AQkwm8VQszcS8Gc0-1)<br>
  [画面設計書(SHOP権限)](https://www.figma.com/file/ezQ4wmZp4bOBQFp5qMHTLN/ecSite%EF%BC%88SHOP%E6%A8%A9%E9%99%90%EF%BC%89?t=AQkwm8VQszcS8Gc0-1)<br>
- API定義書は、OpenAPI Generator を使ってビルド時に自動生成した。<br>
  [API定義書](http://aws-infra-meron.com/openapi/files/)
- [DB設計書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/DatabaseDesign/DB設計.xlsx)
- [単体試験実施状況](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/3.Implementation/UnitTest/JUNITテストコードの範囲.xlsx)
- [試験項目書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/4.Verification/SystemTest/総合テスト仕様書.xlsx)
