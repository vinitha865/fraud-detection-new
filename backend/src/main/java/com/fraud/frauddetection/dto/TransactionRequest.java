package com.fraud.frauddetection.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public class TransactionRequest {
 @NotBlank @Size(max=40) private String transactionId;
 @NotNull @DecimalMin("0.01") @DecimalMax("100000000") private BigDecimal amount;
 @NotBlank private String merchantCategory;
 @NotBlank private String locationType;
 @NotBlank private String deviceType;
 @NotBlank private String velocity;
 @NotBlank private String timeOfDay;
 public String getTransactionId(){return transactionId;} public void setTransactionId(String v){transactionId=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;}
 public String getMerchantCategory(){return merchantCategory;} public void setMerchantCategory(String v){merchantCategory=v;}
 public String getLocationType(){return locationType;} public void setLocationType(String v){locationType=v;}
 public String getDeviceType(){return deviceType;} public void setDeviceType(String v){deviceType=v;}
 public String getVelocity(){return velocity;} public void setVelocity(String v){velocity=v;}
 public String getTimeOfDay(){return timeOfDay;} public void setTimeOfDay(String v){timeOfDay=v;}
}
