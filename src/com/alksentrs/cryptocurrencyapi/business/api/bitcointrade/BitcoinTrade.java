package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

import com.alksentrs.cryptocurrencyapi.business.*;
import com.alksentrs.cryptocurrencyapi.business.api.ApiConnection;
import com.alksentrs.cryptocurrencyapi.business.api.IExchangeConnection;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BitcoinTrade extends ApiConnection implements IExchangeConnection {

    public static final String [] ASSETSPAIR = {"BRLBTC","BRLETH","BRLLTC","BRLXRP","BRLBCH","BRLEOS","BRLDAI"};

    private static final String [] ASSETS = {"BTC","ETH","LTC","XRP","BCH","EOS","DAI"};
    private static final String PAIR = "BRL";

    private static final String BASE_URL = "https://api.bitcointrade.com.br";

    private static final String VERSION = "/v3";

    private static final String PUBLIC  = "/public";

    private static final String TICKER  = "/ticker";

    private static final String DATA = "data";

    private static final String CONTENT_TYPE  = "Content-Type";
    private static final String APPLICATION_JSON  = "application/json";

    private ObjectMapper objectMapper = new ObjectMapper();
    private JSONParser jsonParser = new JSONParser();

    @Override
    public ServerTime getServerTime() {
        return null;
    }

    @Override
    public CoinList getPrices(String[] coins) {
        CoinList coinList = null;

        try {
            if (null!= coins) {
                coinList = new CoinList();
                for (int i=0; i<coins.length; i++) {
                    String assetPair = coins[i];
                    String TICKER_URL = BASE_URL + VERSION + PUBLIC + "/" + assetPair + TICKER;
                    Map<String,String> header = new HashMap<>();
                    header.put(CONTENT_TYPE,APPLICATION_JSON);
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(call(TICKER_URL,header));
                    String data = jsonObject.get(DATA).toString();

                    if (null!=data) {
                        BitcoinTradeTicker ticker = objectMapper.readValue(data, BitcoinTradeTicker.class);

                        String asset = assetPair.replace(PAIR, "");
                        String pair = PAIR;
                        double price = ticker.getLast();
                        long time = ticker.getDateDate().getTime();

                        Coin coin = new Coin(asset, pair, price, time);

                        coinList.add(coin);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coinList;
    }

    @Override
    public BalanceList getBalanceList() {
        return null;
    }

    @Override
    public TradeList getTradeList(String asset) {
        return null;
    }

    @Override
    public DepositList getDepositWithdrawList(String asset) {
        return null;
    }
}
