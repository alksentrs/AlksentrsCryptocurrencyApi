package com.alksentrs.cryptocurrencyapi.application;

import com.alksentrs.cryptocurrencyapi.business.api.IExchangeConnection;
import com.alksentrs.cryptocurrencyapi.business.api.bitcointrade.BitcoinTrade;
import com.alksentrs.cryptocurrencyapi.business.api.novaDax.NovaDax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest {

    public static void main(String[] args) {

        /*
        BitcoinTrade connection = new BitcoinTrade();
        //connection.getPrices(BitcoinTrade.ASSETSPAIR);
        connection.setApiKey("U2FsdGVkX181YqkPXc+JrcLhcE9kn7yBpla+ZmDMyJm+amA8MkGG2WugiPX+OAuk");
        connection.getDepositWithdrawList(BitcoinTrade.BITCOIN);
         */

        NovaDax connection = new NovaDax();
        connection.setSecret("andaYi42PfFeTLBkz6oomTv99bH7A2HU");
        connection.setApiKey("615a29bd-3d10-457d-a198-d5dcde99ebc6");
        System.out.println(connection.getBalanceList());
        System.out.println(connection.getTradeList("BTC_BRL"));
        System.out.println(connection.getDepositWithdrawList("BTC"));
    }

}
