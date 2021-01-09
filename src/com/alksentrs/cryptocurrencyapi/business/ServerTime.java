package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

/**
 * Created by ksander on 12/01/18.
 */
public class ServerTime implements Parcelable {

    private long serverTime;

    public ServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public long getServerTime() {
        return serverTime;
    }

    public String toString() {
        return String.valueOf(serverTime);
    }

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public ServerTime createFromParcel(Parcel in) {
            return new ServerTime(in);
        }

        public ServerTime[] newArray(int size) {
            return new ServerTime[size];
        }
    };

    // Parcelling part
    public ServerTime(Parcel in){
        this.serverTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.serverTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
