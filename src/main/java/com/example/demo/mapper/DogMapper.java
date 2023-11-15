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
     * 犬詳細情報をidで取得.
     * @param dogId Integer
     * @return 犬詳細情報
     */
    DogEntity selectDogById(String dogId);
}
