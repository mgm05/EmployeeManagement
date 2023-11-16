package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogListRequestEntity;
import com.example.demo.entity.dog.DogListResponseEntity;
import com.example.demo.mapper.DogListMapper;
import com.example.demo.mapper.DogMapper;

/**
 * 犬情報サービス.
 */
@Service
@Transactional
public class DogListService {
    /** 犬情報マッパー. */
    @Autowired
    DogListMapper dogListMapper;
    
    /**
     * 犬リスト取得.
     * @param dogInfoEntity DogListRequest
     * @return dogInfoMapper.selectDogInfo()
     */
    public List<DogListResponseEntity> select(DogListRequestEntity dogListRequestEntity){
        return dogListMapper.select(dogListRequestEntity);
    }
}
