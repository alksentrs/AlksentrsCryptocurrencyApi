package com.alksentrs.cryptocurrencyapi.network;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * Created by ksander on 12/01/18.
 */
public class HttpsClient {

    int [] errorCodes = {400,401,403,404,429,500,503,504};

    public String connect(String https_url, Map<String,String> headers) throws IOException {

        URL url;
        url = new URL(https_url);
        String input = null;

        HttpsURLConnection con = (HttpsURLConnection)url.openConnection();


        if (null!=headers) {
            Iterator<String> it = headers.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                con.setRequestProperty(key, headers.get(key));
            }
        }
        int responseCode = con.getResponseCode();
        boolean error = IntStream.of(errorCodes).anyMatch(x -> x == responseCode);
        //System.out.println("ResponseMessage: "+con.getResponseMessage()+" "+responseCode+" "+error);
        if (!error) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            input = br.readLine();
            br.close();
        }
        return input;
    }
}
