package com.alksentrs.cryptocurrencyapi.business.api;


import com.alksentrs.cryptocurrencyapi.network.HttpsClient;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Map;

/**
 * Created by ksander on 15/02/18.
 */

public class ApiConnection {

    public static final String ENCRYPTION_ALGORITHM_SHA512 = "HmacSHA512";
    public static final String ENCRYPTION_ALGORITHM_SHA256 = "HmacSHA256";

    protected HttpsClient httpsClient;
    protected JSONParser jsonParser = new JSONParser();

    public ApiConnection() {
        httpsClient = new HttpsClient();
    }

    protected String join(String [] array, String delimiter) {
        String join = ("" + Arrays.asList(array)).replaceAll("(^.|.$)", "").replace(", ", delimiter );
        return join;
    }

    protected String getQueryString(String [] querys, String delimiter) {
        Arrays.sort(querys);
        return join(querys,delimiter);
    }

    protected String encode(String key, String data, String encryptionAlgorithm) throws Exception {
        if ((null!=key)&&(null!=data)) {
            Mac mac = Mac.getInstance(encryptionAlgorithm);
            byte [] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, encryptionAlgorithm);
            mac.init(secretKeySpec);

            byte [] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte [] bytes = mac.doFinal(dataBytes);

            Formatter formatter = new Formatter();
            for (byte b : bytes) formatter.format("%02x", b);
            return formatter.toString();
        }
        return "";
    }

    protected String getSHA512(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    protected String call(String url, Map<String,String> headers) throws IOException {
        String ret = httpsClient.connect(url,headers);
        return ret;
    }


}
