package com.example.demo.service.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.dog.DogEntity;
import com.example.demo.mapper.DogMapper;

/**
 * 犬サービス.
 */
@Service
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
}
