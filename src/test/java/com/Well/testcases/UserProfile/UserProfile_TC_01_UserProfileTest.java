package com.Well.testcases.UserProfile;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class UserProfile_TC_01_UserProfileTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum" })
	public void UserProfile_TC_01_00_ValidateUserProfilePersonalInformationFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.ClickOnProfile();
			membership.ValidateUserProfileContactInformation(SheetName, rowNum);
			membership.ValidateUserProfilePersonalInformationFunctionality(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_01_UserProfileTest.UserProfile_TC_01_00_ValidateUserProfilePersonalInformationFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void UserProfile_TC_01_01_ValidateUserProfileContactInformationFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User Profile Contact Information Functionality");

		try {
			membership.ValidateUserProfileContactInformationFunctionality(SheetName, rowNum);
			membership.ValidateUserProfilePersonalInformationFunctionality1(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_01_UserProfileTest.UserProfile_TC_01_01_ValidateUserProfileContactInformationFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void UserProfile_TC_01_02_ValidateUserProfileAboutFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User Profile About Functionality");

		try {
			membership.ValidateUserProfileAboutFunctionality(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_01_UserProfileTest.UserProfile_TC_01_02_ValidateUserProfileAboutFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void UserProfile_TC_01_03_ValidateUserProfileUpdateLogInFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User Profile Update LogIn Functionality");

		try {
			membership.ValidateUserProfileUpdateLogInFunctionality(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_01_UserProfileTest.UserProfile_TC_01_03_ValidateUserProfileUpdateLogInFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void UserProfile_TC_01_04_ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			login.AdminLogin();
			membership.ClickOnProfile();
			membership.ValidateUserProfileContactInformationFunctionality(SheetName, rowNum);
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLFacultyRadioBtn", "MPValidateWELLFacultyLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLAPRadioBtn", "MPValidateWELLApLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLAdvisorRadioBtn", "MPValidateWELLAdvisorLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLPortfolioRadioBtn", "MPValidateWELLPortfolioLogo");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
