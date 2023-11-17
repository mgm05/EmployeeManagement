package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.dog.DogTypeRegistRequest;
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
	 * 犬種グループで犬種コードと犬種名を絞り込み取得.
	 * @param dogGroup List<String>
	 * @return selectDogTypeByGroup(dogGroup)
	 */
	public List<DogTypeEntity> selectDogTypeByGroup(List<String> dogGroup) {
		return dogTypeMapper.selectDogTypeByGroup(dogGroup);
	}
	
	/**
	 * 犬種コードから犬種名と犬種グループコードを取得.
	 * @param dogCode
	 * @return selectDogTypeAndGroup(dogCode)
	 */
	public DogTypeEntity selectDogTypeAndGroup(String dogCode) {
		return dogTypeMapper.selectDogTypeAndGroup(dogCode);
	}

	/**
	 * 登録.
	 * @param entity
	 * @return insert(entity)
	 */
	public int insert(DogTypeEntity entity) {
		return dogTypeMapper.insert(entity);
	}

	/**
	 * 一致する犬種名を取得.
	 * @param dogTypeName
	 * @return selectByDogTypeNm(dogTypeName)
	 */
	public DogTypeEntity selectByDogTypeNm(String dogTypeName) {
		return dogTypeMapper.selectByDogTypeNm(dogTypeName);
	}
	
	/**
	 * 全件取得.
	 * @return selectDogType()
	 */
	public List<DogTypeEntity> selectDogType(){
		return dogTypeMapper.selectDogType();
	}
}