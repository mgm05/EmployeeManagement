package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 *入出金Entity.
 */
@Data
public class CashFlowEntity {
    /** ID. */
    private Integer cashFlowId;
    /** 犬情報ID. */
    private Integer dogId;
    /** 経費情報ID. */
    private Integer expenseId;
    /** 発生店舗コード. */
    private String storeCode;
    /** 発生店舗名. */
    private String storeName;
    /** 入出金区分. */
    private Integer cashFlowType;
    /** 内容. */
    private Integer info;
    /** 説明. */
    private String note;
    /** 金額. */
    private Integer price;
    /** キャンセルフラグ. */
    private Integer cancelFlag;
    /** 発生日. */
    private Date cashFlowDate;
    /** 確定日. */
    private Date closeDate;
    /** 登録日時. */
    private Timestamp createDatetime;
    /** 登録者ユーザID. */
    private String createUserId;
    /** 更新日時. */
    private Timestamp updateDatetime;
    /** 更新者ユーザID. */
    private String updateUserId;

}