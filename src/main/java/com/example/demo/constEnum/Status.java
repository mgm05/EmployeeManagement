package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ステータスEnum.
 */
@Getter
@AllArgsConstructor
public enum Status implements Enum<String> {
	/** 在籍. */
	ENROLLMENT("0", "在籍"),
	/** 退職. */
	RETIREMENT("1", "退職"),
	/** 入社待. */
	JOINED_WAIT("2", "入社待"),
	/** 入社取り消し. */
	JOINED_CANCELLATION("3", "入社取り消し");

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
	public static Status of(String code) {
		return Arrays.stream(values()).filter(t -> t.getCode().equals(code)).findFirst().orElseThrow(null);
	}
}
