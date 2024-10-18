package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V1_TC_42_PerformanceV1ProjectTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_41_HealthSafetyV1ProjectTest.V1_TC_41_00_CompleteScorecardHealthSafetyV1Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_42_00_CompletePerformanceScorecardV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Complete Scorecard HealthSafety V2Project");
		try {
		    v2project.performanceV2Project(SheetName, rowNum);
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_42_PerformanceV1ProjectTest.V1_TC_42_00_CompletePerformanceScorecardV1Project" })
	@Parameters({ "SheetName","rowNum","ProjectType" })
	public void V1_TC_42_01_UploadPerformanceScorecardDocV1Project(String SheetName,int rowNum, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Upload Performance Scorecard Document V2Project");
		try {
			v2project.uploadPerformanceDocV2Project(SheetName);	
				
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V1_TC_42_PerformanceV1ProjectTest.V1_TC_42_01_UploadPerformanceScorecardDocV1Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V1_TC_42_02_ValidatePerformanceRatingInDocumentsTabV1Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Validate Health Safety in Documents Tab V1 Project");
		try {
			rc.clickDocument();
			v2project.ValidatePerformanceRatingInDocumentsTabV1Project(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
