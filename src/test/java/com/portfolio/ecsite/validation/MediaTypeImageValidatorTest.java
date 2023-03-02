package com.portfolio.ecsite.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("MediaTypeImageValidatorのテスト")
class MediaTypeImageValidatorTest {

    private final MediaTypeImageValidator validator = new MediaTypeImageValidator();

    @Test
    @DisplayName("画像ファイルが正しい形式である場合、trueを返す")
    void isValid_whenImageIsValid_returnsTrue() throws Exception {
        MockMultipartFile mockImage = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test".getBytes());
        assertTrue(validator.isValid(mockImage, null));
    }

    @Test
    @DisplayName("画像ファイルが不正な形式である場合、falseを返す")
    void isValid_whenImageIsInvalid_returnsFalse() throws Exception {
        MockMultipartFile mockImage = new MockMultipartFile("image", "test.txt", MediaType.TEXT_PLAIN_VALUE, "test".getBytes());
        assertFalse(validator.isValid(mockImage, null));
    }

    @Test
    @DisplayName("空の画像ファイルが渡された場合、trueを返す")
    void isValid_whenImageIsEmpty_returnsTrue() throws Exception {
        MockMultipartFile mockImage = new MockMultipartFile("image", "", null, new byte[0]);
        assertTrue(validator.isValid(mockImage, null));
    }
}