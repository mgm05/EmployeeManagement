package com.example.demo.dto.dog;

import customvalidation.DogTypeDuplication;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 犬種リクエストDto.
 */
@Data
public class DogTypeRequest {
	/** 犬種. */
	@NotBlank(message = "犬種を入力して下さい。")
	@DogTypeDuplication(message = "犬種が重複しています。")
	private String dogType;
	/** 犬種グループ. */
	@NotBlank(message = "犬種グループを選択して下さい。")
	private String dogGroup;

}
