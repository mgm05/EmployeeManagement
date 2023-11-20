package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 発生区分Enum.
 */
@Getter
@AllArgsConstructor
public enum OccurrenceType {
	/** 仕入れ. */
	PURCHASE("0", "仕入れ"),
	/** 在庫. */
	STOCK("1", "在庫"),
	/** 販売. */
	SALE("2", "販売");

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
	public static OccurrenceType of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
