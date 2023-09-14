package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogEntity;

/**
 * 犬種グループMapper.
 */
@Mapper
public interface DogMapper {
    /** 登録.
     * @param dogEntity DogEntity
     * @return int*/
    int insert(DogEntity dogEntity);
    
    /**
     * insert直後のdogIdを取得.
     * @return insert直後のdogId
     */
    int selectLastDogId();
    
    DogEntity test();
}
