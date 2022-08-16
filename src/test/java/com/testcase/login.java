package com.testcase;

import com.alibaba.fastjson.JSON;
import com.methodpackage.basic.testbasic;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class login extends testbasic{
    public String token;  //定义一个变量，用于存放登录后的token值

    // 登录接口测试并获取token值
    @Test
    public void Login_POST_APITest() throws Exception {

        // 通过HttpPost来发送post请求，带Body参数
        HttpPost httpPost = new HttpPost("http://localhost:8080/login");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", "admin");
        jsonParam.put("password", "123456");
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");

        // Headers的Content-Type类型
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        // 通过HttpClient来执行请求，获取一个响应结果
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 添加断言，获取服务器响应的状态码
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "服务器返回的状态码不是200");
        System.out.println("服务器响应的状态码为：" + statusCode);

        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        httpPost.setConfig(requestConfig);

        // 获取Response Headers的值
        Header[] headerArray = response.getAllHeaders(); //获取响应头信息,返回是一个数组
        HashMap<String, String> hm = new HashMap<String, String>(); //创建一个hashmap对象
        // 增强for循环遍历headerArray数组，依次把元素添加到hashmap集合
        for(Header header : headerArray) {
            hm.put(header.getName(), header.getValue());
        }
        // 打印hashmap
        System.out.println("Response Headers的结果为："+ hm);

        // 获取Content-Type的类型
        HttpEntity httpentity = response.getEntity();

        // 获取Response Body结果
        String str = EntityUtils.toString(httpentity, "utf-8");
        writeTxt(str.toString()); //调用writeTxt()函数，将Response Body的结果保存到本地文本文件中
        System.out.println("登录接口的Response Body结果为：" + str);

        // 登录成功后，获取服务器返回Body值里的token值
        JSONObject json1 = JSON.parseObject(str); //将str的结果转换成json格式
        String data = json1.getString("data");
        JSONObject json2 = JSON.parseObject(data); //将data的结果转换成json格式
        token = json2.getString("token");
        System.out.println("登录成功后的token值为：" + token);

        // 关闭
        response.close();
    }

    // 将服务器的响应结果保存到本地文本文件中
    public static void  writeTxt(String str) throws Exception{

        File file=new File("./Api_AutoTestCase");
        if(!file.exists()){ //如果文件夹不存在
            file.mkdir(); //创建文件夹
        }
        FileWriter fw = null;
        String path = "./Api_AutoTestCase/登录接口的Response Body值.txt";
        File f = new File(path);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(str.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
