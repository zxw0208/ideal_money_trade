package com.zxw;

import com.zxw.exception.CryptsyApiException;
import com.zxw.model.AccountInfo;
import org.apache.commons.codec.binary.Hex;
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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-18
 * Time: 下午9:23
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String [] args) throws IOException, ParseException {

        String key = "916f13117227c5fea82950874b824d7af5990c54";
        String secret = "6bd6079674a664d02e3beec80f89d240b5f8bce4464b260a6f6fe91096a72e0d1abc0e1c07beee63";

        final HttpClient httpClient = new DefaultHttpClient();
        final HttpPost post = new HttpPost("https://www.cryptsy.com/api");

        String method = "getinfo";

        String nonce = String.valueOf(System.currentTimeMillis());

        String query = "method=" + method + "&nonce=" + nonce;

        Header header1 = new BasicHeader("Sign", generateHMAC(query, secret));
        Header header2 = new BasicHeader("Key", key);
        post.setHeaders(new Header[]{header1, header2});
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("method", "getinfo"));
        formparams.add(new BasicNameValuePair("nonce", nonce));
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
            ai.setTime(new Date(df.parse(time).getTime() + 43200000));
            System.out.println(df.format(ai.getTime()));
            System.out.println(returns);
        }else{
            throw new CryptsyApiException("调用api返回成功状态不为1");
        }
        System.out.println(response);

    }

    private static String generateHMAC( String data, String secret){
        Mac mac;
        String result = "";
        try
        {
            byte [] bytesKey = secret.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec( bytesKey,  "HmacSHA512" );
            mac = Mac.getInstance( "HmacSHA512" );
            mac.init( secretKey );
            byte[] word = mac.doFinal(data.getBytes());
            byte[] hex = new Hex().encode(word);
            result = new String( hex,  "ISO-8859-1" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return result;

    }

}
