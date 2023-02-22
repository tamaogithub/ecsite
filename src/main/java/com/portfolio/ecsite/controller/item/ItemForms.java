package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.validation.MediaTypeImage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Size(max=255)
    private String fileName;

    @MediaTypeImage
    private MultipartFile itemImage;

    @NotBlank
    @Size(max=20)
    private String company;

    @Min(value = 1, message = "{value}以上の値を設定してください")
    @NotNull
    private Integer price;

    @Min(value = 1, message = "{value}以上の値を設定してください")
    @NotNull
    private Integer stock;

    private String payment;
}
