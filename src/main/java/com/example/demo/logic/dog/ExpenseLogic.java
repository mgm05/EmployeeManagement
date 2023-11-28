package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.ExpenseType;
import com.example.demo.constEnum.OccurrenceType;
import com.example.demo.dto.dog.ExpenseRequest;
import com.example.demo.dto.dog.ExpenseResponse;
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
	
	
	/**
	 * 経費リスト取得.
	 * @param dogId Integer
	 * @return expenseResList
	 */
	public List<ExpenseResponse> createExpenseResList(Integer dogId) {
		List<ExpenseEntity> expenseEntityList = expenseService.selectByDogId(dogId);
		List<ExpenseResponse> expenseResList = new ArrayList<>();
		for(ExpenseEntity expenseEntity:expenseEntityList) {
			ExpenseResponse expenseRes = new ExpenseResponse();
			expenseRes.setExpenseId(expenseEntity.getExpenseId());
			expenseRes.setDogId(dogId);
			expenseRes.setStatus(getStatus(expenseEntity.getFixFlag()));
			expenseRes.setOccurrenceType(getOccurrenceType(expenseEntity));
			expenseRes.setCashFlowType(getCashFlowType(expenseEntity));
			expenseRes.setExpenseType(getExpenseType(expenseEntity));
			expenseRes.setInfo(expenseEntity.getInfo());
			expenseRes.setQuotationYen(expenseEntity.getQuotationYen());
			expenseRes.setCloseYen(expenseEntity.getCloseYen());
			expenseRes.setPaymentDate(getPaymentDate(expenseEntity));
			expenseRes.setPurchaseDate(getPurchaseDate(expenseEntity));
			expenseRes.setFixFlag(expenseEntity.getFixFlag());
			expenseResList.add(expenseRes);
		}
		return expenseResList;
	}

	/**
	 * 仕入日取得.
	 * 確定フラグオンでyyyy/MM/dd、オフでyyyy-MM-dd形式.
	 * @param expenseEntity ExpenseEntity
	 * @return 仕入日 yyyy/MM/dd or yyyy-MM-dd
	 */
	private String getPurchaseDate(ExpenseEntity expenseEntity) {
		if("1". equals(expenseEntity.getFixFlag())) {
			return CommonUtils.formatOptDate(expenseEntity.getPurchaseDate(), "yyyy/MM/dd").orElse(null);
		}
		return CommonUtils.formatOptDate(expenseEntity.getPurchaseDate(), "yyyy-MM-dd").orElse(null);
	}


	/**
	 * 	発生日取得.
	 * 確定フラグオンでyyyy/MM/dd、オフでyyyy-MM-dd形式.
	 * @param expenseEntity ExpenseEntity
	 * @return 発生日 yyyy/MM/dd or yyyy-MM-dd
	 */
	private String getPaymentDate(ExpenseEntity expenseEntity) {
		if("1". equals(expenseEntity.getFixFlag())) {
			return CommonUtils.formatOptDate(expenseEntity.getPaymentDate(), "yyyy/MM/dd").orElseThrow();
		}
		return CommonUtils.formatOptDate(expenseEntity.getPaymentDate(), "yyyy-MM-dd").orElseThrow();
	}


	/**
	 * 経費種別取得.
	 * 確定フラグオンで経費種別名、オフで経費種別コード.
	 * @param expenseEntity ExpenseEntity
	 * @return 経費種別名 or 経費種別コード
	 */
	private String getExpenseType(ExpenseEntity expenseEntity) {
		if("1". equals(expenseEntity.getFixFlag())) {
			return ExpenseType.of(expenseEntity.getExpenseType()).getVal();
		}
		return expenseEntity.getExpenseType();
	}

	/**
	 * 入出金区分取得.
	 * 確定フラグオンで入出金区分名、オフで入出金区分コード.
	 * @param expenseEntity ExpenseEntity
	 * @return 入出金区分名 or 入出金区分コード
	 */
	private String getCashFlowType(ExpenseEntity expenseEntity) {
		if("1". equals(expenseEntity.getFixFlag())) {
			return CashFlowType.of(expenseEntity.getCashFlowType()).getVal();
		}
		return expenseEntity.getCashFlowType();
	}


	/**
	 * 発生区分取得.
	 * 確定フラグオンで発生区分名、オフで発生区分コード.
	 * @param expenseEntity ExpenseEntity
	 * @return 発生区分名 or 発生区分コード
	 */
	private String getOccurrenceType(ExpenseEntity expenseEntity) {
		if("1". equals(expenseEntity.getFixFlag())) {
			return OccurrenceType.of(expenseEntity.getOccurrenceType()).getVal();
		}
		return expenseEntity.getOccurrenceType();
	}


	/**
	 * ステータス取得.
	 * @param fixFlag String
	 * @return ステータス
	 */
	private String getStatus(String fixFlag) {
		if("1".equals(fixFlag)) {
			return "確定済";
		}
		return "未確定";
	}


	/**
	 * 登録(更新)
	 * @param req
	 * @param loginId
	 */
	public void regist(ExpenseRequest req, String loginId) {
		//経費テーブル登録と更新
		
		if(req.getExpenseId() == null) {
			expenseService.insertId(loginId);
		}
		ExpenseEntity expenseEntity = createExpenseEntity(req, loginId);
		expenseService.update(expenseEntity);
		
		//入出金テーブル登録と更新
		CashFlowEntity cashFlowEntity = createCashFlowEntity(req, loginId, expenseEntity.getExpenseId());
		registUpdateCashFlow(cashFlowEntity);
	}
	
	

	/**
	 * 入出金登録更新.
	 * @param cashFlowEntity CashFlowEntity
	 */
	private void registUpdateCashFlow(CashFlowEntity cashFlowEntity) {
		// 入出金テーブルに経費IDが登録されていなければ登録
		Integer expenseId = cashFlowService.selectByExpenseId(cashFlowEntity.getExpenseId());
		if(expenseId == null) {
			cashFlowService.insertId(cashFlowEntity);
		}
		cashFlowService.update(cashFlowEntity);
	}


	/**
	 * CashFlowEntity生成.
	 * 
	 * @param req       ExpenseRequest
	 * @param loginId
	 * @param expenseId 
	 * @param dogId     Integer
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
		entity.setExpenseId(req.getExpenseId());
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
