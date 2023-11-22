package com.example.demo.dto.dog;

import java.util.List;

import lombok.Data;

/**
 * 犬詳細Dto.
 */
@Data
public class DogDetailResponse {
	/** 犬ID. */
	private String dogId;
	/** JKC登録番号. */
	private String jkcNo;
	/** 犬種グループ名. */
	private String dogGroupName;
	/** 犬種サイズ名. */
	private String dogSizeName;
	/** 犬種名. */
	private String dogTypeName;
	/** 性別. */
	private String sex;
	/** 生年月日. */
	private String birthday;

	/** 契約区分. */
	private String contractType;
	/** 成約日. */
	private String contractDate;
	/** 仕入日. */
	private String purchaseDate;
	/** 契約金額. */
	private String contractYen;
	/** 支払予定日. */
	private String paymentExpectedDate;
	/** 支払日. */
	private String paymentDate;
	/** 入出金リスト. */
	private List<CashFlow> cashFlowList;

}
