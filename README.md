# ECサイトWebアプリケーション
このリポジトリは、ECサイトのWebアプリケーションのコードを含んでいます。
<br>このアプリケーションは、企業間向けECサイトに仕入れ業務に必要な機能を提供しています。主な機能には、以下が含まれます。
- ユーザーの登録、閲覧、編集
- 商品の登録、閲覧、編集、購入
- 注文の管理

# 認証権限について
ログイン機能を実装し、認証権限に合わせて認可された操作を行う。
- 管理者権限（ADMIN）<br>
  管理者は、ECサイトにログインするユーザーを登録できる。
- メーカー権限(MAKER)<br>
  メーカーは、出店企業（店舗）に向けて商品を販売できる。
- 店舗権限(SHOP)<br>
  店舗は、商品をメーカーから買う（仕入れる）ことができる。

# 共通事項
- ECサイトへの情報の入力は、マウス及びキーボード、スマホから行う。
- ブラウザはChromeのみ対応とする
- スマホからも使用すると想定される為、レスポンシブデザイン対応をする

# 制約事項
- 購入後の決済処理は対象外とする。
- ユーザー情報、商品情報の削除処理は対象外とする。
- 
# 技術スタック
- フロントエンド：HTML5、CSS、JavaScript、jQuery、BootStrap、Thymeleaf
- バックエンド：Java18、SpringBoot、SpringSecurity、Mybatis、MySQL、lombok、Validation、JUnit、DbUnit ...etc<br>
※ その他技術スタックは、build.gradle 参照

# ローカル環境での動作確認
このアプリケーションをローカル環境で実行するためには、以下の手順に従ってください。
1. このリポジトリをクローンします。<br>
```git clone https://github.com/tamaogithub/ecsite.git ```
2. 下記コマンドでアプリケーションをビルドします。<br> 
```./gradlew build```
3. 下記コマンドでjarファイルを実行してアプリケーションを起動します。<br>
```java -jar target/{jarファイル名}```
4. ブラウザで下記URLにアクセスして、アプリケーションが正常に動作していることを確認します。<br>
```http://localhost:8080/login ```
5. ログイン画面で下記ユーザ、パスワードを入力します。
#### 管理者権限（ADMIN）でログインする場合
- ユーザー：tom
- パスワード：password1234
#### メーカー権限(MAKER)でログインする場合
- ユーザー：bob
- パスワード：password1234
#### 店舗権限(SHOP)でログインする場合
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

# 納品物
ソースコード以外の納品物は下記とする
- [要件定義](https://docs.google.com/document/d/10nq4WdK7KVjKBI2eLFSeeKP2L65iv4za/edit)
- [ユースケース図](https://drive.google.com/drive/folders/1P5bdhr7V7B6z9jghsLN6iMIrtcHMYAm_)
- [シーケンス図]
- [画面遷移図](https://drive.google.com/drive/folders/1y4oY_1f3hZc__UtuiwES0q1rbpZeTY9s)
- [API定義書](http://localhost:61112/swaggerui.html?project=b920a53b&filename=C:/Users/farno/ecsite/src/main/resources/api-schema.yaml&renderer=0)
- [画面設計書](https://docs.google.com/spreadsheets/d/1A0jdlHoIj8a1JjD0q2jo4bblBcmKMHvQ1LSGgM0Q-1E/edit#gid=0)
- [DB設計書](https://docs.google.com/spreadsheets/d/1haTe27o6zy1MlmP4Zu3EzqU_CKUCFNrSfQAwkuZ2MZE/edit#gid=877501984)
- [試験項目書](https://docs.google.com/spreadsheets/d/1OGezkqqGK2X-pxyfaLvJaUFUVmCoyfFr/edit#gid=672063631)
