package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradeOrder {

    private String code;
    private String create_date;
    private double executed_amount;
    private String id;
    private String pair_code;
    private double remaining_amount;
    private double remaining_price;
    private double requested_amount;
    private String status;
    private String subtype;
    private double total_price;
    private String type;
    private double unit_price;
    private String update_date;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public double getExecuted_amount() {
        return executed_amount;
    }

    public void setExecuted_amount(double executed_amount) {
        this.executed_amount = executed_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPair_code() {
        return pair_code;
    }

    public void setPair_code(String pair_code) {
        this.pair_code = pair_code;
    }

    public double getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(double remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public double getRemaining_price() {
        return remaining_price;
    }

    public void setRemaining_price(double remaining_price) {
        this.remaining_price = remaining_price;
    }

    public double getRequested_amount() {
        return requested_amount;
    }

    public void setRequested_amount(double requested_amount) {
        this.requested_amount = requested_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    @Override
    public String toString() {
        return "{\"code\" : " + (code == null ? null : "\"" + code + "\"") + ",\"create_date\" : " + (create_date == null ? null : "\"" + create_date + "\"") + ",\"executed_amount\" : " + executed_amount + ",\"id\" : " + (id == null ? null : "\"" + id + "\"") + ",\"pair_code\" : " + (pair_code == null ? null : "\"" + pair_code + "\"") + ",\"remaining_amount\" : " + remaining_amount + ",\"remaining_price\" : " + remaining_price + ",\"requested_amount\" : " + requested_amount + ",\"status\" : " + (status == null ? null : "\"" + status + "\"") + ",\"subtype\" : " + (subtype == null ? null : "\"" + subtype + "\"") + ",\"total_price\" : " + total_price + ",\"type\" : " + (type == null ? null : "\"" + type + "\"") + ",\"unit_price\" : " + unit_price + ",\"update_date\" : " + (update_date == null ? null : "\"" + update_date + "\"") + "}";
    }
}
