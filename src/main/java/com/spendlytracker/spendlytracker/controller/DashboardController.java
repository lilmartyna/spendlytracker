package com.spendlytracker.spendlytracker.controller;
import com.spendlytracker.spendlytracker.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Map;

/// handles API requests for dashboard

    @RestController
    @RequestMapping("/api/dashboard")

public class DashboardController {
        private final TransactionService transactionService;

/// constructor injection provides access to transaction calculations
    public DashboardController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

/// return total income, expense and balance
    @GetMapping("/summary")
    public Map<String, BigDecimal> getDashboardSummary(){

        return Map.of(
                "totalIncome", transactionService.getTotalIncome(),
                "totalExpenses", transactionService.getTotalExpense(),
                "balance", transactionService.getBalance()

                );
    }
}
