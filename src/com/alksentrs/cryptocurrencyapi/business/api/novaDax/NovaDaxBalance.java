package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

public class NovaDaxBalance {

    private double available;
    private double balance;
    private String currency;
    private int hold;

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    @Override
    public String toString() {
        return "{\"available\" : " + available + ",\"balance\" : " + balance + ",\"currency\" : " + (currency == null ? null : "\"" + currency + "\"") + ",\"hold\" : " + hold + "}";
    }
}
