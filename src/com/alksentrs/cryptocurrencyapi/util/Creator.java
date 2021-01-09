package com.alksentrs.cryptocurrencyapi.util;

import android.os.Parcel;

/**
 * Created by ksander on 15/02/18.
 */
public interface Creator {

    public Object createFromParcel(Parcel in);

    public Object[] newArray(int size);
}
