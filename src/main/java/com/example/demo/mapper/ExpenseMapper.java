package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.ExpenseEntity;

/**
 * 経費Mapper.
 */
@Mapper
public interface ExpenseMapper {
    /** 医療金額経費登録.
     * @param expenseEntity ExpenseEntity
     * @return int */
    int insert(ExpenseEntity expenseEntity);
    
    /** 最後に挿入されたexpenseId取得. 
     * @return int*/
    int selectLastExpenseId();
}
