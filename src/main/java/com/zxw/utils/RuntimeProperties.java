package com.zxw.utils;

import com.zxw.persistence.TradeMapper;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 昕炜
 * Date: 13-4-16
 * Time: 下午12:31
 * To change this template use File | Settings | File Templates.
 */
public class RuntimeProperties {

    public static final String LOGIN_PAGE_NUM = "_LOGIN_PAGE_NUM";
    public static final String PROCESS_PAGE_NUM = "_PROCESS_PAGE_NUM";

    private static TradeMapper tradeMapper;

    public static void setProperty(String name, String value, String description){
        String val = getTradeMapper().getProperty( name);
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", name);
        param.put("value", value);
        param.put("description", description);
        if(val == null){
            getTradeMapper().setProperty(param);
        }else{
            getTradeMapper().updateProperty(param);
        }
    }

    public static void setProperty(String name, String value){
        setProperty(name, value, null);
    }

    public static String getProperty(String name){
        return getTradeMapper().getProperty(name);
    }

    private static TradeMapper getTradeMapper(){
        if(tradeMapper != null){
            return tradeMapper;
        }
        TradeMapper sst = SpringContext.getBean(TradeMapper.class);
        tradeMapper = sst;
        return sst;
    }

}
