package com.alksentrs.cryptocurrencyapi.business.api;

import com.alksentrs.cryptocurrencyapi.business.*;

/**
 * Created by ksander on 16/02/18.
 */

public interface IExchangeConnection {

    void setApiKey(String apiKey);
    void setSecret(String secret);
    ServerTime getServerTime();
    CoinList getPrices(String[] coins);
    BalanceList getBalanceList();
    TradeList getTradeList(String pair);
    DepositList getDepositWithdrawList(String asset);
}
