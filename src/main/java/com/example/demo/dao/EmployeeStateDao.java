package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.EmployeeStateEntity;

/**
 * 社員状況Dao.
 */
@Repository
public class EmployeeStateDao {
    /** JdbcTemplate. */
    @Autowired
    private NamedParameterJdbcTemplate jdbc;
    
    /**
     * 社員情報新規登録.
     * @param entity EmployeeStateEntity
     */
    public void insertEmployeeDetail(EmployeeStateEntity entity) {
        String sql = 
                " INSERT "
               + " INTO employee_state ("
               + " employee_info_id "
               + " , company_info_id "
               + " , business_manager "
               + " , department "
               + " , commissioning_status "
               + " , status "
               + " , hire_date "
               + " , retire_date "
               + " , created_id "
               + " , modified_id "
               + " )"
               + "VALUES   ( "
               + " :employeeInfoId"
               + " , :companyInfoId"
               + " , :businessManager"
               + " , :department"
               + " , :commissioningStatus"
               + " , :status"
               + " , :hireDate"
               + " , :retireDate"
               + " , :createdId"
               + " , :modifiedId"
               + " )";

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("employeeInfoId", entity.getEmployeeInfoId())
                .addValue("companyInfoId", entity.getCompanyInfoId())
                .addValue("businessManager", entity.getBusinessManager())
                .addValue("department", entity.getDepartment())
                .addValue("commissioningStatus", entity.getCommissioningStatus())
                .addValue("status", entity.getStatus())
                .addValue("hireDate", entity.getHireDate())
                .addValue("retireDate", entity.getRetireDate())
                .addValue("createdId", entity.getCreatedId())
                .addValue("modifiedId", entity.getModifiedId());
        jdbc.update(sql, map);
    }
    
    /**
     * empIdを指定して更新.
     * @param entity EmployeeStateEntity
     */
    public void updateEmployeeDetaile(EmployeeStateEntity entity) {
        String sql =
                "UPDATE employee_state "
                    +"SET"
                    +" company_info_id = :companyInfoId"
                    +" , business_manager = :businessManager"
                    +" , department = :department"
                    +" , commissioning_status = :commissioningStatus"
                    +" , status = :status"
                    +" , hire_date = :hireDate"
                    +" , retire_date = :retireDate"
                    +" , modified_id = :modifiedId "
                    +"WHERE"
                    +" employee_info_id = :employeeInfoId";
        
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("employeeInfoId", entity.getEmployeeInfoId())
                .addValue("companyInfoId", entity.getCompanyInfoId())
                .addValue("businessManager", entity.getBusinessManager())
                .addValue("department", entity.getDepartment())
                .addValue("commissioningStatus", entity.getCommissioningStatus())
                .addValue("status", entity.getStatus())
                .addValue("hireDate", entity.getHireDate())
                .addValue("retireDate", entity.getRetireDate())
                .addValue("modifiedId", entity.getModifiedId());
        
        jdbc.update(sql, map);
    }
    
    /**
     * empIdを指定して削除.
     * @param empId int
     */
    public void deleteEmployeeDetail(int empId) {
        String sql =
                "DELETE FROM employee_state WHERE employee_info_id = :empId";
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("empId", empId);
        jdbc.update(sql, map);
    }
}
