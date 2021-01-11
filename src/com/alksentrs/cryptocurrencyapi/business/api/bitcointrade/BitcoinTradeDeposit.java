package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradeDeposit {

    private String code;
    private String hash;
    private double amount;
    private String status;
    private String create_date;
    private String confirmation_date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(String confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    @Override
    public String toString() {
        return "{\"code\" : " + (code == null ? null : "\"" + code + "\"") + ",\"hash\" : " + (hash == null ? null : "\"" + hash + "\"") + ",\"amount\" : " + amount + ",\"status\" : " + (status == null ? null : "\"" + status + "\"") + ",\"create_date\" : " + (create_date == null ? null : "\"" + create_date + "\"") + ",\"confirmation_date\" : " + (confirmation_date == null ? null : "\"" + confirmation_date + "\"") + "}";
    }
}
