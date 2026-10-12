package com.fraud.frauddetection.dto;
import java.time.LocalDateTime;
import java.util.List;
public record TransactionResponse(String transactionId,double amount,String merchantCategory,int riskScore,String riskLevel,String summary,List<String> reasons,LocalDateTime createdAt){}
