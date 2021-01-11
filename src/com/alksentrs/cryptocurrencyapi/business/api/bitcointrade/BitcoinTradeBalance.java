package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradeBalance {

    private double available_amount;
    private double locked_amount;
    private String currency_code;

    public double getAvailable_amount() {
        return available_amount;
    }

    public void setAvailable_amount(double available_amount) {
        this.available_amount = available_amount;
    }

    public double getLocked_amount() {
        return locked_amount;
    }

    public void setLocked_amount(double locked_amount) {
        this.locked_amount = locked_amount;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    @Override
    public String toString() {
        return "{\"available_amount\" : " + available_amount + ",\"locked_amount\" : " + locked_amount + ",\"currency_code\" : " + currency_code + "}";
    }
}
