package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeInfoDao;
import com.example.demo.entity.EmployeeDetailEntity;
import com.example.demo.entity.EmployeeInfoEntity;
import com.example.demo.entity.EmployeeListEntity;

import lombok.RequiredArgsConstructor;

/**
 * 社員情報サービス.
 */
@Service
@RequiredArgsConstructor
public class EmployeeInfoService {
    /** 社員情報. */
    private final EmployeeInfoDao employeeInfoDao;
    
    /**
     * 社員情報リストを取得.
     * @return employeeInfolist
     */
    public List<EmployeeListEntity> getEmployeeList(){
        List<EmployeeListEntity> employeeInfolist = employeeInfoDao.selectEmployeeList();
        return employeeInfolist;
    }
    
    /**
     * 社員詳細を取得.
     * @param empId String
     * @return employeeDetailEntity
     */
    public EmployeeDetailEntity getEmployeeDetail(String empId) {
        EmployeeDetailEntity employeeDetailEntity = employeeInfoDao.selectEmployeeDetail(empId);
        return employeeDetailEntity;
    }
    
    /**
     * 登録.
     * @param employeeInfoEntity EmployeeInfoEntity
     */
    public void insert(EmployeeInfoEntity employeeInfoEntity) {
        employeeInfoDao.insertEmployeeDetail(employeeInfoEntity);
        
    }
    
    /**
     * 更新.
     * @param employeeInfoEntity EmployeeInfoEntity
     */
    public void update(EmployeeInfoEntity employeeInfoEntity) {
        employeeInfoDao.updateEmployeeDetail(employeeInfoEntity);
        
    }
    
    /**
     * insert直後のAuto Incrementを取得.
     * @return empId
     */
    public int getLastEmployeeId() {
        EmployeeInfoEntity employeeInfoEntity = employeeInfoDao.selectLastEmployeeId();
        return employeeInfoEntity.getEmployeeId();
    }
    
    /**
     * empIdを指定して削除.
     * @param empId int
     */
    public void delete(int empId) {
        employeeInfoDao.deleteEmployeeDetail(empId);
    }
}
