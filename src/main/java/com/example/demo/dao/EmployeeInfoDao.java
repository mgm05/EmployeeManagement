package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeDetailEntity;
import com.example.demo.entity.EmployeeInfoEntity;
import com.example.demo.entity.EmployeeListEntity;

/**
 * 社員情報.
 */
@Repository
public class EmployeeInfoDao {
    /** JdbcTemplate. */
    @Autowired
    private NamedParameterJdbcTemplate jdbc;
    
    
    /**
     * 社員情報をリストで取得.
     * @return employeeInfolist
     */
    public List<EmployeeListEntity> selectEmployeeList(){
        String sql =
                "SELECT EI.employee_id"
                + " , EI.name"
                + " , EI.name_hiragana"
                + " , EI.birthday"
                + " , ES.business_manager"
                + " , ES.commissioning_status "
                + " , ES.department"
                + " , CI.abbreviation"
                + " , ES.hire_date"
                + " FROM employee_info AS EI JOIN employee_state AS ES"
                + " ON EI.employee_id = ES.employee_info_id"
                + " JOIN company_info AS CI ON ES.company_info_id = CI.company_id";
        //RowMapper宣言
        RowMapper<EmployeeListEntity> rowMapper = new BeanPropertyRowMapper<>(EmployeeListEntity.class);
        //リスト取得
        List<EmployeeListEntity> employeelist = jdbc.query(sql, rowMapper);
        
        return employeelist;
    }
    
    /**
     * 社員詳細を取得.
     * @param empId String
     * @return employeeDetailEntity
     */
    public EmployeeDetailEntity selectEmployeeDetail(String empId) {
        String sql =
                "SELECT EI.employee_id"
                + " , EI.name"
                + " , EI.name_hiragana"
                + " , EI.birthday AS birthday"
                + " , EI.sex"
                + " , EI.mail_address"
                + " , EI.telephone_number"
                + " , ES.company_info_id"
                + " , ES.business_manager"
                + " , ES.department"
                + " , ES.commissioning_status"
                + " , ES.hire_date"
                + " , ES.retire_date"
                + " , ES.status"
                + " FROM employee_info AS EI JOIN employee_state AS ES"
                + " ON EI.employee_id = ES.employee_info_id"
                + " WHERE EI.employee_id = :empId";
        //empIdをセット
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("empId", empId);
        //RowMapper宣言
        RowMapper<EmployeeDetailEntity> rowMapper = new BeanPropertyRowMapper<>(EmployeeDetailEntity.class);
        
        EmployeeDetailEntity employeeDetailEntity = jdbc.queryForObject(sql, map, rowMapper);
        
        return employeeDetailEntity;
    }
    
    /**
     * 社員情報新規登録.
     * @param entity EmployeeInfoEntity
     */
    public void insertEmployeeDetail(EmployeeInfoEntity entity) {
        String sql =
            "INSERT "
            +"INTO employee_info( "
            +" name"
            +" , name_hiragana"
            +" , birthday"
            +" , sex"
            +" , mail_address"
            +" , telephone_number"
            +" , created_id"
            +" , modified_id"
            +") "
            +"VALUES ( "
            +" :name"
            +" , :nameHiragana"
            +" , :birthday"
            +" , :sex"
            +" , :mailAddress"
            +" , :telephoneNumber"
            +" , :createdId"
            +" , :modifiedId"
            +")";
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("name", entity.getName())
                .addValue("nameHiragana", entity.getNameHiragana())
                .addValue("birthday", entity.getBirthday())
                .addValue("sex", entity.getSex())
                .addValue("mailAddress", entity.getMailAddress())
                .addValue("telephoneNumber", entity.getTelephoneNumber())
                .addValue("createdId", entity.getCreatedId())
                .addValue("modifiedId", entity.getModifiedId());
        jdbc.update(sql, map);
    }
    
    /**
     * insert直後のAuto Incrementを取得.
     * @return employeeInfoEntity
     */
    public EmployeeInfoEntity selectLastEmployeeId() {
        String sql =
                " SELECT"
                + " LAST_INSERT_ID() AS employeeId"
                + " FROM employee_info";
        
        //RowMapper宣言
        RowMapper<EmployeeInfoEntity> rowMapper = new BeanPropertyRowMapper<>(EmployeeInfoEntity.class);
        EmployeeInfoEntity employeeInfoEntity = jdbc.query(sql, rowMapper).get(0);
        return employeeInfoEntity;
    }
    
    /**
     * empIdを指定して更新.
     * @param entity EmployeeInfoEntity
     */
    public void updateEmployeeDetail(EmployeeInfoEntity entity) {
        String sql = 
                "UPDATE employee_info "
                +"SET"
                +" name = :name"
                +" , name_hiragana = :nameHiragana"
                +" , birthday = :birthday"
                +" , sex = :sex"
                +" , mail_address = :mailAddress"
                +" , telephone_number = :telephoneNumber"
                +" , modified_id = :modifiedId "
                +"WHERE"
                +" employee_id = :employeeId";
        
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("employeeId", entity.getEmployeeId())
                .addValue("name", entity.getName())
                .addValue("nameHiragana", entity.getNameHiragana())
                .addValue("birthday", entity.getBirthday())
                .addValue("sex", entity.getSex())
                .addValue("mailAddress", entity.getMailAddress())
                .addValue("telephoneNumber", entity.getTelephoneNumber())
                .addValue("modifiedId", entity.getModifiedId());
        jdbc.update(sql, map);
    }
    
    /**
     * empIdを指定して削除.
     * @param empId int
     */
    public void deleteEmployeeDetail(int empId) {
        String sql =
                "DELETE FROM employee_info WHERE employee_id = :empId";
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("empId", empId);
        jdbc.update(sql, map);
    }
}
