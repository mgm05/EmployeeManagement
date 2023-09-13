package com.example.demo.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.constEnum.CommissioningStatus;
import com.example.demo.constEnum.Department;
import com.example.demo.constEnum.Status;
import com.example.demo.dto.EmployeeListResponse;
import com.example.demo.entity.EmployeeListEntity;
import com.example.demo.service.EmployeeInfoService;
import com.example.demo.service.EmployeeStateService;

import lombok.RequiredArgsConstructor;

/**
 * 社員リストロジック.
 */
@Component
public class EmployeeListLogic {
    /**社員情報.*/
    @Autowired
    private EmployeeInfoService employeeInfoService;
    /**社員状況.*/
    @Autowired
    private EmployeeStateService employeeStateService;
    
    /**
     * 社員情報リストを取得後、画面表示用に加工.
     * @return employeeListResponse
     */
    public List<EmployeeListResponse> getEmployeeListResponse() {
        List<EmployeeListResponse> employeeListResponse = new ArrayList<EmployeeListResponse>();
        
        //社員リスト取得
        List<EmployeeListEntity> employeeList = employeeInfoService.getEmployeeList();
        
        for (EmployeeListEntity list : employeeList) {
            //Enumコード値から名称変換
            String departmentName = Department.of(list.getDepartment()).getVal();
            String commissioningStatusName = CommissioningStatus.of(list.getCommissioningStatus()).getVal();
            
            //入社日をyyyy/MM/ddに変換
            String hireDate = CommonUtils.formatDate(list.getHireDate());
            
            //レスポンスDtoにセット
            EmployeeListResponse response = new EmployeeListResponse();
            response.setEmployeeId(list.getEmployeeId());
            response.setName(list.getName());
            response.setNameHiragana(list.getNameHiragana());
            response.setBusinessManager(list.getBusinessManager());
            response.setAbbreviation(list.getAbbreviation());
            response.setHireDate(hireDate);
            response.setCommissioningStatusName(commissioningStatusName);
            response.setDepartmentName(departmentName);
            response.setAge(CommonUtils.calcAge(list.getBirthday()));
            employeeListResponse.add(response);
        }
        return employeeListResponse;
    }
    
    /**
     * empIdを元に社員情報、社員状況の項目を削除.
     * @param empId int
     */
    public void delete(int empId) {
        employeeInfoService.delete(empId);
        employeeStateService.delete(empId);
    }
}
