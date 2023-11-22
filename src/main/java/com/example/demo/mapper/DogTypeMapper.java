package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.dog.DogTypeEntity;

/**
 * 犬種Mapper.
 */

@Mapper
public interface DogTypeMapper {
	/**
	 * 犬種グループで犬種コードと犬種名を絞り込み取得.
	 * @param dogGroup List<String>
	 * @return 犬種コード,犬種名
	 */
	List<DogTypeEntity> selectDogTypeByGroup(List<String> dogGroup);

	/**
	 * 犬種コードから犬種名と犬種グループコードと犬種サイズコードを取得.
	 * @param dogCode String
	 * @return 犬種名,犬種グループコード,犬種サイズコード
	 */
	DogTypeEntity selectByDogTypeCode(String dogCode);

	/**
	 * 登録.
	 * @param entity DogTypeEntity
	 * @return int
	 */
	int insert(DogTypeEntity entity);

	/**
	 * 一致する犬種名を取得.
	 * @param dogTypeName String
	 * @return 犬種名
	 */
	DogTypeEntity selectByDogTypeNm(String dogTypeName);

	/**
	 * 全件取得.
	 * @return 犬種コード,犬種名
	 */
	List<DogTypeEntity> selectDogType();
}
