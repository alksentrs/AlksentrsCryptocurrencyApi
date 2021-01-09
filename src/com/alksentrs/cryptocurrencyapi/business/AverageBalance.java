package com.alksentrs.cryptocurrencyapi.business;


import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

/**
 * Created by ksander on 13/01/18.
 */
public class AverageBalance implements Parcelable {

    private String asset;
    private float qty;
    private float price;
    private float profit;
    private long lastTime;

    public AverageBalance(String asset, float qty, float price, float profit, long lastTime) {
        this.asset = asset;
        this.qty = qty;
        this.price = price;
        this.profit = profit;
        this.lastTime = lastTime;
    }

    public String toString() {
        return qty+" "+price+" "+profit+" "+lastTime;
    }

    public String getAsset() {
        return asset;
    }

    public float getQty() {
        return qty;
    }

    public float getPrice() {
        return price;
    }

    public float getProfit() {
        return profit;
    }

    public long getLastTime() {
        return lastTime;
    }

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public AverageBalance createFromParcel(Parcel in) {
            return new AverageBalance(in);
        }

        public AverageBalance[] newArray(int size) {
            return new AverageBalance[size];
        }
    };

    // Parcelling part
    public AverageBalance(Parcel in){
        asset = in.readString();
        qty = in.readFloat();
        price = in.readFloat();
        profit = in.readFloat();
        lastTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(asset);
        dest.writeFloat(qty);
        dest.writeFloat(price);
        dest.writeFloat(profit);
        dest.writeLong(lastTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
