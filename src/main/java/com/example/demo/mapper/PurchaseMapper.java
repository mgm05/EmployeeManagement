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
    
    /**
     * dogIdを元に仕入れ情報を取得.
     * @param dogId Integer
     * @return 仕入れ情報
     */
    PurchaseEntity selectPurchaseByDogId(Integer dogId);
}
