package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradeWithdraw {

    private double tax_amount;
    private String transaction_id;
    private double amount;
    private String is_internal;
    private String code;
    private String destination_address;
    private String link;
    private String origin_address;
    private int tax_index;
    private String memo;
    private String update_date;
    private String currency_code;
    private int tax_index_calculated;
    private String tag;
    private String create_date;
    private double miner_fee;
    private String miner_fee_type;
    private String status;

    public double getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(double tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIs_internal() {
        return is_internal;
    }

    public void setIs_internal(String is_internal) {
        this.is_internal = is_internal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOrigin_address() {
        return origin_address;
    }

    public void setOrigin_address(String origin_address) {
        this.origin_address = origin_address;
    }

    public int getTax_index() {
        return tax_index;
    }

    public void setTax_index(int tax_index) {
        this.tax_index = tax_index;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public int getTax_index_calculated() {
        return tax_index_calculated;
    }

    public void setTax_index_calculated(int tax_index_calculated) {
        this.tax_index_calculated = tax_index_calculated;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public double getMiner_fee() {
        return miner_fee;
    }

    public void setMiner_fee(double miner_fee) {
        this.miner_fee = miner_fee;
    }

    public String getMiner_fee_type() {
        return miner_fee_type;
    }

    public void setMiner_fee_type(String miner_fee_type) {
        this.miner_fee_type = miner_fee_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"tax_amount\" : " + tax_amount + ",\"transaction_id\" : " + (transaction_id == null ? null : "\"" + transaction_id + "\"") + ",\"amount\" : " + amount + ",\"is_internal\" : " + (is_internal == null ? null : "\"" + is_internal + "\"") + ",\"code\" : " + (code == null ? null : "\"" + code + "\"") + ",\"destination_address\" : " + (destination_address == null ? null : "\"" + destination_address + "\"") + ",\"link\" : " + (link == null ? null : "\"" + link + "\"") + ",\"origin_address\" : " + (origin_address == null ? null : "\"" + origin_address + "\"") + ",\"tax_index\" : " + tax_index + ",\"memo\" : " + (memo == null ? null : "\"" + memo + "\"") + ",\"update_date\" : " + (update_date == null ? null : "\"" + update_date + "\"") + ",\"currency_code\" : " + (currency_code == null ? null : "\"" + currency_code + "\"") + ",\"tax_index_calculated\" : " + tax_index_calculated + ",\"tag\" : " + (tag == null ? null : "\"" + tag + "\"") + ",\"create_date\" : " + (create_date == null ? null : "\"" + create_date + "\"") + ",\"miner_fee\" : " + miner_fee + ",\"miner_fee_type\" : " + (miner_fee_type == null ? null : "\"" + miner_fee_type + "\"") + ",\"status\" : " + (status == null ? null : "\"" + status + "\"") + "}";
    }
}
