package com.example.demo.entity.dog;

import lombok.Data;

/**
 * 犬種Entity.
 */
@Data
public class DogTypeEntity {
    private Integer dogTypeCode;
    private String dogSize;
    private String dogGroup;
    private String dogTypeNm;

}
