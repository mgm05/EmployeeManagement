package com.example.demo.entity.dog;

import java.util.Date;

import lombok.Data;

/**
 * 犬一覧レスポンスエンティティ.
 */
@Data
public class DogListResponseEntity {
	/** 犬ID. */
	private String dogId;
	/** JKC登録番号. */
	private String jkcNo;
	/** 犬種グループ名. */
	private String dogGroupName;
	/** 犬種名. */
	private String dogTypeNm;
	/** 性別. */
	private String sex;
	/** 生年月日. */
	private Date birthday;
	/** 成約日. */
	private Date contractDate;
	/** 仕入日. */
	private Date purchaseDate;
}
