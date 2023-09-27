package com.example.demo.dto.dog;

import java.util.List;

import lombok.Data;

/**
 * 犬リストリクエストDto.
 */
@Data
public class DogListRequest {
    /** ID. */
    private Integer dogId;
    /** JKC登録番号. */
    private String jkcNo;
    /** 犬種グループ. */
    private List<String> dogGroup;
    /** 犬種. */
    private String dogType;
    /** 性別. */
    private Integer sex;
    /** 生年月日From. */
    private String birthdayFrom;
    /** 生年月日To. */
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
