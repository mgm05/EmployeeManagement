package customvalidation.logic;


import customvalidation.ZenkakuHiragana;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 全角ひらがなを判定.
 * 未入力の場合は含まない
 */
public class ZenkakuHiraganaValidator implements  ConstraintValidator<ZenkakuHiragana, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return value.matches("^[\\p{Script=Hiragana}ー－]*$");
    }

}
