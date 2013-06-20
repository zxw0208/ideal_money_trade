package com.zxw.service.impl;

import com.zxw.exception.CryptsyApiException;
import com.zxw.model.Market;
import com.zxw.model.User;
import com.zxw.persistence.TradeMapper;
import com.zxw.sdk.CryptsyApi;
import com.zxw.service.CryptsyService;
import com.zxw.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-20
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CryptsyServiceImpl implements CryptsyService {

    CryptsyApi cApi = new CryptsyApi();

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public void updateMarket() {
        User user = UserHolder.getUser();
        if(user == null){
            user = tradeMapper.findUserByName("zxw");
        }
        try {
            List<Market> list = cApi.getMarkets(user);
            List<Market> oldList = tradeMapper.findAllMarkets();
            for(Market market : list){
                if(!oldList.contains(market)){
                    tradeMapper.addMarket(market);
                }
            }
        } catch (CryptsyApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
