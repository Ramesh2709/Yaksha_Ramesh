package com.caiiht.fusion.selenium.yaksha;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.QuestionsConstants;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaQuestionBankCreate {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
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
			BaseSetup.driver.navigate().to(
					"https://yaksha-staging-ui.azurewebsites.net/default/app/question-bank/question-bank-operation");
			controls.clickByXpath(QuestionsConstants.qbcreate);
			controls.sendTextByXpath(QuestionsConstants.qbname, record[0]);
			controls.clickByXpath(QuestionsConstants.qbselecttenant);
			controls.sendTextByXpath(QuestionsConstants.qbselecttenantsearch, record[1]);
			controls.clickByXpath("//input[@aria-label='" + record[1] + "']/..");
			controls.clickByXpath(QuestionsConstants.qaselectcategory);
			controls.sendTextByXpath(QuestionsConstants.qaselectcategorysearch, record[2]);
			controls.clickByXpath("//input[@aria-label='" + record[2] + "']/..");
			Thread.sleep(1000);
			String str = record[3];
			//WebElement link = BaseSetup.driver.findElement(By.xpath(QuestionsConstants.qadroplist));
			categories = str.split(",");
			for (String a : categories) {
				controls.cleartextbyxpath(QuestionsConstants.qbskillsearch);
				//controls.sendTextByXpath(QuestionsConstants.qbskillsearch, a+"\n");
				controls.clickByXpath("//span[text()='Select Skill']/..");
				controls.sendTextByXpath("//span[text()='Select Skill']/../../..//input[@placeholder='Search']", a);
				controls.clickByXpath("//input[@aria-label='"+a+"']/..");
				Actions action = new Actions(BaseSetup.driver);
				//action.moveToElement(BaseSetup.driver.findElement(By.xpath("//button[text()='Create And Assign']")));
				action.sendKeys(Keys.PAGE_DOWN).build().perform();
				Thread.sleep(4000);
				WebElement link2 = BaseSetup.driver.findElement(By.xpath("//h4[text()='JulyTestSkill']/../.."));
				int x1=link2.getLocation().getX();
				int y1=link2.getLocation().getY();
				System.out.println(x1+" "+y1);
				String script = "var p=document.querySelector('#cdk-drop-list-0 > div:nth-child(1)');var ele=document.querySelector('#cdk-drop-list-1');ele.appendChild(p);";
				((JavascriptExecutor) BaseSetup.driver).executeScript(script);
				Actions builder = new Actions(BaseSetup.driver);
				Thread.sleep(2000);
				int x=link2.getLocation().getX();
				int y=link2.getLocation().getY();
				System.out.println(x+" "+y);
				
				//WebDriver driver; // Assigned elsewhere
				JavascriptExecutor js = (JavascriptExecutor) BaseSetup.driver;
				js.executeScript("document.querySelector('#cdk-drop-list-1').setAttribute('ng-reflect-data', '[object Object]')");
				
				/*
				 * String scriptnew =
				 * "var p = document.createElement('a');var ele = document.querySelector('#cdk-drop-list-0 > div:nth-child(1)');p.setAttribute('_ngcontent-ddo-c395','');p.setAttribute('href','javascript:void(0);');p.setAttribute('class','align-self-center border text-danger p-10');ele.appendChild(p);"
				 * ; ((JavascriptExecutor) BaseSetup.driver).executeScript(scriptnew);
				 */
				WebElement ele=BaseSetup.driver.findElement(By.xpath("//*[@id=\"cdk-drop-list-1\"]/div/span/img"));
				int xb=ele.getLocation().getX();
				int yb=ele.getLocation().getY();
				//WebElement ele2=BaseSetup.driver.findElement(By.xpath("//*[@id=\"cdk-drop-list-1\"]"));
				WebElement ele2=BaseSetup.driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/app-create-question-bank/div[5]/div[1]/div/div"));//("//*[@id=\"main-wrapper\"]/div/div/app-create-question-bank/div[3]/div[2]/div"));
				builder.clickAndHold(ele).pause(Duration.ofSeconds(1)).moveToElement(BaseSetup.driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div/div/app-create-question-bank/div[3]/div[2]/div"))).pause(Duration.ofSeconds(1)).click().perform();
				builder.click(ele2);
				//builder.dragAndDropBy(ele, xb,yb).perform();
				//builder.dragAndDrop(ele, ele2).perform();
				//WebElement a = driver.findElement(By.cssSelector("your_selector"));
		        //WebElement b = driver.findElement(By.cssSelector("your_selector"));

				
				
		        int xn = ele2.getLocation().x;
		        int yn = ele2.getLocation().y;

		        Actions actions = new Actions(BaseSetup.driver);
		        actions.moveToElement(ele)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(ele)
                .pause(Duration.ofSeconds(1))
                .moveToElement(ele)
                .pause(Duration.ofSeconds(1))
                .release(ele).build().perform();

				
				
				
				//String script2="document.querySelector('#cdk-drop-list-1').contentWindow.location.reload(true);";
				//((JavascriptExecutor) BaseSetup.driver).executeScript(script2);
				
				
				
				
				
				//builder.clickAndHold(link2).build().perform();
				//builder.dragAndDrop(link2, link2).build().perform();
				//builder.dragAndDropBy(link2, x+2,y);
				//builder.dragAndDrop(link2, link2).perform();
				Thread.sleep(4000);
				//controls.clickByXpath("//h4[text()='JulyTestSkill']/../..");
			}
			controls.clickByXpath(QuestionsConstants.createandselect);
			try {
				Thread.sleep(2000);
				Reporter.log(controls.gettextxpath(LoginConstants.successmessage));
				Assert.assertEquals(controls.checkelement(LoginConstants.successmessage), true, "QB Created Successfully");
				// Reporter.log("File uploaded successfully");
			} catch (Exception e) {
				Reporter.log("Question Bank not created");
				Assert.fail("Question Bank not created");
			}
		}

	}
}
