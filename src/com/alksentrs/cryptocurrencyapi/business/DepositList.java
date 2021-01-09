package com.alksentrs.cryptocurrencyapi.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ksander on 12/01/18.
 */
public class DepositList implements Iterable<Deposit> {

    private List<Deposit> depositList;

    public DepositList() {
        depositList = new ArrayList<>();
    }

    public List<Deposit> getList() {
        return depositList;
    }

    public void addAll(DepositList depositList) {
        if(null!=depositList) this.depositList.addAll(depositList.getList());
    }

    public void add(Deposit deposit) {
        depositList.add(deposit);
    }

    @Override
    public Iterator<Deposit> iterator() {

        Iterator<Deposit> it = new Iterator<Deposit>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < depositList.size();
            }

            @Override
            public Deposit next() {
                if (currentIndex < depositList.size() ) {
                    return depositList.get(currentIndex++);
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


    @Override
    public String toString() {
        return "{" +
                "depositList:" + depositList +
                "}";
    }
}
