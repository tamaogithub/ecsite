# 以下は、Java 17のインストール方法について、Windows、Mac、Linuxそれぞれの場合について説明します。

### Windows<br>
1. [公式サイト](https://www.oracle.com/jp/java/technologies/downloads/#jdk19-windows)からJava SE Development Kit 17をダウンロードします。<br>
2. ダウンロードしたファイルを実行して、インストーラーを起動します。<br>
3. インストール先を選択します。既定のままで問題ありません。<br>
4. インストールを開始します。<br>
5. インストールが完了したら、環境変数の設定を行います。Windowsの場合、<br>
6. コントロールパネルの「システム」から「システムの詳細設定」を開き、<br>
7. 「環境変数」を選択します。システム環境変数の「Path」に、Javaのインストール先のパスを追加します。<br>
### Mac<br>
1. [公式サイト](https://www.oracle.com/jp/java/technologies/downloads/#jdk19-mac)からJava SE Development Kit 17をダウンロードします。<br>
2. ダウンロードしたファイルを実行して、インストーラーを起動します。<br>
3. インストーラーに従って、インストールを開始します。<br>
4. インストールが完了したら、Javaのバージョンを確認します。<br>
   ```java -version```
5. バージョン情報が表示されれば、インストールが成功しています。<br>
### Linux<br>
1. コマンドラインから、Java SE Development Kit 17をインストールします。以下のコマンドを実行します。<br>
```sudo apt update```
```sudo apt install openjdk-17-jdk ```
2. インストールが完了したら、Javaのバージョンを確認します。<br>
```java -version```
3. バージョン情報が表示されれば、インストールが成功しています。<br>