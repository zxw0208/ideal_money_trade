package com.zxw.persistence;

import com.zxw.model.Market;
import com.zxw.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-18
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public interface TradeMapper {

    public String getProperty(String name);

    public void setProperty(Map<String, String> map);

    public void updateProperty(Map<String, String> map);

    public void addMarket(Market market);

    public void updateMarket(Market market);

    public List<Market> findAllMarkets();

    public User findUserByName(String username);
}
