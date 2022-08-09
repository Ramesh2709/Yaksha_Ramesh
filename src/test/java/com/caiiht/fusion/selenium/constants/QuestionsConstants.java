package com.caiiht.fusion.selenium.constants;

public class QuestionsConstants {
	public static String questiontypeselect="//select[@ng-reflect-name='questionType']";
	public static String proficiency="//select[@ng-reflect-name='proficiency']";
	public static String category="//span[text()='Select Category']/..";
	public static String categorysearch="//ng-multiselect-dropdown[@ng-reflect-placeholder='Select Category']//input[@aria-label='multiselect-search']/..";
	public static String skill="//span[text()='Select Skill']/..";
	public static String skillsearch="//ng-multiselect-dropdown[@ng-reflect-placeholder='Select Skill']//input[@aria-label='multiselect-search']/..";
	public static String subskill="//input[@formcontrolname='subSkill']";
	public static String question="//div[@data-placeholder='Enter question here...']";
	public static String options="//div/input[contains(@placeholder,'Option')]";
	public static String hints="//div[@data-placeholder='Enter hint here...']";
	public static String savebutton="//button[contains(text(),'Save')]";
	
	//----------------------------------Question bank
	public static String qbcreate="//button[text()='Create New']";
	public static String qbname="//input[@aria-label='Question Bank Name']";
	public static String qbselecttenant="//span[text()='Select Tenant']/..";
	public static String qbselecttenantsearch="//span[text()='Select Tenant']/../../..//input[@placeholder='Search']";
	public static String qaselectcategory="//span[text()='Select Category']/..";
	public static String qaselectcategorysearch="//span[text()='Select Category']/../../..//input[@placeholder='Search']";
	public static String qadroplist="(//div[contains(@id,'cdk-drop-list')])[2]";
	public static String qbskillsearch="//label[text()='Search By Skill Name']/..//input[@placeholder='Search']";
	public static String createandselect="//button[text()='Create And Assign']";
	//*******************************************************************************************
	public static String mcqradio="//label[text()='MCQ']/../input";
	public static String questionupload="//button[text()='Upload']";
}
