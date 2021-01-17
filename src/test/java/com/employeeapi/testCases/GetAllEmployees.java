package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class GetAllEmployees extends TestBase {

    @BeforeClass
    public static void getAllEmployees(){
        logger.info("*****  Started GetAllEmployees  *******");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");
    }

    @Test
    public void checkResponseBody(){
        logger.info("*****  Checking Response Body  *******");
        test = extent.createTest("GetAllEmployees checkResponseBody");

        String responseBody = response.getBody().asString();
        logger.info("Response Body = " + responseBody);
        Assert.assertTrue(responseBody != null);
    }

    @Test
    public void checkStatusCode(){
        logger.info("******  Checking Status Code  *******");
        test = extent.createTest("GetAllEmployees checkStatusCode");

        int statusCode = response.getStatusCode();
        logger.info("Status Code is = " + statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void checkResponseTime(){
        logger.info("*******  Checking Response Time  *******");
        test = extent.createTest("GetAllEmployees checkResponseTime");

        long responseTime = response.getTime();
        logger.info("Response Time is = "+ responseTime);
        Assert.assertTrue(responseTime<2000);
    }

    @Test
    public void checkStatusLine(){
        logger.info("*****  Check Status Line  *******");
        test = extent.createTest("GetAllEmployees checkStatusLine");

        String statusLine = response.getStatusLine();
        logger.info("Status Line is = "+ statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }

    @Test
    public void checkContentType(){
        logger.info("******  Checking Content Type  *********");
        test = extent.createTest("GetAllEmployees checkContentType");

        String contentType = response.header("Content-Type");
        logger.info("Content Type is = " + contentType);
        Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }

    @Test
    public void checkServerType(){
        logger.info("*******  Checking Server Type  ********");
        test = extent.createTest("GetAllEmployees checkServerType");

        String serverType = response.header("Server");
        logger.info("Server Type is = "+serverType);
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @AfterClass
    public static void tearDown(){
        logger.info("******  Finished GetAllEmployees  *******");
    }

}
