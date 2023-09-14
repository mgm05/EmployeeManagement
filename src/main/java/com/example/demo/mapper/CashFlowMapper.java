package com.example.demo.mapper;

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
}
