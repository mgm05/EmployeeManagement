package com.example.demo.dto;

import lombok.Data;

/**
 * 社員リスト画面用レスポンスDto.
 */
@Data
public class EmployeeListResponse {
    /** 社員ID. */
    private int employeeId;
    /** 氏名. */
    private String name;
    /** 氏名ひらがな. */
    private String nameHiragana;
    /** 担当管理営業. */
    private String businessManager;
    /** 会社名略称. */
    private String abbreviation;
    /** 入社日. */
    private String hireDate;
    /** 稼働状況名称. */
    private String commissioningStatusName;
    /** 事業部名称. */
    private String departmentName;
    /** 年齢. */
    private int age;
}
