package com.example.wsy.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Map;
import java.util.Set;

public class HttpUtils {

    public static String httpPost(String url, String data, Map<String, String> header, String charSet) throws Exception {
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, charSet));
        if (header != null && header.size() > 0) {
            Set<String> keys = header.keySet();
            for (String key : keys) {
                httpPost.addHeader(key, header.get(key));
            }
        }
        HttpResponse response = client.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(), charSet);
        return result;
    }

}
