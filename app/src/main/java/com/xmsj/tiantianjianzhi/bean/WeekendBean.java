package com.xmsj.tiantianjianzhi.bean;

/**
 * Created by SuHongJin on 2018/7/26.
 */

public class WeekendBean {
    private String title_weekend;
    private String money_weekend;
    private String count_weekend;
    private String company_weekend;
    private String address_weekend;
    private String createdAt;
    private int wCount;

    public int getwCount() {
        return wCount;
    }

    public void setwCount(int wCount) {
        this.wCount = wCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    private String url_weekend;

    public String getUrl_weekend() {
        return url_weekend;
    }

    public void setUrl_weekend(String url_weekend) {
        this.url_weekend = url_weekend;
    }

    public String getTitle_weekend() {
        return title_weekend;
    }

    public void setTitle_weekend(String title_weekend) {
        this.title_weekend = title_weekend;
    }

    public String getMoney_weekend() {
        return money_weekend;
    }

    public void setMoney_weekend(String money_weekend) {
        this.money_weekend = money_weekend;
    }

    public String getCount_weekend() {
        return count_weekend;
    }

    public void setCount_weekend(String count_weekend) {
        this.count_weekend = count_weekend;
    }

    public String getCompany_weekend() {
        return company_weekend;
    }

    public void setCompany_weekend(String company_weekend) {
        this.company_weekend = company_weekend;
    }

    public String getAddress_weekend() {
        return address_weekend;
    }

    public void setAddress_weekend(String address_weekend) {
        this.address_weekend = address_weekend;
    }

}
