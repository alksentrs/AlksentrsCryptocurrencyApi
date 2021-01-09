package com.alksentrs.cryptocurrencyapi.application;

import com.alksentrs.cryptocurrencyapi.business.api.IExchangeConnection;
import com.alksentrs.cryptocurrencyapi.business.api.bitcointrade.BitcoinTrade;

public class AppBitcoinTrade {

    public static void main(String[] args) {

        IExchangeConnection connection = new BitcoinTrade();
        connection.getPrices(BitcoinTrade.ASSETSPAIR);
    }

}
