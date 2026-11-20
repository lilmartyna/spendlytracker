package com.spendlytracker.spendlytracker.repository;
import com.spendlytracker.spendlytracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import com.spendlytracker.spendlytracker.entity.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
/// calculates total amount of transactions based on the type (INCOME/EXPENSE)
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction  t WHERE t.type = :type")
    BigDecimal calculateTotalByType(@Param("type") TransactionType type);

}
