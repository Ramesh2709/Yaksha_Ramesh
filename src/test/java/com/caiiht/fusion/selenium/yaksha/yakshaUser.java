package com.caiiht.fusion.selenium.yaksha;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.Userportalconstants;
import com.caiiht.fusion.selenium.constants.YakshaAssessmentconst;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class yakshaUser {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	LoginConstants logins = new LoginConstants();
	//static ManagetagsConstants asses = new ManagetagsConstants();
	private static CSVReader reader = null;
	private static String fileName =BaseSetup.inputFilePath+"/Tenant/userportal.csv";
	private String[] record;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}


	@Test
	public void YUserLogin() throws Exception {
		Thread.sleep(2000);
		BaseSetup.launchincobrowser();
		String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
				.getData(DataFlavor.stringFlavor);
		while ((record = reader.readNext()) != null) {
		BaseSetup.incognitodriver.get(data);
		controls.sendTextByXpathinco(Userportalconstants.Firstname, record[0]);
		controls.sendTextByXpathinco(Userportalconstants.lastname, record[1]);
		controls.sendTextByXpathinco(Userportalconstants.email, record[2]);
		controls.clickByXpathincog(Userportalconstants.startbutton);
		controls.clickByXpathincog(Userportalconstants.checkbox);
		controls.clickByXpathincog(Userportalconstants.usercontinue);
		Thread.sleep(5000);
		Boolean colorr=BaseSetup.incognitodriver.findElement(By.xpath(Userportalconstants.next)).isDisplayed();
		System.out.println(colorr);
		boolean i=true;String s2,s;
		Thread.sleep(7000);
		while(colorr) {
			s2=BaseSetup.incognitodriver.findElement(By.xpath("//div[@class='card']/div[contains(@class,'card-body')]//p/..")).getText();
			List<WebElement> inputs=BaseSetup.incognitodriver.findElements(By.xpath(Userportalconstants.inputs));
			System.out.println(inputs.size());
			for(WebElement a:inputs) {
				try {
				a.click();
				}catch(Exception e) {
					
				}
			}
			//String s=BaseSetup.incognitodriver.findElement(By.xpath("//div[@class='card-body']//div")).getText();
			BaseSetup.incognitodriver.findElement(By.xpath(Userportalconstants.nextold)).click();
			Thread.sleep(2000);
			s=BaseSetup.incognitodriver.findElement(By.xpath("//div[@class='card']/div[contains(@class,'card-body')]//p/..")).getText();
			if(!s.equals(s2)) {
			//BaseSetup.incognitodriver.findElement(By.xpath(Userportalconstants.next)).click();
			colorr=true;
			}
			else
				colorr=false;
			}
		}
		controls.clickByXpathincog(Userportalconstants.submitassessment);
		controls.clickByXpathincog(Userportalconstants.submitconform);
		Thread.sleep(7000);
		BaseSetup.incognitodriver.close();
		}

	}


