package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetSingleEmployee extends TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;

    @BeforeClass
    public static void getEmployeeData(){
        logger.info("***** Started Get Employee Data  ******");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employee/"+empID);
    }

    @Test
    public void checkResponseBody(){
        test = extent.createTest("GetSingleEmployee checkResponseBody");

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empID), true);
    }

    @Test
    public void checkStatusCode(){
        test = extent.createTest("GetSingleEmployee checkStatusCode");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void checkResponseTime(){
        test = extent.createTest("GetSingleEmployee checkResponseTime");

        long responseTime = response.getTime();
        Assert.assertTrue(responseTime<2000);
    }

    @Test
    public void checkStatusLine(){
        test = extent.createTest("GetSingleEmployee checkStatusLine");

        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType(){
        test = extent.createTest("GetSingleEmployee checkContentType");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json");
    }

    @Test
    public void checkServerType(){
        test = extent.createTest("GetSingleEmployee checkServerType");

        String serverType = response.header("Server");
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @AfterClass
    public static void tearDown(){
        logger.info("******  Finished GetAllEmployees  *******");
    }

}
