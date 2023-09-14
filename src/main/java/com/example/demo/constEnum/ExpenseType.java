package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * 経費種別Enum.
 */
@Getter
@AllArgsConstructor
public enum ExpenseType {
    /** 経費. */
    EXPENSE(0, "経費"),
    /** 医療費. */
    MEDICAL(1, "医療費"),
    /** 餌. */
    FOOD(2, "餌代"),
    /** その他費. */
    OTHER(3, "その他");

    /** code. */
    private final int code;
    /** val. */
    private final String val;
    
    /**
     * コードからEnum,code,valを取得.
     * @param code int
     * @return Enum,code,val
     */
    public static ExpenseType of(int code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode() == code)
                .findFirst()
                .orElseThrow(null);
    }
}
