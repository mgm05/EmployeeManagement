package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.DogSex;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogInfoResponse;
import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogInfoEntity;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogInfoService;

/**
 * 犬一覧ロジック.
 */
@Component
public class DogListLogic {
    /** 犬情報サービス. */
    @Autowired
    DogInfoService dogInfoService;
    /** 犬種グループサービス. */
    @Autowired
    DogGroupService dogGroupService;

    /**
     * 犬一覧リスト取得.
     * @return dogList
     */
    public List<DogInfoResponse> createDogList(DogListRequest req) {
        List<DogInfoEntity> dogInfoEntites = dogInfoService.selectDogInfo(req);
        List<DogInfoResponse> dogList = new ArrayList<>();
        for (DogInfoEntity entity : dogInfoEntites) {
            DogInfoResponse dto = new DogInfoResponse();
            dto.setDogId(entity.getDogId());
            dto.setJkcNo(entity.getJkcNo());
            dto.setDogGroupName(entity.getDogGroupName());
            dto.setDogTypeNm(entity.getDogTypeNm());
            dto.setSex(DogSex.of(entity.getSex()).getVal());
            dto.setBirthday(CommonUtils.formatDate(entity.getBirthday()));
            dto.setContractDate(CommonUtils.formatDate(entity.getContractDate()));
            if (entity.getPurchaseDate() != null) {
                dto.setPurchaseDate(CommonUtils.formatDate(entity.getPurchaseDate()));
            }
            dogList.add(dto);
        }
        return dogList;
    }

    /**
     * 犬種グループリスト取得.
     * @return dogGroupList
     */
    public List<DogGroup> createDogGroupList() {
        List<DogGroupEntity> dogGroupEntites = dogGroupService.selectAll();
        List<DogGroup> dogGroupList = new ArrayList<>();
        for (DogGroupEntity entity: dogGroupEntites) {
            DogGroup dogGroup = new DogGroup();
            dogGroup.setDogGroupCode(entity.getDogGroupCode());
            dogGroup.setDogGroupName(entity.getDogGroupName());
            dogGroupList.add(dogGroup);
        }
        return dogGroupList;
    }
    
}
