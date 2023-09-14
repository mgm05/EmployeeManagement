package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

/**
 * 社員情報Entity.
 */
@Data
public class EmployeeInfoEntity {
    /** 社員ID. */
    private int employeeId;
    /** 氏名. */
    private String name;
    /** 氏名ひらがな. */
    private String nameHiragana;
    /** 生年月日. */
    private Date birthday;
    /** 性別. */
    private String sex;
    /** メールアドレス. */
    private String mailAddress;
    /** 電話番号. */
    private String telephoneNumber;
    /** 削除フラグ. */
    private String isDeleted;
    /** 登録日時. */
    private Date createdDate;
    /** 更新日時. */
    private Date modifiedDate;
    /** 登録者ID. */
    private String createdId;
    /** 更新者ID. */
    private String modifiedId;
}
