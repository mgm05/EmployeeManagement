package com.example.demo.dto.dog;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 経費リクエストフォーム.
 */
@Data
public class ExpenseReqForm {
	/**
	 * 経費リクエストリスト.
	 */
	@Valid
	@NotNull(message = "経費情報が入力されていません。")
	private List<ExpenseRequest> expenseRequestList;
}
