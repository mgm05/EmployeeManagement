package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事業部Enum.
 */
@Getter
@AllArgsConstructor
public enum Department implements Enum<String> {
	/** 開発. */
	DEVELOPMENT("0", "開発"),
	/** NW. */
	NETWORK("1", "NW"),
	/** 検証. */
	VERIFICATION("2", "検証"),
	/** オフィス. */
	OFFICE("3", "オフィス"),
	/** 管理. */
	MANAGEMENT("4", "管理");

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
	public static Department of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
