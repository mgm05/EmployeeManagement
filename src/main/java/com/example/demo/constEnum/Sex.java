package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性別Enum.
 */
@Getter
@AllArgsConstructor
public enum Sex implements Enum<String> {
	/** 男. */
	MALE("0", "男"),
	/** 女. */
	FEMLE("1", "女");

	/** code. */
	private final String code;
	/** val. */
	private final String val;

	/**
	 * コードからEnum,code,valを取得.
	 * 
	 * @param code String
	 * @return Enum,code,val
	 */
	public static Sex of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
