package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitcoinTradeTicker {

    private double volume;
    private String date;

    private double high;
    private double last;
    private double trades_quantity;
    private double low;
    private double buy;
    private double sell;

    private SimpleDateFormat sdf;

    public BitcoinTradeTicker() {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        sdf = new SimpleDateFormat(pattern);
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTrades_quantity() {
        return trades_quantity;
    }

    public void setTrades_quantity(double trades_quantity) {
        this.trades_quantity = trades_quantity;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public String getDate() {
        return date;
    }

    public Date getDateDate() {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "{\"volume\" : " + volume + ",\"date\" : " + (date == null ? null : "\"" + date + "\"") + ",\"high\" : " + high + ",\"last\" : " + last + ",\"trades_quantity\" : " + trades_quantity + ",\"low\" : " + low + ",\"buy\" : " + buy + ",\"sell\" : " + sell + "}";
    }
}
