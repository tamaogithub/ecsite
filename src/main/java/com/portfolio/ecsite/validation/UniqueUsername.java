package com.portfolio.ecsite.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//メソッドが付与できる場所
@Target({ElementType.METHOD, ElementType.FIELD})
//アノテーションをどの段階まで残すか
// RUNTIME つまり、実行時までアノテーションを残す
@Retention(RetentionPolicy.RUNTIME)
//バリデーションを実行するクラスを指定する
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    //バリデーションが発生したときのエラーメッセージ
    String message() default "入力されたユーザー名はすでに登録されています。別のユーザー名を入力してください";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
