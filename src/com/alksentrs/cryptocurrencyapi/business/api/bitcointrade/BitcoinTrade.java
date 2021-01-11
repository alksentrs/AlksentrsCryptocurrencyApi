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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BitcoinTrade extends ApiConnection implements IExchangeConnection {

    public static final String [] ASSETSPAIR = {"BRLBTC","BRLETH","BRLLTC","BRLXRP","BRLBCH","BRLEOS","BRLDAI"};

    public static final String BRL_BTC = "BRLBTC";
    public static final String BRL_ETH = "BRLETH";

    public static final String BITCOIN  = "bitcoin";
    public static final String RIPPLE  = "ripple";
    public static final String LITECOIN  = "litecoin";
    public static final String ETHEREUM  = "ethereum";
    public static final String EOS  = "eos";
    public static final String DAI  = "dai";
    public static final String BITCOIN_CASH  = "bitcoincash";

    private static final String [] ASSETS = {"BTC","ETH","LTC","XRP","BCH","EOS","DAI"};
    private static final String PAIR = "BRL";

    private static final String BASE_URL = "https://api.bitcointrade.com.br";

    private static final String URL_VERSION = "/v3";
    private static final String URL_PUBLIC = "/public";
    private static final String URL_TICKER = "/ticker";
    private static final String URL_WALLETS = "/wallets";
    private static final String URL_BALANCE = "/balance";
    private static final String URL_MARKET = "/market";
    private static final String URL_USER_ORDERS = "/user_orders";
    private static final String URL_LIST = "/list";
    private static final String URL_DEPOSITS  = "/deposits";
    private static final String URL_WITHDRAW  = "/withdraw";
    private static final String URL_BITCOIN  = "/bitcoin";
    private static final String URL_RIPPLE  = "/ripple";
    private static final String URL_LITECOIN  = "/litecoin";
    private static final String URL_ETHEREUM  = "/ethereum";
    private static final String URL_EOS  = "/eos";
    private static final String URL_DAI  = "/dai";
    private static final String URL_BITCOIN_CASH  = "/bitcoincash";

    private static final String JSON_KEY_DATA = "data";
    private static final String JSON_KEY_ORDERS = "orders";
    private static final String JSON_KEY_PAGINATION = "pagination";
    private static final String JSON_KEY_DEPOSITS = "deposits";
    private static final String JSON_KEY_WITHDRAWS = "withdrawals";

    private static final String QUERY_PARAM_STATUS = "status";
    private static final String QUERY_VALUE_EXECUTED_COMPLETELY = "executed_completely";
    private static final String QUERY_VALUE_CONFIRMED = "confirmed";
    private static final String QUERY_PARAM_PAIR = "pair";
    private static final String QUERY_PARAM_PAGE_SIZE = "page_size";
    private static final String QUERY_PARAM_CURRENT_PAGE = "current_page";

    private static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_VALUE_APPLICATION_JSON = "application/json";

    private static final String HEADER_KEY_X_API_KEY = "x-api-key";

    private ObjectMapper objectMapper = new ObjectMapper();
    private JSONParser jsonParser = new JSONParser();

    private String apiKey;
    private String secret;

    private SimpleDateFormat sdf;

    private Map<String,String> assetUrlMap;

    public BitcoinTrade() {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        sdf = new SimpleDateFormat(pattern);

        assetUrlMap = new HashMap<>();
        assetUrlMap.put(BITCOIN,URL_BITCOIN);
        assetUrlMap.put(RIPPLE,URL_RIPPLE);
        assetUrlMap.put(LITECOIN,URL_LITECOIN);
        assetUrlMap.put(BITCOIN_CASH,URL_BITCOIN_CASH);
        assetUrlMap.put(EOS,URL_EOS);
        assetUrlMap.put(DAI,URL_DAI);
        assetUrlMap.put(ETHEREUM,URL_ETHEREUM);
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

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
                    String URL = BASE_URL + URL_VERSION + URL_PUBLIC + "/" + assetPair + URL_TICKER;
                    Map<String,String> header = new HashMap<>();
                    header.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(call(URL,header));
                    String data = jsonObject.get(JSON_KEY_DATA).toString();

                    if (null!=data) {
                        BitcoinTradeTicker ticker = objectMapper.readValue(data, BitcoinTradeTicker.class);
                        String asset = assetPair.replace(PAIR, "");
                        String pair = PAIR;
                        double price = ticker.getLast();
                        long time = sdf.parse(ticker.getDate()).getTime();
                        coinList.add(new Coin(asset, pair, price, time));
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
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return coinList;
    }

    @Override
    public BalanceList getBalanceList() {

        BalanceList balanceList = null;
        try {
            if (null!=apiKey) {
                String URL = BASE_URL + URL_VERSION + URL_WALLETS + URL_BALANCE;
                Map<String, String> header = new HashMap<>();
                header.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);
                header.put(HEADER_KEY_X_API_KEY, apiKey);

                JSONObject jsonObject = (JSONObject) jsonParser.parse(call(URL, header));
                JSONArray jsonObjectData = (JSONArray) jsonObject.get(JSON_KEY_DATA);
                if (null!=jsonObjectData) {
                    balanceList = new BalanceList();
                    for (int i = 0; i < jsonObjectData.size(); i++) {
                        BitcoinTradeBalance bitcoinTradeBalance = objectMapper.readValue(jsonObjectData.get(i).toString(), BitcoinTradeBalance.class);
                        Balance balance = new Balance(bitcoinTradeBalance.getCurrency_code(), bitcoinTradeBalance.getAvailable_amount(), bitcoinTradeBalance.getLocked_amount());
                        balanceList.add(balance);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return balanceList;
    }

    @Override
    public TradeList getTradeList(String pair) {

        TradeList tradeList = null;
        int page = 1;
        boolean doItAgain = true;
        try {
            while (doItAgain) {
                doItAgain = false;
                String[] querys = {QUERY_PARAM_STATUS + "=" + QUERY_VALUE_EXECUTED_COMPLETELY,
                        QUERY_PARAM_PAIR + "=" + pair,
                        QUERY_PARAM_PAGE_SIZE + "=" + 200,
                        QUERY_PARAM_CURRENT_PAGE + "=" + page};

                Arrays.sort(querys);
                String query = join(querys, "&");

                String URL = BASE_URL + URL_VERSION + URL_MARKET + URL_USER_ORDERS + URL_LIST + "?" + query;

                Map<String, String> header = new HashMap<>();
                header.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);
                header.put(HEADER_KEY_X_API_KEY, apiKey);

                JSONObject jsonObject = (JSONObject) jsonParser.parse(call(URL, header));
                JSONObject jsonObjectData = (JSONObject) jsonObject.get(JSON_KEY_DATA);

                JSONObject jsonObjectPagination = (JSONObject) jsonObjectData.get(JSON_KEY_PAGINATION);

                if (null != jsonObjectPagination) {
                    BitcoinTradePagination pagination = objectMapper.readValue(jsonObjectPagination.toString(), BitcoinTradePagination.class);
                    page = pagination.getCurrent_page();
                    if (page < pagination.getTotal_pages()) {
                        page++;
                        doItAgain = true;
                    }
                }

                JSONArray jsonArrayOrders = (JSONArray) jsonObjectData.get(JSON_KEY_ORDERS);

                if (null != jsonArrayOrders) {
                    if (null == tradeList) tradeList = new TradeList();
                    for (int i = 0; i < jsonArrayOrders.size(); i++) {
                        BitcoinTradeOrder bitcoinTradeOrder = objectMapper.readValue(jsonArrayOrders.get(i).toString(), BitcoinTradeOrder.class);
                        String coinPair = bitcoinTradeOrder.getPair_code();
                        String id = bitcoinTradeOrder.getId();
                        double price = bitcoinTradeOrder.getUnit_price();
                        double qty = bitcoinTradeOrder.getExecuted_amount();
                        double commission = 0;
                        String commissionAsset = "";
                        long time = sdf.parse(bitcoinTradeOrder.getCreate_date()).getTime();
                        boolean buyer = bitcoinTradeOrder.getType().equals("buy");
                        boolean maker = bitcoinTradeOrder.getSubtype().equals("market");
                        Trade trade = new Trade(coinPair, id, price, qty, commission, commissionAsset, time, buyer, maker, true, false, false);
                        tradeList.add(trade);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return tradeList;
    }

    @Override
    public DepositList getDepositWithdrawList(String asset) {

        DepositList depositList = null;

        DepositList depositListWithdraw = getDepositWithdrawListAux(asset);
        DepositList depositListDeposit = getDepositDepositAux(asset);

        if ((null!=depositListWithdraw)||(null!=depositListDeposit)) {
            depositList = new DepositList();
            depositList.addAll(depositListWithdraw);
            depositList.addAll(depositListDeposit);
        }

        return depositList;
    }

    private DepositList getDepositDepositAux(String asset) {
        DepositList depositList = null;
        String assetUrl = assetUrlMap.get(asset);

        if (null!=assetUrl) {
            boolean doItAgain = true;
            int page = 1;

            try {
                while (doItAgain) {
                    doItAgain = false;

                    String[] querys = {QUERY_PARAM_STATUS + "=" + QUERY_VALUE_CONFIRMED,
                            QUERY_PARAM_PAGE_SIZE + "=" + 200,
                            QUERY_PARAM_CURRENT_PAGE + "=" + page};

                    Arrays.sort(querys);
                    String query = join(querys, "&");

                    String URL = BASE_URL + URL_VERSION + assetUrl + URL_DEPOSITS + "?" + query;
                    Map<String, String> header = new HashMap<>();
                    header.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);
                    header.put(HEADER_KEY_X_API_KEY, apiKey);

                    JSONObject jsonObject = null;

                    String resp = call(URL, header);

                    jsonObject = (JSONObject) jsonParser.parse(resp);

                    JSONObject jsonObjectData = (JSONObject) jsonObject.get(JSON_KEY_DATA);

                    JSONObject jsonObjectPagination = (JSONObject) jsonObjectData.get(JSON_KEY_PAGINATION);

                    if (null != jsonObjectPagination) {
                        BitcoinTradePagination pagination = objectMapper.readValue(jsonObjectPagination.toString(), BitcoinTradePagination.class);
                        page = pagination.getCurrent_page();
                        if (page < pagination.getTotal_pages()) {
                            page++;
                            doItAgain = true;
                        }
                    }

                    JSONArray jsonArrayOrders = (JSONArray) jsonObjectData.get(JSON_KEY_DEPOSITS);

                    if (null != jsonArrayOrders) {
                        if (null == depositList) depositList = new DepositList();
                        for (int i = 0; i < jsonArrayOrders.size(); i++) {
                            BitcoinTradeDeposit bitcoinTradeDeposit = objectMapper.readValue(jsonArrayOrders.get(i).toString(), BitcoinTradeDeposit.class);

                            String id = bitcoinTradeDeposit.getCode();
                            long insertTime = sdf.parse(bitcoinTradeDeposit.getCreate_date()).getTime();
                            double amount = bitcoinTradeDeposit.getAmount();
                            Deposit deposit = new Deposit(id,insertTime,amount,asset,true,false);
                            depositList. add(deposit);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        return depositList;
    }

    private DepositList getDepositWithdrawListAux(String asset) {

        DepositList depositList = null;
        String assetUrl = assetUrlMap.get(asset);
        if (null!=assetUrl) {
            boolean doItAgain = true;
            int page = 1;

            try {
                while (doItAgain) {
                    doItAgain = false;

                    String[] querys = {QUERY_PARAM_STATUS + "=" + QUERY_VALUE_CONFIRMED,
                        QUERY_PARAM_PAGE_SIZE + "=" + 200,
                        QUERY_PARAM_CURRENT_PAGE + "=" + page};

                    Arrays.sort(querys);
                    String query = join(querys, "&");

                    String URL = BASE_URL + URL_VERSION + assetUrl + URL_WITHDRAW + "?" + query;

                    Map<String, String> header = new HashMap<>();
                    header.put(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_APPLICATION_JSON);
                    header.put(HEADER_KEY_X_API_KEY, apiKey);

                    JSONObject jsonObject = null;

                    String resp = call(URL, header);

                    jsonObject = (JSONObject) jsonParser.parse(resp);

                    JSONObject jsonObjectData = (JSONObject) jsonObject.get(JSON_KEY_DATA);

                    JSONObject jsonObjectPagination = (JSONObject) jsonObjectData.get(JSON_KEY_PAGINATION);

                    if (null != jsonObjectPagination) {
                        BitcoinTradePagination pagination = objectMapper.readValue(jsonObjectPagination.toString(), BitcoinTradePagination.class);
                        page = pagination.getCurrent_page();
                        if (page < pagination.getTotal_pages()) {
                            page++;
                            doItAgain = true;
                        }
                    }

                    JSONArray jsonArrayOrders = (JSONArray) jsonObjectData.get(JSON_KEY_WITHDRAWS);

                    if (null != jsonArrayOrders) {
                        if (null == depositList) depositList = new DepositList();
                        for (int i = 0; i < jsonArrayOrders.size(); i++) {
                            BitcoinTradeWithdraw bitcoinTradeWithdraw = objectMapper.readValue(jsonArrayOrders.get(i).toString(), BitcoinTradeWithdraw.class);

                            String id = bitcoinTradeWithdraw.getTransaction_id();
                            long insertTime = sdf.parse(bitcoinTradeWithdraw.getCreate_date()).getTime();
                            double amount = bitcoinTradeWithdraw.getAmount();
                            String currency = bitcoinTradeWithdraw.getCurrency_code();
                            Deposit deposit = new Deposit(id,insertTime,amount,currency,false,false);
                            depositList. add(deposit);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        return depositList;
    }
}
