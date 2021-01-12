package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

public class NovaDaxTicker {

    private double ask;
    private double baseVolume24h;
    private double bid;
    private double high24h;
    private double lastPrice;
    private double low24h;
    private double open24h;
    private double quoteVolume24h;
    private String symbol;
    private long timestamp;

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBaseVolume24h() {
        return baseVolume24h;
    }

    public void setBaseVolume24h(double baseVolume24h) {
        this.baseVolume24h = baseVolume24h;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getHigh24h() {
        return high24h;
    }

    public void setHigh24h(double high24h) {
        this.high24h = high24h;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getLow24h() {
        return low24h;
    }

    public void setLow24h(double low24h) {
        this.low24h = low24h;
    }

    public double getOpen24h() {
        return open24h;
    }

    public void setOpen24h(double open24h) {
        this.open24h = open24h;
    }

    public double getQuoteVolume24h() {
        return quoteVolume24h;
    }

    public void setQuoteVolume24h(double quoteVolume24h) {
        this.quoteVolume24h = quoteVolume24h;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{\"ask\" : " + ask + ",\"baseVolume24h\" : " + baseVolume24h + ",\"bid\" : " + bid + ",\"high24h\" : " + high24h + ",\"lastPrice\" : " + lastPrice + ",\"low24h\" : " + low24h + ",\"open24h\" : " + open24h + ",\"quoteVolume24h\" : " + quoteVolume24h + ",\"symbol\" : " + (symbol == null ? null : "\"" + symbol + "\"") + ",\"timestamp\" : " + timestamp + "}";
    }
}
