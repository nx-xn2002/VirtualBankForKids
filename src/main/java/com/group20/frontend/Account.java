package com.group20.frontend;

import lombok.Data;

@Data
public class Account {
    /**
     * account name
     */
    private String accountName;
    /**
     * balance
     */
    private double balance;
    public Account(String accountName) {
        this.accountName = accountName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            // 如果账户余额不足，则打印错误消息
            System.out.println("Insufficient funds!");
        }
    }
}