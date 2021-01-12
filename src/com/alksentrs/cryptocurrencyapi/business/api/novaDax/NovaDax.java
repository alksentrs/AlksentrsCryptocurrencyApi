package com.alksentrs.cryptocurrencyapi.business.api.novaDax;

import com.alksentrs.cryptocurrencyapi.business.*;
import com.alksentrs.cryptocurrencyapi.business.api.ApiConnection;
import com.alksentrs.cryptocurrencyapi.business.api.IExchangeConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NovaDax extends ApiConnection implements IExchangeConnection {

    private static final String BASE_URL = "https://api.novadax.com";
    private static final String URL_VERSION = "/v1";
    private static final String URL_COMMON = "/common";
    private static final String URL_SYMBOLS = "/symbols";
    private static final String URL_TIMESTAMP = "/timestamp";
    private static final String URL_MARKET = "/market";
    private static final String URL_TICKERS = "/tickers";
    private static final String URL_ACCOUNT = "/account";
    private static final String URL_GET_BALANCE = "/getBalance";
    private static final String URL_ORDERS = "/orders";
    private static final String URL_GET = "/get";
    private static final String URL_LIST = "/list";
    private static final String URL_WALLET = "/wallet";
    private static final String URL_QUERY = "/query";
    private static final String URL_DEPOSIT_WITHDRAW = "/deposit-withdraw";

    private static final String HEADER_KEY_ACCESS_KEY = "X-Nova-Access-Key";
    private static final String HEADER_KEY_SIGNATURE = "X-Nova-Signature";
    private static final String HEADER_KEY_TIMESTAMP = "X-Nova-Timestamp";

    private static final String QUERY_PARAM_SYMBOL = "symbol";
    private static final String QUERY_PARAM_STATUS = "status";
    private static final String QUERY_PARAM_LIMIT = "limit";
    private static final String QUERY_PARAM_CURRENCY = "currency";
    private static final String QUERY_PARAM_TYPE = "type";
    private static final String QUERY_PARAM_SIZE = "size";
    private static final String QUERY_PARAM_DIRECT = "direct";
    private static final String QUERY_PARAM_START = "start";

    private static final String QUERY_VALUE_FILLED = "FILLED";
    private static final String QUERY_VALUE_PARTIAL_FILLED = "PARTIAL_FILLED";
    private static final String QUERY_VALUE_COIN_IN = "coin_in";
    private static final String QUERY_VALUE_COIN_OUT = "coin_out";

    private static final String COIN_IN = "COIN_IN";


    private static final String JSON_KEY_DATA = "data";

    private static final String SIGNATURE_NAME = "name";
    private static final String SIGNATURE_ID = "cpf";
    private static final String SIGNATURE_BIRTHDAY = "birthday";

    private static final String GET = "GET";
    private static final String SELL = "SELL";
    private static final String MARKET = "MARKET";

    private ObjectMapper objectMapper = new ObjectMapper();
    private JSONParser jsonParser = new JSONParser();
    private SimpleDateFormat sdf;
    private SimpleDateFormat sdfSignature;

    private String apiKey;
    private String secret;

    private String name;
    private String cpf;
    private Date birthday;

    public NovaDax() {
        String pattern = "yyyy-MM-dd";
        sdfSignature = new SimpleDateFormat(pattern);
    }

    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        try {
            this.birthday = sdfSignature.parse(birthday);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    private String getSignature(String method, String path, String params, long timestamp) {
        String signature = String.format("%s\n%s\n%s\n%s", method, path, params, timestamp);
        return signature;
    }

    public List<NovaDaxSymbol> getSymbols() {
        String URL = BASE_URL + URL_VERSION + URL_COMMON + URL_SYMBOLS;
        Map<String, String> header = null;
        List<NovaDaxSymbol> symbolList = null;

        try {
            String response = call(URL, header);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray jsonArrayData = (JSONArray) jsonObject.get(JSON_KEY_DATA);

            if (null != jsonArrayData) {
                symbolList = new Vector<>();
                for (int i = 0; i < jsonArrayData.size(); i++) {
                    NovaDaxSymbol symbol = objectMapper.readValue(jsonArrayData.get(i).toString(), NovaDaxSymbol.class);
                    symbolList.add(symbol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return symbolList;
    }

    @Override
    public ServerTime getServerTime() {

        String URL = BASE_URL + URL_VERSION + URL_COMMON + URL_TIMESTAMP;
        Map<String, String> header = null;
        ServerTime serverTime = null;

        try {
            String response = call(URL, header);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            Long longData = (Long) jsonObject.get(JSON_KEY_DATA);
            serverTime = new ServerTime(longData);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return serverTime;
    }

    @Override
    public CoinList getPrices(String[] coins) {

        CoinList coinList = null;
        String URL = BASE_URL + URL_VERSION + URL_MARKET + URL_TICKERS;
        Map<String, String> header = null;

        try {
            String response = call(URL, header);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray jsonArrayData = (JSONArray) jsonObject.get(JSON_KEY_DATA);

            if (null != jsonArrayData) {
                coinList = new CoinList();
                for (int i = 0; i < jsonArrayData.size(); i++) {
                    NovaDaxTicker symbol = objectMapper.readValue(jsonArrayData.get(i).toString(), NovaDaxTicker.class);

                    String assetPair_ = symbol.getSymbol();
                    String [] assetPair = assetPair_.split("_");

                    String asset = assetPair[0];
                    String pair = assetPair[1];
                    double price = symbol.getLastPrice();
                    long time = symbol.getTimestamp();
                    Coin coin = new Coin(asset, pair, price, time);

                    if ((null==coins)||(coins.length==0)) {
                        coinList.add(coin);
                    } else {
                        boolean add = false;
                        for (int j=0; j<coins.length; j++) if (coins[j].toLowerCase().equals(asset.toLowerCase())) add = true;
                        if (add) coinList.add(coin);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return coinList;
    }

    @Override
    public BalanceList getBalanceList() {
        BalanceList balanceList = null;

        Map<String, String> header = null;

        try {
            String path = URL_VERSION + URL_ACCOUNT + URL_GET_BALANCE;
            String query = "";

            ServerTime serverTime = getServerTime();
            String URL = BASE_URL + path;
            String signatureStr = getSignature(GET,path,query,serverTime.getServerTime());
            String signature = encode(secret, signatureStr,ApiConnection.ENCRYPTION_ALGORITHM_SHA256);

            header = new HashMap<>();
            header.put(HEADER_KEY_ACCESS_KEY, apiKey);
            header.put(HEADER_KEY_SIGNATURE, signature);
            header.put(HEADER_KEY_TIMESTAMP, String.valueOf(serverTime.getServerTime()));

            String response = call(URL, header);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray jsonArrayData = (JSONArray) jsonObject.get(JSON_KEY_DATA);

            if (null != jsonArrayData) {
                balanceList = new BalanceList();
                for (int i = 0; i < jsonArrayData.size(); i++) {
                    NovaDaxBalance novaDaxBalance = objectMapper.readValue(jsonArrayData.get(i).toString(), NovaDaxBalance.class);

                    String asset = novaDaxBalance.getCurrency();
                    double free = novaDaxBalance.getAvailable();
                    double locked = novaDaxBalance.getHold();

                    Balance balance = new Balance(asset, free, locked);
                    balanceList.add(balance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balanceList;
    }

    @Override
    public TradeList getTradeList(String pair) {

        TradeList tradeList = null;

        try {
            String path = URL_VERSION + URL_ORDERS + URL_LIST;

            String[] querys = {QUERY_PARAM_SYMBOL + "=" + pair,
                    QUERY_PARAM_STATUS + "=" + QUERY_VALUE_FILLED,
                    QUERY_PARAM_LIMIT + "=" + 100};
            Arrays.sort(querys);
            String query = join(querys, "&");

            ServerTime serverTime = getServerTime();
            String URL = BASE_URL + path + "?" + query;
            String signatureStr = getSignature(GET,path,query,serverTime.getServerTime());
            String signature = encode(secret, signatureStr,ApiConnection.ENCRYPTION_ALGORITHM_SHA256);

            Map<String, String> header = new HashMap<>();
            header.put(HEADER_KEY_ACCESS_KEY, apiKey);
            header.put(HEADER_KEY_SIGNATURE, signature);
            header.put(HEADER_KEY_TIMESTAMP, String.valueOf(serverTime.getServerTime()));

            String response = call(URL, header);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray jsonArrayData = (JSONArray) jsonObject.get(JSON_KEY_DATA);

            if (null != jsonArrayData) {
                tradeList = new TradeList();
                for (int i = 0; i < jsonArrayData.size(); i++) {
                    NovaDaxOrder novaDaxOrder = objectMapper.readValue(jsonArrayData.get(i).toString(), NovaDaxOrder.class);

                    String coinPair = novaDaxOrder.getSymbol();
                    String id = novaDaxOrder.getId();
                    double price = novaDaxOrder.getPrice();
                    double qty = novaDaxOrder.getFilledAmount();
                    double commission = novaDaxOrder.getFilledFee();
                    String commissionAsset = "";
                    long time = novaDaxOrder.getTimestamp();
                    boolean buyer = !(novaDaxOrder.getSide() == SELL);
                    boolean maker = (novaDaxOrder.getType() == MARKET);

                    Trade trade = new Trade(coinPair, id, price, qty, commission, commissionAsset, time, buyer, maker, true, false, false);
                    tradeList.add(trade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tradeList;
    }

    @Override
    public DepositList getDepositWithdrawList(String asset) {
        DepositList depositList = null;

        try {
            String path = URL_VERSION + URL_WALLET + URL_QUERY + URL_DEPOSIT_WITHDRAW;

            String[] querys = {QUERY_PARAM_CURRENCY + "=" + asset};
            Arrays.sort(querys);
            String query = join(querys, "&");

            ServerTime serverTime = getServerTime();
            String URL = BASE_URL + path + "?" + query;
            String signatureStr = getSignature(GET,path,query,serverTime.getServerTime());
            String signature = encode(secret, signatureStr,ApiConnection.ENCRYPTION_ALGORITHM_SHA256);

            Map<String, String> header = new HashMap<>();
            header.put(HEADER_KEY_ACCESS_KEY, apiKey);
            header.put(HEADER_KEY_SIGNATURE, signature);
            header.put(HEADER_KEY_TIMESTAMP, String.valueOf(serverTime.getServerTime()));

            String response = call(URL, header);

            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONArray jsonArrayData = (JSONArray) jsonObject.get(JSON_KEY_DATA);

            if (null != jsonArrayData) {
                depositList = new DepositList();
                for (int i = 0; i < jsonArrayData.size(); i++) {
                    NovaDaxDeposit novaDaxDeposit = objectMapper.readValue(jsonArrayData.get(i).toString(), NovaDaxDeposit.class);

                    String id = novaDaxDeposit.getId();
                    long insertTime = novaDaxDeposit.getUpdatedAt();
                    double amount = novaDaxDeposit.getAmount();
                    String currency = novaDaxDeposit.getCurrency();
                    boolean isDeposit = novaDaxDeposit.getType().equals(COIN_IN);

                    Deposit deposit = new Deposit(id,insertTime,amount,currency,isDeposit,false);
                    depositList. add(deposit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depositList;
    }
}
