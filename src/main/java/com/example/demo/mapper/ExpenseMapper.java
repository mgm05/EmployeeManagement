package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.ExpenseEntity;

/**
 * 経費Mapper.
 */
@Mapper
public interface ExpenseMapper {
	/**
	 * 登録.
	 * 
	 * @param expenseEntity ExpenseEntity
	 * @return int
	 */
	int insert(ExpenseEntity expenseEntity);

	/**
	 * dogIdで経費情報取得.
	 * 
	 * @param dogId Integer
	 * @return 経費情報
	 */
	List<ExpenseEntity> selectByDogId(Integer dogId);

	/**
	 * 経費ID,キャンセルフラグ,確定フラグ,登録日時,ユーザーのみ登録.
	 * @param expenseEntity ExpenseEntity
	 * @return int 
	 */
	int insertId(ExpenseEntity expenseEntity) ;

	/**
	 * 更新.
	 * @param expenseEntity ExpenseEntity
	 * @return int
	 */
	int update(ExpenseEntity expenseEntity);
}
