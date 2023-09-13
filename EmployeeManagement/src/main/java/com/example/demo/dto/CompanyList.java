package com.example.demo.dto;

import lombok.Data;

/**
 * 会社リストDto.
 */
@Data
public class CompanyList {
    /** 社員ID. */
    private String companyId;
    /** 会社名略称. */
    private String abbreviation;

}
