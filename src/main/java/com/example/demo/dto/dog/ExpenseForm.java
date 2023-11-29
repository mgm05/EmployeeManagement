package com.example.demo.dto.dog;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * 経費FormDto.
 */
@Data
public class ExpenseForm {
	/**
	 * 経費リクエスト.
	 */
	@Valid
	private List<ExpenseRequest> expenseRequestList;
}
