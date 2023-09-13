package customvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import customvalidation.logic.ZenkakuHiraganaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * アノテーションクラス.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ZenkakuHiraganaValidator.class})
public @interface ZenkakuHiragana {
    
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
