package com.example.demo.logic.dog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.DogSex;
import com.example.demo.dto.dog.DogGroup;
import com.example.demo.dto.dog.DogListResponse;
import com.example.demo.dto.dog.DogListRequest;
import com.example.demo.dto.dog.DogType;
import com.example.demo.entity.dog.DogGroupEntity;
import com.example.demo.entity.dog.DogListRequestEntity;
import com.example.demo.entity.dog.DogListResponseEntity;
import com.example.demo.entity.dog.DogTypeEntity;
import com.example.demo.service.dog.DogGroupService;
import com.example.demo.service.dog.DogListService;
import com.example.demo.service.dog.DogTypeService;

/**
 * 犬一覧ロジック.
 */
@Component
public class DogListLogic {
	/** 犬情報サービス. */
	@Autowired
	DogListService dogInfoService;
	/** 犬種グループサービス. */
	@Autowired
	DogGroupService dogGroupService;
	/** 犬種サービス. */
	@Autowired
	DogTypeService dogTypeService;

	/**
	 * 犬一覧リスト取得.
	 * @param req DogListRequest
	 * @return dogList
	 */
	public List<DogListResponse> createDogList(DogListRequest req) {
		List<DogListResponseEntity> dogListEntites = dogInfoService.select(createDogListEntity(req));
		List<DogListResponse> dogList = new ArrayList<>();

		// マッピング
		for (DogListResponseEntity entity : dogListEntites) {
			DogListResponse dto = new DogListResponse();
			dto.setDogId(entity.getDogId());
			dto.setJkcNo(entity.getJkcNo());
			dto.setDogGroupName(entity.getDogGroupName());
			dto.setDogTypeNm(entity.getDogTypeNm());
			dto.setSex(DogSex.of(entity.getSex()).getVal());
			dto.setBirthday(CommonUtils.formatDate(entity.getBirthday()));
			dto.setContractDate(CommonUtils.formatDate(entity.getContractDate()));
			if (entity.getPurchaseDate() != null) {
				dto.setPurchaseDate(CommonUtils.formatDate(entity.getPurchaseDate()));
			}
			dogList.add(dto);
		}
		return dogList;
	}


	/**
	 * リクエストDtoのデータをリクエストEntityに格納
	 * @param req DogListRequest
	 * @return dogListRequestEntity
	 */
	private DogListRequestEntity createDogListEntity(DogListRequest req) {
		DogListRequestEntity dogListRequestEntity = new DogListRequestEntity();
		dogListRequestEntity.setDogId(req.getDogId());
		dogListRequestEntity.setJkcNo(req.getJkcNo());
		dogListRequestEntity.setDogGroupCode(req.getDogGroup());
		dogListRequestEntity.setDogTypeCode(req.getDogType());
		dogListRequestEntity.setSex(req.getSex());
		dogListRequestEntity.setBirthdayFrom(req.getBirthdayFrom());
		dogListRequestEntity.setBirthdayTo(req.getBirthdayTo());
		dogListRequestEntity.setContractDateFrom(req.getContractDateFrom());
		dogListRequestEntity.setContractDateTo(req.getContractDateTo());
		dogListRequestEntity.setPurchaseDateFrom(req.getPurchaseDateFrom());
		dogListRequestEntity.setPurchaseDateTo(req.getPurchaseDateTo());
		
		return dogListRequestEntity;
	}


	/**
	 * 犬種グループリスト取得.
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
	 * 犬種リスト取得.
	 * @return dogTypeList
	 */
	public List<DogType> createDogTypeList(List<String> dogGroup) {
		List<DogTypeEntity> dogTypeEntity = dogTypeService.selectDogTypeByGroup(dogGroup);
		List<DogType> dogTypeList = new ArrayList<>();
		for (DogTypeEntity entity:dogTypeEntity) {
			DogType dogType  = new DogType(); 
			dogType.setDogTypeCode(entity.getDogTypeCode());
			dogType.setDogTypeNm(entity.getDogTypeNm());
			dogTypeList.add(dogType);
		}
		return dogTypeList;
	}
}
