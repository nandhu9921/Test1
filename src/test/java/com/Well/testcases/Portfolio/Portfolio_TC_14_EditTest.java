package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_14_EditTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_12_AlternativesTest.Portfolio_TC_12_Alternatives"})
	@Parameters({ "SheetName", "rowNum","ProjectType" })
	public void Portfolio_TC_14_00_EditAndValidateAccountInformation(String SheetName, int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify account and admin field value");
		try {
			portfolio.editAndValidateAccountInformationPortfolio(SheetName, rowNum, ProjectType);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_14_EditTest.Portfolio_TC_14_00_EditAndValidateAccountInformation"})
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_14_01_EditAndValidateAdminField(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verify account and admin field value");
		try {
			
			login.AdminLogin();
			portfolio.editAndValidateAdmin(SheetName, rowNum);		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_14_EditTest.Portfolio_TC_14_01_EditAndValidateAdminField"})
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_14_02_EditAddAndValidateLegalEntity(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Add and Validate Legal Entity");
		try {	
			if (TestNGTestName.contains("Main")) {
			portfolio.AccountInformationAddAndValidateLegalEntity();
			portfolio.AdminFieldsAddAndValidateLegalEntity();
			}
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
