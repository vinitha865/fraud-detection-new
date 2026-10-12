package com.fraud.frauddetection.service;
import com.fraud.frauddetection.dto.*;
import com.fraud.frauddetection.model.TransactionRecord;
import com.fraud.frauddetection.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class FraudAnalysisService {
 private final TransactionRepository repository;
 public FraudAnalysisService(TransactionRepository r){repository=r;}
 @Transactional public TransactionResponse analyze(TransactionRequest q){
  String txn=q.getTransactionId().trim();
  if(repository.existsByTransactionId(txn))throw new IllegalArgumentException("Transaction ID already exists. Please use a different ID.");
  double amount=q.getAmount().doubleValue(); int score=0; List<String> reasons=new ArrayList<>();
  if(amount>=50000){score+=35;reasons.add("High transaction amount (₹50,000 or more): +35");}
  else if(amount>=20000){score+=20;reasons.add("Elevated transaction amount (₹20,000 or more): +20");}
  else reasons.add("Amount is within the demo's usual range: +0");
  if("different".equals(q.getLocationType())){score+=25;reasons.add("Transaction from an unusual location: +25");}else reasons.add("Usual location selected: +0");
  if("new".equals(q.getDeviceType())){score+=20;reasons.add("New or unrecognized device: +20");}else reasons.add("Known device selected: +0");
  if("rapid".equals(q.getVelocity())){score+=25;reasons.add("Rapid transaction activity detected: +25");}else reasons.add("No rapid-activity signal selected: +0");
  if("unusual".equals(q.getTimeOfDay())){score+=10;reasons.add("Transaction at an unusual hour: +10");}
  if(List.of("gaming","money_transfer").contains(q.getMerchantCategory())){score+=10;reasons.add("Merchant category has an extra demo risk weight: +10");}
  score=Math.min(100,score);String level=score>=60?"HIGH":score>=30?"MEDIUM":"LOW";
  String summary=level.equals("HIGH")?"Several risk signals were detected. Review this sample transaction carefully.":level.equals("MEDIUM")?"Some risk signals were detected. Additional verification may be appropriate.":"Few risk signals were detected by the demo rules.";
  LocalDateTime now=LocalDateTime.now();
  TransactionRecord saved=repository.save(new TransactionRecord(txn,amount,q.getMerchantCategory(),level,score,String.join("\n",reasons),now));
  return new TransactionResponse(saved.getTransactionId(),saved.getAmount(),saved.getMerchantCategory(),saved.getRiskScore(),saved.getRiskLevel(),summary,reasons,saved.getCreatedAt());
 }
 @Transactional(readOnly=true) public List<TransactionResponse> history(){
  return repository.findTop50ByOrderByCreatedAtDesc().stream().map(r->{
   List<String> reasons=Arrays.asList(r.getReasons().split("\n"));
   String summary=r.getRiskLevel().equals("HIGH")?"Several risk signals were detected. Review this sample transaction carefully.":r.getRiskLevel().equals("MEDIUM")?"Some risk signals were detected. Additional verification may be appropriate.":"Few risk signals were detected by the demo rules.";
   return new TransactionResponse(r.getTransactionId(),r.getAmount(),r.getMerchantCategory(),r.getRiskScore(),r.getRiskLevel(),summary,reasons,r.getCreatedAt());
  }).collect(Collectors.toList());
 }
}
