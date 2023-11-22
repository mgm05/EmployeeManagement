package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.DogSize;
import com.example.demo.constEnum.ExpenseType;
import com.example.demo.constEnum.OccurrenceType;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogType;
import com.example.demo.dto.dog.PuchaseRequest;
import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.service.dog.CashFlowService;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogService;
import com.example.demo.service.dog.DogTypeService;
import com.example.demo.service.dog.ExpenseService;
import com.example.demo.service.dog.PurchaseService;

/**
 * 仕入れロジック.
 */
@Component
public class PurchaseLogic {
	/** 犬種グループサービス. */
	@Autowired
	private DogGroupService dogGroupService;
	/** 犬サービス. */
	@Autowired
	private DogService dogService;
	/** 仕入れサービス. */
	@Autowired
	private PurchaseService purchaseService;
	/** 犬種サービス. */
	@Autowired
	private DogTypeService dogTypeService;
	/** 経費サービス. */
	@Autowired
	private ExpenseService expenseService;
	/** 入出金サービス. */
	@Autowired
	private CashFlowService cashFlowService;

	/**
	 * 犬種取得.
	 * 
	 * @return dogTypeList
	 */
	public List<DogType> createDogTypeList() {
		List<DogTypeEntity> entities = dogTypeService.selectDogType();
		List<DogType> dogTypeList = new ArrayList<>();

		for (DogTypeEntity entity : entities) {
			DogType dogType = new DogType();
			dogType.setDogTypeCode(entity.getDogTypeCode());
			dogType.setDogTypeNm(entity.getDogTypeNm());
			dogTypeList.add(dogType);
		}
		return dogTypeList;
	}

	/**
	 * 登録.
	 * 
	 * @param puchaseReq PuchaseRequest
	 * @param userId     String
	 */
	public void regist(PuchaseRequest puchaseReq, String userId) {
		// 犬登録
		DogEntity dogEntity = createDogEntity(puchaseReq, userId);
		dogService.insert(dogEntity);

		// dogId取得
		int dogId = dogEntity.getDogId();

		// 仕入れ登録
		PurchaseEntity purchaseEntity = createPuchaseEntity(puchaseReq, userId, dogEntity.getDogId());
		purchaseService.insert(purchaseEntity);

		// 仕入れ価格入出金登録
		CashFlowEntity cashFlowEntity = createDogCashFlowEntity(puchaseReq, userId, dogEntity.getDogId());
		cashFlowService.insert(cashFlowEntity);

		// 医療費登録 経費、入出金
		if (StringUtils.isNotEmpty(puchaseReq.getMedicalYen())) {
			ExpenseEntity expenseEntity = createExpenseEntity(puchaseReq, puchaseReq.getMedicalYen(), userId, dogId,
					ExpenseType.MEDICAL.getCode());
			expenseService.insert(expenseEntity);
			CashFlowEntity expenseCashFlowEntity = createExpenseCashFlowEntity(puchaseReq, puchaseReq.getMedicalYen(),
					userId, dogId, expenseEntity.getExpenseId());
			cashFlowService.insert(expenseCashFlowEntity);
		}

		// 輸送費登録 経費、入出金
		if (StringUtils.isNotEmpty(puchaseReq.getTransportYen())) {
			ExpenseEntity expenseEntity = createExpenseEntity(puchaseReq, puchaseReq.getTransportYen(), userId, dogId,
					ExpenseType.EXPENSE.getCode());
			expenseService.insert(expenseEntity);
			CashFlowEntity expenseCashFlowEntity = createExpenseCashFlowEntity(puchaseReq, puchaseReq.getTransportYen(),
					userId, dogId, expenseEntity.getExpenseId());
			cashFlowService.insert(expenseCashFlowEntity);
		}

		System.out.println("a");
	}

