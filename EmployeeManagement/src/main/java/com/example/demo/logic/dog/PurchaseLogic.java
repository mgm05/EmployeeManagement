package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogType;
import com.example.demo.dto.dog.PuchaseRequest;
import com.example.demo.entity.dog.CashFlowEntity;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.entity.dog.PurchaseEntity;
import com.example.demo.mapper.DogTypeMapper;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogService;
import com.example.demo.service.dog.DogTypeService;
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
            dogType.setDogTypeId(entity.getDogTypeId());
            dogType.setDogTypeName(entity.getDogTypeName());
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

        DogEntity dogEntity = createDogEntity(puchaseReq, userId);
        dogService.insert(dogEntity);
        PurchaseEntity purchaseEntity = createPuchaseEntity(puchaseReq, userId, dogService.getLastDogId());
        purchaseService.insert(purchaseEntity);
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
}
