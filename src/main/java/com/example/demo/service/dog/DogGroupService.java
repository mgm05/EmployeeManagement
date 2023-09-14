package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.mapper.DogGroupMapper;

/**
 * 犬種グループサービス.
 */
@Service
public class DogGroupService {
    /** 犬種グループマッパー. */
    @Autowired
    DogGroupMapper dogGroupMapper;
    
    /**
     * 全件取得.
     * @return dogGroupMapper.selectAll
     */
    public List<DogGroupEntity> selectAll() {
        return dogGroupMapper.selectAll();
    }
}
