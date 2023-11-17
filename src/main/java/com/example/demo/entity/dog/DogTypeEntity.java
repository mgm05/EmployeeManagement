package com.example.demo.entity.dog;

import lombok.Data;

/**
 * 犬種Entity.
 */
@Data
public class DogTypeEntity {
	/** 犬種コード. */
	private Integer dogTypeCode;
	/** 犬種サイズ. */
	private String dogSize;
	/** 犬種グループ. */
	private String dogGroup;
	/** 犬種名. */
	private String dogTypeNm;
}
