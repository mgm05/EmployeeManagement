package com.example.demo.dto.dog;

import java.util.Date;

import lombok.Data;

/**
 * 犬詳細Dto.
 */
@Data
public class DogDetailResponse {
    /** ID. */
    private Integer dogId;
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
    
    /** 契約区分.*/
    private String contractType;
    /** 成約日.*/
    private String contractDate;
    /** 仕入日.*/
    private String purchaseDate;
    /** 契約金額.*/
    private Integer contractYen;
    /** 支払予定日.*/
    private String paymentExpectedDate;
    /** 支払日.*/
    private String paymentDate;
    
    
    
}
