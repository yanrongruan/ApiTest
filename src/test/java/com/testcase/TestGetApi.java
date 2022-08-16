package com.testcase;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestGetApi {
    @Test
    public void httpClientTest() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClientContext context = new HttpClientContext();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get, context);
            System.out.println(response.getStatusLine().getStatusCode());//返回状态值
            Header[] headers = response.getAllHeaders();//获取所有的header信息
            for (int i = 0; i < headers.length; i++) {
                Header header = headers[i];
                System.out.println("headers: name:[" + header.getName() + "];value:[" + header.getValue() + "]");
            }
            List<Cookie> returnCookie = context.getCookieStore().getCookies();
            for (Cookie cookie : returnCookie) {
                System.out.println("cookie: name:[" + cookie.getName() + "] ;value:[" + cookie.getValue()
                        + "] ;domain:[" + cookie.getDomain() + "] ;path:[" + cookie.getPath() + "] ;version:["
                        + cookie.getVersion() + "]");
            }
            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.assertFalse(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.assertFalse(true);
        } finally {
            try {
                response.close();
                client.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}