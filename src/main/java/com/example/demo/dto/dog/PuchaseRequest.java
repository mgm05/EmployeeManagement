package com.example.demo.dto.dog;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.example.demo.CommonUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 仕入れリクエストDto.
 */
@Data
public class PuchaseRequest {
	/** JKC登録番号. */
	@Length(min = 10, max = 10, message = "JKC登録番号は10文字で入力して下さい。")
	@Pattern(regexp = "^[A-Z0-9]*$", message = "JKC登録番号は大文字英字、半角数字で入力して下さい。")
	private String jkcNo;
	/** 犬種グループ. */
	private String dogGroupCode;
	/** 犬種コード. */
	@NotBlank(message = "犬種を入力して下さい。")
	private String dogCode;
	/** 犬種名. */
	private String dogName;
	/** 性別. */
	@NotBlank(message = "性別を選択して下さい。")
	private String sex;
	/** 生年月日. */
	@NotBlank(message = "生年月日を入力して下さい。")
	private String birthday;

	/** 契約区分. */
	@NotBlank(message = "契約区分を選択して下さい。")
	private String contractType;
	/** 成約日. */
	@NotBlank(message = "成約日を入力して下さい。")
	private String contractDate;
	/** 仕入日. */
	private String purchaseDate;
	/** 契約金額. */
	private String contractYen;
	/** 仕入価格. */
	@NotBlank(message = "仕入価格を入力して下さい。")
	@Length(max = 7, message = "仕入価格は7桁以内で入力して下さい。")
	@Pattern(regexp = "^[0-9]*$", message = "仕入価格は半角数字で入力して下さい。")
	private String purchaseYen;
	/** 医療金額. */
	@Length(max = 7, message = "医療金額は7桁以内で入力して下さい。")
	@Pattern(regexp = "^[0-9]*$", message = "医療金額は半角数字で入力して下さい。")
	private String medicalYen;
	/** 輸送費. */
	@Length(max = 7, message = "輸送費は7桁以内で入力して下さい。")
	@Pattern(regexp = "^[0-9]*$", message = "輸送費は半角数字で入力して下さい。")
	private String transportYen;
	/** 支払予定日. */
	@NotBlank(message = "支払予定日を入力して下さい。")
	private String paymentExpectedDate;
	/** 支払日. */
	private String paymentDate;

	@AssertTrue(message = "仕入日は成約日と同日以降を入力して下さい。")
	public boolean isPurchaseDateValid() {
		Date parsePurchaseDate = CommonUtils.parseHyphenDate(purchaseDate).orElse(null);
		Date parseContractDate = CommonUtils.parseHyphenDate(contractDate).orElse(null);
		if (parsePurchaseDate == null) {
			return true;
		}
		return parsePurchaseDate.compareTo(parseContractDate) >= 0 ? true : false;
	}

	@AssertTrue(message = "支払日は支払予定日より同日以降を入力して下さい。")
	public boolean isPaymentDateValid() {
		Date parsePaymentDate = CommonUtils.parseHyphenDate(paymentDate).orElse(null);
		Date parsePaymentExpectedDate = CommonUtils.parseHyphenDate(paymentExpectedDate).orElse(null);
		if (parsePaymentDate == null) {
			return true;
		}
		return parsePaymentDate.compareTo(parsePaymentExpectedDate) >= 0 ? true : false;
	}
}
