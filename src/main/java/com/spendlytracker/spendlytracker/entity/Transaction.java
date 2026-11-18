package com.spendlytracker.spendlytracker.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/// represents transaction in spendlytracker
@Entity
@Table (name = "transactions")
public class Transaction {

/// variables

/// primary key | database generates this value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/// stores transaction amount | cannot be null, up to 12 digits with 2 decimal places
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

/// stores if transaction is income or an expense | cannot be null
    @Column(nullable = false)
    private String type;

/// stores transaction category | cannot be null
    @Column(nullable = false)
    private String category;

/// stores the date the transaction occured | cannot be null
    @Column(nullable = false)
    private LocalDate date;

/// transaction description | optional
    private String description;

/// empty constructor
    public Transaction() {
    }

/// getters and setters


/// returns transactions id
    public Long getId() {

        return id;
    }

/// returns transactions amount
    public BigDecimal getAmount() {

        return amount;
    }

/// updates transactions amount
    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

/// returns transactions type
    public String getType() {

        return type;
    }

/// sets transactions type
    public void setType(String type) {

        this.type = type;
    }

/// returns transactions category
    public String getCategory() {

        return category;
    }

/// updates transactions amount
    public void setCategory(String category) {

        this.category = category;
    }

/// returns transactions description
    public String getDescription() {

        return description;
    }

/// updates transactions description
    public void setDescription(String description) {

        this.description = description;
    }

/// returns transactions date
    public LocalDate getDate() {

        return date;
    }

/// updates transactions date
    public void setDate(LocalDate date) {

        this.date = date;
    }
}

