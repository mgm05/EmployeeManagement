package com.example.demo.service.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.mapper.DogMapper;
import com.example.demo.mapper.ExpenseMapper;
import com.example.demo.mapper.PurchaseMapper;

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
     * 医療金額経費登録.
     * @param expenseEntity ExpenseEntity
     * @return expenseMapper.insertMedicalYen(ExpenseEntity)
     */
    public int insert(ExpenseEntity expenseEntity) {
        return expenseMapper.insert(expenseEntity);
    }
    
    /**
     * 最後に挿入されたexpenseId取得.
     * @return expenseMapper.selectLastExpenseId()
     */
    public int selectLastExpenseId() {
        return expenseMapper.selectLastExpenseId();
    }
}
