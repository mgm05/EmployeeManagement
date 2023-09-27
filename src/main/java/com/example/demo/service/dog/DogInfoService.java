package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogInfoEntity;
import com.example.demo.mapper.DogInfoMapper;
import com.example.demo.mapper.DogMapper;

/**
 * 犬情報サービス.
 */
@Service
@Transactional
public class DogInfoService {
    /** 犬情報マッパー. */
    @Autowired
    DogInfoMapper dogInfoMapper;
    
    /**
     * 犬情報取得.
     * @param req DogListRequest
     * @return dogInfoMapper.selectDogInfo()
     */
    public List<DogInfoEntity> selectDogInfo(DogListRequest req){
        return dogInfoMapper.selectDogInfo(req);
    }
}
