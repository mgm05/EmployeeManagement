package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

/**
 * 社員状況Entity.
 */
@Data
public class EmployeeStateEntity {
    /** 社員ID. */
    private int employeeInfoId;
    /** 所属会社ID. */
    private int companyInfoId;
    /** 担当管理営業. */
    private String businessManager;
    /** 事業部. */
    private String department;
    /** 稼働状況. */
    private String commissioningStatus;
    /** ステータス. */
    private String status;
    /** 入社日. */
    private Date hireDate;
    /** 退職日. */
    private Date retireDate;
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
