package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_14B_AddTeamV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.V2Project.V2_TC_06_AgreementV2ProjectTest.V2_TC_06_00_AgreementV2Project" })
	@Parameters({ "SheetName", "rowNum" })
	public void V2_TC_14B_00_AddTeamV2Project(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Adding Team for V2Project");
		try {
			rc.TeamPermissionLevel(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.V2Project.V2_TC_14B_AddTeamV2ProjectTest.V2_TC_14B_00_AddTeamV2Project" })
	@Parameters({ "SheetName", "rowNum" })
	public void V2_TC_14B_00_ValidateChangeProjectAdministratorForInvalidEmail(String SheetName, int rowNum) throws IOException {

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
