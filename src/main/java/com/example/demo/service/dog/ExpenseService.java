package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.mapper.ExpenseMapper;

/**
 * 経費サービス.
 */
@Service
@Transactional
public class ExpenseService {
	/** 経費マッパー. */
	@Autowired
	ExpenseMapper expenseMapper;

	/**
	 * 登録.
	 * 
	 * @param expenseEntity ExpenseEntity
	 * @return expenseMapper.insert(ExpenseEntity)
	 */
	public int insert(ExpenseEntity expenseEntity) {
		return expenseMapper.insert(expenseEntity);
	}

	/**
	 * dogIdで経費情報取得.
	 * 
	 * @param dogId Integer
	 * @return expenseMapper.selectByDogId(dogId)
	 */
	public List<ExpenseEntity> selectByDogId(Integer dogId) {
		return expenseMapper.selectByDogId(dogId);
	}

	/**
	 * 経費ID,キャンセルフラグ,確定フラグ,登録日時,ユーザーのみ登録.
	 * @param expenseEntity ExpenseEntity
	 * @return int
	 */
	public int insertId(ExpenseEntity expenseEntity) {
		return expenseMapper.insertId(expenseEntity);
	}

	/**
	 * 更新.
	 * @param expenseEntity ExpenseEntity
	 * @return int
	 */
	public int update(ExpenseEntity expenseEntity) {
		return expenseMapper.update(expenseEntity);
	}

	/**
	 * 確定.
	 * @param fixExpenseEntity ExpenseEntity
	 * @return int
	 */
	public int updateFix(ExpenseEntity fixExpenseEntity) {
		return expenseMapper.updateFix(fixExpenseEntity);
	}

	public Integer selectCloseyen(Integer expenseId) {
		return expenseMapper.selectCloseyen(expenseId);
	}
}
