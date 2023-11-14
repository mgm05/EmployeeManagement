package com.example.demo.entity.dog;

import java.util.List;

import lombok.Data;

/**
 * 犬一覧リクエストエンティティ.
 */
@Data
public class DogListRequestEntity{
    /** 犬ID. */
    private String dogId;
    /** JKC登録番号. */
    private String jkcNo;
    /** 犬種グループコード. */
    private List<String> dogGroupCode;
    /** 犬種コード. */
    private String dogTypeCode;
    /** 性別. */
    private String sex;
    /** 生年月日From.*/
    private String birthdayFrom;
    /** 生年月日To.*/
    private String birthdayTo;
    /** 成約日From.*/
    private String contractDateFrom;
    /** 成約日To.*/
    private String contractDateTo;
    /** 仕入日From.*/
    private String purchaseDateFrom;
    /** 仕入日To.*/
    private String purchaseDateTo;
}
