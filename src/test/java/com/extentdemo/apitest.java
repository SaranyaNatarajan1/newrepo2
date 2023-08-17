package com.extentdemo;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class testclass {
	
	public ExtentSparkReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
@BeforeTest
public void extentInstance() {
        
    	htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/sample.html");
    	extent = new ExtentReports();
        extent.attachReporter(htmlreporter);
        extent.setSystemInfo("Release No", "22");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Build no", "B-12673");
                      
    }

	@Test
	public void testmethod() {
		test=extent.createTest("MySecondTest");
		test.log(Status.INFO, "Test Started");
		Response response = RestAssured.get("https://reqres.in/api/users?page=2"); 
		System.out.println(response.getStatusCode());
		AssertJUnit.assertEquals(true, response.getStatusCode()==200);
		test.log(Status.PASS, "Test Passed");
		System.out.println("test method");
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
}