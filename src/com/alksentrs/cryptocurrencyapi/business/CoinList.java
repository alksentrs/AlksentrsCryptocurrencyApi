package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ksander on 12/01/18.
 */
public class CoinList implements Iterable<Coin>, Parcelable {

    private int size;
    private List<Coin> coinList;

    public CoinList() {
        size = 0;
        coinList = new ArrayList<>();
    }

    public List<Coin> getList() {
        return coinList;
    }

    public List<Coin> sortAscAsset() {
        Collections.sort(coinList, Coin.CoinAssetAscComparator);
        return coinList;
    }

    public List<Coin> sortDescAsset() {
        Collections.sort(coinList, Coin.CoinAssetDescComparator);
        return coinList;
    }

    public List<Coin> sortAscPrice() {
        Collections.sort(coinList, Coin.CoinPriceAscComparator);
        return coinList;
    }
    public List<Coin> sortDescPrice() {
        Collections.sort(coinList, Coin.CoinPriceDescComparator);
        return coinList;
    }
    public List<Coin> sortAscProfit() {
        Collections.sort(coinList, Coin.CoinProfitAscComparator);
        return coinList;
    }
    public List<Coin> sortDescProfit() {
        Collections.sort(coinList, Coin.CoinProfitDescComparator);
        return coinList;
    }

    public int size() {
        return size;
    }

    public Coin get(int i){
        if (i<size) {
            return coinList.get(i);
        } else {
            return null;
        }
    }

    public void add(Coin coin) {
        coinList.add(coin);
        size++;
    }

    @Override
    public Iterator<Coin> iterator() {

        Iterator<Coin> it = new Iterator<Coin>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < coinList.size();
            }

            @Override
            public Coin next() {
                if (currentIndex < coinList.size() ) {
                    return coinList.get(currentIndex++);
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
        public CoinList createFromParcel(Parcel in) {
            return new CoinList(in);
        }

        public CoinList[] newArray(int size) {
            return new CoinList[size];
        }
    };

    // Parcelling part
    public CoinList(Parcel in){
        size = in.readInt();
        coinList = new ArrayList<>();
        for (int i=0; i<size; i++) {
            Coin coin = in.readParcelable(Coin.class.getClassLoader());
            coinList.add(coin);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        Iterator<Coin> it = this.iterator();
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
        return "{\"size\" : " + size + ",\"coinList\" : " + (coinList == null ? null : coinList) + "}";
    }
}
