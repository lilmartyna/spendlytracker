package com.spendlytracker.spendlytracker.repository;
import com.spendlytracker.spendlytracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
