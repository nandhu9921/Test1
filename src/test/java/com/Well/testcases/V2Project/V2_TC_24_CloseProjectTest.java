package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_24_CloseProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_29_CoreProjectPreCertificationTest.V2_TC_29_CoreProjectPreCertification" })
	@Parameters({ "SheetName","rowNum"})
	public void V2_TC_24_00_CloseProject(String SheetName,int rowNum) throws IOException {

	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Close project functionality");
		try {
			v2project.AdminSearchById(SheetName, rowNum);
			rc.ValidateCloseProject(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_24_CloseProjectTest.V2_TC_24_00_CloseProject" })
	@Parameters({ "SheetName","rowNum"})
	public void V2_TC_24_01_ValidateCloseProjectByAuthenticatedUser(String SheetName,int rowNum) throws IOException {

	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Close project functionality");
		try {
			rc.SignOut();
			login.Login();
			v2project.SearchV2ById(SheetName, rowNum);
			v2project.ClickSearchV2ProjectById(SheetName, rowNum);
			rc.ValidateCloseProjectByAuthenticatedUser(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}