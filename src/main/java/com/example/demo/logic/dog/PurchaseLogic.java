package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.CashFlowType;
import com.example.demo.constEnum.ExpenseType;
import com.example.demo.constEnum.Info;
import com.example.demo.constEnum.OccurrenceType;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogType;
import com.example.demo.dto.dog.PuchaseRequest;
import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.entity.dog.ExpenseEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.mapper.DogTypeMapper;
import com.example.demo.service.dog.CashFlowService;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogService;
import com.example.demo.service.dog.DogTypeService;
import com.example.demo.service.dog.ExpenseService;
import com.example.demo.service.dog.PurchaseService;

/**
 * 仕入れロジック.
 */
@Component
public class PurchaseLogic {
    /** 犬種グループサービス. */
    @Autowired
    DogGroupService dogGroupService;
    /** 犬サービス.*/
    @Autowired
    DogService dogService;
    /** 仕入れサービス. */
    @Autowired
    PurchaseService purchaseService;
    /** 犬種サービス. */
    @Autowired
    DogTypeService dogTypeService;
    /** 経費サービス. */
    @Autowired
    ExpenseService expenseService;
    /** 入出金サービス. */
    @Autowired
    CashFlowService cashFlowService;
    
    /**
     * 犬種グループ全件取得.
     * @return dogGroupList
     */
    public List<DogGroup> createDogGroupList(){
        List<DogGroupEntity> entities = dogGroupService.selectAll();
        List<DogGroup> dogGroupList = new ArrayList<>();
        
        for (DogGroupEntity entity:entities) {
            DogGroup dogGroup = new DogGroup();
            dogGroup.setDogGroupCode(entity.getDogGroupCode());
            dogGroup.setDogGroupName(entity.getDogGroupName());
            dogGroupList.add(dogGroup);
        }
        return dogGroupList;
    }
    
    /**
     * 犬種取得.
     * @return dogTypeList
     */
    public List<DogType> createDogTypeList(){
        List<DogTypeEntity> entities = dogTypeService.selectDogType();
        List<DogType> dogTypeList = new ArrayList<>();
           
        for (DogTypeEntity entity :entities) {
            DogType dogType = new DogType();
            dogType.setDogTypeCode(entity.getDogTypeCode());
            dogType.setDogTypeNm(entity.getDogTypeNm());
            dogTypeList.add(dogType);
        }
        return dogTypeList;
    }
    
    /**
     * 登録.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     */
    public void regist(PuchaseRequest puchaseReq, String userId) {
        //犬登録
        DogEntity dogEntity = createDogEntity(puchaseReq, userId);
        dogService.insert(dogEntity);
        
        //dogId取得
        int dogId = dogService.getLastDogId();
        
        //仕入れ登録
        PurchaseEntity purchaseEntity = createPuchaseEntity(puchaseReq, userId, dogId);
        purchaseService.insert(purchaseEntity);
        
        //仕入れ価格入出金登録
        CashFlowEntity cashFlowEntity = createDogCashFlowEntity(puchaseReq, userId, dogId);
        cashFlowService.insert(cashFlowEntity);
        
        //医療費登録 経費、入出金
        if (StringUtils.isNotEmpty(puchaseReq.getMedicalYen())){
            ExpenseEntity expenseEntity = createMedicalExpenseEntity(puchaseReq, userId, dogId);
            expenseService.insert(expenseEntity);
            CashFlowEntity medicalCashFlowEntity = createMedicalCashFlowEntity(puchaseReq, userId, dogId, expenseService.selectLastExpenseId());
            cashFlowService.insert(medicalCashFlowEntity);
        }
        
        //輸送費登録 経費、入出金
        if (StringUtils.isNotEmpty(puchaseReq.getTransportYen())) {
            ExpenseEntity expenseEntity = createTransportExpenseEntity(puchaseReq, userId, dogId);
            expenseService.insert(expenseEntity);
            CashFlowEntity transportCashFlowEntity = createTransportCashFlowEntity(puchaseReq, userId, dogId, expenseService.selectLastExpenseId());
            cashFlowService.insert(transportCashFlowEntity);
        }

        
        System.out.println("a");
    }
    


