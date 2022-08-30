package com.testcase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;
public class httpTest2 {
    @Test
    public void getfunction1() throws Exception {

        given().
                when().
                //get("http://www.baidu.com").
                        get("http://10.0.160.252:32001/mockservice/mock/296/api/user-api-service/pass/gtp-app-api/client_tasks/list").
                then().
                log().all();
    }
    @Test
    public void test1(){
        given().
                param("userid",2).
        when().
                get("http://jsonplaceholder.typicode.com/posts").
        then().statusCode(200).log().all();
        }

    @Test
    public void test2(){
        Map<String,String> parameters =new HashMap<String,String>();
        parameters.put("userid","2");
        parameters.put("id","14");
        given().
                params(parameters).
        when().
              get("http://jsonplaceholder.typicode.com/posts").
        then().
              statusCode(200).log().all();

    }
}
