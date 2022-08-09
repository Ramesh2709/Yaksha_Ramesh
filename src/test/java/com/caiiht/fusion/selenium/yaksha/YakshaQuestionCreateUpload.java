package com.caiiht.fusion.selenium.yaksha;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.QuestionsConstants;
import com.caiiht.fusion.selenium.constants.YakshaAssessmentconst;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaQuestionCreateUpload {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static QuestionsConstants asses = new QuestionsConstants();
	private static CSVReader reader = null;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Question.csv";
	private String[] record, categories;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test
	public void YQuestioncreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
			BaseSetup.driver.navigate()
			.to("https://yaksha-staging-ui.azurewebsites.net/default/app/question/create-question");
			controls.clickByXpath(QuestionsConstants.mcqradio);
			BaseSetup.driver.findElement(By.xpath("//input[@id='fileUpload']")).sendKeys(
					 "C:\\Users\\rameshg\\Desktop\\IIHT-yaksha\\Objective_Question_Import.xlsx");
			Thread.sleep(7000);
			controls.clickByXpath(QuestionsConstants.questionupload);
			Thread.sleep(7000);

	}
}
