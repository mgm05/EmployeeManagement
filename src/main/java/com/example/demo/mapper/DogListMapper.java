package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.entity.dog.DogEntity;
import com.example.demo.entity.dog.DogListRequestEntity;
import com.example.demo.entity.dog.DogListResponseEntity;

/**
 * 犬情報Mapper.
 */
@Mapper
public interface DogListMapper {
    /** 犬情報取得.
     * @param dogInfoEntity DogListRequest
     * @return DogInfo*/
    List<DogListResponseEntity> select(DogListRequestEntity dogListRequestEntity);
}
