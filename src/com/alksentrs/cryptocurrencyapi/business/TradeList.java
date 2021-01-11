package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ksander on 12/01/18.
 */
public class TradeList implements Iterable<Trade>, Parcelable {

    private List<Trade> tradeList;

    public TradeList() {
        tradeList = new ArrayList<>();
    }

    public void add(Trade trade) {
        tradeList.add(trade);
    }

    public String toString() {
        String ret = "";
        Iterator<Trade> it = this.iterator();
        while (it.hasNext()) ret = ret+" : "+it.next().toString();
        return ret;
    }

    public int size() { return tradeList.size(); }

    public Trade get(int position) {
        if (null!=tradeList) {
            return tradeList.get(position);
        }
        return null;
    }

    public List<Trade> getTrades() {
        return tradeList;
    }

    public AverageBalance getMeanBalance() {

        double value = 0;
        double qty = 0;
        double price;
        double profit = 0;
        long lastTime = 0;
        String asset = "";

        Iterator<Trade> it = this.iterator();
        while (it.hasNext()) {
            Trade trade = it.next();
            asset = trade.getCoinPair();

            if (!trade.isBypass()) {
                if (trade.isBuyer()) {
                    value = value + trade.getPrice() * trade.getQty();
                    qty = qty + trade.getQty();
                } else {
                    price = value / qty;
                    qty = qty - trade.getQty();
                    value = qty * price;
                    profit = trade.getPrice() * trade.getQty() - price * trade.getQty();
                }
                lastTime = trade.getTime();
            }
        }

        if (0!=qty) {
            price = value / qty;
        } else {
            price = 0;
        }
        AverageBalance averageBalance = new AverageBalance(asset,qty,price,profit,lastTime);

        return averageBalance;

    }

    @Override
    public Iterator<Trade> iterator() {

        Iterator<Trade> it = new Iterator<Trade>() {
            private int currentIndex = tradeList.size()-1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public Trade next() {
                if (currentIndex < tradeList.size() ) {
                    return tradeList.get(currentIndex--);
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
        public TradeList createFromParcel(Parcel in) {
            return new TradeList(in);
        }

        public TradeList[] newArray(int size) {
            return new TradeList[size];
        }
    };

    // Parcelling part
    public TradeList(Parcel in){
        int size = in.readInt();
        tradeList = new ArrayList<>();
        for (int i=0; i<size; i++) {
            Trade trade = in.readParcelable(Trade.class.getClassLoader());
            tradeList.add(trade);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tradeList.size());
        Iterator<Trade> it = this.iterator();
        while (it.hasNext()) {
            dest.writeParcelable(it.next(),flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
