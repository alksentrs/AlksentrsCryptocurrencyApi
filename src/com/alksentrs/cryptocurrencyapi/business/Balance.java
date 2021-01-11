package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.Comparator;

/**
 * Created by ksander on 12/01/18.
 */
public class Balance implements Parcelable {

    private String asset;
    private double free;
    private double locked;
    private double value;

    public Balance(String asset, double free, double locked) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
        value = 0;
    }

    public Balance(String asset, double free, double locked, double btcPrice) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
        value = (free+locked)*btcPrice;
    }

    public void calculateValue(float btcPrice) {
        value = (free+locked)*btcPrice;
    }

    public String getAsset() {
        return asset;
    }

    public double getFree() {
        return free;
    }

    public double getLocked() {
        return locked;
    }

    public double getValue() {
        return value;
    }

    public static Comparator<Balance> BalanceAssetAscComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            String asset1 = balance1.getAsset().toUpperCase();
            String asset2 = balance2.getAsset().toUpperCase();
            //ascending order
            return asset1.compareTo(asset2);
        }
    };
    public static Comparator<Balance> BalanceAssetDescComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            String asset1 = balance1.getAsset().toUpperCase();
            String asset2 = balance2.getAsset().toUpperCase();
            //ascending order
            return asset2.compareTo(asset1);
        }
    };

    public static Comparator<Balance> BalanceBalanceAscComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            double fbalance1 = balance1.getFree()+balance1.getLocked();
            double fbalance2 = balance2.getFree()+balance2.getLocked();
            //ascending order
            double delta = fbalance1-fbalance2;
            if (0!=delta) while (Math.abs(delta) < 100) delta = delta * 10;
            return Math.round((float)delta);
        }
    };

    public static Comparator<Balance> BalanceBalanceDescComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            double fbalance1 = balance1.getFree()+balance1.getLocked();
            double fbalance2 = balance2.getFree()+balance2.getLocked();
            //descending order
            double delta = fbalance2-fbalance1;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round((float)delta);
        }
    };

    public static Comparator<Balance> BalanceValueAscComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            double value1 = balance1.value;
            double value2 = balance2.value;
            //ascending order
            double delta = value1-value2;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round((float)delta);
        }
    };

    public static Comparator<Balance> BalanceValueDescComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            double value1 = balance1.value;
            double value2 = balance2.value;
            //descending order
            double delta = value2-value1;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round((float)delta);
        }
    };

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public Balance createFromParcel(Parcel in) {
            return new Balance(in);
        }

        public Balance[] newArray(int size) {
            return new Balance[size];
        }
    };

    // Parcelling part
    public Balance(Parcel in){
        asset = in.readString();
        free = in.readFloat();
        locked = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(asset);
        dest.writeFloat(free);
        dest.writeFloat(locked);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "asset:" + asset + "," +
                "free:" + free + "," +
                "locked:" + locked + "," +
                "value:" + value +
                "}";
    }
}
