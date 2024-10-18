package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class  Equity_TC_20_CloseProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08B_ScoreCardFilterValidationTest.Equity_TC_08B_ScoreCardFilterValidation" })
	@Parameters({ "SheetName","rowNum"})
	public void  Equity_TC_20_00_CloseProject(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Close project functionality");
		try {
			login.AdminLogin();
			equity.AdminSearchWer(SheetName,rowNum);
			rc.ValidateCloseProject(SheetName, rowNum);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_20_CloseProjectTest.Equity_TC_20_00_CloseProject" })
	@Parameters({ "SheetName","rowNum"})
	public void Equity_TC_20_01_ValidateCloseProjectByAuthenticatedUser(String SheetName,int rowNum) throws IOException {

	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Close project functionality");
		try {
			rc.SignOut();
			login.Login();
			equity.SearchEquityByID(SheetName, rowNum);
			rc.ValidateCloseProjectByAuthenticatedUser(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
