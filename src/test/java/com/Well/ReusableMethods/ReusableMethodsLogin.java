package com.Well.ReusableMethods;

import java.io.IOException;
import org.zaproxy.clientapi.core.ClientApiException;
import com.Well.Engine.BaseClass;
import com.Well.Engine.CommonMethod;


public class ReusableMethodsLogin extends BaseClass {
	
	public void Login() throws IOException, InterruptedException, ClientApiException {
		
	//	try{
	   testlog.info("Given User navigate to Wellcertified page");
		String Username=null;
		String Password=null;
		int rowNum; 
				if(ModuleName.contains("Faculty")){
					rowNum =5;
					//rc.RunTimeGetToken(rowNum);
				Username = data.getCellData("Login", "AdminUserName", rowNum);
				Password = data.getCellData("Login", "Password", rowNum);
				}
				if(ModuleName.contains("V2")){
					rowNum =6;
					//rc.RunTimeGetToken(rowNum);
					Username = data.getCellData("Login", "UserName", rowNum);
					Password = data.getCellData("Login", "Password", rowNum);
					}
				if(ModuleName.contains("Common")){
					rowNum =3;
					//rc.RunTimeGetToken(rowNum);
			Username = data.getCellData("Login", "UserName", rowNum);
			Password = data.getCellData("Login", "Password", rowNum);
			}
				if(ModuleName.contains("Keystone")){
					rowNum =7;
					//rc.RunTimeGetToken(rowNum);
					Username = data.getCellData("Login", "UserName", rowNum);
					Password = data.getCellData("Login", "Password", rowNum);
					}
				if(ModuleName.contains("Cornerstone")){
					rowNum =8;
					//rc.RunTimeGetToken(rowNum);
					Username = data.getCellData("Login", "UserName", rowNum);
					Password = data.getCellData("Login", "Password", rowNum);
					}
				if(ModuleName.contains("WELLMembership")){
					rowNum =9;
					//rc.RunTimeGetToken(rowNum);
					Username = data.getCellData("Login", "UserName", rowNum);
					Password = data.getCellData("Login", "Password", rowNum);
					}
				if(!CommonMethod.isElementsExist("Username",5)) {
					rc.SignOut();	
				}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.clearAndSendKey("Username", Username);
		CommonMethod.clearAndSendKey("Password", Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username: " + Username);
		testlog.info("Sending Password: " + Password);
		Thread.sleep(1000);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LoginButton", 0);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.RobustclickElementVisible("LoginButton","SuccessfulLogin");
		testlog.info("And User clicks on Sign IN button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SuccessfulLogin", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("Verfies Login Successful");	
//	}
//	catch(Exception ignored) {
//		CommonMethod.captureNegativeAssertScreenshot();
//	}
	}
	
	public void AdminLogin() throws IOException, InterruptedException, ClientApiException {
		rc.SignOut();
		testlog.info("Given User navigate to Wellcertified page");
		String Username=null;
		String Password=null;
		int rowNum;
			if(TestNGTestName.contains("Faculty")) {
				rowNum =5;
				//rc.RunTimeGetToken(rowNum);
				Username = data.getCellData("Login", "AdminUserName", rowNum);
				Password = data.getCellData("Login", "AdminPassword", rowNum);
			}
			else {
				rowNum =3;
				//rc.RunTimeGetToken(rowNum);
			Username = data.getCellData("Login", "AdminUserName", rowNum);
			Password = data.getCellData("Login", "AdminPassword", rowNum);
			}
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.clearAndSendKey("Username", Username);
		CommonMethod.clearAndSendKey("Password", Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username: " + Username);
		testlog.info("Sending Password: " + Password);
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.click("LoginButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SuccessfulLogin", 0);
		testlog.info("Then User will be redirected to Dashboard page");
	}
	
	public void commonLogin(String Username, String Password, int rowNum) throws IOException, InterruptedException, ClientApiException {
		//rc.RunTimeGetToken(rowNum);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.clearAndSendKey("Username", Username);
		CommonMethod.clearAndSendKey("Password", Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username: " + Username);
		testlog.info("Sending Password: " + Password);
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.click("LoginButton");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("SuccessfulLogin", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		
	}
	
//	public void RedesignPage() throws IOException, InterruptedException, ClientApiException {
//		if (CommonMethod.isElementsExist("IframeRedesignWellContinebtn", 8)) {
//				CommonMethod.switchToFrame("IframeRedesignWellContinebtn");
//				CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("RedesignWellContinebtn", 0);
//				CommonMethod.click("RedesignWellContinebtn");
//				Thread.sleep(3000);
//				CommonMethod.switchToParentFrame();
//			}
//	}
	}
