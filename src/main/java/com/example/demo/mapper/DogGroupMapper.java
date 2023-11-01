package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;

/**
 * 犬種グループMapper.
 */

@Mapper
public interface DogGroupMapper {
    /** 全件取得.
     * @return DogGroup*/
    List<DogGroupEntity> selectAll();
    
    /**
     * 犬種グループコードから犬種グループ名取得
     * @param dogGroupCode 
     */
    DogGroupEntity selectDogGroupNameByCode(String dogGroupCode);
}