	/**
	 * PuchaseEntity生成.
	 * 
	 * @param puchaseReq PuchaseRequest
	 * @param userId     String
	 * @param dogId      int
	 * @return entity
	 */
	private PurchaseEntity createPuchaseEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
		PurchaseEntity entity = new PurchaseEntity();
		entity.setDogId(dogId);
		entity.setContractType(puchaseReq.getContractType());
		entity.setPurchaseUserId(userId);
		entity.setContractDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()).orElseThrow());
		entity.setPurchaseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()).orElse(null));
		entity.setContractYen(getContractYen(puchaseReq));
		entity.setPurchaseYen(Integer.parseInt(puchaseReq.getPurchaseYen()));
		entity.setMedicalYen(CommonUtils.parseStrNum(puchaseReq.getMedicalYen()).orElse(0));
		entity.setTransportYen(CommonUtils.parseStrNum(puchaseReq.getTransportYen()).orElse(0));
		entity.setPaymentExpectedDate(CommonUtils.parseHyphenDate(puchaseReq.getPaymentExpectedDate()).orElseThrow());
		entity.setPaymentDate(CommonUtils.parseHyphenDate(puchaseReq.getPaymentDate()).orElse(null));
		entity.setCreateUserId(userId);
		entity.setUpdateUserId(userId);
		return entity;
	}

	/**
	 * 契約金額を取得.
	 * 
	 * @param puchaseReq
	 * @return 契約金額
	 */
	private Integer getContractYen(PuchaseRequest puchaseReq) {
		int medicalYen = 0;
		int transportYen = 0;
		int purchaseYen = Integer.parseInt(puchaseReq.getPurchaseYen());
		if (StringUtils.isNotEmpty(puchaseReq.getMedicalYen())) {
			medicalYen = Integer.parseInt(puchaseReq.getMedicalYen());
		}
		if (StringUtils.isNotEmpty(puchaseReq.getTransportYen())) {
			transportYen = Integer.parseInt(puchaseReq.getTransportYen());
		}
		return medicalYen + transportYen + purchaseYen;
	}

	/**
	 * DogEntity生成.
	 * 
	 * @param puchaseReq PuchaseRequest
	 * @param userId     String
	 * @return entity
	 */
	private DogEntity createDogEntity(PuchaseRequest puchaseReq, String userId) {
		DogTypeEntity dogTypeEntity = createDogTypeEntity(puchaseReq.getDogCode());
		DogEntity entity = new DogEntity();
		entity.setJkcNo(puchaseReq.getJkcNo());
		entity.setDogCode(puchaseReq.getDogCode());
		entity.setDogGroupCode(dogTypeEntity.getDogGroup());
		entity.setDogGroupName(getDogGroupName(dogTypeEntity.getDogGroup()));
		entity.setDogSizeCode(dogTypeEntity.getDogSize());
		entity.setDogSizeName(getDogSizeName(dogTypeEntity.getDogSize()));
		entity.setDogName(dogTypeEntity.getDogTypeNm());
		entity.setSex(puchaseReq.getSex());
		entity.setBirthday(CommonUtils.parseHyphenDate(puchaseReq.getBirthday())
				.orElseThrow(() -> new IllegalArgumentException("barthDay is not")));
		entity.setCreateUserId(userId);
		entity.setUpdateUserId(userId);
		return entity;
	}

	/**
	 * 犬種コードから犬種名と犬種グループコードを取得.
	 * 
	 * @param dogCode
	 * @return 犬種名
	 */
	private DogTypeEntity createDogTypeEntity(String dogCode) {
		DogTypeEntity dogTypeEntity = dogTypeService.selectByDogTypeCode(dogCode);
		return dogTypeEntity;
	}

	/**
	 * 犬種グループコードから犬種グループ名を取得.
	 * 
	 * @param dogGroupCode String
	 * @return 犬種グループ名
	 */
	private String getDogGroupName(String dogGroupCode) {
		DogGroupEntity dogGroupEntity = dogGroupService.selectDogGroupNameByCode(dogGroupCode);
		return dogGroupEntity.getDogGroupName();
	}

	/**
	 * 犬種サイズコードから犬種サイズ名を取得.
	 * 
	 * @param dogSize
	 * @return 犬種サイズ名
	 */
	private String getDogSizeName(String dogSizeCode) {
		return DogSize.of(dogSizeCode).getVal();
	}

	/**
	 * ExpenseEntity生成.
	 * 
	 * @param puchaseReq  PuchaseRequest
	 * @param userId      String
	 * @param expense
	 * @param dogId       int
	 * @param expenseType
	 * @return entity
	 */
	private ExpenseEntity createExpenseEntity(PuchaseRequest puchaseReq, String expensePrice, String userId, int dogId,
			String expenseType) {
		ExpenseEntity entity = new ExpenseEntity();
		entity.setDogId(dogId);
		// entity.setCreateStoreCode();
		// entity.setCreateStoreName();
		entity.setOccurrenceType(OccurrenceType.PURCHASE.getCode());
		entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
		entity.setExpenseType(expenseType);
		entity.setQuotationYen(Integer.parseInt(expensePrice));
		entity.setCloseYen(Integer.parseInt(expensePrice));
		entity.setPaymentDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()).orElseThrow());
		entity.setPurchaseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()).orElseThrow());
		entity.setCreateUserId(userId);
		entity.setUpdateUserId(userId);
		return entity;
	}

	/**
	 * 犬本体価格登録CashFlowEntity生成.
	 * 
	 * @param puchaseReq PuchaseRequest
	 * @param userId     String
	 * @param dogId      int
	 * @return entity
	 */
	private CashFlowEntity createDogCashFlowEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
		CashFlowEntity entity = new CashFlowEntity();
		entity.setDogId(dogId);
		entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
		entity.setPrice(Integer.parseInt(puchaseReq.getPurchaseYen()));
		entity.setCashFlowDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()).orElseThrow());
		entity.setCloseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()).orElse(null));
		entity.setCreateUserId(userId);
		entity.setUpdateUserId(userId);
		return entity;
	}

	/**
	 * 経費登録時CashFlowEntity生成.
	 * 
	 * @param puchaseReq PuchaseRequest
	 * @param userId     String
	 * @param dogId      int
	 * @param expenseId  int
	 * @return entity
	 */
	private CashFlowEntity createExpenseCashFlowEntity(PuchaseRequest puchaseReq, String cashFlowPrice, String userId,
			int dogId, int expenseId) {
		CashFlowEntity entity = new CashFlowEntity();
		entity.setDogId(dogId);
		entity.setExpenseId(expenseId);
		entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
		entity.setPrice(Integer.parseInt(cashFlowPrice));
		entity.setCashFlowDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()).orElseThrow());
		entity.setCloseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()).orElse(null));
		entity.setCreateUserId(userId);
		entity.setUpdateUserId(userId);
		return entity;
	}
}
