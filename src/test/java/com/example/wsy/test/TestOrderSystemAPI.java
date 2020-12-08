package com.example.wsy.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOrderSystemAPI {

    private final static String orderKey = "c4b565f7d4d9c9b5a041f97ef1283998";
    private final static String url = "http://chiguotest.ytoglobal.com/order-api/orderQuery/listCustomerByOrderWaybillNos";

    public static void main(String[] args) throws Exception {


        List<String> bills = new ArrayList<>();
        bills.add("KA000000024671");
        bills.add("pi01091495");
        bills.add("1");

        Map<String, String> header = new HashMap<>();
        String time = String.valueOf(System.currentTimeMillis());
        String data = JSON.toJSONString(bills);
        header.put("timestamp", time) ;
        String sign = MD5.cod32(JSON.toJSONString(bills) + "|" + time + "|" + orderKey, "UTF-8");
        header.put("sign", sign) ;
        header.put("Content-Type", "application/json") ;

        String result = HttpUtils.httpPost(url ,data, header, "UTF-8");
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        Object o = jsonObject.get("data");
        System.out.println(o);
        List<JSONObject> array = JSONArray.parseArray(o.toString(), JSONObject.class);

        System.out.println(JSON.toJSONString(array));

        System.out.println("_______________________________________________++++++++++++++++++++++++++++++++____________________________________________+++++++++++++++++++++_");
        JSONArray objects = JSON.parseArray(o.toString());

        System.out.println(objects);

    }

}


