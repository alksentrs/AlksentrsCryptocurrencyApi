package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.Iterator;


/**
 * Created by ksander on 12/01/18.
 */
public class Account implements Parcelable {

    private BalanceList balanceList;

    public Account() {
        balanceList = new BalanceList();
    }

    public String toString() {
        String ret = "";
        Iterator<Balance> it = balanceList.iterator();
        while (it.hasNext()) {
            Balance balance = it.next();
            if (null!=balance) ret = ret + " : " + balance.toString();
        }
        return ret;
    }

    public void addBalance(Balance balance) {
        balanceList.add(balance);
    }

    public BalanceList getBalanceList() {
        return balanceList;
    }



    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    // Parcelling part
    public Account(Parcel in){
        int size = in.readInt();
        balanceList = new BalanceList();
        for (int i=0; i<size; i++) {
            Balance balance = in.readParcelable(Balance.class.getClassLoader());
            balanceList.add(balance);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(balanceList.size());
        Iterator<Balance> it = balanceList.iterator();
        while (it.hasNext()) {
            dest.writeParcelable(it.next(),flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
