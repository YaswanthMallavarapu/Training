package com.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal aumAmount;
    private BigDecimal expenseRatio;

    private Instant createdAt;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Fund() {
    }
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Fund(BigDecimal aumAmount, Instant createdAt, BigDecimal expenseRatio, Long id, Manager manager, String name) {
        this.aumAmount = aumAmount;
        this.createdAt = createdAt;
        this.expenseRatio = expenseRatio;
        this.id = id;
        this.manager = manager;
        this.name = name;
    }

    public BigDecimal getAumAmount() {
        return aumAmount;
    }

    public void setAumAmount(BigDecimal aumAmount) {
        this.aumAmount = aumAmount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(BigDecimal expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "aumAmount=" + aumAmount +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", expenseRatio=" + expenseRatio +
                ", createdAt=" + createdAt +
                ", manager=" + manager +
                '}';
    }
}
