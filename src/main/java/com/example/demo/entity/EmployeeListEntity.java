package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

/**
 * 社員リストEntity.
 */
@Data
public class EmployeeListEntity {
    /** 会社ID. */
    private int employeeId;
    /** 氏名. */
    private String name;
    /** 氏名ひらがな. */
    private String nameHiragana;
    /** 生年月日. */
    private Date birthday;
    /** 担当管理営業. */
    private String businessManager;
    /** 稼働状況. */
    private String commissioningStatus;
    /** 事業部. */
    private String department;
    /** 略称. */
    private String abbreviation;
    /** 入社日. */
    private Date hireDate;
}
