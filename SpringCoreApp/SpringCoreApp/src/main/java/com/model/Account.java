package com.model;

public class Account {
    private int id;
    private String accountNumber;
    private String accountBalance;

    public Account() {
    }

    public Account(String accountBalance, String accountNumber, int id) {
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
        this.id = id;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountBalance='" + accountBalance + '\'' +
                ", id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
