package com.portfolio.ecsite.controller.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class ItemForms {

    @NotBlank
    @Size(max=20)
    private String itemName;

    @NotBlank
    @Size(max=100)
    private String description;

    // 添付ファイル
//    @ContentType(allowed = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE })
    private MultipartFile itemImage; // serializableではないのでtransientにする
//    @Lob
//    @Type(type="org.hibernate.type.ImageType")
//    private byte[] itemImage;

    @NotBlank
    @Size(max=20)
    private String company;

    private int price;

    private int stock;
}
