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
    MALE(0, "オス"),
    /** メス. */
    FEMLE(1, "メス");

    /** code. */
    private final int code;
    /** val. */
    private final String val;
    
    /**
     * コードからEnum,code,valを取得.
     * @param code int
     * @return Enum,code,val
     */
    public static DogSex of(int code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode() == code)
                .findFirst()
                .orElseThrow(null);
    }
}
