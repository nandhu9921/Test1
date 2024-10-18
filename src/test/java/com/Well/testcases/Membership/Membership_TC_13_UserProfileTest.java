package com.Well.testcases.Membership;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Membership_TC_13_UserProfileTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_13_00_ValidateUserProfilePersonalInformationFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			membership.ValidateUserProfilePersonalInformationFunctionality(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_13_UserProfileTest.Membership_TC_13_00_ValidateUserProfilePersonalInformationFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_13_01_ValidateUserProfileContactInformationFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User Profile Contact Information Functionality");

		try {
			membership.ValidateUserProfileContactInformationFunctionality(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_13_UserProfileTest.Membership_TC_13_01_ValidateUserProfileContactInformationFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_13_02_ValidateUserProfileAboutFunctionality(String SheetName,int rowNum) throws IOException {

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
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_13_UserProfileTest.Membership_TC_13_02_ValidateUserProfileAboutFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_13_03_ValidateUserProfileUpdateLogInFunctionality(String SheetName,int rowNum) throws IOException {

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
	
	@Test(dependsOnMethods = { "com.Well.testcases.Membership.Membership_TC_13_UserProfileTest.Membership_TC_13_03_ValidateUserProfileUpdateLogInFunctionality" })
	@Parameters({ "SheetName","rowNum" })
	public void Membership_TC_13_04_ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate User's Profile Personal Information Functionality");

		try {
			login.AdminLogin();
			membership.ClickOnProfile();
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLFacultyRadioBtn", "MPValidateWELLFacultyLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLAPRadioBtn", "MPValidateWELLApLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLAdvisorRadioBtn", "MPValidateWELLAdvisorLogo");
			membership.ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality(SheetName, rowNum, "MPGreenbuildAwardWELLPortfolioRadioBtn", "MPValidateWELLPortfolioLogo");
			rc.SignOut();
			login.Login();
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
