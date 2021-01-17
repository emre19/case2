package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteEmployee extends TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;

    @BeforeClass
    public static void deleteEmployee(){
        logger.info("*****  Started Delete Employee  *******");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");

        JsonPath JsonPathEvaluator = response.jsonPath();

        String empID = JsonPathEvaluator.get("data[1].id");
        response = httpRequest.request(Method.DELETE,"/delete/"+empID);
    }

    @Test
    public void checkResponseBody(){
        test = extent.createTest("DeleteEmployee checkResponseBody");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"),true);
    }

    @Test
    public void checkStatusCode(){
        test = extent.createTest("DeleteEmployee checkStatusCode");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkStatusLine(){
        test = extent.createTest("DeleteEmployee checkStatusLine");

        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
    @Test
    public void checkContentType(){
        test = extent.createTest("DeleteEmployee checkContentType");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json");
    }

    @Test
    public void checkServerType(){
        test = extent.createTest("DeleteEmployee checkServerType");

        String serverType = response.header("Server");
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @AfterClass
    public static void tearDown(){
        logger.info("******  Finished Delete Employees  *******");
    }

}
