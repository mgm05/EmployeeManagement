package com.example.demo.entity;

import java.util.Date;
import lombok.Data;

/**
 * 社員詳細Entity.
 */
@Data
public class EmployeeDetailEntity {
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
    /** 所属会社ID. */
    private int companyInfoId;
    /** 担当管理営業. */
    private String businessManager;
    /** 事業部. */
    private String department;
    /** 稼働状況. */
    private String commissioningStatus;
    /** 入社日. */
    private Date hireDate;
    /** 退職日. */
    private Date retireDate;
    /** ステータス. */
    private String status;
}
