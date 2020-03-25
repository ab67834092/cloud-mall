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

    /**
     * 测试是否可以获取JWT
     */
    @Test
    public void testGetJWT(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost httpPost = new HttpPost("http://localhost:10010/api/auth/logout");
            httpPost.setHeader("token","eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiIxODYyMjM1ODU0MCIsImV4cCI6MTU4NDIzNjQzOH0.AlqMVPs-8cLu7Cs3A9uOAgqhKgzd8KOpyojF574xCEQT9Yj54WbVIyrWLhOgMid3EL6ZYmJt2ilJvw75ZtkX7TR_3P8PYVih-SbsGrIcO_mZyLpKbghkiPcqZnTO3pR4BxYb5FMjQNDkoXpjfMRf1o4ylL9crs2dPBWc0NpkPMQ");
//            httpPost.setHeader("token","7887");
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
     * 测试刷新token
     */
    @Test
    public void testRefreshToken(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost httpPost = new HttpPost("http://localhost:10010/api/auth/refreshToken");
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
//            pairList.add(new BasicNameValuePair("refreshToken", "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNTg2ODQ3NjM5fQ.I5z6Mv-v0DcwzoS_c4G23-MlPoDt0nA0pmF5GJ7ZjD7y8qzuEubPBrEl35_gIeq1aVDdchwHh9okLsF4nQH8E6k8nJfyM_pTbuuYKeL8RTGnHNFPWdLifBGT6Ut7Gek0jC6mRsH13qkDs_F-E8elk2mYPDZ4UDizEbLo1WSjWss"));
            pairList.add(new BasicNameValuePair("refreshToken", "Q.I5z6Mv-v0DcwzoS_c4G23-MlPoDt0nA0pmF5GJ7ZjD7y8qzuEubPBrEl35_gIeq1aVDdchwHh9okLsF4nQH8E6k8nJfyM_pTbuuYKeL8RTGnHNFPWdLifBGT6Ut7Gek0jC6mRsH13qkDs_F-E8elk2mYPDZ4UDizEbLo1WSjWss"));
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
