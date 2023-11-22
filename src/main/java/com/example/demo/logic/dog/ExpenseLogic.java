package com.example.demo.logic.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.dto.dog.ExpenseRequest;
import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.service.dog.CashFlowService;
import com.example.demo.service.dog.ExpenseService;

/**
 * 経費ロジック.
 */
@Component
public class ExpenseLogic {
	/** 経費サービス. */
	@Autowired
	private ExpenseService expenseService;
	/** 入出金サービス. */
	@Autowired
	private CashFlowService cashFlowService;

	/**
	 * 経費リスト取得.
	 * 
	 * @param dogId Integer
	 * @return 経費リスト
	 */
	public List<ExpenseEntity> createExpenseList(Integer dogId) {
		return expenseService.selectByDogId(dogId);
	}

	public void regist(ExpenseRequest req, Integer dogId, String loginId) {
		ExpenseEntity expenseEntity = createExpenseEntity(req, loginId);
		expenseService.insert(expenseEntity);
		CashFlowEntity cashFlowEntity = createCashFlowEntity(req, loginId, expenseEntity.getExpenseId());
		cashFlowService.insert(cashFlowEntity);
	}

	/**
	 * CashFlowEntity生成.
	 * 
	 * @param req       ExpenseRequest
	 * @param loginId
	 * @param dogId     Integer
	 * @param expenseId Integer
	 * @return entity
	 */
	private CashFlowEntity createCashFlowEntity(ExpenseRequest req, String loginId, Integer expenseId) {
		CashFlowEntity entity = new CashFlowEntity();
		entity.setDogId(req.getDogId());
		entity.setExpenseId(expenseId);
		entity.setCashFlowType(req.getCashFlowType());
		entity.setPrice(req.getQuotationYen());
		entity.setCashFlowDate(CommonUtils.parseHyphenDate(req.getPaymentDate()).orElseThrow());
		entity.setCreateUserId(loginId);
		entity.setUpdateUserId(loginId);
		return entity;
	}

	/**
	 * ExpenseEntity生成.
	 * 
	 * @param req     ExpenseRequest
	 * @param loginId
	 * @param dogId   Integer
	 * @return entity
	 */
	private ExpenseEntity createExpenseEntity(ExpenseRequest req, String loginId) {
		ExpenseEntity entity = new ExpenseEntity();
		entity.setDogId(req.getDogId());
		entity.setOccurrenceType(req.getOccurrenceType());
		entity.setCashFlowType(req.getCashFlowType());
		entity.setExpenseType(req.getExpenseType());
		entity.setInfo(req.getInfo());
		entity.setQuotationYen(req.getQuotationYen());
		entity.setCloseYen(req.getCloseYen());
		entity.setPaymentDate(CommonUtils.parseHyphenDate(req.getPaymentDate()).orElseThrow());
		entity.setPurchaseDate(CommonUtils.parseHyphenDate(req.getPurchaseDate()).orElse(null));
		entity.setCreateUserId(loginId);
		entity.setUpdateUserId(loginId);
		return entity;
	}
}
