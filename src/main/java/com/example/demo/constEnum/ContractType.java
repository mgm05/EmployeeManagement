package com.example.demo.constEnum;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 
 * 契約区分Enum.
 */
@Getter
@AllArgsConstructor
public enum ContractType implements Enum<String> {
    /** 男. */
    BREEDER("0", "ブリーダー"),
    /** 女. */
    STORE("1", "店舗");

    /** code. */
    private final String code;
    /** val. */
    private final String val;
    
    /**
     * コードからEnum,code,valを取得.
     * @param code String
     * @return Enum,code,val
     */
    public static ContractType of(String code) {
        return Arrays.stream(values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(null);
    }
}
