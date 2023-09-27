package com.example.demo.mapper;

import java.util.List;

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
    
    /** dogIdで経費情報取得.
     * @param dogId Integer
     * @return 経費情報
     * */
    List<ExpenseEntity> selectByDogId(Integer dogId);
}
