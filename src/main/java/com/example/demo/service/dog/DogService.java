package com.example.demo.service.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.dog.DogEntity;
import com.example.demo.mapper.DogMapper;

/**
 * 犬サービス.
 */
@Service
@Transactional
public class DogService {
    /** 犬マッパー. */
    @Autowired
    DogMapper dogMapper;
    
    /**
     * 登録.
     * @param dogEntity DogEntity
     * @return dogMapper.insert(dogEntity)
     */
    public int insert(DogEntity dogEntity) {
        return dogMapper.insert(dogEntity);
    }
    
    /**
     * insert直後のdogIdを取得.
     * @return dogMapper.selectLastDogId()
     */
    public int getLastDogId() {
        return dogMapper.selectLastDogId();
    }
    
    /**
     * 犬詳細情報をidで取得.
     * @param dogId Integer
     * @return dogMapper.selectDogById(dogId)
     */
    public DogEntity selectDogById(Integer dogId){
        return dogMapper.selectDogById(dogId);
    }
}
