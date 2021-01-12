package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

public class NovaDaxDeposit {

    private String id;
    private String  type;
    private String currency;
    private String chain;
    private String address;
    private String addressTag;
    private double amount;
    private String state;
    private String txHash;
    private long createdAt;
    private long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressTag() {
        return addressTag;
    }

    public void setAddressTag(String addressTag) {
        this.addressTag = addressTag;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "{\"id\" : " + (id == null ? null : "\"" + id + "\"") + ",\"type\" : " + (type == null ? null : type) + ",\"currency\" : " + (currency == null ? null : "\"" + currency + "\"") + ",\"chain\" : " + (chain == null ? null : "\"" + chain + "\"") + ",\"address\" : " + (address == null ? null : "\"" + address + "\"") + ",\"addressTag\" : " + (addressTag == null ? null : "\"" + addressTag + "\"") + ",\"amount\" : " + amount + ",\"state\" : " + (state == null ? null : "\"" + state + "\"") + ",\"txHash\" : " + (txHash == null ? null : "\"" + txHash + "\"") + ",\"createdAt\" : " + createdAt + ",\"updatedAt\" : " + updatedAt + "}";
    }
}
