package customvalidation.logic;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.service.dog.DogTypeService;

import customvalidation.DogTypeDuplication;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * yyyy/MM/ddの形式か判定.
 * 未入力の場合は含まない
 */
public class DogTypeDuplicationValidator implements  ConstraintValidator<DogTypeDuplication, String> {
	@Autowired
	DogTypeService dogTypeService;
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	DogTypeEntity dogTypeEntity = dogTypeService.selectDogTypeNmByCode(value);
    	if(dogTypeEntity == null) {
    		return true;
    	}
    	return false;
    }
}
