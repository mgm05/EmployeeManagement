package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 *入出金Entity.
 */
@Data
public class ExpenseEntity {
    private int cashFlowId;
    private int dogId;
    private int expenseId;
    private String storeCode;
    private String storeName;
    private int cashFlowType;
    private String info;
    private String note;
    private int price;
    private int cancelFlag;
    private Date cashFlowDate;
    private Date closeDate;
    private Timestamp createDatetime;
    private String createUserId;
    private Timestamp updateDatetime;
    private String updateUserId;

}
