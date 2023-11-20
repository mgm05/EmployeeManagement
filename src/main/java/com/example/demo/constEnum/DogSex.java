package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性別Enum.
 */
@Getter
@AllArgsConstructor
public enum DogSex {
	/** オス. */
	MALE("0", "オス"),
	/** メス. */
	FEMLE("1", "メス");

	/** code. */
	private final String code;
	/** val. */
	private final String val;

	/**
	 * コードからEnum,code,valを取得.
	 * 
	 * @param code int
	 * @return Enum,code,val
	 */
	public static DogSex of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
