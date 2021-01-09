package com.alksentrs.cryptocurrencyapi.business;

import android.os.Parcel;
import android.os.Parcelable;
import com.alksentrs.cryptocurrencyapi.util.Creator;

import java.util.*;

/**
 * Created by ksander on 14/01/18.
 */

public class AverageBalanceList implements Iterable<AverageBalance>, Parcelable {

    private int size;
    private Map<String, AverageBalance> averageBalanceMap;

    public AverageBalanceList() {
        size = 0;
        averageBalanceMap = new HashMap<>();
    }

    public int size() {
        return size;
    }

    public AverageBalance get(int i){
        if (i<size) {
            return averageBalanceMap.get((String) averageBalanceMap.keySet().toArray()[i]);
        } else {
            return null;
        }
    }

    public List<String> getAssetList() {
        ArrayList<String> ret = new ArrayList<>();
        ret.addAll(averageBalanceMap.keySet());
        return ret;
    }

    public void put(String asset, AverageBalance AverageBalance) {
        averageBalanceMap.put(asset,AverageBalance);
        size++;
    }

    public AverageBalance get(String asset) {
        return averageBalanceMap.get(asset);
    }

    public String toString() {
        String ret = "";
        Iterator<AverageBalance> it = this.iterator();
        while (it.hasNext()) ret = ret+" : "+it.next().toString();
        return ret;
    }

    @Override
    public Iterator<AverageBalance> iterator() {

        Iterator<AverageBalance> it = new Iterator<AverageBalance>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < averageBalanceMap.keySet().size();
            }

            @Override
            public AverageBalance next() {
                if (currentIndex < averageBalanceMap.keySet().size() ) {
                    return averageBalanceMap.get(averageBalanceMap.keySet().toArray()[currentIndex++]);
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
        public AverageBalanceList createFromParcel(Parcel in) {
            return new AverageBalanceList(in);
        }

        public AverageBalanceList[] newArray(int size) {
            return new AverageBalanceList[size];
        }
    };

    // Parcelling part
    public AverageBalanceList(Parcel in){
        size = in.readInt();
        averageBalanceMap = new HashMap<>();
        for (int i=0; i<size; i++) {
            AverageBalance AverageBalance = in.readParcelable(AverageBalance.class.getClassLoader());
            averageBalanceMap.put(AverageBalance.getAsset(),AverageBalance);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        Iterator<AverageBalance> it = this.iterator();
        while (it.hasNext()) {
            dest.writeParcelable(it.next(),flags);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }
}
