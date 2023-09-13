package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.PurchaseEntity;

/**
 * 仕入れMapper.
 */
@Mapper
public interface PurchaseMapper {
    /** 登録.
     * @param purchaseEntity PurchaseEntity
     * @return int*/
    int insert(PurchaseEntity purchaseEntity);
}
