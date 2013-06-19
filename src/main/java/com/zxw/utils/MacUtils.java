package com.zxw.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-19
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public class MacUtils {

    public static String generateHMAC( String data, String secret){
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
