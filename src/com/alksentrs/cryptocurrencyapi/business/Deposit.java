package com.alksentrs.cryptocurrencyapi.business;

/**
 * Created by ksander on 12/01/18.
 */
public class Deposit {

    private String id;
    private long insertTime;
    private double amount;
    private String asset;
    private boolean deposit;
    private boolean homeMade;

    public Deposit(String id, long insertTime, double amount, String asset, boolean deposit, boolean homeMade) {
        this.id = id;
        this.insertTime = insertTime;
        this.amount = amount;
        this.asset = asset;
        this.deposit = deposit;
        this.homeMade = homeMade;
    }

    public String getId() {
        return id;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getAsset() {
        return asset;
    }

    public boolean isDeposit() {return deposit; }

    public boolean isHomeMade() {
        return homeMade;
    }

    public void setHomeMade(boolean homeMade) {
        this.homeMade = homeMade;
    }

    public void setBypass(boolean b) {
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id + "," +
                "insertTime:" + insertTime + "," +
                "amount:" + amount + "," +
                "asset:" + asset + "," +
                "deposit:" + deposit + "," +
                "homeMade:" + homeMade +
                "}";
    }

}
