package customvalidation.logic;

import customvalidation.DateYMD;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * yyyy/MM/ddの形式か判定.
 * 未入力の場合は含まない
 */
public class DateYMDValidator implements  ConstraintValidator<DateYMD, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return value.matches("\\d{4}/\\d{2}/\\d{2}");
    }
}
