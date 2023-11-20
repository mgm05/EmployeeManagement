package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.mapper.CashFlowMapper;

/**
 * 入出金サービス.
 */
@Service
@Transactional
public class CashFlowService {
	/** 入出金マッパー. */
	@Autowired
	CashFlowMapper cashFlowMapper;

	/**
	 * 登録.
	 * 
	 * @param cashFlowEntity CashFlowEntity
	 * @return cashFlowMapper.insert(cashFlowEntity)
	 */
	public int insert(CashFlowEntity cashFlowEntity) {
		return cashFlowMapper.insert(cashFlowEntity);
	}

	/**
	 * dogIdで入出金情報取得.
	 * 
	 * @param dogId Integer
	 * @return cashFlowMapper.selectByDogId(dogId)
	 */
	public List<CashFlowEntity> selectByDogId(String dogId) {
		return cashFlowMapper.selectByDogId(dogId);
	}

}
