package customvalidation.logic;

import customvalidation.Zenkaku;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 全角文字を判定.
 * 未入力の場合は含まない
 */
public class ZenkakuValidator implements  ConstraintValidator<Zenkaku, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return value.matches("^[^\\x00-\\x7F]*$");
    }
}
