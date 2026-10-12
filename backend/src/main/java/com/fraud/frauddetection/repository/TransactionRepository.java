package com.fraud.frauddetection.repository;
import com.fraud.frauddetection.model.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository extends JpaRepository<TransactionRecord,Long> {
 List<TransactionRecord> findTop50ByOrderByCreatedAtDesc();
 boolean existsByTransactionId(String transactionId);
}
