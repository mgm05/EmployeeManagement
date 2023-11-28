package com.example.demo.dto.dog;

import lombok.Data;

/**
 * 経費リクエストDto.
 */
@Data
public class ExpenseRequest {
	/** 経費ID. */
	private Integer expenseId;
	/** 犬ID. */
	private Integer dogId;
	/** 発生区分. */
	private String occurrenceType;
	/** 入出金区分. */
	private String cashFlowType;
	/** 経費種別. */
	private String expenseType;
	/** 内容. */
	private String info;
	/** 見積金額. */
	private Integer quotationYen;
	/** 確定金額. */
	private Integer closeYen;
	/** 発生日. */
	private String paymentDate;
	/** 仕入日. */
	private String purchaseDate;
}
