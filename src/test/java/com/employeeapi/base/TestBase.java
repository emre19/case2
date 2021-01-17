package com.employeeapi.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


public class TestBase {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static RequestSpecification httpRequest;
    public static Response response;
    public static String empID = "1";

    public static Logger logger;

    @BeforeClass
    public static void setup(){
        logger = Logger.getLogger("EmployeesRestAPI");
        PropertyConfigurator.configure("log4j.properties");
        logger.setLevel(Level.DEBUG);

        htmlReporter = new ExtentHtmlReporter("C:\\Users\\Asus\\IdeaProjects\\restAssured\\Reports\\MyOwnReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterMethod
    public void getResult(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            test.fail(MarkupHelper.createLabel(result.getName()+" Test Case failed", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SUCCESS){
            test.pass(MarkupHelper.createLabel(result.getName()+" Test Case Passed", ExtentColor.GREEN));
        }
        else {
            test.skip(MarkupHelper.createLabel(result.getName()+" Test Case skipped", ExtentColor.YELLOW));
            test.fail(result.getThrowable());
        }
    }

    @AfterClass
    public static void close(){
        extent.flush();
    }

}
