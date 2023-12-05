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
import com.example.demo.dto.dog.ExpenseForm;
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
	 * @param dogId Integer
	 * @return expenseResList
	 */
	public List<ExpenseResponse> createExpenseResList(Integer dogId) {
		List<ExpenseEntity> expenseEntityList = expenseService.selectByDogId(dogId);
		List<ExpenseResponse> expenseResList = new ArrayList<>();
		for(ExpenseEntity expenseEntity:expenseEntityList) {
			ExpenseResponse expenseRes = new ExpenseResponse();
			expenseRes.setExpenseId(expenseEntity.getExpenseId());
			//dogID	消す？
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
	 * 登録・更新.
	 * @param dogId Integer
	 * @param expenseForm ExpenseForm
	 * @param loginId String
	 */
	public void regist(Integer dogId, ExpenseForm expenseForm, String loginId) {
		List<ExpenseRequest> expenseRequestList =  expenseForm.getExpenseRequestList();
		boolean flg = true;
		for(ExpenseRequest req:expenseRequestList) {
			//ループ1回目はテンプレートのため無視
			if(flg) {
				flg = false;
				continue;
			}
			
			ExpenseEntity expenseEntity = createExpenseEntity(dogId, req, loginId);
			//経費テーブル登録と更新
			registUpdateExpense(expenseEntity);
			
			CashFlowEntity cashFlowEntity = createCashFlowEntity(dogId, req, loginId, expenseEntity.getExpenseId());
			//入出金テーブル登録と更新
			registUpdateCashFlow(cashFlowEntity);
			
		}
	}

	/**
	 * 経費登録更新.
	 * @param expenseEntity ExpenseEntity
	 */
	private void registUpdateExpense(ExpenseEntity expenseEntity) {
		if(expenseEntity.getExpenseId() == null) { //経費IDがなければ登録
			expenseService.insertId(expenseEntity);
		}
		expenseService.update(expenseEntity);
		
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
	 * @param dogId 
	 * 
	 * @param req       ExpenseRequest
	 * @param loginId
	 * @param expenseId 
	 * @param dogId     Integer
	 * @return entity
	 */
	private CashFlowEntity createCashFlowEntity(Integer dogId, ExpenseRequest req, String loginId, Integer expenseId) {
		CashFlowEntity entity = new CashFlowEntity();
		entity.setExpenseId(expenseId);
		//確定フラグがオフのデータのみ経費ID以外セット。(オフのみ更新のため)
		if("0".equals(req.getFixFlag())) {
			entity.setDogId(dogId);
			entity.setCashFlowType(req.getCashFlowType());
			entity.setPrice(req.getQuotationYen());
			entity.setCashFlowDate(CommonUtils.parseHyphenDate(req.getPaymentDate()).orElseThrow());
			entity.setCreateUserId(loginId);
			entity.setUpdateUserId(loginId);
			return entity;
		}
		return entity;
	}

	/**
	 * ExpenseEntity生成.
	 * @param dogId 
	 * 
	 * @param req     ExpenseRequest
	 * @param loginId
	 * @param dogId   Integer
	 * @return entity
	 */
	private ExpenseEntity createExpenseEntity(Integer dogId, ExpenseRequest req, String loginId) {
		ExpenseEntity entity = new ExpenseEntity();
		entity.setExpenseId(req.getExpenseId());
		//確定フラグがオフのデータのみセット。(オフのみ更新のため)
		if("0".equals(req.getFixFlag())) {
			entity.setDogId(dogId);
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
		return entity;
	}
}
