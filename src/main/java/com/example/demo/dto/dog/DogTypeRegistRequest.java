package com.example.demo.dto.dog;

import customvalidation.DogTypeDuplication;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 犬種登録リクエストDto.
 */
@Data
public class DogTypeRegistRequest {
	/** 犬種名. */
	@NotBlank(message = "犬種を入力して下さい。")
	@DogTypeDuplication(message = "犬種が重複しています。")
	private String dogTypeName;
	/** 犬種グループコード. */
	@NotBlank(message = "犬種グループを選択して下さい。")
	private String dogGroupCode;
	/** 犬種サイズ. */
	@NotBlank(message = "犬種サイズを選択して下さい。")
	private String dogSizeCode;
}
