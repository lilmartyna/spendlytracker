package com.spendlytracker.spendlytracker.service;

import com.spendlytracker.spendlytracker.entity.Transaction;
import com.spendlytracker.spendlytracker.repository.TransactionRepository;
import com.spendlytracker.spendlytracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.spendlytracker.spendlytracker.entity.TransactionCategories;
import com.spendlytracker.spendlytracker.entity.TransactionType;
import java.math.BigDecimal;

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

/// validates that the category matches the transaction type
        validateTransactionCategory(transaction);

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

/// validate that the category matches the updated transaction type
           validateTransactionCategory(updatedTransaction);

/// update transaction fields
       transaction.setAmount(updatedTransaction.getAmount());
       transaction.setCategory(updatedTransaction.getCategory());
       transaction.setDate(updatedTransaction.getDate());
       transaction.setDescription(updatedTransaction.getDescription());
       transaction.setType(updatedTransaction.getType());

/// save and return updated transactions
       return transactionRepository.save(transaction);

       }

/// throw an exception if transaction doesn't exist
       throw new ResourceNotFoundException(
               "Transaction with ID: " +id + " cannot be found."
       );
    }

/// checks if a category is valid for the transaction type chosen
    private void validateTransactionCategory(Transaction transaction){
/// checks if chosen income category is permitted
    if(transaction.getType() == TransactionType.INCOME) {
        if(transaction.getCategory() != TransactionCategories.SALARY &&
                transaction.getCategory() != TransactionCategories.OTHER){

            throw new IllegalArgumentException(
                    "Income transactions must use SALARY or OTHER categories!"
            );
        }
    }

    }

/// calculates total income from all transactions
    public BigDecimal getTotalIncome(){
        return transactionRepository.calculateTotalByType(TransactionType.INCOME);
    }

/// calculates total expense from all transactions
    public BigDecimal getTotalExpense(){
        return transactionRepository.calculateTotalByType(TransactionType.EXPENSE);
    }

/// calculate the users current balance
    public BigDecimal getBalance(){
        return getTotalIncome().subtract(getTotalExpense());
    }

}


