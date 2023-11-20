package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 犬種サイズEnum.
 */
@Getter
@AllArgsConstructor
public enum DogSize {
	/** 超小型犬. */
	URTRA_SMALL_DOG("1", "超小型犬"),
	/** 小型犬. */
	SMALL_DOG("2", "小型犬"),
	/** 中型犬. */
	MEDIUM_DOG("3", "中型犬"),
	/** 大型犬. */
	LARGE_DOG("4", "大型犬"),
	/** 超大型犬. */
	URTRA_LARGE_DOG("5", "超大型犬");

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
	public static DogSize of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
