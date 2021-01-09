package com.alksentrs.cryptocurrencyapi.business;


import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

/**
 * Created by ksander on 17/01/18.
 */

public class UserKey implements Parcelable {

    private String exchange;
    private String apiKey;
    private String secret;

    public UserKey(String exchange, String apiKey, String secret) {
        this.exchange = exchange;
        this.apiKey = apiKey;
        this.secret = secret;
    }

    public String getExchange() {
        return exchange;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public UserKey createFromParcel(Parcel in) {
            return new UserKey(in);
        }

        public UserKey[] newArray(int size) {
            return new UserKey[size];
        }
    };

    // Parcelling part
    public UserKey(Parcel in){
        exchange = in.readString();
        apiKey = in.readString();
        secret = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exchange);
        dest.writeString(apiKey);
        dest.writeString(secret);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
