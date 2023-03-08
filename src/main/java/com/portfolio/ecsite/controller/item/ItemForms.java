package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.validation.MediaTypeImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemForms {

    @NotBlank
    @Size(max = 20, message = "20文字以下で入力してください")
    private String itemName;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String description;

    @Size(max = 255, message = "255文字以下で入力してください")
    private String fileName;

    @MediaTypeImage
    private MultipartFile itemImage;

    @NotBlank
    @Size(max = 100, message = "100文字以下で入力してください")
    private String company;

    @Min(value = 1, message = "{value}以上で入力してください")
    @Max(value = 1000000000, message = "{value}以下で入力してください")
    @NotNull(message = "空白は許可されていません")
    private Integer price;

    @Min(value = 1, message = "{value}以上で入力してください")
    @Max(value = 1000000000, message = "{value}以下で入力してください")
    @NotNull(message = "空白は許可されていません")
    private Integer stock;
}
