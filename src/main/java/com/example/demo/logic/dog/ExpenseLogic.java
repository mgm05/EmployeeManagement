package com.example.demo.logic.dog;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.Info;
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
    ExpenseService expenseService;
    /** 入出金サービス. */
    @Autowired
    CashFlowService cashFlowService;
    
    /**
     * 経費リスト取得.
     * @param dogId Integer
     * @return 経費リスト
     */
    public List<ExpenseEntity> createExpenseList(Integer dogId) {
        return expenseService.selectByDogId(dogId);
    }

    
    public void regist(ExpenseRequest req, Integer dogId) {
        ExpenseEntity expenseEntity = createExpenseEntity(req, dogId);
        expenseService.insert(expenseEntity);
        CashFlowEntity cashFlowEntity = createCashFlowEntity(req, dogId, expenseService.selectLastExpenseId());
        cashFlowService.insert(cashFlowEntity);
    }

    /**
     * CashFlowEntity生成.
     * @param req ExpenseRequest
     * @param dogId Integer
     * @param expenseId Integer
     * @return entity
     */
    private CashFlowEntity createCashFlowEntity(ExpenseRequest req, Integer dogId, Integer expenseId) {
        CashFlowEntity entity = new CashFlowEntity();
        entity.setDogId(dogId);
        entity.setExpenseId(expenseId);
        entity.setCashFlowType(req.getCashFlowType());
        entity.setPrice(req.getQuotationYen());
        entity.setCashFlowDate(CommonUtils.parseSlashDate(req.getPaymentDate()));
        return entity;
    }

    /**
     * ExpenseEntity生成.
     * @param req ExpenseRequest
     * @param dogId Integer
     * @return entity
     */
    private ExpenseEntity createExpenseEntity(ExpenseRequest req, Integer dogId) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setDogId(dogId);
        entity.setOccurrenceType(req.getOccurrenceType());
        entity.setCashFlowType(req.getCashFlowType());
        entity.setExpenseType(req.getExpenseType());
        entity.setInfo(req.getInfo());
        entity.setQuotationYen(req.getQuotationYen());
        entity.setCloseYen(req.getCloseYen());
        entity.setPaymentDate(CommonUtils.parseSlashDate(req.getPaymentDate()));
        if (StringUtils.isNotEmpty(req.getArrivalDate())) {
            entity.setArrivalDate(CommonUtils.parseSlashDate(req.getArrivalDate()));
        }
        return entity;
    }
}
