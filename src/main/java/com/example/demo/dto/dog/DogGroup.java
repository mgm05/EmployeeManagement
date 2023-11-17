package com.example.demo.dto.dog;

import lombok.Data;

/**
 * 犬種グループDto.
 */
@Data
public class DogGroup {
	/** 犬種グループコード. */
	private String dogGroupCode;
	/** 犬種グループ名. */
	private String dogGroupName;
}
