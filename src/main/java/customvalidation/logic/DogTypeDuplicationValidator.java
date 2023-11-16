package customvalidation.logic;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.service.dog.DogTypeService;

import customvalidation.DogTypeDuplication;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 犬種名の重複チェック.
 * 
 */
public class DogTypeDuplicationValidator implements  ConstraintValidator<DogTypeDuplication, String> {
	@Autowired
	DogTypeService dogTypeService;
    
    @Override
    public boolean isValid(String dogTypeName, ConstraintValidatorContext context) {
    	DogTypeEntity dogTypeEntity = dogTypeService.selectByDogTypeNm(dogTypeName);
    	if(dogTypeEntity == null) {
    		return true;
    	}
    	return false;
    }
}
