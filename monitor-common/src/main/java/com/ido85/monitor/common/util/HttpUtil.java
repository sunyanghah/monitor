package com.ido85.monitor.common.util;

import com.ido85.monitor.common.dto.HttpResult;
import com.ido85.monitor.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dell on 2018/8/6.
 * @author dell
 */
public class HttpUtil {

    public static CloseableHttpClient getHttpClient(){
            return new DefaultHttpClient();
    }


    /**
     * get请求
     * @return
     */
    public static HttpResult doGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        return getResponseToString(httpGet);
    }

    public static HttpResult doPut(String url,String params) throws Exception{
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        if (StringUtils.isNotBlank(params)) {
            StringEntity reqEntity = new StringEntity(params, charSet);
            httpPut.setEntity(reqEntity);
        }
        return getResponseToString(httpPut);
    }


    private static HttpResult getResponseToString(HttpUriRequest httpRequest) throws Exception{
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = getHttpClient();
        HttpResult httpResult = new HttpResult();
        try {
            response = httpClient.execute(httpRequest);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK || state == HttpStatus.SC_CREATED) {
                httpResult.setSuccess(true);
            }else{
                httpResult.setSuccess(false);
            }
            HttpEntity entity = response.getEntity();
            StringBuilder sb = new StringBuilder();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    sb = sb.append(text).append("\n");
                }
            }
            EntityUtils.consume(entity);
            httpResult.setResponseString(sb.toString());
            return httpResult;
        }catch (Exception e){
            throw new BusinessException("http请求失败："+httpRequest.getURI());
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static HttpResult doPost(String url, String params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        if (StringUtils.isNotBlank(params)) {
            StringEntity reqEntity = new StringEntity(params, charSet);
            httpPost.setEntity(reqEntity);
        }
        return getResponseToString(httpPost);
    }

    public static HttpResult doDelete(String url) throws Exception{
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-Type", "application/json");
        return getResponseToString(httpDelete);
    }

}
