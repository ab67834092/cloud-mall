package com.cjb.mall;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserService {

    /**
     * 测试发送手机验证码
     */
    @Test
    public void testSendVCode(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            pairList.add(new BasicNameValuePair("phone", "18622358540"));
            HttpPost httpPost = new HttpPost("http://localhost:10010/api/user/sendRegPhoneVCode");
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
            // 执行请求
            HttpResponse resp  = httpClient.execute(httpPost);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                System.out.println(EntityUtils.toString(he,"UTF-8"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册
     */
    @Test
    public void testRegister(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            pairList.add(new BasicNameValuePair("phone", "18622358540"));
            pairList.add(new BasicNameValuePair("vcode", "541281"));
            pairList.add(new BasicNameValuePair("pwd", "123456"));
            HttpPost httpPost = new HttpPost("http://localhost:10010/api/user/register");
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
            // 执行请求
            HttpResponse resp  = httpClient.execute(httpPost);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                System.out.println(EntityUtils.toString(he,"UTF-8"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 登录
     */
    @Test
    public void testLogin(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            pairList.add(new BasicNameValuePair("phone", "18622358540"));
            pairList.add(new BasicNameValuePair("pwd", "123456"));
            HttpPost httpPost = new HttpPost("http://localhost:10010/api/auth/login");
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
            // 执行请求
            HttpResponse resp  = httpClient.execute(httpPost);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                System.out.println(EntityUtils.toString(he,"UTF-8"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
