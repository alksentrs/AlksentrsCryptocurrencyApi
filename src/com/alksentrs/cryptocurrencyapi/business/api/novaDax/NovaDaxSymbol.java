package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

public class NovaDaxSymbol {

    private int amountPrecision;
    private String baseCurrency;
    private double minOrderAmount;
    private double minOrderValue;
    private int pricePrecision;
    private String quoteCurrency;
    private String status;
    private String symbol;
    private int valuePrecision;

    public int getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(int amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public double getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(double minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public int getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(int pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getValuePrecision() {
        return valuePrecision;
    }

    public void setValuePrecision(int valuePrecision) {
        this.valuePrecision = valuePrecision;
    }

    @Override
    public String toString() {
        return "{\"amountPrecision\" : " + amountPrecision + ",\"baseCurrency\" : " + (baseCurrency == null ? null : "\"" + baseCurrency + "\"") + ",\"minOrderAmount\" : " + minOrderAmount + ",\"minOrderValue\" : " + minOrderValue + ",\"pricePrecision\" : " + pricePrecision + ",\"quoteCurrency\" : " + (quoteCurrency == null ? null : "\"" + quoteCurrency + "\"") + ",\"status\" : " + (status == null ? null : "\"" + status + "\"") + ",\"symbol\" : " + (symbol == null ? null : "\"" + symbol + "\"") + ",\"valuePrecision\" : " + valuePrecision + "}";
    }
}
