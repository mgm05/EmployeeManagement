package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
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

    /**
     * 犬種グループコードから犬種グループ名取得.
     * @param dogGroupCode
     * @return dogGroupMapper.selectByDogTypeCode
     */
	public DogGroupEntity selectDogGroupNameByCode(String dogGroupCode) {
		return dogGroupMapper.selectDogGroupNameByCode(dogGroupCode);
	}
}
