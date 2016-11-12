package com.stepping.step5.repository;

import com.stepping.step5.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by faos7 on 12.11.16.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
