package com.example.demo.service.dog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.dog.DogTypeEntity;
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
	 * 
	 * @param dogGroup List<String>
	 * @return selectDogTypeByGroup(dogGroup)
	 */
	public List<DogTypeEntity> selectDogTypeByGroup(List<String> dogGroup) {
		return dogTypeMapper.selectDogTypeByGroup(dogGroup);
	}

	/**
	 * 犬種コードから犬種名と犬種グループコードと犬種サイズコードを取得.
	 * 
	 * @param dogCode String
	 * @return selectByDogTypeCode(dogCode)
	 */
	public DogTypeEntity selectByDogTypeCode(String dogCode) {
		return dogTypeMapper.selectByDogTypeCode(dogCode);
	}

	/**
	 * 登録.
	 * 
	 * @param entity DogTypeEntity
	 * @return int
	 */
	public int insert(DogTypeEntity entity) {
		return dogTypeMapper.insert(entity);
	}

	/**
	 * 一致する犬種名を取得.
	 * 
	 * @param dogTypeName String
	 * @return selectByDogTypeNm(dogTypeName)
	 */
	public DogTypeEntity selectByDogTypeNm(String dogTypeName) {
		return dogTypeMapper.selectByDogTypeNm(dogTypeName);
	}

	/**
	 * 全件取得.
	 * 
	 * @return selectDogType()
	 */
	public List<DogTypeEntity> selectDogType() {
		return dogTypeMapper.selectDogType();
	}
}