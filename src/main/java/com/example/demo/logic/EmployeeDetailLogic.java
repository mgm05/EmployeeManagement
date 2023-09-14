package com.example.demo.logic;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.CommonUtils;
import com.example.demo.dto.EmployeeDetailRequet;
import com.example.demo.dto.EmployeeDetailResponse;
import com.example.demo.entity.CompanyInfoEntity;
import com.example.demo.entity.EmployeeDetailEntity;
import com.example.demo.entity.EmployeeInfoEntity;
import com.example.demo.entity.EmployeeStateEntity;
import com.example.demo.service.CompanyInfoService;
import com.example.demo.service.EmployeeInfoService;
import com.example.demo.service.EmployeeStateService;

/**
 * 社員詳細.
 * @author user
 */
@Component
public class EmployeeDetailLogic {
    /** 社員情報. */
    @Autowired
    private EmployeeInfoService employeeInfoService;
    /** 社員状況. */
    @Autowired
    private EmployeeStateService employeeStateService;
    /** 会社情報. */
    @Autowired
    private CompanyInfoService companyInfoService;
    
    /**
     * 会社リスト取得.
     * @return CompanyList
     */
    public List<CompanyInfoEntity> getCompanyList(){
        return companyInfoService.getCompanyList();
    }
    
    /**
    * 社員詳細を取得しレスポンスDTOにセット.
    * @param empId String
    * @return employeeDetailResponse
    */
    public EmployeeDetailResponse getEmployeeDetailResponse(String empId){
        EmployeeDetailResponse employeeDetailRes = new EmployeeDetailResponse();
        
        //社員詳細取得
        EmployeeDetailEntity employeeDetailEntity = employeeInfoService.getEmployeeDetail(empId);
        
        //日付変換
        String strBirthday = CommonUtils.formatDate(employeeDetailEntity.getBirthday());
        String strHireDate = CommonUtils.formatDate(employeeDetailEntity.getHireDate());
        Date retireDate = employeeDetailEntity.getRetireDate();
        String strRetireDate = null;
        if (retireDate != null) {
            strRetireDate = CommonUtils.formatDate(retireDate);
        }
        
        //レスポンス用Dtoにセット
        employeeDetailRes.setEmployeeId(Integer.toString(employeeDetailEntity.getEmployeeId()));
        employeeDetailRes.setName(employeeDetailEntity.getName());
        employeeDetailRes.setNameHiragana(employeeDetailEntity.getNameHiragana());
        employeeDetailRes.setBirthday(strBirthday);
        employeeDetailRes.setSex(employeeDetailEntity.getSex());
        employeeDetailRes.setMailAddress(employeeDetailEntity.getMailAddress());
        employeeDetailRes.setTelephoneNumber(employeeDetailEntity.getTelephoneNumber());
        employeeDetailRes.setCompanyInfoId(employeeDetailEntity.getCompanyInfoId());
        employeeDetailRes.setBusinessManager(employeeDetailEntity.getBusinessManager());
        employeeDetailRes.setDepartment(employeeDetailEntity.getDepartment());
        employeeDetailRes.setCommissioningStatus(employeeDetailEntity.getCommissioningStatus());
        employeeDetailRes.setHireDate(strHireDate);
        employeeDetailRes.setRetireDate(strRetireDate);
        employeeDetailRes.setStatus(employeeDetailEntity.getStatus());

        return employeeDetailRes;
    }
    
    /**
     * 社員情報、社員状況テーブルを登録.
     * @param req EmployeeDetailRequet
     * @param loginId String
     */
    public void regist(EmployeeDetailRequet req, String loginId) {
        //リクエストデータを社員情報、社員状況エンティティにセットする
        EmployeeInfoEntity employeeInfoEntity = createEmployeeInfoEntity(req, loginId);

        
        //社員情報登録
        employeeInfoService.insert(employeeInfoEntity);
        
        //insertで追加されempIdを取得しエンティティにセット
        int empId = employeeInfoService.getLastEmployeeId();
        EmployeeStateEntity employeeStateEntity = createEmployeeStateEntity(req, loginId, empId);
        employeeStateEntity.setEmployeeInfoId(empId);
        //社員状況登録
        employeeStateService.insert(employeeStateEntity);

    }
    
