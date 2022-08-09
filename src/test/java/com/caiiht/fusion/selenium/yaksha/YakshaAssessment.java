package com.caiiht.fusion.selenium.yaksha;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.caiiht.fusion.selenium.constants.LoginConstants;
import com.caiiht.fusion.selenium.constants.YakshaAssessmentconst;
import com.caiiht.fusion.selenium.controls.BaseSetup;
import com.caiiht.fusion.selenium.controls.ControlSelectors;

import au.com.bytecode.opencsv.CSVReader;

public class YakshaAssessment {
	static ControlSelectors controls = new ControlSelectors();
	BaseSetup basetSetup = new BaseSetup();
	static LoginConstants logins = new LoginConstants();
	static YakshaAssessmentconst asses = new YakshaAssessmentconst();
	private static CSVReader reader = null;
	private static CSVReader reader1 = null, publishreader1 = null, addtenant = null, scheulereader;
	private static String fileName = "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Assementcreate.csv";
	// private static String fileName1 =
	// "C:\\Users\\rameshg\\seleneium4Example\\seleniumScriptsRegression\\Input\\Tenant\\Assementcreate2.csv";
	private String[] record, categories, categories2, record2, publishreader, addtenantreader, schedulerecord;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		reader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
		scheulereader = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
		reader1 = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
		publishreader1 = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
		addtenant = new CSVReader(new FileReader(fileName), LoginConstants.CSV_FILE_SEPARATOR,
				LoginConstants.DEFAULT_QUOTE_CHARACTER, LoginConstants.CSV_SKIP_HEADER_LINE);
	}

	@Test(priority = 1)
	public void YAssessmentcreate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((record = reader.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/create-assessment");
			controls.clickByXpath(YakshaAssessmentconst.selectcategory);
			String str = record[0];
			categories = str.split(",");
			for (String a : categories) {
				controls.sendTextByXpath(YakshaAssessmentconst.categorysearch, a);
				Thread.sleep(3000);
				controls.clickByXpath("(//li/input[@aria-label='" + a + "']/..)[1]");
				controls.cleartextbyxpath(YakshaAssessmentconst.categorysearch);
			}
			controls.clickByXpath(YakshaAssessmentconst.Assessmenttitle);
			controls.sendTextByXpath(YakshaAssessmentconst.Assessmenttitle, record[1]);
			controls.sendTextByXpath(YakshaAssessmentconst.Assessmentdesc, record[2]);
			controls.sendTextByXpath(YakshaAssessmentconst.AssessmentInstruction, record[3]);
			controls.clickByXpath(YakshaAssessmentconst.savebutton);
			Thread.sleep(4000);
			try {
				boolean c1 = controls.checkelement("//h3[text()=' " + record[1] + "']");
				boolean c2 = controls.checkelement(YakshaAssessmentconst.draftcheck);
				if (c1 == true && c2 == true) {
					Assert.assertEquals(true, true, "Assesment created as draft");
					Reporter.log(record[1] + ":Assesment created as draft");
				} else {
					Reporter.log(record[1] + ":Assesment not created as draft");
					Assert.fail("Assesment not created as draft");
				}

			} catch (Exception e) {
				Reporter.log(record[1] + ":Assesment not created as draft");
				Assert.fail("Assesment not created as draft");
			}

		}
	}

	@Test(priority = 2)
	public void YAssessmentQuestionAdd() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((record2 = reader1.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/list-assessment");
			Thread.sleep(2000);
			controls.sendTextByXpath(YakshaAssessmentconst.manageassessmentsearch, record2[1] + "\n");
			controls.clickByXpath("//h4[text()='" + record2[1] + "']");
			controls.clickByXpath(YakshaAssessmentconst.questionsection);
			String str = record2[4], s1, s2, s3;
			categories = str.split(",");
			for (String a : categories) {
				controls.clickByXpath(YakshaAssessmentconst.addnewsection);
				controls.sendTextByXpath(YakshaAssessmentconst.sectionname, a);
				controls.clickByXpath(YakshaAssessmentconst.selectskilldropdown);
				controls.sendTextByXpath(YakshaAssessmentconst.skillsearch, record2[5]);
				controls.clickByXpath("//input[@aria-label='" + record2[5] + "']/..");
				Thread.sleep(1000);
				controls.clickByXpath(YakshaAssessmentconst.Questiontype);
				controls.clickByXpath(
						YakshaAssessmentconst.Questiontype + "/option[contains(text(),'" + record2[6] + "')]");
				controls.clickByXpath(YakshaAssessmentconst.sectionname);
				controls.clickByXpath(YakshaAssessmentconst.Questionsearch);
				if (record2[7].equalsIgnoreCase("Automatic")) {
					s1 = controls.gettextbyxpath(YakshaAssessmentconst.beginnerresult);
					s2 = controls.gettextbyxpath(YakshaAssessmentconst.interrmediresult);
					s3 = controls.gettextbyxpath(YakshaAssessmentconst.advanceresult);
					s1 = s1.substring(1, s1.length() - 1);// remove last and first
					s2 = s2.substring(1, s2.length() - 1);
					s3 = s3.substring(1, s3.length() - 1);
					// System.out.println(s1+s2+s3);
					int beg = Integer.parseInt(s1);
					/*
					 * System.out.println(beg); System.out.println(record2[8]+record2[9]);
					 */
					if (Integer.parseInt(record2[8]) <= beg) {
						controls.sendTextByXpath(YakshaAssessmentconst.beginnerinput, record2[8]);
					} else {
						Assert.fail("Specified beg question count not present");
					}
					if (Integer.parseInt(record2[9]) <= Integer.parseInt(s2)) {
						controls.sendTextByXpath(YakshaAssessmentconst.interrmedinput, record2[9]);
					} else {
						Assert.fail("Specified intermediate question count not present");
					}
					if (Integer.parseInt(record2[10]) <= Integer.parseInt(s3)) {
						controls.sendTextByXpath(YakshaAssessmentconst.advanceinput, record2[10]);
					} else {
						Assert.fail("Specified intermediate question count not present");
					}

				} else {
					controls.clickByXpath(YakshaAssessmentconst.selectmanullybutton);
					String q = record2[11];
					categories2 = q.split(",");
					for (String t : categories2) {
						try {
							Assert.assertEquals(controls.checkelement("//p[text()='" + t + "']/../../..//input"), true,
									"");
							// Actions actions = new Actions(BaseSetup.driver);
							// actions.moveToElement(BaseSetup.driver.findElement(By.xpath("//p[text()='"+t+"']/../../..//input")));
							controls.clickByXpathpagedown("(//p[text()='" + t + "']/../../..//div)[1]");
						} catch (Exception e) {
							Assert.fail("Given Question not present");
						}
					}
				}

				controls.clickByXpath(YakshaAssessmentconst.Automaticadd);
				controls.clickByXpath(YakshaAssessmentconst.viewselected);
				controls.clickByXpath(YakshaAssessmentconst.viewsavebutton);
				try {
					Assert.assertEquals(controls.checkelement("//h5[contains(text(),'" + record2[4] + "')]"), true,
							"Question section added");
					Reporter.log(record2[4] + " Question section added for Assessment '" + record2[1] + "'");
				} catch (Exception e) {
					Reporter.log(record2[4] + " Question section not added");
					Assert.fail("Question section not added");
				}

			}
		}
	}

	@Test(priority = 3)
	public void YAssessmentPublish() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((publishreader = publishreader1.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/list-assessment");
			Thread.sleep(2000);
			controls.sendTextByXpath(YakshaAssessmentconst.manageassessmentsearch, publishreader[1] + "\n");
			controls.clickByXpath("//h4[text()='" + publishreader[1] + "']");
			Thread.sleep(2000);
			controls.clickByXpath(YakshaAssessmentconst.publishbutton);
			Thread.sleep(2000);
			controls.clickByXpath(YakshaAssessmentconst.Yesbutton);
			Thread.sleep(3000);
			try {
				boolean c1 = controls.checkelement("//h3[text()=' " + publishreader[1] + "']");
				boolean c2 = controls.checkelement(YakshaAssessmentconst.publishcheck);
				if (c1 == true && c2 == true) {
					Assert.assertEquals(true, true, "Assesment published");
					Reporter.log(publishreader[1] + ":Assesment published");
				} else {
					Reporter.log(publishreader[1] + ":Assesment not published");
					Assert.fail("Assesment not published");
				}

			} catch (Exception e) {
				Reporter.log(publishreader[1] + ":Assesment not published");
				Assert.fail("Assesment not published");
			}

		}
	}

	@Test(priority = 4)
	public void YAssessmentTenent() throws InterruptedException, IOException {
		Thread.sleep(2000);
		while ((addtenantreader = addtenant.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/list-assessment");
			Thread.sleep(2000);
			controls.sendTextByXpath(YakshaAssessmentconst.manageassessmentsearch, addtenantreader[1] + "\n");
			controls.clickByXpath(YakshaAssessmentconst.toggelassement);
			controls.clickByXpath(YakshaAssessmentconst.assigntenant);
			controls.clickByXpath(YakshaAssessmentconst.assigntenantdrop);
			controls.sendTextByXpath(YakshaAssessmentconst.assignetenantsearch, addtenantreader[12]);
			controls.clickByXpath(YakshaAssessmentconst.tennantselect + addtenantreader[12] + "']/..");
			controls.clickByXpath(YakshaAssessmentconst.assignbutton);
			try {
				Thread.sleep(2000);
				Reporter.log(controls.gettextxpath(LoginConstants.successmessage));
				Assert.assertEquals(controls.checkelement(LoginConstants.successmessage), true, "File uploaded");
			} catch (Exception e) {
				Reporter.log("Tenent not assigned to Assessment");
				Assert.fail("Tenent not assigned to Assessment");
			}
		}
	}

	@Test(priority = 5)
	public void YAssessmentSchedule()
			throws InterruptedException, IOException, HeadlessException, UnsupportedFlavorException {
		Thread.sleep(2000);
		while ((schedulerecord = scheulereader.readNext()) != null) {
			BaseSetup.driver.navigate()
					.to("https://yaksha-staging-ui.azurewebsites.net/default/app/assessment/list-assessment");
			Thread.sleep(2000);
			controls.sendTextByXpath(YakshaAssessmentconst.manageassessmentsearch, schedulerecord[1] + "\n");
			controls.clickByXpath("//h4[text()='" + schedulerecord[1] + "']");
			controls.clickByXpath(YakshaAssessmentconst.assschedulebutton);
			Thread.sleep(4000);
			controls.clickByXpath(YakshaAssessmentconst.scheduleSelectTenantdrop);
			controls.sendTextByXpath(YakshaAssessmentconst.scheduletenantsearch, schedulerecord[12]);
			controls.clickByXpath("//input[@aria-label='" + schedulerecord[12] + "']/..");
			controls.sendTextByXpath(YakshaAssessmentconst.scheduleAttempt, schedulerecord[13]);
			controls.sendTextByXpath(YakshaAssessmentconst.schedulepasspercent, schedulerecord[14]);
			controls.sendTextByXpath(YakshaAssessmentconst.scheduleduration, schedulerecord[15]);
			if (schedulerecord[16].equalsIgnoreCase("yes")) {
				controls.clickByXpath(YakshaAssessmentconst.schedulesufflequestion);
			}
			if (schedulerecord[17].equalsIgnoreCase("yes")) {
				controls.clickByXpath(YakshaAssessmentconst.scheduleshowresult);
			}
			if (!schedulerecord[18].isEmpty()) {
				String[] news = schedulerecord[18].split("-");
				controls.sendTextByXpath(YakshaAssessmentconst.schedulestartdate,
						news[2] + "-" + news[1] + "-" + news[0]);
				controls.sendTextByXpath(YakshaAssessmentconst.schedulestarttime, schedulerecord[19]);
				String[] news2 = schedulerecord[20].split("-");
				controls.sendTextByXpath(YakshaAssessmentconst.scheduleenddate,
						news2[2] + "-" + news2[1] + "-" + news2[0]);
				controls.sendTextByXpath(YakshaAssessmentconst.scheduleendtime, schedulerecord[21]);
			}
			controls.cleartextbyxpath(YakshaAssessmentconst.scheduleexecutioncount);
			controls.sendTextByXpath(YakshaAssessmentconst.scheduleexecutioncount, schedulerecord[22]);
			if (schedulerecord[23].equalsIgnoreCase("yes")) {
				controls.clickByXpath(YakshaAssessmentconst.schedulecutoffcheckbox);
				controls.cleartextbyxpath(YakshaAssessmentconst.schedulecutofftime);
				controls.sendTextByXpath(YakshaAssessmentconst.schedulecutofftime, schedulerecord[24]);
			}
			if (schedulerecord[25].equalsIgnoreCase("yes"))
				controls.clickByXpath(YakshaAssessmentconst.schedulemock);
			if (schedulerecord[26].equalsIgnoreCase("yes"))
				controls.clickByXpath(YakshaAssessmentconst.schedulesufflemcqoption);
			controls.sendTextByXpath(YakshaAssessmentconst.schedulecustomlable1, schedulerecord[27]);
			controls.sendTextByXpath(YakshaAssessmentconst.schedukecustomlable2, schedulerecord[28]);
			if (schedulerecord[29].equalsIgnoreCase("mail")) {
				controls.clickByXpath(YakshaAssessmentconst.scheuleinvite);
				controls.sendTextByXpath(YakshaAssessmentconst.scheduleemail, schedulerecord[30]);
			} else
				controls.sendTextByXpath(YakshaAssessmentconst.scheduledomain, schedulerecord[30]);
			if (schedulerecord[31].contains("Enable")) {
				controls.clickByXpath(YakshaAssessmentconst.schedulepoctoring);
				if (schedulerecord[31].contains("copy"))
					controls.clickByXpath(YakshaAssessmentconst.schedulecopypaste);
				if (schedulerecord[31].contains("fullscreen"))
					controls.clickByXpath(YakshaAssessmentconst.schedulefullscreen);
				if (schedulerecord[31].contains("window")) {
					controls.clickByXpath(YakshaAssessmentconst.schedulewindowviolation);
					controls.cleartextbyxpath(YakshaAssessmentconst.windowlimit);
					controls.sendTextByXpath(YakshaAssessmentconst.windowlimit, schedulerecord[32]);
				}
			}
			if (schedulerecord[31].contains("whee")) {
				controls.clickByXpath(YakshaAssessmentconst.schedulewheebox);
				if (schedulerecord[31].contains("internetstatus"))
					controls.clickByXpath(YakshaAssessmentconst.Internetstatus);
				if (schedulerecord[31].contains("facecheck"))
					controls.clickByXpath(YakshaAssessmentconst.facecheck);
				if (schedulerecord[31].contains("objectcheck"))
					controls.clickByXpath(YakshaAssessmentconst.objectcheck);
				if (schedulerecord[31].contains("twoface"))
					controls.clickByXpath(YakshaAssessmentconst.twoface);
				if (schedulerecord[31].contains("noface"))
					controls.clickByXpath(YakshaAssessmentconst.noface);
				if (schedulerecord[31].contains("videocheck"))
					controls.clickByXpath(YakshaAssessmentconst.videorec);
				if (schedulerecord[31].contains("livecheck"))
					controls.clickByXpath(YakshaAssessmentconst.live);
				if (schedulerecord[31].contains("end"))
					controls.clickByXpath(YakshaAssessmentconst.endtest);
				if (!schedulerecord[33].isEmpty()) {
					controls.clickByXpath(YakshaAssessmentconst.scheuleenvironment + schedulerecord[33] + "]");
				}
			}
			Thread.sleep(2000);
			controls.clickByXpath(YakshaAssessmentconst.scheduleclick);
			Thread.sleep(2000);
			String message = controls.gettextxpath(LoginConstants.successmessage);
			try {
				Assert.assertEquals(controls.checkelement(YakshaAssessmentconst.copylink), true,
						"Scheduled the Assessment properly");
				controls.clickByXpath(YakshaAssessmentconst.copylink);
				String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
						.getData(DataFlavor.stringFlavor);
				Assert.assertEquals(!data.isEmpty(), true, "Scheduled the Assessment properly");
				Reporter.log(data + " is Schedule link of :" + schedulerecord[1]);
			} catch (Exception e) {
				Reporter.log("Error message: " + message);
				Assert.fail("Scheduled not happen");

			}
		}
	}

}
