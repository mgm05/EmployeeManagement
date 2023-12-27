package com.example.demo.controller.dog;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.ExpenseType;
import com.example.demo.constEnum.OccurrenceType;
import com.example.demo.dto.dog.ExpenseReqForm;
import com.example.demo.dto.dog.ExpenseRequest;
import com.example.demo.dto.dog.ExpenseResForm;
import com.example.demo.dto.dog.ExpenseResponse;
import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.logic.dog.ExpenseLogic;
import com.example.demo.session.SessionUser;

/**
 * 経費コントローラー.
 */

@Controller
public class ExpenseController {
	/** セッション. */
	@Autowired
	private SessionUser session;
	/** 経費ロジック. */
	@Autowired
	private ExpenseLogic logic;

	/**
	 * 経費初期表示.
	 * @param expenseForm 
	 * 
	 * @param dogId String
	 * @param model Model
	 * @return dogList
	 */
	@GetMapping("/expense")
	public String index(ExpenseReqForm expenseReqForm, Integer dogId, Model model) {
		// セッションが切れていたらログイン画面へ遷移
		if (session.getLoginId() == null) {
			return "redirect:/login";
		}
		
		commonUtils(model, dogId);
		return "expense";
	}

	/**
	 * 登録(更新).
	 * @param req ExpenseRequest
	 * @param dogId Integer
	 * @param result BindingResult
	 * @param model Model
	 * @return expense
	 */
	@PostMapping("/expense/regist")
	public String regist(@Validated  ExpenseReqForm expenseReqForm, BindingResult result, @RequestParam Integer dogId, Model model) {
		
		if(result.hasErrors()) {
			commonUtils(model, dogId);
			model.addAttribute("expenseReqForm", expenseReqForm);
			return "expense";
		}
		//登録(更新)
		logic.regist(dogId, expenseReqForm,  session.getLoginId());
		return "redirect:/expense" + "?dogId=" +dogId;
	}
	
	/**
	 * 確定.
	 * @param expenseId Integer
	 * @return
	 */
	@PostMapping("/expense/fix")
	public String fix(@RequestParam Integer expenseId, Integer dogId) {
		logic.fix(expenseId, session.getLoginId());
		return "redirect:/expense" + "?dogId=" +dogId;
	}
	
	/**
	 * 共通部品.    
	 * @param model Model
	 * @return model
	 */
	public Model commonUtils(Model model, Integer dogId) {
		ExpenseResForm expenseResForm = new ExpenseResForm();
		expenseResForm.setExpenseResponseList(logic.createExpenseResList(dogId));
		
		model.addAttribute("expenseResForm", expenseResForm);
		model.addAttribute("dogId", dogId);
		model.addAttribute("occurrenceTypeEnum", OccurrenceType.values());
		model.addAttribute("expenseTypeEnum", ExpenseType.values());
		model.addAttribute("cashFlowTypeEnum", CashFlowType.values());
		return model;
	}
}
