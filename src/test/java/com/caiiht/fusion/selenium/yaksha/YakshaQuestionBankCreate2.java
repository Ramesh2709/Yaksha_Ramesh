package com.caiiht.fusion.selenium.yaksha;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.network.Network;
import org.openqa.selenium.devtools.v99.network.model.Headers;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.QuestionsConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;
import com.github.dockerjava.transport.DockerHttpClient.Request;
import com.github.dockerjava.transport.DockerHttpClient.Response;
import com.google.gson.JsonObject;

import au.com.bytecode.opencsv.CSVReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class YakshaQuestionBankCreate2 {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static NewTest newtest=new NewTest();
	static QuestionsConstants asses = new QuestionsConstants();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\QuestionBank.csv";
	private String[] record, categories;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@SuppressWarnings({ "unused", "unused" })
	@Test
	public void YQuestionbankcreate() throws InterruptedException, IOException, AWTException {
		Thread.sleep(2000);

		while ((record = reader.readNext()) != null) {
			
			
			
			newtest.f(record[0],record[1],record[2],record[3]);
			BaseSetup.driver.navigate().to(
					"https://yaksha-staging-ui.azurewebsites.net/default/app/question-bank/question-bank-operation");
			Thread.sleep(4000);
			BaseSetup.driver.findElement(By.xpath("//span[text()='Select Tenant']/..")).click();
			controls.sendTextByXpath("//li/input[@placeholder='Search']", record[1]);
			controls.clickByXpath("//input[@aria-label='"+record[1]+"']/..");
			controls.sendTextByXpath("//div/input[@placeholder='Search']", record[0]+"\n");
			try {
				Thread.sleep(2000);
				Assert.assertEquals(controls.checkelement("//i[@ngbpopover='View Questions']"), true,"Question Bank created");
				Reporter.log(record[0]+" Question Bank Created.");
				
			}catch(Exception e) {
				Reporter.log(record[0]+" Question Bank not Created.");
				Assert.fail(record[0]+" Question Bank not Created.");
			}
			/*
			 * DevTools devTools = ((ChromeDriver) BaseSetup.driver).getDevTools();
			 * devTools.createSession(); devTools.send(Network.enable(Optional.of(0),
			 * Optional.of(0), Optional.of(0))); String Authtoken;Headers samplesss = null;
			 * 
			 * devTools.addListener(Network.requestWillBeSent(), request -> {
			 * 
			 * Headers header1 = request.getRequest().getHeaders();
			 * //samplesss=request.getRequest().getHeaders();
			 * 
			 * if (!header1.isEmpty()) {
			 * 
			 * System.out.println("Request Headers: "); header1.forEach((key, value) -> {
			 * 
			 * System.out.println("  " + key + " = " + value);
			 * 
			 * });
			 * 
			 * }
			 * 
			 * });
			 * 
			 * devTools.addListener(Network.responseReceived(), response -> {
			 * 
			 * Headers header = response.getResponse().getHeaders();
			 * 
			 * if (!header.isEmpty()) {
			 * 
			 * System.out.println("Response Headers: "); header.forEach((key, value) -> {
			 * 
			 * System.out.println("  " + key + " = " + value);
			 * 
			 * }); System.out.println("Response URL:"+response.getResponse().getUrl()
			 * +"/t Status:"+response.getResponse().getStatus());
			 * 
			 * }
			 * 
			 * 
			 * 
			 * });
			 * 
			 * 
			 */
			/*
			 * RestAssured.baseURI=
			 * "https://lx-platform.azurewebsites.net/api/TokenAuth/Authenticate";
			 * //RestAssured.baseURI=
			 * "https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/QuestionBank/CreateOrUpdateQuestionBankAsync";
			 * //Request object RequestSpecification httprequest=RestAssured.given();
			 * //Response Object JsonObject requestparams=new JsonObject(); //String
			 * json"{\"userNameOrEmailAddress\":\"admin\",\"password\":\"123qwe\",\"rememberClient\":false,\"tenancyName\":\"default\"}";
			 * String json=
			 * "{\"userNameOrEmailAddress\":\"admin\",\"password\":\"123qwe\",\"rememberClient\":false,\"tenancyName\":\"default\"}";
			 * httprequest.header("Content-Type","application/json"); //Response
			 * io.restassured.response.Response respose=httprequest.request(Method.POST);
			 * String responseboy=respose.getBody().toString();
			 * System.out.println(responseboy);
			 * 
			 * 
			 * 
			 * 
			 * String json=
			 * "{\"QuestionBankId\":0,\"Name\":\"SAAST\",\"TenantId\":540,\"Scope\":2,\"Type\":0,\"ConfigJson\":null}";
			 * 
			 * 
			 * ArrayList<String>
			 * amounts=RestAssured.given().body(json).post().then().log().all().
			 * extract().path("result.statements.id"); for(String a:amounts){
			 * 
			 * System.out.println("The amount value fetched is "+a); String
			 * json2="{\"QuestionBankId\":"+a+
			 * ",\"SkillIds\":[4651],\"TenantId\":540,\"IsVisible\":true}";
			 * RestAssured.given().body(json2).post().then().log().all(); }
			 */

		}

	}
}
