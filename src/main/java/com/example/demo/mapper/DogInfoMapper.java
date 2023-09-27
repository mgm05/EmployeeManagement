package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogInfoEntity;

/**
 * 犬情報Mapper.
 */
@Mapper
public interface DogInfoMapper {
    /** 犬情報取得.
     * @param req DogListRequest
     * @return DogInfo*/
    List<DogInfoEntity> selectDogInfo(DogListRequest req);
}
