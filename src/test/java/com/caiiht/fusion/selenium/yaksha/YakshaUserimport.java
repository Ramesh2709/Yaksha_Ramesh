package com.caiiht.fusion.selenium.yaksha;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.RenderingHints.Key;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

//import com.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVReader;

public class YakshaUserimport {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Createtenant.csv";
	private String[] record;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test
	public void YUserImport() throws InterruptedException, IOException, AWTException {
		Thread.sleep(2000);
		basetSetup.openUserUrl();
		controls.clickByXpath(LoginConstants.importuser);
		Thread.sleep(5000);
		//controls.clickByXpath(LoginConstants.fileup);
		Robot rb = new Robot();
		Thread.sleep(10000);
		StringSelection str = new StringSelection("C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Users_Bulk_Upload.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		BaseSetup.driver.findElement(By.xpath("/html/body/ngb-modal-window/div/div/app-file-upload/div[2]/div/div[1]/div[2]/input")).sendKeys("C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Users_Bulk_Upload.xlsx");
		Thread.sleep(5000);
		//String keys=Keys.chord(Keys.CONTROL,Keys.)
		// press Contol+V for pasting
			//rb.keyPress(KeyEvent.VK_CONTROL);
			//rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
			//rb.keyRelease(KeyEvent.VK_CONTROL);
			//rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
			//rb.keyPress(KeyEvent.VK_ENTER);
			//rb.keyRelease(KeyEvent.VK_ENTER);
			//Thread.sleep(10000);

		controls.clickByXpath(LoginConstants.upload);
		/*
		 * BaseSetup.driver.navigate().to(
		 * "https://yaksha-staging-ui.azurewebsites.net/default/app/question/create-question"
		 * ); Thread.sleep(10000);
		 * BaseSetup.driver.findElement(By.xpath("//input[@id='fileUpload']")).sendKeys(
		 * "C:\\Users\\rameshg\\Desktop\\IIHT-yaksha\\Objective_Question_Import.xlsx");
		 * Thread.sleep(10000);
		 */
		
		try {
			Thread.sleep(2000);
			Reporter.log(controls.gettextxpath(LoginConstants.successmessage));
			Assert.assertEquals(controls.checkelement(LoginConstants.successmessage), true, "File uploaded");
			// Reporter.log("File uploaded successfully");
		} catch (Exception e) {
			Reporter.log("File not uploaded");
			Assert.fail("File not uploaded");
		}

	}
}
