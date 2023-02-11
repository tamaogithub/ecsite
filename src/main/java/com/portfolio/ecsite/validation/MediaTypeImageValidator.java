package com.portfolio.ecsite.validation;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class MediaTypeImageValidator implements ConstraintValidator<MediaTypeImage, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {

        // Emptyなら通す。他のバリデーターで検証する
//        if (image.isEmpty()) {
//            return true;
//        }

        // メディアタイプ
        MediaType mediaType = MediaType.parseMediaType(image.getContentType());

        String mediaFileName = image.getOriginalFilename();
        // 拡張子
        String ext = FilenameUtils.getExtension(mediaFileName);

        List<MediaType> mediaTypeList = Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.IMAGE_GIF);
        List<String> extList = Arrays.asList("jpg", "jpeg", "png", "gif");

        // メディアタイプと拡張子をチェック
        return mediaTypeList.stream().anyMatch((mType) -> mediaType.includes(mType))
                && extList.stream().anyMatch((v) -> ext.toLowerCase().equals(v));
    }
}
