package com.example.demo.dto.dog;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.example.demo.CommonUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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
	@NotBlank(message = "発生区分を選択して下さい。")
	private String occurrenceType;
	/** 入出金区分. */
	@NotBlank(message = "入出金区分を選択して下さい。")
	private String cashFlowType;
	/** 経費種別. */
	@NotBlank(message = "経費種別を選択して下さい。")
	private String expenseType;
	/** 内容. */
	@Length(max = 255, message = "内容は255文字以内で入力して下さい。")
	private String info;
	/** 見積金額. */
	@NotNull(message = "見積金額を入力して下さい。")
	@PositiveOrZero(message = "見積金額は半角数字で入力して下さい。")
	@Max(value = 9999999, message = "見積金額は7桁以内で入力して下さい。")
	private Integer quotationYen;
	/** 確定金額. */
	@PositiveOrZero(message = "確定金額は半角数字で入力して下さい。")
	@Max(value = 9999999, message = "確定金額は7桁以内で入力して下さい。")
	private Integer closeYen;
	/** 発生日. */
	@NotBlank(message = "発生日を入力して下さい。")
	private String paymentDate;
	/** 仕入日. */
	private String purchaseDate;
	/** 確定フラグ. */
	private String fixFlag;
	
	@AssertTrue(message = "仕入日は発生日の同日以降を入力して下さい。")
	public boolean isPurchaseDateValid() {
		Date parsePaymentDate = CommonUtils.parseHyphenDate(paymentDate).orElse(null);
		Date parsePurchaseDate = CommonUtils.parseHyphenDate(purchaseDate).orElse(null);
		if(parsePaymentDate == null || parsePurchaseDate == null) {
			return true;
		}
		return parsePurchaseDate.compareTo(parsePaymentDate) >= 0 ? true : false;
	}
}
