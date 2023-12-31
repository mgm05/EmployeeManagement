package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * 犬Entity.
 */
@Data
public class DogEntity {
	/** 犬ID. */
	private int dogId;
	/** JKC登録番号. */
	private String jkcNo;
	/** 犬種グループコード. */
	private String dogGroupCode;
	/** 犬種グループ名. */
	private String dogGroupName;
	/** 犬種サイズコード. */
	private String dogSizeCode;
	/** 犬種サイズ名. */
	private String dogSizeName;
	/** 犬種コード. */
	private String dogCode;
	/** 犬種名. */
	private String dogName;
	/** 性別. */
	private String sex;
	/** 生年月日. */
	private Date birthday;
	/** 死去日. */
	private Date deadDate;
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
