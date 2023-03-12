# ECサイトWebアプリケーション
このリポジトリは、ECサイトのWebアプリケーションのコードを含んでいます。
<br>このアプリケーションは、企業間向けECサイトに仕入れ業務に必要な機能を提供しています。主な機能には、以下が含まれます。
- ユーザーの登録・編集
- 商品の登録・削除・更新、購入
- 注文の管理

# 技術スタック
- フロントエンド：HTML5、CSS、BootStrap、Thymeleaf
- バックエンド：Java、SpringBoot、SpringSecurity、Mybatis、MySQL、lombok、Validation、JUnit、DbUnit ...etc<br>
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


## CRUD操作の例
- 新しい商品を追加するには、管理者権限が必要です。

#### 管理者権限（ADMIN）でログインした場合
1. ユーザーの登録、編集
2. ユーザー情報の表示
3. 商品情報の閲覧
4. ログアウト

#### メーカー権限(MAKER)でログインした場合
1. 商品情報の登録、編集
2. ログアウト

#### 店舗権限(SHOP)でログインした場合
1. 商品情報の登録、編集
2. ログアウト

###
