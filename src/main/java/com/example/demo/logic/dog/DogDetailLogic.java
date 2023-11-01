package com.example.demo.logic.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.service.dog.CashFlowService;
import com.example.demo.service.dog.DogService;
import com.example.demo.service.dog.PurchaseService;

/**
 * 犬詳細ロジック.
 */
@Component
public class DogDetailLogic {
    /**犬サービス.*/
    @Autowired
    DogService dogService;
    /**仕入れサービス.*/
    @Autowired
    PurchaseService purchaseService;
    /**入出金Eサービス.*/
    @Autowired
    CashFlowService cashFlowService;
    
    /**
     * 犬情報取得.
     * @param dogId Integer
     * @return 犬情報
     */
    public DogEntity createDogDetail(Integer dogId) {
        return dogService.selectDogById(dogId);
    }

    /**
     * 仕入れ情報取得.
     * @param dogId Integer
     * @return 仕入れ情報
     */
    public PurchaseEntity createDogDetailPurchase(Integer dogId) {
        return purchaseService.selectPurchaseByDogId(dogId);
    }

    /**
     * 入出金情報取得.
     * @param dogId Integer
     * @return 仕入れ情報
     */
    public List<CashFlowEntity> createDogDetailCashFlow(Integer dogId) {
        return cashFlowService.selectByDogId(dogId);
    }




}
;