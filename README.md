# ECサイトWebアプリケーション
このリポジトリは、ECサイトのWebアプリケーションのコードを含んでいます。

# 概要
このアプリケーションは、企業間向けECサイトに仕入れ業務に必要な機能を提供しています。主な機能には、以下が含まれます。
- ユーザーの登録、閲覧、編集
- 商品の登録、閲覧、編集、購入
- 注文の管理

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
## 権限ごとの表示画面一覧

| 画面/権限    | ADMIN | MAKER | SHOP | 画面URL 例                                       |
|:---------|:------|:------|:-----|:----------------------------------------------|
| ログイン画面   | -     | -     | -    | http://localhost:8080/login                   |
| メニュー画面   | 〇     | 〇     | 〇    | http://localhost:8080                         |
| ユーザ登録画面  | 〇     | -     | -    | http://localhost:8080/users/creationForm      |
| ユーザー一覧画面 | 〇     | -     | -    | http://localhost:8080/users?limit=10&offset=0 |
| ユーザー編集画面 | 〇     | -     | -    | http://localhost:8080/users/update/1          |
| 商品登録画面   | -     | 〇     | -    | http://localhost:8080/items/creationForm      |
| 商品一覧画面   | 〇     | 〇     | 〇    | http://localhost:8080/items?limit=10&offset=0 |
| 商品詳細画面   | 〇     | -     | -    | http://localhost:8080/items/discription/1     |
| 商品編集画面   | -     | 〇     | -    | http://localhost:8080/items/update/1          |         
| 商品購入画面   | -     | -     | 〇    | http://localhost:8080/items/buy/1             |
| 購入確認画面   | -     | -     | 〇    | http://localhost:8080/items/confirm/1         |
| 購入完了画面   | -     | -     | 〇    | http://localhost:8080/items/complete/1        |

# 共通事項
- ECサイトへの情報の入力は、マウス及びキーボード、スマホから行う。
- ブラウザはChromeを推奨とする。<br>
  Chromeのみで動作を確認済みだが、古いブラウザ（internet explorer）などはJavaScriptが対応していない可能性があるため、動作を保証することはできません。
- スマホからも使用すると想定される為、レスポンシブデザイン対応をする。

# 制約事項
- ユーザー情報、商品情報の検索処理は対象外とする。
- ユーザー情報、商品情報の削除処理は対象外とする。
- 商品の購入後の決済処理、購入取り消し、税額計算は対象外とする。

# 技術スタック
- フロントエンド：HTML5、CSS、JavaScript、jQuery、BootStrap、Thymeleaf
- バックエンド：Java18、SpringBoot、SpringSecurity、Mybatis、MySQL、lombok、Validation、JUnit、DbUnit ...etc<br>
※ その他技術スタックは、build.gradle 参照

# ローカル環境での使用方法
このアプリケーションをローカル環境で実行するためには、以下の手順に従ってください。
1. ターミナルまたはコマンドプロンプトで、このリポジトリをクローンします。<br>
```git clone https://github.com/tamaogithub/ecsite.git ```
2. cloneしたプロジェクトのフォルダに移動します。<br>
```cd ecsite ```
3. 下記コマンドでアプリケーションをビルドします。<br>
```./gradlew build -x test```
4. 下記コマンドでjarファイルを実行してアプリケーションを起動します。<br>
```java -jar build/libs/ecsite-0.0.1-SNAPSHOT.jar```<br>
   手順2 で java17でビルドした場合、ランタイムもJava17 に合わせる必要があります。<br>
   下記、コマンドでローカル環境のJavaのバージョンを確認し、Javaが入ってない場合はインストールしてください<br>
``` java -version ```
5. :iブラウザで下記URLにアクセスして、アプリケーションが正常に動作していることを確認します。<br>
```http://localhost:8080/login ```
6. ログイン画面で下記ユーザ、パスワードを入力します。
#### 管理者権限（ADMIN）でログインする場合の例
- ユーザー：tom
- パスワード：password1234
#### メーカー権限(MAKER)でログインする場合の例
- ユーザー：bob
- パスワード：password1234
#### 店舗権限(SHOP)でログインする場合の例
- ユーザー：dag
- パスワード：password1234

