package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * 入出金区分Enum.
 */
@Getter
@AllArgsConstructor
public enum CashFlowType {
    /** 入金. */
    DEPOSIT("0", "入金"),
    /** 出金. */
    WITHDRAW("1", "出金");

    /** code. */
    private final String code;
    /** val. */
    private final String val;
    
    /**
     * コードからEnum,code,valを取得.
     * @param string int
     * @return Enum,code,val
     */
    public static CashFlowType of(String code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(null);
    }
}
