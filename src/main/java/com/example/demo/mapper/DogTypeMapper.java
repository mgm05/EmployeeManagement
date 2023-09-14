package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;

/**
 * 犬種Mapper.
 */

@Mapper
public interface DogTypeMapper {
    /** 犬種取得.
     * @return DogGroup*/
    List<DogTypeEntity> selectDogType();
}
