package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogGroupEntity;

/**
 * 犬種グループMapper.
 */

@Mapper
public interface DogGroupMapper {
    /** 全件取得.
     * @return DogGroup*/
    List<DogGroupEntity> selectAll();
}
