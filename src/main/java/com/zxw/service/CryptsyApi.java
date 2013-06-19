package com.zxw.service;

import com.zxw.exception.CryptsyApiException;
import com.zxw.model.AccountInfo;
import com.zxw.model.User;
import com.zxw.utils.MacUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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

    public AccountInfo getInfo(User user){
        final HttpClient httpClient = new DefaultHttpClient();
        final HttpPost post = new HttpPost("https://www.cryptsy.com/api");

        String method = "getinfo";

        String nonce = String.valueOf(System.currentTimeMillis());

        String query = "method=" + method + "&nonce=" + nonce;

        Header header1 = new BasicHeader("Sign", MacUtils.generateHMAC(query, user.getSecret()));
        Header header2 = new BasicHeader("Key", user.getKey());
        post.setHeaders(new Header[]{header1, header2});
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("method", "getinfo"));
        formparams.add(new BasicNameValuePair("nonce", nonce));
        try{
            UrlEncodedFormEntity e = new UrlEncodedFormEntity(formparams, "UTF-8");
            post.setEntity(e);
            HttpResponse resp = httpClient.execute(post);
            int status = resp.getStatusLine().getStatusCode();
            System.out.println(status);
            String response = EntityUtils.toString(resp.getEntity());
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
}
