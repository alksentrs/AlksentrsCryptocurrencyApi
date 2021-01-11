package com.alksentrs.cryptocurrencyapi.business.api.bitcointrade;

public class BitcoinTradePagination {

    private int registers_count;
    private int total_pages;
    private int current_page;
    private int page_size;

    public int getRegisters_count() {
        return registers_count;
    }

    public void setRegisters_count(int registers_count) {
        this.registers_count = registers_count;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    @Override
    public String toString() {
        return "{\"registers_count\" : " + registers_count + ",\"total_pages\" : " + total_pages + ",\"current_page\" : " + current_page + ",\"page_size\" : " + page_size + "}";
    }
}
