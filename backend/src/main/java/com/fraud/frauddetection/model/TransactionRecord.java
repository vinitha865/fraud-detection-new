package com.fraud.frauddetection.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="transaction_records")
public class TransactionRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=40) private String transactionId;
 @Column(nullable=false) private double amount;
 @Column(nullable=false,length=24) private String merchantCategory;
 @Column(nullable=false,length=16) private String riskLevel;
 @Column(nullable=false) private int riskScore;
 @Column(nullable=false,length=1200) private String reasons;
 @Column(nullable=false) private LocalDateTime createdAt;
 protected TransactionRecord(){}
 public TransactionRecord(String t,double a,String m,String l,int s,String r,LocalDateTime c){transactionId=t;amount=a;merchantCategory=m;riskLevel=l;riskScore=s;reasons=r;createdAt=c;}
 public String getTransactionId(){return transactionId;} public double getAmount(){return amount;} public String getMerchantCategory(){return merchantCategory;} public String getRiskLevel(){return riskLevel;} public int getRiskScore(){return riskScore;} public String getReasons(){return reasons;} public LocalDateTime getCreatedAt(){return createdAt;}
}
