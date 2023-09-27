package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * infoEnum.
 */
@Getter
@AllArgsConstructor
public enum Info {
    /** 本体価格. */
    BASE_PRICE(0, "本体価格"),
    /** 医療費. */
    MEDICAL_YEN(1, "医療費"),
    /** 輸送費. */
    TRANSPORT_YEN(2, "輸送費");
    

    /** code. */
    private final int code;
    /** val. */
    private final String val;
    
    /**
     * コードからEnum,code,valを取得.
     * @param code int
     * @return Enum,code,val
     */
    public static Info of(int code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode() == code)
                .findFirst()
                .orElseThrow(null);
    }
}
