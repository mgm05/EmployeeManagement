package com.example.demo.service.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.mapper.PurchaseMapper;

/**
 * 仕入れサービス.
 */
@Service
@Transactional
public class PurchaseService {
	/** 仕入れマッパー. */
	@Autowired
	PurchaseMapper purchaseMapper;

	/**
	 * 登録.
	 * 
	 * @param purchaseEntity PurchaseEntity
	 * @return purchaseMapper.insert(purchaseEntity)
	 */
	public int insert(PurchaseEntity purchaseEntity) {
		return purchaseMapper.insert(purchaseEntity);
	}

	/**
	 * dogIdを元に仕入れ情報を取得.
	 * 
	 * @param dogId Integer
	 * @return purchaseMapper.selectPurchaseByDogId(dogId)
	 */
	public PurchaseEntity selectPurchaseByDogId(String dogId) {
		return purchaseMapper.selectPurchaseByDogId(dogId);
	}

}
