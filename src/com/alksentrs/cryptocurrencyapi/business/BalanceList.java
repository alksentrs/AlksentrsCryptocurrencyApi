package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ksander on 15/01/18.
 */

public class BalanceList implements Iterable<Balance>, Parcelable {

    private int size;
    private List<Balance> balanceList;

    public BalanceList() {
        size = 0;
        balanceList = new ArrayList<>();
    }

    public List<Balance> getList() {
        return balanceList;
    }

    public int size() {
        return size;
    }

    public Balance get(int i){
        if (i<size) {
            return balanceList.get(i);
        }
        return null;
    }

    public void add(Balance balance) {
        balanceList.add(balance);
        size++;
    }

    @Override
    public Iterator<Balance> iterator() {

        Iterator<Balance> it = new Iterator<Balance>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < balanceList.size();
            }

            @Override
            public Balance next() {
                if (currentIndex < balanceList.size() ) {
                    return balanceList.get(currentIndex++);
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    // Parcell part
    public static final Creator CREATOR = new Creator() {
        public BalanceList createFromParcel(Parcel in) {
            return new BalanceList(in);
        }

        public BalanceList[] newArray(int size) {
            return new BalanceList[size];
        }
    };

    // Parcelling part
    public BalanceList(Parcel in){
        size = in.readInt();
        balanceList = new ArrayList<>();
        for (int i=0; i<size; i++) {
            Balance balance = in.readParcelable(Balance.class.getClassLoader());
            balanceList.add(balance);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        Iterator<Balance> it = this.iterator();
        while (it.hasNext()) {
            dest.writeParcelable(it.next(),flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "{" +
                "balanceList:" + balanceList +
                "}";
    }
}
