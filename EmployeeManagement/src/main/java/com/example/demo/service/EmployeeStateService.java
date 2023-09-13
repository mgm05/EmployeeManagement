package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeStateDao;
import com.example.demo.entity.EmployeeStateEntity;

import lombok.RequiredArgsConstructor;

/** 社員状況サービス. */
@Service
@RequiredArgsConstructor
public class EmployeeStateService {
    /** 社員状況. */
    private final EmployeeStateDao employeeStateDao;

    /**
     * 登録.
     * @param employeeStateEntity EmployeeStateEntity
     */
    public void insert(EmployeeStateEntity employeeStateEntity) {
        employeeStateDao.insertEmployeeDetail(employeeStateEntity);
    }
    
    /**
     * 更新.
     * @param employeeStateEntity EmployeeStateEntity
     */
    public void update(EmployeeStateEntity employeeStateEntity) {
        employeeStateDao.updateEmployeeDetaile(employeeStateEntity);
    }
    
    /**
     * empIdを指定して削除.
     * @param empId int
     */
    public void delete(int empId) {
        employeeStateDao.deleteEmployeeDetail(empId);
    }
}
