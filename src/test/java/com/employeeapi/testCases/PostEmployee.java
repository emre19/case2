package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilties.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PostEmployee extends TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;

    static String empName = RestUtils.empName();
    static String empSalary = RestUtils.empSal();
    static String empAge = RestUtils.empAge();

    @BeforeClass
    public static void createEmployee(){
        logger.info("*****  Started Post Employee *****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST,"/create");
    }

    @Test
    public void checkResponseBody(){
        test = extent.createTest("PostEmployee checkResponseBody");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertEquals(responseBody.contains(empName),true);
        Assert.assertEquals(responseBody.contains(empSalary),true);
        Assert.assertEquals(responseBody.contains(empAge),true);
    }

    @Test
    public void checkStatusCode(){
        test = extent.createTest("PostEmployee checkStatusCode");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkStatusLine(){
        test = extent.createTest("PostEmployee checkStatusLine");

        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType(){
        test = extent.createTest("PostEmployee checkContentType");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json");
    }

    @Test
    public void checkServerType(){
        test = extent.createTest("PostEmployee checkServerType");

        String serverType = response.header("Server");
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @AfterClass
    public static void tearDown(){
        logger.info("******  Finished Post Employees  *******");
    }

}
