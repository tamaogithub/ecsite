package com.portfolio.ecsite.common;

import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("FileOpeUtilのテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FileOpeUtilTest {

    @BeforeAll
    static void setUp() {
        System.out.println("テスト開始");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("テスト終了");
    }

    @Test
    @Order(1)
    @DisplayName("MultipartFileをバイト配列に変換できるか確認")
    public void testUploadAction() throws IOException {
        // テスト用のMultipartFileを作成
        String content = "test image";
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", content.getBytes());

        // テスト対象メソッドを実行
        byte[] result = FileOpeUtil.uploadAction(file);

        // 結果を検証
        assertNotNull(result);
        assertArrayEquals(content.getBytes(), result);
    }

    @Test
    @DisplayName("MultipartFileをBase64形式の文字列に変換するテスト")
    @Order(2)
    void testFileToBase64Data() throws IOException {

        // 画像ファイルをアップロードするためのMockMultipartFileを作成
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image".getBytes());

        byte[] bytes = FileOpeUtil.uploadAction(file);
        String base64Data = FileOpeUtil.fileToBase64Data(file);

        // 変換された文字列がBase64形式かどうかをチェックする
        assertTrue(Base64.getDecoder().decode(base64Data).length > 0);
    }

    @Test
    @DisplayName("画像ファイルでないMultipartFileを渡すと、MIMEタイプが画像形式でないと判定されるテスト")
    @Order(3)
    void testIsImageMimeType2() throws IOException {

        // 画像形式でないファイルをアップロードするためのMockMultipartFileを作成
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test image".getBytes());

        assertFalse(FileOpeUtil.isImageMimeType(file));
    }

    @Test
    @DisplayName("画像ファイルが存在しない場合、指定された画像ファイルが返されるテスト")
    @Order(4)
    public void testNoImageFileToBase64Data() {
        String base64Data = FileOpeUtil.noImageFileToBase64Data();
        assertNotNull(base64Data, "base64Dataがnullであるべきではありません");
        assertEquals(11828, base64Data.length(), "base64Dataの長さが一致しません");
    }

    @Test
    @Order(5)
    @DisplayName("存在しないファイルを読み込んだ場合のテスト")
    public void testNoImageFileToBase64DataWithNonExistingFile() {
        File fileImg = new File("\\c\\tmp\\nonExistingImage.png");
        String base64Data = null;
        try {
            byte[] byteImg = Files.readAllBytes(fileImg.toPath());
            base64Data = Base64.getEncoder().encodeToString(byteImg);
        } catch (IOException e) {
            // 例外が発生することを期待する
            assertNotNull(e);
            assertEquals("\\c\\tmp\\nonExistingImage.png", e.getMessage());

        }
        assertEquals(null, base64Data, "base64Dataがnullであるべきです");
    }

    @Test
    @DisplayName("空のMultipartFileを渡すと、MIMEタイプが画像形式でないと判定されるテスト")
    @Order(6)
    void testIsImageMimeType() throws IOException {

        // 空のMultipartFileを作成
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "".getBytes());

        assertFalse(FileOpeUtil.isImageMimeType(file));
    }
}