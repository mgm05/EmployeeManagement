package com.example.demo.entity.dog;

import java.util.Date;

import lombok.Data;

/**
 * 犬Entity.
 */
@Data
public class DogInfoEntity{
    /** ID. */
    private Integer dogId;
    /** JKC登録番号. */
    private String jkcNo;
    /** 犬種グループ名. */
    private String dogGroupName;
    /** 犬種名. */
    private String dogTypeNm;
    /** 性別. */
    private Integer sex;
    /** 生年月日. */
    private Date birthday;
    /** 成約日.*/
    private Date contractDate;
    /** 仕入日.*/
    private Date purchaseDate;
    
}
