package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CompanyInfoDao;
import com.example.demo.entity.CompanyInfoEntity;

import lombok.RequiredArgsConstructor;

/**
 * 会社情報サービス.
 */
@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    /** 会社情報. */
    private final CompanyInfoDao companyInfoDao;
    
    /**
     * 会社情報テーブルから会社情報IDと略称をリストにして取得.
     * @return companyList
     */
    public List<CompanyInfoEntity> getCompanyList(){
        return companyInfoDao.selectCompanyList();
    }
}
