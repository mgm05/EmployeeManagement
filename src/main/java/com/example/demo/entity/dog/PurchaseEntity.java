package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * 仕入Entity.
 */
@Data
public class PurchaseEntity {
	/** 仕入ID. */
	private Integer purchaseId;
	/** 犬ID. */
	private Integer dogId;
	/** 契約ID. */
	private Integer contractId;
	/** 契約区分. */
	private String contractType;
	/** 店舗ID. */
	private String storeId;
	/** 店舗名. */
	private String storeName;
	/** 仕入担当ユーザーID. */
	private String purchaseUserId;
	/** 仕入担当ユーザー名. */
	private String purchaseUserName;
	/** グループID. */
	private Integer groupId;
	/** 成約日. */
	private Date contractDate;
	/** 仕入日. */
	private Date purchaseDate;
	/** 契約金額. */
	private Integer contractYen;
	/** 仕入価格. */
	private Integer purchaseYen;
	/** 医療金額. */
	private Integer medicalYen;
	/** 輸送費. */
	private Integer transportYen;
	/** 支払予定日. */
	private Date paymentExpectedDate;
	/** 支払日. */
	private Date paymentDate;
	/** 所有者変更日. */
	private Date changeNameDate;
	/** キャンセル日時. */
	private Timestamp cancelDatetime;
	/** キャンセルフラグ. */
	private String cancelFlag;
	/** 登録日時. */
	private Timestamp createDatetime;
	/** 登録者ユーザID. */
	private String createUserId;
	/** 更新日時. */
	private Timestamp updateDatetime;
	/** 更新者ユーザID. */
	private String updateUserId;

}
