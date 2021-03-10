package assureSignTest;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;
import net.assuresign.utils.BaseAssertion;
import net.assuresign.utils.JsonUtils;
import net.assuresign.utils.TestUtils;

public class TestAPI extends Base{

	String token;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public String responseBody;
	public String requestBody;
	
	@Test(enabled=false)
	public void getTokenBearer() throws IOException
	{
		String payload = JsonUtils.payloadGenerator("apiUser.json");
		log.info("Payload generated");
		requestBody = payload;
		RequestSpecification request = RestAssured.given().body(payload);
		request.header("Content-Type","application/json");
		Response response = request.post("https://account.assuresign.net/api/v3.7/authentication/apiUser");
		log.info("Requeste Submited");
		responseBody = response.asPrettyString();
		System.out.println(responseBody);
		token = JsonUtils.getKeyValue(response, "result.token");
		System.out.println(token);
		
		BaseAssertion.verifyStatusCode(response, 200);
	}
	
	@Test
	public void submitSign()
	{
		TestUtils.sendEmail();
	}
	
	

	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Assuresign");
		extent.addSystemInfo("User Name", "NA");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	@BeforeMethod
	public void reportUp(ITestResult result)
	{
		extentTest = extent.startTest(result.getMethod().getMethodName());
		requestBody = null;
		responseBody = null;
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS : " + result.getName());
			extentTest.log(LogStatus.PASS, "Request : "+requestBody+"\n");
			extentTest.log(LogStatus.PASS, "Response : "+responseBody);
		}
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
	}
}
