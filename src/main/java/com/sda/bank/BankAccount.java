package com.sda.bank;

import java.util.Objects;

public class BankAccount {
    private String id;
    private int amount;

    public BankAccount(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount account = (BankAccount) o;
        return amount == account.amount &&
                Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, amount);
    }
}
