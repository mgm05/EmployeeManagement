package com.example.demo.dto.dog;

import lombok.Data;

/**
 * 犬情報Dto.
 */
@Data
public class DogInfoResponse {
    /** ID. */
    private String dogId;
    /** JKC登録番号. */
    private String jkcNo;
    /** 犬種グループ名. */
    private String dogGroupName;
    /** 犬種名. */
    private String dogTypeNm;
    /** 性別. */
    private String sex;
    /** 生年月日. */
    private String birthday;
    /** 成約日.*/
    private String contractDate;
    /** 仕入日.*/
    private String purchaseDate;
}
