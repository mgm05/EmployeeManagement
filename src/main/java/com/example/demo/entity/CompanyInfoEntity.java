package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

/**
 * 会社情報Entity.
 */
@Data
public class CompanyInfoEntity {
    /** 所属会社ID. */
    private int companyId;
    /** 会社名. */
    private String companyName;
    /** 略称. */
    private String abbreviation;
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
