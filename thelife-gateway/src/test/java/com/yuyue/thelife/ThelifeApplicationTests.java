package com.yuyue.thelife;

import com.yuyue.thelife.utils.HttpClientHelper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThelifeApplicationTests {

    @Resource
    private HttpClientHelper httpClientHelper;

    @Test
    public void httpClientHelperTest(){
        String url = "https://api.map.baidu.com/geocoding/v3";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("address","齐齐哈尔市泰来县");
        paramMap.put("output","json");
        paramMap.put("ak","XSpSgFpfDC5ZMUq6cojw3XqpFa8VGmDF");
        paramMap.put("address","齐齐哈尔市泰来县");
        paramMap.put("address","齐齐哈尔市泰来县");
        String responseStr = httpClientHelper.get(url, paramMap, null);
        System.out.println(responseStr);
    }

    /**
     * 根据城市名称获取经纬度
     */
    @Test
    public void test0() {
//        http://api.map.baidu.com/geocoding/v3/?address=北京市海淀区上地十街10号&output=json&ak=您的ak&callback=showLocation //GET请求
        String address = "齐齐哈尔市泰来县";
        String output = "json";
        String ak = "XSpSgFpfDC5ZMUq6cojw3XqpFa8VGmDF";
        String params = "address=" + address + "&output=" + output + "&ak=" + ak;
        String url = "https://api.map.baidu.com/geocoding/v3/?" + params;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            System.out.println("相应状态码：" + httpResponse.getStatusLine());
            if (responseEntity != null) {
                System.out.println("相应内容长度：" + responseEntity.getContentLength());
                System.out.println("相应内容：" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据经纬度获取详细地址
     */
    @Test
    public void test1() {
//        http://api.map.baidu.com/reverse_geocoding/v3/?ak=您的ak&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194  //GET请求
        String ak = "XSpSgFpfDC5ZMUq6cojw3XqpFa8VGmDF";
        String latitude = "31.23037";
        String longitude = "121.4737";
        String location = latitude + "," + longitude;
        String output = "json";
        String coordtype = "wgs84ll";
        String params = "ak=" + ak + "&output=" + output + "&coordtype=" + coordtype + "&location=" + location;
        String url = "https://api.map.baidu.com/reverse_geocoding/v3/?" + params;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            System.out.println("相应状态码：" + httpResponse.getStatusLine());
            if (responseEntity != null) {
                System.out.println("相应内容长度：" + responseEntity.getContentLength());
                System.out.println("相应内容：" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据两个经纬度获取距离间隔（上海人民广场 ------北京市海淀区上地十街10号）
     */
    @Test
    public void test2() {
//        http://api.map.baidu.com/direction/v2/driving?origin=40.01116,116.339303&destination=39.936404,116.452562&ak=您的AK  //GET请求
        //经纬度取小数点后六位
        String ak = "XSpSgFpfDC5ZMUq6cojw3XqpFa8VGmDF";
        //上海人民广场
        String latitude = "31.23037";
        String longitude = "121.4737";
        String origin = latitude + "," + longitude;
        //北京市海淀区上地十街10号
        String latitude1 = "40.057031";
        String longitude1 = "116.308421";
        String destination = latitude1 + "," + longitude1;
        String params = "origin=" + origin + "&destination=" + destination + "&ak=" + ak;
        String url = "https://api.map.baidu.com/direction/v2/driving?" + params;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            System.out.println("相应状态码：" + httpResponse.getStatusLine());
            if (responseEntity != null) {
                System.out.println("相应内容长度：" + responseEntity.getContentLength());
                System.out.println("相应内容：" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
