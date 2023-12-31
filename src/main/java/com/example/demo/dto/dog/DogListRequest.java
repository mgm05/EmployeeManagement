package com.example.demo.dto.dog;

import java.util.List;

import lombok.Data;

/**
 * 犬一覧リクエストDto.
 */
@Data
public class DogListRequest {
	/** 犬ID. */
	private String dogId;
	/** JKC登録番号. */
	private String jkcNo;
	/** 犬種グループ. */
	private List<String> dogGroup;
	/** 犬種サイズ. */
	private String dogSizeCode;
	/** 犬種. */
	private String dogType;
	/** 性別. */
	private String sex;
	/** 生年月日From. */
	private String birthdayFrom;
	/** 生年月日To. */
	private String birthdayTo;
	/** 成約日From. */
	private String contractDateFrom;
	/** 成約日To. */
	private String contractDateTo;
	/** 仕入日From. */
	private String purchaseDateFrom;
	/** 仕入日To. */
	private String purchaseDateTo;

}
