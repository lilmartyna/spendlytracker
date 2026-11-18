package com.spendlytracker.spendlytracker.service;

import com.spendlytracker.spendlytracker.entity.Transaction;
import com.spendlytracker.spendlytracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/// handles logic for transaction operations
@Service
public class TransactionService {

/// repository dependency used to perform database operations
    private final TransactionRepository transactionRepository;

/// constructor injection allows Spring Boot to provide the repository automatically
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

/// saves a new transaction to the database | returns saved transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

/// retrieves all transactions from the database | returns list containing all transactions
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

/// deletes a transaction using its id
    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

/// updates an existing transaction using its id. Updates its fields and saves changes
   public Transaction updateTransaction(Long id, Transaction updatedTransaction){

/// find existing transaction in database using id
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);

/// check if transaction exists
       if (existingTransaction.isPresent()){
           Transaction transaction = existingTransaction.get();

/// update transaction fields
       transaction.setAmount(updatedTransaction.getAmount());
       transaction.setCategory(updatedTransaction.getCategory());
       transaction.setDate(updatedTransaction.getDate());
       transaction.setDescription(updatedTransaction.getDescription());
       transaction.setType(updatedTransaction.getType());

/// save and return updated transactions
       return transactionRepository.save(transaction);

       }

/// return null if transaction doesnt exist
       return null;
    }
}


