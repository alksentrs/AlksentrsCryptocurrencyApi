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
    private float free;
    private float locked;
    private float value;

    public Balance(String asset, float free, float locked) {
        this.asset = asset;
        this.free = free;
        this.locked = locked;
        value = 0;
    }

    public Balance(String asset, float free, float locked, float btcPrice) {
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

    public float getFree() {
        return free;
    }

    public float getLocked() {
        return locked;
    }

    public float getValue() {
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
            float fbalance1 = balance1.getFree()+balance1.getLocked();
            float fbalance2 = balance2.getFree()+balance2.getLocked();
            //ascending order
            float delta = fbalance1-fbalance2;
            if (0!=delta) while (Math.abs(delta) < 100) delta = delta * 10;
            return Math.round(delta);
        }
    };

    public static Comparator<Balance> BalanceBalanceDescComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            float fbalance1 = balance1.getFree()+balance1.getLocked();
            float fbalance2 = balance2.getFree()+balance2.getLocked();
            //descending order
            float delta = fbalance2-fbalance1;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round(delta);
        }
    };

    public static Comparator<Balance> BalanceValueAscComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            float value1 = balance1.value;
            float value2 = balance2.value;
            //ascending order
            float delta = value1-value2;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round(delta);
        }
    };

    public static Comparator<Balance> BalanceValueDescComparator = new Comparator<Balance>() {
        public int compare(Balance balance1, Balance balance2) {
            float value1 = balance1.value;
            float value2 = balance2.value;
            //descending order
            float delta = value2-value1;
            if (0!=delta) while (Math.abs(delta) < 100) delta=delta*10;
            return Math.round(delta);
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
