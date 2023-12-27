package com.example.demo.dto.dog;

import java.util.List;

import lombok.Data;

/**
 * 経費レスポンスフォーム.
 */
@Data
public class ExpenseResForm {
	/**
	 * 経費レスポンスリスト.
	 */
	private List<ExpenseResponse> expenseResponseList;
}
