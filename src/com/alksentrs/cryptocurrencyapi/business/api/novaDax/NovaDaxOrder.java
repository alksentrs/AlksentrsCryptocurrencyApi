package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

public class NovaDaxOrder {

    private String id;
    private String symbol;
    private String type;
    private String side;
    private double price;
    private double averagePrice;
    private double amount;
    private double filledAmount;
    private double value;
    private double filledValue;
    private double filledFee;
    private String status;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFilledAmount() {
        return filledAmount;
    }

    public void setFilledAmount(double filledAmount) {
        this.filledAmount = filledAmount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getFilledValue() {
        return filledValue;
    }

    public void setFilledValue(double filledValue) {
        this.filledValue = filledValue;
    }

    public double getFilledFee() {
        return filledFee;
    }

    public void setFilledFee(double filledFee) {
        this.filledFee = filledFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{\"id\" : " + (id == null ? null : "\"" + id + "\"") + ",\"symbol\" : " + (symbol == null ? null : "\"" + symbol + "\"") + ",\"type\" : " + (type == null ? null : "\"" + type + "\"") + ",\"side\" : " + (side == null ? null : "\"" + side + "\"") + ",\"price\" : " + price + ",\"averagePrice\" : " + averagePrice + ",\"amount\" : " + amount + ",\"filledAmount\" : " + filledAmount + ",\"value\" : " + value + ",\"filledValue\" : " + filledValue + ",\"filledFee\" : " + filledFee + ",\"status\" : " + (status == null ? null : "\"" + status + "\"") + ",\"timestamp\" : " + timestamp + "}";
    }
}
