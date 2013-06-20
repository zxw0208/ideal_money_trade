package com.zxw.model;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-20
 * Time: 下午12:22
 * To change this template use File | Settings | File Templates.
 */
public class Market {
    private Integer marketId;
    private String label;
    private String primaryCurrencyCode;
    private String primaryCurrencyName;

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrimaryCurrencyCode() {
        return primaryCurrencyCode;
    }

    public void setPrimaryCurrencyCode(String primaryCurrencyCode) {
        this.primaryCurrencyCode = primaryCurrencyCode;
    }

    public String getPrimaryCurrencyName() {
        return primaryCurrencyName;
    }

    public void setPrimaryCurrencyName(String primaryCurrencyName) {
        this.primaryCurrencyName = primaryCurrencyName;
    }

    @Override
    public boolean equals(Object obj) {
        Market market = (Market) obj;
        return market.getMarketId().equals(this.getMarketId());
    }
}
