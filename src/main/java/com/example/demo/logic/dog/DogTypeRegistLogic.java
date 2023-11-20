package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogTypeRegistRequest;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogTypeService;

/**
 * 犬種登録ロジック.
 */
@Component
public class DogTypeRegistLogic {
	/** 犬種グループサービス. */
	@Autowired
	private DogGroupService dogGroupService;
	/** 犬種サービス. */
	@Autowired
	private DogTypeService dogTypeService;

	/**
	 * 犬種グループリスト取得.
	 * 
	 * @return dogGroupList
	 */
	public List<DogGroup> createDogGroupList() {
		List<DogGroupEntity> dogGroupEntites = dogGroupService.selectAll();
		List<DogGroup> dogGroupList = new ArrayList<>();
		for (DogGroupEntity entity : dogGroupEntites) {
			DogGroup dogGroup = new DogGroup();
			dogGroup.setDogGroupCode(entity.getDogGroupCode());
			dogGroup.setDogGroupName(entity.getDogGroupName());
			dogGroupList.add(dogGroup);
		}
		return dogGroupList;
	}

	/**
	 * 登録.
	 * @param dogTypeRequest DogTypeRegistRequest
	 */
	public void regist(DogTypeRegistRequest dogTypeRegistRequest) {
		DogTypeEntity entity = creteDogTypeEntity(dogTypeRegistRequest);
		dogTypeService.insert(entity);
	}

	/**
	 * 犬種エンティティ生成.
	 * @param dogTypeRequest DogTypeRegistRequest
	 * @return 
	 */
	private DogTypeEntity creteDogTypeEntity(DogTypeRegistRequest dogTypeRegistRequest) {
		DogTypeEntity entity = new DogTypeEntity();
		entity.setDogTypeNm(dogTypeRegistRequest.getDogTypeName());
		entity.setDogGroup(dogTypeRegistRequest.getDogGroupCode());
		entity.setDogSize(dogTypeRegistRequest.getDogSizeCode());
		return entity;
	}
}