    /**
     * 社員情報、社員状況テーブルを更新.
     * @param req EmployeeDetailRequet
     * @param loginId String
     */
    public void update(EmployeeDetailRequet req, String loginId) {
        // リクエストデータを社員情報、社員状況エンティティにセットする
        EmployeeInfoEntity employeeInfoEntity = createEmployeeInfoEntity(req, loginId);
        EmployeeStateEntity employeeStateEntity = createEmployeeStateEntity(req, loginId, Integer.parseInt(req.getEmployeeId()));
        
        // 更新
        employeeInfoService.update(employeeInfoEntity);
        employeeStateService.update(employeeStateEntity);
    }
    
    
    /**
     * 社員状況エンティティを作成.
     * @param req EmployeeDetailRequet
     * @param loginId String
     * @param empId int
     * @return employeeStateEntity
     */
    private EmployeeStateEntity createEmployeeStateEntity(EmployeeDetailRequet req, String loginId, int empId) {
        EmployeeStateEntity employeeStateEntity = new EmployeeStateEntity();
        
        if (!isRegist(req)) {
            empId = Integer.parseInt(req.getEmployeeId());
        }

        // 日付変換
        Date hireDate = CommonUtils.parseSlashDate(req.getHireDate());
        Date retireDate = null;
        String strRetireDate = req.getRetireDate();
        if (StringUtils.isNotEmpty(strRetireDate)) {
            retireDate = CommonUtils.parseSlashDate(strRetireDate);
        }
        
        // エンティティにセット
        employeeStateEntity.setEmployeeInfoId(empId);
        employeeStateEntity.setCompanyInfoId(Integer.parseInt(req.getCompanyId()));
        employeeStateEntity.setBusinessManager(req.getBusinessManager());
        employeeStateEntity.setDepartment(req.getDepartment());
        employeeStateEntity.setCommissioningStatus(req.getCommissioningStatus());
        employeeStateEntity.setHireDate(hireDate);
        employeeStateEntity.setRetireDate(retireDate);
        employeeStateEntity.setStatus(req.getStatus());
        employeeStateEntity.setCreatedId(loginId);
        employeeStateEntity.setModifiedId(loginId);
        return employeeStateEntity;
    }
    
    /**
     * 社員情報エンティティを作成　　.
     * @param req EmployeeDetailRequet
     * @param loginId String
     * @return employeeInfoEntity
     */
    private EmployeeInfoEntity createEmployeeInfoEntity(EmployeeDetailRequet req, String loginId) {
        EmployeeInfoEntity employeeInfoEntity = new EmployeeInfoEntity();

        //更新の場合employeeIdをセット
        if (!isRegist(req)) {
            employeeInfoEntity.setEmployeeId(Integer.parseInt(req.getEmployeeId()));
        
        }
        //日付変換
        Date birthday = CommonUtils.parseSlashDate(req.getBirthday());
        
        //エンティティにセット
        employeeInfoEntity.setName(req.getName());
        employeeInfoEntity.setNameHiragana(req.getNameHiragana());
        employeeInfoEntity.setBirthday(birthday);
        employeeInfoEntity.setSex(req.getSex());
        employeeInfoEntity.setMailAddress(req.getMailAddress());
        employeeInfoEntity.setTelephoneNumber(req.getTelephoneNumber());
        employeeInfoEntity.setCreatedId(loginId);
        employeeInfoEntity.setModifiedId(loginId);
        return employeeInfoEntity;
    }
    
    /**
     * 登録可能か判定.
     * @param req EmployeeDetailRequet
     * @return boolean
     */
    public boolean isRegist(EmployeeDetailRequet req) {
        return StringUtils.isEmpty(req.getEmployeeId());
    }
}
