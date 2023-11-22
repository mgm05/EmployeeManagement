package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.ContractType;
import com.example.demo.constEnum.DogSex;
import com.example.demo.dto.dog.CashFlow;
import com.example.demo.dto.dog.DogDetailResponse;
import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.service.dog.CashFlowService;
import com.example.demo.service.dog.DogService;
import com.example.demo.service.dog.PurchaseService;

/**
 * 犬詳細ロジック.
 */
@Component
public class DogDetailLogic {
	/** 犬サービス. */
	@Autowired
	private DogService dogService;
	/** 仕入れサービス. */
	@Autowired
	private PurchaseService purchaseService;
	/** 入出金サービス. */
	@Autowired
	private CashFlowService cashFlowService;


	/**
	 * レスポンス用Dto生成.
	 * 
	 * @param dogId String
	 * @return res
	 */
	public DogDetailResponse createDogDetailRes(String dogId) {
		DogDetailResponse res = new DogDetailResponse();
		DogEntity dogEntity = dogService.selectDogById(dogId);
		PurchaseEntity purchaseEntity = purchaseService.selectPurchaseByDogId(dogId);

		res.setCashFlowList(createCashFlowList(dogId));
		res.setDogId(dogId);
		res.setJkcNo(dogEntity.getJkcNo());
		res.setDogGroupName(dogEntity.getDogGroupName());
		res.setDogSizeName(dogEntity.getDogSizeName());
		res.setDogTypeName(dogEntity.getDogName());
		res.setSex(DogSex.of(dogEntity.getSex()).getVal());
		res.setBirthday(CommonUtils.formatDate(dogEntity.getBirthday()));
		res.setContractType(ContractType.of(purchaseEntity.getContractType()).getVal());
		res.setContractDate(CommonUtils.formatDate(purchaseEntity.getContractDate()));
		res.setPurchaseDate(CommonUtils.formatOptDate(purchaseEntity.getPurchaseDate()).orElse(null));
		res.setContractYen(String.format("%,d", purchaseEntity.getContractYen()));
		res.setPaymentExpectedDate(CommonUtils.formatDate(purchaseEntity.getPaymentExpectedDate()));
		res.setPaymentDate(CommonUtils.formatOptDate(purchaseEntity.getPaymentDate()).orElse(null));
		return res;
	}

	/**
	 * 入出金リスト生成.
	 * 
	 * @param dogId String
	 * @return cashFlowList
	 */
	private List<CashFlow> createCashFlowList(String dogId) {
		List<CashFlowEntity> cashFlowEntity = cashFlowService.selectByDogId(dogId);
		List<CashFlow> cashFlowList = new ArrayList<>();

		for (CashFlowEntity entity : cashFlowEntity) {
			CashFlow cashFlow = new CashFlow();
			cashFlow.setCashFlowId(entity.getCashFlowId());
			cashFlow.setExpenseId(entity.getExpenseId());
			cashFlow.setInfo(entity.getInfo());
			cashFlow.setCashFlowType(CashFlowType.of(entity.getCashFlowType()).getVal());
			cashFlow.setPrice(String.format("%,d", entity.getPrice()));
			cashFlow.setCashFlowDate(CommonUtils.formatDate(entity.getCashFlowDate()));
			cashFlow.setCloseDate(CommonUtils.formatOptDate(entity.getCloseDate()).orElse(null));
			cashFlowList.add(cashFlow);
		}
		return cashFlowList;
	}

};