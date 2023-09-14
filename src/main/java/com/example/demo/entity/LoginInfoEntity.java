package com.example.demo.entity;

import java.util.Date;

import lombok.Data;

/**
 * ログイン情報Entity.
 */
@Data
public class LoginInfoEntity {
    /** ログインID. */
    private String loginId;
    /** パスワード. */
    private String password;
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
