package com.example.demo.dto.dog;

import java.util.Date;

import lombok.Data;

/**
 * 入出金Dto.
 */
@Data
public class CashFlow {
	/** ID. */
	private Integer cashFlowId;
	/** 犬情報ID. */
	private Integer dogId;
	/** 経費情報ID. */
	private Integer expenseId;
	/** 入出金区分. */
	private String cashFlowType;
	/** 内容. */
	private Integer info;
	/** 説明. */
	private String note;
	/** 金額. */
	private String price;
	/** 発生日. */
	private String cashFlowDate;
	/** 確定日. */
	private String closeDate;

}
