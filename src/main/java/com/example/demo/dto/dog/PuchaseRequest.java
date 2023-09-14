package com.example.demo.dto.dog;

import java.util.Date;

import lombok.Data;

/**
 * 仕入れリクエストDto.
 */
@Data
public class PuchaseRequest {
    /** JKC登録番号. */
    private String jkcNo;
    /** 犬種グループ. */
    private String dogGroupCode;
    /** 犬種コード. */
    private String dogCode;
    /** 犬種名. */
    private String dogName;
    /** 性別. */
    private String sex;
    /** 生年月日. */
    private String birthday;
    
    /** 契約区分. */
    private String contractType;
    /** 成約日. */
    private String contractDate;
    /** 仕入日. */
    private String purchaseDate;
    /** 契約金額. */
    private String contractYen;
    /** 仕入価格. */
    private String purchaseYen;
    /** 医療金額. */
    private String medicalYen;
    /** 輸送費. */
    private String transportYen;
    /** 支払予定日. */
    private String paymentExpectedDate;
    /** 支払日. */
    private String paymentDate;

}
