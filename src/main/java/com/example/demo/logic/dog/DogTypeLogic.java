package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogTypeRequest;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogTypeService;

/**
 * 犬一覧ロジック.
 */
@Component
public class DogTypeLogic {
	/** 犬種グループサービス. */
	@Autowired
	DogGroupService dogGroupService;
	/** 犬種サービス. */
	@Autowired
	DogTypeService dogTypeService;

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

	public void regist(DogTypeRequest dogTypeRequest) {
		DogTypeEntity entity = creteDogTypeEntity(dogTypeRequest);
		dogTypeService.insert(entity);
	}

	private DogTypeEntity creteDogTypeEntity(DogTypeRequest dogTypeRequest) {
		DogTypeEntity entity = new DogTypeEntity();
		entity.setDogTypeNm(dogTypeRequest.getDogType());
		entity.setDogGroup(dogTypeRequest.getDogGroup());
		return entity;
	}
}
