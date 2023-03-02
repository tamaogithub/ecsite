package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.validation.MediaTypeImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemFormsTest {

    @Test
    @Order(1)
    @DisplayName("バリデーションがないかチェック【正常系】")
    void testValidation() throws IOException {
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        Validator validator = factory.getValidator();
        // 画像ファイルを含むMockMultipartFileオブジェクトを作成する
        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", getClass().getResourceAsStream("/c/tmp/image.jpg"));
        // バリデーション対象のオブジェクトを生成
        ItemForms itemForms = new ItemForms("歯ブラシ", "歯ブラシ（極細）", "歯ブラシ2.jpg", file, "ELECOM", 157, 10, null);
        // バリデーションを実行
        var violations = validator.validate(itemForms);
        // バリデーションがないかチェック
        assertTrue(violations.isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("バリデーションがあるかチェック【異常系】")
    void testInvalidValidation() {
        // バリデータのファクトリを取得
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // バリデータを取得
        Validator validator = factory.getValidator();

        // バリデーション対象のオブジェクトを生成（バリデーションエラーあり）
        ItemForms itemForms = new ItemForms("", "", "azarashi.png", null, "", null, null, null);

        // バリデーションを実行
        var violations = validator.validate(itemForms);

        var violationsSize = violations.size();

        //バリデーションがあるかをチェック
        //@NotBlank @NotEmpty のフィールドが効いているか確認
        assertEquals(5, violationsSize);

        // バリデーションを実行
        Set<ConstraintViolation<ItemForms>> violationss = validator.validate(itemForms);

        // エラーがあるかをチェック
        if (!violationss.isEmpty()) {
            for (ConstraintViolation<ItemForms> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("画像情報チェック【異常系】")
    void testImageMimeType() throws Exception {
        // テスト用の画像を読み込み
        byte[] bytes = getClass().getResourceAsStream("/static/images/invalid_image.jpg").readAllBytes();

        // MultipartFileを作成
        MultipartFile file = new MockMultipartFile("/static/images/invalid_image.jpg", bytes);

        // ItemFormsを生成して、MultipartFileを設定
        ItemForms itemForms = new ItemForms();
        itemForms.setItemImage(file);

        // バリデーションを実行
        var violations = Validation.buildDefaultValidatorFactory().getValidator().validate(itemForms);

        // エラーがあるかをチェック
        assertTrue(violations.isEmpty());

        // 不正なMIMEタイプの画像を作成して、MultipartFileを設定
        byte[] invalidBytes = getClass().getResourceAsStream("/static/images/sample.txt").readAllBytes();
        MultipartFile invalidFile = new MockMultipartFile("/static/images/invalid_image.jpg", invalidBytes);
        itemForms.setItemImage(invalidFile);

        // バリデーションを実行
        violations = Validation.buildDefaultValidatorFactory().getValidator().validate(itemForms);

        MediaTypeImage mediaTypeImage = ItemForms.class.getDeclaredField("massage").getAnnotation(MediaTypeImage.class);

        // エラーがあるかをチェック
        assertEquals(1, violations.size());
        assertEquals(mediaTypeImage.message(), violations.iterator().next().getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("画像情報チェック【異常系】")
    void testUploadImage() throws Exception {
        // 画像ファイルを含むMockMultipartFileオブジェクトを作成する
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "image.jpg",
                "image/jpeg",
                getClass().getResourceAsStream("/c/tmp/image.jpg")
        );

        // 画像ファイルをアップロードするメソッドを呼び出す
//        MyImageUploadService imageUploadService = new MyImageUploadService();
//        String response = imageUploadService.uploadImage(file);

        // レスポンスが期待通りかどうかを検証する
//        assertThat(response).isEqualTo("画像がアップロードされました");
    }
}
