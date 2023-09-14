package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 *入出金Entity.
 */
@Data
public class CashFlowEntity {
    private Integer cashFlowId;
    private Integer dogId;
    private Integer expenseId;
    private String storeCode;
    private String storeName;
    private Integer cashFlowType;
    private String info;
    private String note;
    private Integer price;
    private Integer cancelFlag;
    private Date cashFlowDate;
    private Date closeDate;
    private Timestamp createDatetime;
    private String createUserId;
    private Timestamp updateDatetime;
    private String updateUserId;

}
