package com.zxw.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-19
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class AccountInfo {

    private Map<String, String> balancesAvailable;
    private Map<String, String> balancesHold;
    private Date time;
    private Integer openOrderCount;

    public Map<String, String> getBalancesAvailable() {
        return balancesAvailable;
    }

    public void setBalancesAvailable(Map<String, String> balancesAvailable) {
        this.balancesAvailable = balancesAvailable;
    }

    public Map<String, String> getBalancesHold() {
        return balancesHold;
    }

    public void setBalancesHold(Map<String, String> balancesHold) {
        this.balancesHold = balancesHold;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getOpenOrderCount() {
        return openOrderCount;
    }

    public void setOpenOrderCount(Integer openOrderCount) {
        this.openOrderCount = openOrderCount;
    }
}
