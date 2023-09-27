package com.example.demo.controller.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.ExpenseType;
import com.example.demo.constEnum.OccurrenceType;
import com.example.demo.dto.dog.ExpenseRequest;
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
    ExpenseLogic logic;
    
    /**
     * 経費初期表示.
     * @param dogId String
     * @param model Model
     * @return dogList
     */
    @GetMapping("/expense")
    public String index(Integer dogId, Model model) {
        List<ExpenseEntity> expenseList = logic.createExpenseList(dogId);
        model.addAttribute("expenseList", expenseList);
        model.addAttribute("occurrenceTypeEnum", OccurrenceType.values());
        model.addAttribute("expenseTypeEnum", ExpenseType.values());
        model.addAttribute("cashFlowTypeEnum", CashFlowType.values());
        return "expense";
    }
    
    @PostMapping("/expense/regist")
    public String regist(ExpenseRequest req, Integer dogId) {
        logic.regist(req, dogId);
        return "expense";
    }

}