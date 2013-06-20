package com.zxw.sdk;

import com.zxw.exception.CryptsyApiException;
import com.zxw.model.AccountInfo;
import com.zxw.model.Market;
import com.zxw.model.User;
import com.zxw.utils.MacUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-19
 * Time: 下午12:09
 * To change this template use File | Settings | File Templates.
 */
public class CryptsyApi {

    public AccountInfo getInfo(User user) throws CryptsyApiException {
        String method = "getinfo";
        String nonce = String.valueOf(System.currentTimeMillis());
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("method", method));
        formParams.add(new BasicNameValuePair("nonce", nonce));

        try{
            String response = post(formParams, user.getKey(), user.getSecret());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response, Map.class);
            String success = (String)map.get("success");
            if("1".equals(success)){
                AccountInfo ai = new AccountInfo();
                Map<String, Object> returns =  (Map<String, Object>)map.get("return");
                ai.setBalancesAvailable((Map<String, String>)returns.get("balances_available"));
                ai.setBalancesHold((Map<String, String>) returns.get("balances_hold"));
                ai.setOpenOrderCount((Integer) returns.get("openordercount"));
                String time = (String)returns.get("serverdatetime");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ai.setTime(new Date(df.parse(time).getTime() + 3612000));
                return ai;
            }else{
                throw new CryptsyApiException("调用api返回成功状态不为1");
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Market> getMarkets(User user) throws CryptsyApiException {
        String method = "getmarkets";
        String nonce = String.valueOf(System.currentTimeMillis());
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("method", method));
        formParams.add(new BasicNameValuePair("nonce", nonce));
        try{
            String response = post(formParams, user.getKey(), user.getSecret());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(response, Map.class);
            String success = (String)map.get("success");
            if("1".equals(success)){
                List<Map<String, Object>> returns =  (List<Map<String, Object>>)map.get("return");
                List<Market> list = new ArrayList<Market>();
                for(Map<String, Object> r : returns){
                    Market market = new Market();
                    market.setMarketId(Integer.valueOf(r.get("marketid").toString()));
                    market.setLabel(r.get("label").toString());
                    market.setPrimaryCurrencyCode(r.get("primary_currency_code").toString());
                    market.setPrimaryCurrencyName(r.get("primary_currency_name").toString());
                    list.add(market);
                }
                return list;
            }else{
                throw new CryptsyApiException("调用api返回成功状态不为1");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        throw new CryptsyApiException("出现异常无返回数据");
    }

    private String post(List<NameValuePair> formParams, String key, String secret) throws CryptsyApiException {
        final HttpClient httpClient = new DefaultHttpClient();
        final HttpPost post = new HttpPost("https://www.cryptsy.com/api");

        String query = URLEncodedUtils.format(formParams, Charset.forName("UTF-8"));

        Header header1 = new BasicHeader("Sign", MacUtils.generateHMAC(query, secret));
        Header header2 = new BasicHeader("Key", key);
        post.setHeaders(new Header[]{header1, header2});
        UrlEncodedFormEntity e = new UrlEncodedFormEntity(formParams, Charset.forName("UTF-8"));
        post.setEntity(e);
        HttpResponse resp;
        try {
            resp = httpClient.execute(post);
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new CryptsyApiException("IO异常：可能连不上服务器");
        }
        int status = resp.getStatusLine().getStatusCode();
        if(status != 200){
            throw new CryptsyApiException("范围http code：" + status);
        }
        HttpEntity entity = resp.getEntity();
        try {
            String response = EntityUtils.toString(entity);
            return response;
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        throw new CryptsyApiException("出现异常无返回数据");
    }
}
