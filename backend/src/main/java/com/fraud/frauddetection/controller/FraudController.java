package com.fraud.frauddetection.controller;
import com.fraud.frauddetection.dto.*;
import com.fraud.frauddetection.service.FraudAnalysisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
public class FraudController {
 private final FraudAnalysisService service;
 public FraudController(FraudAnalysisService s){service=s;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","application","fraud-detection");}
 @PostMapping("/fraud/check") public TransactionResponse analyze(@Valid @RequestBody TransactionRequest q){return service.analyze(q);}
 @GetMapping("/fraud/history") public List<TransactionResponse> history(){return service.history();}
 @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.CONFLICT)
 public Map<String,String> conflict(IllegalArgumentException e){return Map.of("message",e.getMessage());}
}
