package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * 経費Entity.
 */
@Data
public class ExpenseEntity {
	/** 経費ID. */
	private Integer expenseId;
	/** 支払情報ID. */
	private Integer paymentId;
	/** 犬ID. */
	private Integer dogId;
	/** 申請店舗コード. */
	private String requestStoreCode;
	/** 申請店舗名. */
	private String requestStoreName;
	/** 発生区分. */
	private String occurrenceType;
	/** 入出金区分. */
	private String cashFlowType;
	/** 経費種別. */
	private String expenseType;
	/** 名称コード. */
	private Integer itemId;
	/** 名称. */
	private String itemName;
	/** 内容. */
	private String info;
	/** 見積金額. */
	private Integer quotationYen;
	/** 確定金額. */
	private Integer closeYen;
	/** 支払先コード. */
	private String paymentDestinationCode;
	/** 支払先名. */
	private String paymentDestinationName;
	/** 発生日. */
	private Date paymentDate;
	/** 入荷日. */
	private Date arrivalDate;
	/** キャンセルフラグ. */
	private String cancelFlag;
	/** 登録日時. */
	private Timestamp createDatetime;
	/** 登録店舗コード. */
	private String createStoreCode;
	/** 登録店舗名. */
	private String createStoreName;
	/** 登録者ユーザID. */
	private String createUserId;
	/** 更新日時. */
	private Timestamp updateDatetime;
	/** 更新店舗コード. */
	private String updateStoreCode;
	/** 更新店舗名. */
	private String updateStoreName;
	/** 更新者ユーザID. */
	private String updateUserId;

}
