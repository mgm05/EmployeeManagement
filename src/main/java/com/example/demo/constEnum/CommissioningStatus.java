package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 稼働状況Enum.
 */
@Getter
@AllArgsConstructor
public enum CommissioningStatus implements Enum<String> {
	/** 未稼働. */
	NOT_RUNNING("0", "未稼働"),
	/** 稼働. */
	RUNNING("1", "稼働");

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
	public static CommissioningStatus of(String code) {
		return Arrays.stream(values())
				.filter(t -> t.getCode().equals(code))
				.findFirst()
				.orElseThrow(null);
	}
}
