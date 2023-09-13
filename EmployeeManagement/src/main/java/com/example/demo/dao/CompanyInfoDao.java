package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CompanyInfoEntity;

/**
 * 会社情報.
 */
@Repository
public class CompanyInfoDao {
    /** JdbcTemplate. */
    @Autowired
    private NamedParameterJdbcTemplate jdbc;
    
    
    /**
     * 社員情報をリストで取得.
     * @return companyList
     */
    public List<CompanyInfoEntity> selectCompanyList(){
        String sql = "SELECT company_id , abbreviation FROM company_info";
        
        //RowMapper宣言
        RowMapper<CompanyInfoEntity> rowMapper = new BeanPropertyRowMapper<>(CompanyInfoEntity.class);
        //リスト取得
        List<CompanyInfoEntity> companyList = jdbc.query(sql, rowMapper);
        
        return companyList;
    }
}
