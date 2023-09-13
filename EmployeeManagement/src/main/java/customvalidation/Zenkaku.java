package customvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import customvalidation.logic.ZenkakuValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 全角文字を判定.
 * 未入力の場合は含まない
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ZenkakuValidator.class})
public @interface Zenkaku {
    
    /**
     * groups().
     * @return バリデーションを実装する際の必須定義
     */
    Class<?>[] groups() default {};
    /**
     *  payload().
     * @return バリデーションを実装する際の必須定義
     */
    Class<? extends Payload>[] payload() default {};
    /**
     * メッセージ.
     * @return メッセージ
     */
    String message() default "";
}
