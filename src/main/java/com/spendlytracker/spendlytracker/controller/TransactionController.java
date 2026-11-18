package com.spendlytracker.spendlytracker.controller;
import com.spendlytracker.spendlytracker.entity.Transaction;
import com.spendlytracker.spendlytracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/// handles HTTP requests and returns data
@RestController
/// sets base URL for all our transaction end points
@RequestMapping("/api/transactions")

public class TransactionController {
    private final TransactionService transactionService;

/// gives controller access to service layer
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

/// creates transactions
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

/// retrieve transactions
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

/// delete transactions
    @DeleteMapping("/{id}")
    public void deleteTransacion(@PathVariable Long id){
        transactionService.deleteTransaction(id);
    }

}
