package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradeResponse {

    private String code;
    private String message;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "{\"code\" : " + (code == null ? null : "\"" + code + "\"") + ",\"message\" : " + (message == null ? null : "\"" + message + "\"") + ",\"data\" : " + (data == null ? null : "\"" + data + "\"") + "}";
    }
}
