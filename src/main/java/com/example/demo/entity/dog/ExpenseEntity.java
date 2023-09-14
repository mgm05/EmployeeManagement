package com.example.demo.entity.dog;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 *経費Entity.
 */
@Data
public class ExpenseEntity {
    private Integer expenseId;
    private Integer paymentId;
    private Integer dogId;
    private String requestStoreCode;
    private String requestStoreName;
    private Integer occurrenceType;
    private Integer cashFlowType;
    private Integer expenseType;
    private Integer itemId;
    private String itemName;
    private String info;
    private Integer quotationYen;
    private Integer closeYen;
    private String paymentDestinationCode;
    private String paymentDestinationName;
    private Date paymentDate;
    private Date arrivalDate;
    private Integer cancelFlag;
    private Timestamp createDatetime;
    private String createStoreCode;
    private String createStoreName;
    private String createUserId;
    private Timestamp updateDatetime;
    private String updateStoreCode;
    private String updateStoreName;
    private String updateUserId;


}
