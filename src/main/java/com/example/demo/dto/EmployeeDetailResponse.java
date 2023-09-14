package com.example.demo.dto;

import lombok.Data;

/**
 * 社員詳細画面用レスポンスDto.
 */
@Data
public class EmployeeDetailResponse {
    /** 社員ID. */
    private String employeeId;
    /** 氏名. */
    private String name;
    /** 氏名ひらがな. */
    private String nameHiragana;
    /** 生年月日. */
    private String birthday;
    /** 性別. */
    private String sex;
    /** メールアドレス. */
    private String mailAddress;
    /** 電話番号. */
    private String telephoneNumber;
    /** 所属会社ID. */
    private int companyInfoId;
    /** 担当管理営業. */
    private String businessManager;
    /** 事業部. */
    private String department;
    /** 稼働状況. */
    private String commissioningStatus;
    /** 入社日. */
    private String hireDate;
    /** 退職日. */
    private String retireDate;
    /** ステータス. */
    private String status;
}
