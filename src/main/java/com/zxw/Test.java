package com.zxw;

import com.zxw.exception.CryptsyApiException;
import com.zxw.model.User;
import com.zxw.sdk.CryptsyApi;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-18
 * Time: 下午9:23
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String [] args) throws IOException, ParseException, CryptsyApiException {

        String key = "916f13117227c5fea82950874b824d7af5990c54";
        String secret = "6bd6079674a664d02e3beec80f89d240b5f8bce4464b260a6f6fe91096a72e0d1abc0e1c07beee63";

        CryptsyApi ca = new CryptsyApi();
        User user = new User();
        user.setKey(key);
        user.setSecret(secret);
        ca.getMarkets(user);

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
