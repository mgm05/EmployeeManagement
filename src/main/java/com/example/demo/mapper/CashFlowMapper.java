package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.CashFlowEntity;

/**
 * 入出金Mapper.
 */
@Mapper
public interface CashFlowMapper {
    /** 登録.
     * @param cashFlowEntity CashFlowEntity
     * @return int*/
    int insert(CashFlowEntity cashFlowEntity);
    
    /** dogIdで入出金情報取得.
     * @param dogId Integer
     *  @return 入出金情報*/
    List<CashFlowEntity> selectByDogId(String dogId);
    
}
