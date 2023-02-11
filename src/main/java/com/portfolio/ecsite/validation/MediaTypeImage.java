package com.portfolio.ecsite.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MediaTypeImageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MediaTypeImage {
    String message() default "画像ファイル（*.jpg, *.jpeg, *.png, *.gif）を選択してください";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
