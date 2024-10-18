package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_41_HealthSafetyV1ProjectTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_40_ReviewV1ProjectTest.V1_TC_40_03_V1ProjectAdminCompleteFinalDocumentationReview" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_41_00_CompleteScorecardHealthSafetyV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Complete HealthSafety Scorecard for V2Project");
		try {
		v2project.HealthSafetyV2Project(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_41_HealthSafetyV1ProjectTest.V1_TC_41_00_CompleteScorecardHealthSafetyV1Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_41_01_UploadHealthSafetyScorecardDocV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload HealthSafety Scorecard Document V2Project");
		try {			
		v2project.uploadHsrDocV2Project(SheetName);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_41_HealthSafetyV1ProjectTest.V1_TC_41_01_UploadHealthSafetyScorecardDocV1Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_41_02_ValidateHealthSafetyInDocumentsTabV1Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Health Safety in Documents Tab V1 Project");
		try {
			rc.clickDocument();
			v2project.ValidateHealthSafetyInDocumentsTabV1Project(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
