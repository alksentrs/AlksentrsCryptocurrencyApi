package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

/**
 * Created by ksander on 12/01/18.
 */
public class Trade implements Parcelable {

    private String id;
    private double price;
    private double qty;
    private double commission;
    private String commissionAsset;
    private long time;
    private boolean buyer;
    private boolean maker;
    private boolean bestMatch;
    private boolean bypass;
    private boolean homemade;
    private String coinPair;

    public Trade(String coinPair, String id, double price, double qty, double commission, String commissionAsset, long time, boolean buyer, boolean maker, boolean bestMatch, boolean bypass, boolean homemade) {
        this.coinPair = coinPair;
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.commission = commission;
        this.commissionAsset = commissionAsset;
        this.time = time;
        this.buyer = buyer;
        this.maker = maker;
        this.bestMatch = bestMatch;
        this.bypass = bypass;
        this.homemade = homemade;
    }

    public String toString() {
        return coinPair+" "+id+" "+price+" "+qty+" "+buyer+" "+bypass+" "+homemade+" "+time;
    }

    public String getId() {
        return id;
    }

    public String getCoinPair() {
        return coinPair;
    }

    public double getPrice() {
        return price;
    }

    public double getQty() {
        return qty;
    }

    public double getCommission() {
        return commission;
    }

    public String getCommissionAsset() {
        return commissionAsset;
    }

    public long getTime() {
        return time;
    }

    public boolean isBuyer() {
        return buyer;
    }

    public boolean isMaker() {
        return maker;
    }

    public boolean isBestMatch() {
        return bestMatch;
    }

    public boolean isBypass() {
        return bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }

    public boolean isHomemade() {
        return homemade;
    }

    public void setHomemade(boolean homemade) {
        this.homemade = homemade;
    }

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public Trade createFromParcel(Parcel in) {
            return new Trade(in);
        }

        public Trade[] newArray(int size) {
            return new Trade[size];
        }
    };

    // Parcelling part
    public Trade(Parcel in){
        coinPair = in.readString();
        id = in.readString();
        price = in.readFloat();
        qty = in.readFloat();
        commission = in.readFloat();
        commissionAsset = in.readString();
        time = in.readLong();
        buyer = in.readByte() != 0;
        maker = in.readByte() != 0;
        bestMatch = in.readByte() != 0;
        bypass = in.readByte() != 0;
        homemade = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coinPair);
        dest.writeString(id);
        dest.writeFloat(price);
        dest.writeFloat(qty);
        dest.writeFloat(commission);
        dest.writeString(commissionAsset);
        dest.writeLong(time);
        dest.writeByte((byte) (buyer ? 1 : 0));
        dest.writeByte((byte) (maker ? 1 : 0));
        dest.writeByte((byte) (bestMatch ? 1 : 0));
        dest.writeByte((byte) (bypass ? 1 : 0));
        dest.writeByte((byte) (homemade ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