    /**
     * PuchaseEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @return entity
     */
    private PurchaseEntity createPuchaseEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
        PurchaseEntity entity = new PurchaseEntity();
        entity.setDogId(dogId);
        entity.setContractType(puchaseReq.getContractType());
        entity.setPurchaseUserId(userId);
        entity.setContractDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        if (StringUtils.isNotEmpty(puchaseReq.getPurchaseDate())) {
            entity.setPurchaseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()));
        }
        entity.setContractYen(Integer.parseInt(puchaseReq.getContractYen()));
        entity.setPurchaseYen(Integer.parseInt(puchaseReq.getPurchaseYen()));
        if (StringUtils.isNotEmpty(puchaseReq.getMedicalYen())) {
            entity.setMedicalYen(Integer.parseInt(puchaseReq.getMedicalYen()));
        }
        if (StringUtils.isNotEmpty(puchaseReq.getTransportYen())) {
            entity.setTransportYen(Integer.parseInt(puchaseReq.getTransportYen()));
        }
        entity.setPaymentExpectedDate(CommonUtils.parseHyphenDate(puchaseReq.getPaymentExpectedDate()));
        if (StringUtils.isNotEmpty(puchaseReq.getPaymentDate())) {
            entity.setPaymentDate(CommonUtils.parseHyphenDate(puchaseReq.getPaymentDate()));
        }
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
    
    /**
     * DogEntity生成.
     * 
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @return entity
     */
    private DogEntity createDogEntity(PuchaseRequest puchaseReq, String userId) {
        DogEntity entity = new DogEntity();
        entity.setJkcNo(puchaseReq.getJkcNo());
        entity.setDogCode(puchaseReq.getDogCode());
        entity.setDogGroupCode(puchaseReq.getDogGroupCode());
        entity.setDogName(puchaseReq.getDogName());
        entity.setSex(Integer.parseInt(puchaseReq.getSex()));
        entity.setBirthday(CommonUtils.parseHyphenDate(puchaseReq.getBirthday()));
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
    
    /**
     * 医療金額登録時ExpenseEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @return entity
     */
    private ExpenseEntity createMedicalExpenseEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setDogId(dogId);
        //entity.setCreateStoreCode();
        //entity.setCreateStoreName();
        entity.setOccurrenceType(OccurrenceType.PURCHASE.getCode());
        entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
        entity.setExpenseType(ExpenseType.MEDICAL.getCode());
        entity.setQuotationYen(Integer.parseInt(puchaseReq.getMedicalYen()));
        entity.setCloseYen(Integer.parseInt(puchaseReq.getMedicalYen()));
        entity.setPaymentDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        entity.setArrivalDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
    
    /**
     * 輸送費登録時ExpenseEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @return entity
     */
    private ExpenseEntity createTransportExpenseEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setDogId(dogId);
        //entity.setCreateStoreCode();
        //entity.setCreateStoreName();
        entity.setOccurrenceType(OccurrenceType.PURCHASE.getCode());
        entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
        entity.setExpenseType(ExpenseType.EXPENSE.getCode());
        entity.setQuotationYen(Integer.parseInt(puchaseReq.getTransportYen()));
        entity.setCloseYen(Integer.parseInt(puchaseReq.getTransportYen()));
        entity.setPaymentDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        entity.setArrivalDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
    
    /**
     * 犬本体価格登録CashFlowEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @return entity
     */
    private CashFlowEntity createDogCashFlowEntity(PuchaseRequest puchaseReq, String userId, int dogId) {
        CashFlowEntity entity = new CashFlowEntity();
        entity.setDogId(dogId);
        entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
        entity.setPrice(Integer.parseInt(puchaseReq.getPurchaseYen()));
        entity.setCashFlowDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        if (StringUtils.isNotEmpty(puchaseReq.getPurchaseDate())) {
            entity.setCloseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()));
        }
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
    /**
     * 輸送費登録時CashFlowEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @param expenseId int
     * @return entity
     */
    private CashFlowEntity createTransportCashFlowEntity(PuchaseRequest puchaseReq, String userId, int dogId, int expenseId) {
        CashFlowEntity entity = new CashFlowEntity();
        entity.setDogId(dogId);
        entity.setExpenseId(expenseId);
        entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
        entity.setPrice(Integer.parseInt(puchaseReq.getTransportYen()));
        entity.setCashFlowDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        if (StringUtils.isNotEmpty(puchaseReq.getPurchaseDate())) {
            entity.setCloseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()));
        }
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }

    /**
     * 医療費登録時CashFlowEntity生成.
     * @param puchaseReq PuchaseRequest
     * @param userId String
     * @param dogId int
     * @param expenseId int
     * @return entity
     */
    private CashFlowEntity createMedicalCashFlowEntity(PuchaseRequest puchaseReq, String userId, int dogId, int expenseId) {
        CashFlowEntity entity = new CashFlowEntity();
        entity.setDogId(dogId);
        entity.setExpenseId(expenseId);
        entity.setCashFlowType(CashFlowType.WITHDRAW.getCode());
        entity.setPrice(Integer.parseInt(puchaseReq.getMedicalYen()));
        entity.setCashFlowDate(CommonUtils.parseHyphenDate(puchaseReq.getContractDate()));
        if (StringUtils.isNotEmpty(puchaseReq.getPurchaseDate())) {
            entity.setCloseDate(CommonUtils.parseHyphenDate(puchaseReq.getPurchaseDate()));
        }
        entity.setCreateUserId(userId);
        entity.setUpdateUserId(userId);
        return entity;
    }
}
