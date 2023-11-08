package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.dog.DogTypeRequest;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.mapper.DogGroupMapper;
import com.example.demo.mapper.DogTypeMapper;

/**
 * 犬種サービス.
 */
@Service
public class DogTypeService {
	/** 犬種マッパー. */
	@Autowired
	DogTypeMapper dogTypeMapper;

	/**
	 * 全件取得.
	 * 
	 * @return dogGroupMapper.selectAll
	 */
	public List<DogTypeEntity> selectDogType() {
		return dogTypeMapper.selectDogType();
	}
	
	public DogTypeEntity selectDogTypeAndGroup(String dogCode) {
		return dogTypeMapper.selectDogTypeAndGroup(dogCode);
	}

	public int insert(DogTypeEntity entity) {
		return dogTypeMapper.insert(entity);
	}

	public DogTypeEntity selectDogTypeNmByCode(String dogTypeName) {
		return dogTypeMapper.selectByDogTypeNm(dogTypeName);
	}

	public List<DogTypeEntity> selectDogTypeByGroup(String[] dogGroup) {
		return dogTypeMapper.selectDogTypeByGroup(dogGroup);
	}
}