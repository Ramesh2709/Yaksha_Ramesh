package com.caiiht.fusion.selenium.yaksha;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NewTest {
	@SuppressWarnings("null")
	@Test
	public void f(String a, String b, String c, String d) {
		String tenenatids = null;
		// Get Tenant id:
		String baseURI = "https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/Tenant/GetTenantDetails?searchString="
				+ b + "&skipCount=0&maxResultCount=10000";
		BaseSetup.driver.navigate().to(baseURI);
		String snew=BaseSetup.driver.findElement(By.xpath("//pre")).getText();
		System.out.println(snew);
		//RequestSpecification tenantrequest = RestAssured.given();
		//Response tenatresponse = tenantrequest.request(Method.GET);
		String tenatresponsebody = snew;
		Pattern tenants = Pattern.compile("id\":(.*?),\"name\":\""+b+"\"");
		Matcher matcher = tenants.matcher(tenatresponsebody);
		if (matcher.find()) {
			tenenatids = matcher.group(1).toString();
		}
		System.out.println(b+"'s id is: "+tenenatids);
		
		//Get Authtoken
		RestAssured.baseURI = "https://lx-platform.azurewebsites.net/api/TokenAuth/Authenticate";
		RequestSpecification httprequest = RestAssured.given();
		JsonObject requestparams = new JsonObject();
		String json = "{\"userNameOrEmailAddress\":\"admin\",\"password\":\"123qwe\",\"rememberClient\":false,\"tenancyName\":\"default\"}";
		httprequest.header("Content-Type", "application/json");
		httprequest.body(json);
		Response response = httprequest.request(Method.POST);
		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
		String[] resp = Responsebody.split("\"");
		Pattern pattern = Pattern.compile(":\"(.*?)\"");
		Matcher matchers = pattern.matcher(Responsebody);
		// String token=matcher.group(1);
		System.out.println(resp[3]);
		
		//Get Skill ids:
		RestAssured.baseURI = "https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/Skill/SearchSkillDetails";
		RequestSpecification skillrequest = RestAssured.given();
		String[] skillids=d.split(",");String skids = null;String p;int i=0;
		String postskil="{\"ID\":null,\"searchString\":\"";
		for(String s:skillids) {
			p=postskil+s+"\",\"skipCount\":0,\"maxResultCount\":10}";
			skillrequest.header("Content-Type", "application/json");
			skillrequest.header("Authorization", "Bearer " + resp[3]);
			skillrequest.body(p);
			Response skillresponse = skillrequest.request(Method.POST);
			String skillresponsebody = skillresponse.getBody().asString();
			System.out.println(skillresponsebody);
			Pattern pa=Pattern.compile("id\":(.*?)}");
			Matcher m=pa.matcher(skillresponsebody);
			if (m.find()) {
				System.out.println(m.group(1).toString());
			}
			skids=(m.group(1).toString())+",";
			i++;
		}
		
		
		//Create QB
		RestAssured.baseURI = "https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/QuestionBank/CreateOrUpdateQuestionBankAsync";
		RequestSpecification httprequest1 = RestAssured.given();
		String json1 = "{\"QuestionBankId\":0,\"Name\":\""+a+"\",\"TenantId\":"+tenenatids+",\"Scope\":2,\"Type\":0,\"ConfigJson\":null}";
		httprequest1.header("Content-Type", "application/json");
		httprequest1.header("Authorization", "Bearer " + resp[3]);
		httprequest1.body(json1);
		Response response2 = httprequest1.request(Method.POST);
		String Responsebody2 = response2.getBody().asString();
		System.out.println(Responsebody2);

		String[] skillnews=skids.split(",");
		
		
		// https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/QuestionBank/CreateOrUpdateQuestionBankSkillAsync
		String[] resp2 = Responsebody2.split("\"");
		String[] ids = resp2[30].split("}");
		String[] ids2 = ids[0].split(":");
		String json2=null;
		RestAssured.baseURI = "https://yaksha-staging-core-api.azurewebsites.net/api/services/yaksha/QuestionBank/CreateOrUpdateQuestionBankSkillAsync";
		RequestSpecification httprequest2 = RestAssured.given();
		if(skillnews.length>0) {
			json2 = "{\"QuestionBankId\":" + ids2[1]
				+ ",\"SkillIds\":["+skids+"],\"TenantId\":"+tenenatids+",\"IsVisible\":true}";
		}
		else {
			json2 = "{\"QuestionBankId\":" + ids2[1]
					+ ",\"SkillIds\":["+skillnews[0]+"],\"TenantId\":"+tenenatids+",\"IsVisible\":true}";	
		}
		httprequest2.header("Content-Type", "application/json");
		httprequest2.header("Authorization", "Bearer " + resp[3]);
		httprequest2.body(json2);
		Response response3 = httprequest2.request(Method.POST);
		String Responsebody3 = response3.getBody().asString();
		System.out.println(Responsebody3);

	}
}
