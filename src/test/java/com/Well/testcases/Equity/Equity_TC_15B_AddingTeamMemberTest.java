package com.Well.testcases.Equity;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Equity_TC_15B_AddingTeamMemberTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_19A_ValidLocationCountTest.Equity_TC_19A_ValidLocationCount" })
	@Parameters({ "SheetName","rowNum" })
	public void Equity_TC_15B_00_AddingTeamMember(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName,"Verifies user able to access the invited WPR project");
		try {
			rc.TeamPermissionLevel(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Equity.Equity_TC_15B_AddingTeamMemberTest.Equity_TC_15B_00_AddingTeamMember" })
	@Parameters({ "SheetName", "rowNum" })
	public void Equity_TC_15B_01_ValidateChangeProjectAdministratorForInvalidEmail(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Validate change project administrator for invalid email");
		try {
			rc.ValidateChangeProjectAdministratorForInvalidEmail(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
