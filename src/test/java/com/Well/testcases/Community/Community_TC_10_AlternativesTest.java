package com.Well.testcases.Community;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Community_TC_10_AlternativesTest extends BaseClass {
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_09_AddTeamMemberTest.Community_TC_09_05_ChangeProjectAdministrator" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_00_ValidateAlternativeAAPCommunity(String SheetName, int rowNum)
			throws IOException {
	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "AAP Alternatives Community Functionality");
		try {
		    community.SearchCommunityListById(SheetName, rowNum);
			rc.clickOnAlternatives(SheetName, rowNum);			
			rc.alternativesAAP(SheetName, rowNum, "CommunityAAP");
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_10_AlternativesTest.Community_TC_10_00_ValidateAlternativeAAPCommunity" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_01_AAPAdminChangeStatusAndValidate(String SheetName, int rowNum)
			throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "AAP Alternatives Community Functionality");
		try {
			login.AdminLogin();
			community.CommunityAlternativeChangeStatusAndValidate(SheetName, rowNum, "AAP");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_10_AlternativesTest.Community_TC_10_01_AAPAdminChangeStatusAndValidate" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_02_AAPProjectAdminChangeStatusAndValidateTest(String SheetName, int rowNum)
			throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "AAP Alternatives Community Functionality");
		try {
			rc.SignOut();
			login.Login();
			community.AAPProjectAdminChangeStatusAndValidate(SheetName, rowNum, "AAP");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_10_AlternativesTest.Community_TC_10_02_AAPProjectAdminChangeStatusAndValidateTest" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_03_ValidateAlternativeEPCommunity(String SheetName, int rowNum)
			throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "EP Alternatives Community Functionality");
		try {
			rc.alternativesEP(SheetName, rowNum, "CommunityEP");
			community.validateAlternativesEP(SheetName, rowNum);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_10_AlternativesTest.Community_TC_10_03_ValidateAlternativeEPCommunity" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_04_EPAdminChangeStatusAndValidate(String SheetName, int rowNum)
			throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "EP Alternatives Community Functionality");
		try {
			login.AdminLogin();
			community.CommunityAlternativeChangeStatusAndValidate(SheetName, rowNum, "EP");

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Community.Community_TC_10_AlternativesTest.Community_TC_10_04_EPAdminChangeStatusAndValidate" })
	@Parameters({ "SheetName", "rowNum" })
	public void Community_TC_10_05_EPPProjectAdminChangeStatusAndValidate(String SheetName, int rowNum)
			throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "AAP Alternatives Community Functionality");
		try {
			rc.SignOut();
			login.Login();
			community.AAPProjectAdminChangeStatusAndValidate(SheetName, rowNum, "EP");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}


}