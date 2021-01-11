package com.alksentrs.cryptocurrencyapi.application;

import com.alksentrs.cryptocurrencyapi.business.api.IExchangeConnection;
import com.alksentrs.cryptocurrencyapi.business.api.bitcointrade.BitcoinTrade;

public class AppBitcoinTrade {

    public static void main(String[] args) {

        BitcoinTrade connection = new BitcoinTrade();
        //connection.getPrices(BitcoinTrade.ASSETSPAIR);

        connection.setApiKey("U2FsdGVkX181YqkPXc+JrcLhcE9kn7yBpla+ZmDMyJm+amA8MkGG2WugiPX+OAuk");
        connection.getDepositWithdrawList(BitcoinTrade.BITCOIN);
    }

}
