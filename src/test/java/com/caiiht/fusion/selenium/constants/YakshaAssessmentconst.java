package com.caiiht.fusion.selenium.constants;

public class YakshaAssessmentconst {
	public  String username="//input[@aria-label='Username']";
	public  String password="//input[@aria-label='Password']";
	public  String loginbutton="//button[@aria-label='LOGIN']";
	public  String home="//span[text()='Dashboard']";
	public  String addtenentbutton="//button[text()=' Add Tenant']";
	
	public static String selectcategory="//span[@class=\"dropdown-btn\"]";
	public static String categorysearch="//input[@placeholder=\"Search\"]";
	public static String Assessmenttitle="//input[@placeholder=\"Assessment Title\"]";
	public static String Assessmentdesc="//div[@data-placeholder=\"Enter the description here...\"]";
	public static String AssessmentInstruction="//div[@data-placeholder=\"Enter the instructions here...\"]";
	public static String savebutton="//button[text()='Save']";
	public static String draftcheck="//span[text()='Draft']";
	
//************************************Assessment Question***********************************************************
	public static String manageassessmentsearch="//input[@name='searchString']";
	public static String questionsection="//li/a[text()='Questions']";
	public static String addnewsection="//button[text()='Add New Section']";
	public static String sectionname="//input[@formcontrolname='sectionName']";
	public static String selectskilldropdown="//span[text()='Select Skill']";
	public static String skillsearch="//input[@placeholder='Search']";
	public static String Questiontype="//select[@formcontrolname='questionTypeId']";
	public static String Questionsearch="//button[text()=' Search ']";
	public static String beginnerresult="(//label/strong)[1]";
	public static String interrmediresult="(//label/strong)[2]";
	public static String advanceresult="(//label/strong)[3]";
	public static String beginnerinput="(//label/strong)[1]/../..//input";
	public static String interrmedinput="(//label/strong)[2]/../..//input";
	public static String advanceinput="(//label/strong)[3]/../..//input";
	public static String Automaticadd="//button[text()='Add']";
	public static String viewselected="//button[text()='View Selected']";
	public static String begginerviewresult="//label[text()='Beginner']/..//input";
	public static String Intermediateviewresult="//label[text()='Intermediate']/..//input";
	public static String Advancedviewresult="//label[text()='Advanced']/..//input";
	public static String viewsavebutton="//button[text()='Save ']";
	public static String selectmanullybutton="//button[text()='Select Manually']";
	//(//p[text()='cloud']/../../../td)[1]/div/div/input-----------selecte questions,then automatic add need to click
	public static String Addnewskillsbutton="//button[text()=' Add New Skill']";
	public static String publishbutton="//button[text()='Publish']";
	public static String Yesbutton="//button[text()='Yes']";
	public static String publishcheck="//span[text()='Published']";
	public static String schedulebutton="//button[text()='Schedule']";
	//********************************Assign Tenant for assessment
	public static String toggelassement="//*[@id=\"dropdownBasic4\"]/i";
	public static String assigntenant="//button[text()='Assign to Tenant']";
	public static String assigntenantdrop="//div[@class='modal-body']//span[text()='Select Tenant']";
	public static String assignetenantsearch="//div[@class='modal-body']//input[@placeholder='Search']";
	public static String tennantselect="//div[@class='modal-body']//input[@aria-label='";
	public static String assignbutton="//button[text()='Assign']";
	//************************************Schedule******************************************************************
	public static String assschedulebutton="//button[text()='Schedule']";
	public static String scheduleSelectTenantdrop="//span[text()='Select Tenant']";
	public static String scheduletenantsearch="//input[@placeholder='Search']";
	public static String scheduleAttempt="//input[@formcontrolname='attempts']";
	public static String schedulepasspercent="//input[@formcontrolname='passPercentage']";
	public static String scheduleduration="//input[@formcontrolname='duration']";
	public static String schedulesufflequestion="//input[@formcontrolname='shuffleQuestions']/..";
	public static String scheduleshowresult="//input[@formcontrolname='showResults']/..";
	public static String schedulestartdate="//input[@formcontrolname='startDate']";
	public static String schedulestarttime="//input[@formcontrolname='startTime']";
	public static String scheduleenddate="//input[@formcontrolname='endDate']";
	public static String scheduleendtime="//input[@formcontrolname='endTime']";
	public static String scheduleexecutioncount="//input[@formcontrolname='executionCount']";
	public static String schedulecutoffcheckbox="//input[@formcontrolname='isCutOffTimeEnabled']";
	public static String schedulecutofftime="//input[@formcontrolname='cutOffTime']";
	public static String schedulemock="//input[@formcontrolname='isMockSchedule']/..";
	public static String schedulesufflemcqoption="//input[@formcontrolname='isShuffleMcqOptions']/..";
	public static String schedulecustomlable1="//input[@formcontrolname='customLabel1']";
	public static String schedukecustomlable2="//input[@formcontrolname='customLabel2']";
	public static String scheuleinvite="//label[text()='Invite Candidates']";
	public static String scheduledomain="//input[@formcontrolname='domain']";
	public static String scheduleemail="//input[@formcontrolname='candidateEmails']";
	public static String schedulepoctoring="//input[@formcontrolname='enableProctoring']/..";
	public static String schedulecopypaste="//input[@formcontrolname='preventCopyPaste']/..";
	public static String schedulefullscreen="//input[@formcontrolname='enableFullscreenMode']/..";
	public static String schedulewindowviolation="//input[@formcontrolname='restrictBrowserWindowViolation']/..";
	public static String schedulewheebox="//input[@formcontrolname='wheeboxSettings']/..//label";
	public static String manualapproval="//label[text()=' Manual approval ']";
	public static String scheuleenvironment="(//input[@formcontrolname='environmentValidation']/..)[";//2]";
	public static String Internetstatus="//input[@formcontrolname='internetStatus']/..";
	public static String facecheck="//input[@formcontrolname='faceCheck']/..";
	public static String objectcheck="//input[@formcontrolname='suspiciousObjectCheck']/..";
	public static String twoface="//input[@formcontrolname='twoFaces']/..";
	public static String noface="//input[@formcontrolname='noFace']/..";
	public static String videorec="//input[@formcontrolname='videoRecording']/..";
	public static String live="//input[@formcontrolname='liveProctoring']/..";
	public static String endtest="(//input[@formcontrolname='proctoringViolation']/..)[2]";
	public static String scheduleclick="//div[@class='modal-footer']/button[text()='Schedule']";
	public static String windowlimit="//input[@formcontrolname='windowViolationLimit']";
	public static String copylink="//app-schedule-assessment/form/div[2]/div/i";
	//String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); paste the text in log file. 
}
