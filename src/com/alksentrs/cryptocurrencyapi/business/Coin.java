package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.Comparator;

/**
 * Created by ksander on 12/01/18.
 */
public class Coin implements Parcelable {

    public static final String BTC = "BTC";

    private String asset;
    private String pair;
    private String currencyPair;
    private float price;
    private float averagePriceBought;
    private float amount;
    private boolean favorite;
    private long lastTradeTime;
    private long time;

    public Coin(String asset, String pair, float price, long time) {
        this.asset = asset;
        this.pair = pair;
        this.price = price;
        this.time = time;
    }

    public Coin(String asset, String pair, float price, boolean favorite, long time) {
        this.asset = asset;
        this.pair = pair;
        this.price = price;
        this.favorite = favorite;
        this.time = time;
    }

    public Coin(String asset, String pair, float price, float averagePriceBought, float amount, long lastTradeTime, boolean favorite, long time) {
        this.asset = asset;
        this.pair = pair;
        this.price = price;
        this.averagePriceBought = averagePriceBought;
        this.amount = amount;
        this.favorite = favorite;
        this.time = time;
    }

    public Coin(String asset, String pair) {
        this.asset = asset;
        this.pair = pair;
        this.price = 0;
        this.averagePriceBought = 0;
        this.amount = 0;
        this.favorite = false;
        this.time = 0;
    }

    public Coin(String asset, String pair, String currencyPair) {
        this.asset = asset;
        this.pair = pair;
        this.price = 0;
        this.currencyPair = currencyPair;
        this.averagePriceBought = 0;
        this.amount = 0;
        this.favorite = false;
        this.time = 0;
    }

    public String getAsset() {
        return asset;
    }

    public String getPair() {
        return pair;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public float getPrice() {
        return price;
    }

    public String toString() {
        return asset+" "+pair+" "+price+" "+time;
    }

    public long getTime() {
        return time;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public float getAveragePriceBought() {
        return averagePriceBought;
    }

    public void setAveragePriceBought(float averagePriceBought) {
        this.averagePriceBought = averagePriceBought;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setLastTradeTime(long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public long getLastTradeTime() {
        return lastTradeTime;
    }

    public static Comparator<Coin> CoinAssetAscComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            String asset1 = coin1.getAsset().toUpperCase();
            String asset2 = coin2.getAsset().toUpperCase();
            //ascending order
            return asset1.compareTo(asset2);
        }
    };
    public static Comparator<Coin> CoinAssetDescComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            String asset1 = coin1.getAsset().toUpperCase();
            String asset2 = coin2.getAsset().toUpperCase();
            //descending order
            return asset2.compareTo(asset1);
        }
    };

    public static Comparator<Coin> CoinPriceAscComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            float price1 = coin1.getPrice();
            float price2 = coin2.getPrice();
            float delta = price1-price2;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            //ascending order
            return Math.round(delta);
        }
    };
    public static Comparator<Coin> CoinPriceDescComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            float price1 = coin1.getPrice();
            float price2 = coin2.getPrice();
            float delta = price2-price1;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            //ascending order
            return Math.round(delta);
        }
    };
    public static Comparator<Coin> CoinProfitAscComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            float price1 = coin1.getPrice();
            float price2 = coin2.getPrice();
            float priceBought1 = coin1.getAveragePriceBought();
            float priceBought2 = coin2.getAveragePriceBought();
            float delta = (price1-priceBought1)-(price2-priceBought2);
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            //ascending order
            return Math.round(delta);
        }
    };
    public static Comparator<Coin> CoinProfitDescComparator
            = new Comparator<Coin>() {

        public int compare(Coin coin1, Coin coin2) {
            float price1 = coin1.getPrice();
            float price2 = coin2.getPrice();
            float priceBought1 = coin1.getAveragePriceBought();
            float priceBought2 = coin2.getAveragePriceBought();
            float delta = (price2-priceBought2)-(price1-priceBought1);
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            //ascending order
            return Math.round(delta);
        }
    };

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public Coin createFromParcel(Parcel in) {
            return new Coin(in);
        }

        public Coin[] newArray(int size) {
            return new Coin[size];
        }
    };

    // Parcelling part
    public Coin(Parcel in){
        asset = in.readString();
        pair = in.readString();
        currencyPair = in.readString();
        price = in.readFloat();
        averagePriceBought = in.readFloat();
        amount = in.readFloat();
        favorite = in.readByte() != 0;
        lastTradeTime = in.readLong();
        time = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(asset);
        dest.writeString(pair);
        dest.writeString(currencyPair);
        dest.writeFloat(price);
        dest.writeFloat(averagePriceBought);
        dest.writeFloat(amount);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeLong(lastTradeTime);
        dest.writeLong(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