## 仕入れ業務の例
#### 管理者権限（ADMIN）でログインした場合
1. ユーザー情報の登録
2. ユーザー情報の閲覧
3. ユーザー情報の編集
4. 商品情報の閲覧
5. ログアウト

#### メーカー権限(MAKER)でログインした場合
1. 商品情報の登録
2. 商品情報の閲覧
3. 商品情報の編集
4. ログアウト

#### 店舗権限(SHOP)でログインした場合
1. 商品情報の閲覧
2. 商品情報の購入
3. ログアウト

## アプリケーションの修了
1. アプリケーションを停止するには、ターミナルまたはコマンドプロンプトでCtrl + Cを入力します。

# 納品物
ソースコード以外の納品物は下記とする
- [要件定義](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/%E8%A6%81%E4%BB%B6%E5%AE%9A%E7%BE%A9.docx)
- [ユースケース図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/1.RequirementsDefinition/UseCaseDiagram/%E3%83%A6%E3%83%BC%E3%82%B9%E3%82%B1%E3%83%BC%E3%82%B9%E5%9B%B3.pdf)
- [シーケンス図(3層アーキテクチャ)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/sta()tic/docs/2.Design/DetailDesign/SequenceDiagram/3%E5%B1%A4%E3%82%A2%E3%83%BC%E3%82%AD%E3%83%86%E3%82%AF%E3%83%81%E3%83%A3%E3%81%AE%E3%82%B7%E3%83%BC%E3%82%B1%E3%83%B3%E3%82%B9%E5%9B%B3.jpg)
- [シーケンス図(ADMIN権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/ADMIN%E6%A8%A9%E9%99%90%E3%81%A7%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%97%E3%81%9F%E5%A0%B4%E5%90%88%E3%81%AE%E3%82%B7%E3%83%BC%E3%82%B1%E3%83%B3%E3%82%B9%E5%9B%B3.jpg)
- [シーケンス図(MAKER権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/MAKER%E6%A8%A9%E9%99%90%E3%81%A7%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%97%E3%81%9F%E5%A0%B4%E5%90%88%E3%81%AE%E3%82%B7%E3%83%BC%E3%82%B1%E3%83%B3%E3%82%B9%E5%9B%B3.jpg)
- [シーケンス図(SHOP権限)](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/SequenceDiagram/SHOP%E6%A8%A9%E9%99%90%E3%81%A7%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%97%E3%81%9F%E5%A0%B4%E5%90%88%E3%81%AE%E3%82%B7%E3%83%BC%E3%82%B1%E3%83%B3%E3%82%B9%E5%9B%B3.jpg) 
- [画面遷移図](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/BasicDesign/%E7%94%BB%E9%9D%A2%E9%81%B7%E7%A7%BB%E5%9B%B3.png)
- [画面設計書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/BasicDesign/%E7%94%BB%E9%9D%A2%E8%A8%AD%E8%A8%88.xlsx)
- [API定義書](http://localhost:61112/swaggerui.html?project=b920a53b&filename=C:/Users/farno/ecsite/src/main/resources/api-schema.yaml&renderer=0)
- [DB設計書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/2.Design/DetailDesign/DatabaseDesign/DB%E8%A8%AD%E8%A8%88.xlsx)
- [単体試験実施状況](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/3.Implementation/UnitTest/JUNIT%E3%83%86%E3%82%B9%E3%83%88%E3%82%B3%E3%83%BC%E3%83%89%E3%81%AE%E7%AF%84%E5%9B%B2.xlsx)
- [試験項目書](https://github.com/tamaogithub/ecsite/blob/master/src/main/resources/static/docs/4.Verification/SystemTest/%E7%B7%8F%E5%90%88%E3%83%86%E3%82%B9%E3%83%88%E4%BB%95%E6%A7%98%E6%9B%B8.xlsx)
