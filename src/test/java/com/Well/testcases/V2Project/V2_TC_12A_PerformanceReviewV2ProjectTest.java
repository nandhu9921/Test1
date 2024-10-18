package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_12A_PerformanceReviewV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_12_PerformanceV2ProjectTest.V2_TC_12_01_UploadPerformanceScorecardDocV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_12A_00_PerformanceReviewV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V2Project Review Submit Functionality");
		try {	
		v2project.wprSubmitReviewV2Project(SheetName, rowNum);	
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_12A_PerformanceReviewV2ProjectTest.V2_TC_12A_00_PerformanceReviewV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_12A_01_PerformanceCompleteReviewV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V2Project Complete Review Functionality");
		try {
			login.AdminLogin();
			v2project.wprCompleteReviewV2Project(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);	
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
