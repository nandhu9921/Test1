package com.Well.testcases.UserProfile;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class UserProfile_TC_02_ForgotYourPasswordTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_01_UserProfileTest.UserProfile_TC_01_04_ValidateAdminProfilePersonalInformationGreenBuildAwardUpdateFunctionality" })
	@Parameters({ "SheetName", "rowNum" })
	public void UserProfile_TC_02_00_ValidateForgotPasswordFunctionalityWithValidEmail(String SheetName, int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Forgot Your Password Functionality with Valid Email");
		try {
			rc.SignOut();
			membership.ClickForgotYourPasswordLink();
			membership.ValidateForgotPasswordFunctionality("mohammad.huzaifa@sys-core.com", "You will get a reset password link");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	

	@Test(dependsOnMethods = { "com.Well.testcases.UserProfile.UserProfile_TC_02_ForgotYourPasswordTest.UserProfile_TC_02_00_ValidateForgotPasswordFunctionalityWithValidEmail" })
	@Parameters({ "SheetName", "rowNum" })
	public void UserProfile_TC_02_01_ValidateForgotPasswordFunctionalityWithInvalidEmail(String SheetName, int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Forgot Your Password Functionality with Invalid Email");
		try {
			membership.ValidateForgotPasswordFunctionality("abc@sys-core.com", "You will get a reset password link");
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
