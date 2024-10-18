package com.Well.testcases.V2Project;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class V2_TC_11A_HealthSafetyReviewV2ProjectTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_07B_ScorecardV2ProjectOptValidTest.V2_TC_07B_01_ScorecardV2ProjectOptValid" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11A_00_HealthSafetySubmitReviewV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V2Project Review Submit Functionality");
		try {
		v2project.hsrOptSubmitReviewV2Project(SheetName, rowNum);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11A_00_HealthSafetySubmitReviewV2Project.V2_TC_11A_00_HealthSafetySubmitReviewV2Project" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11A_01_EditMRCReviewPreliminary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Edit MRC Review");
		try {
			login.AdminLogin();	
			v2project.AdminSearchById(SheetName, rowNum);
			performance.clickOnReviewTab();
			performance.verifyEditMRCReview(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11A_00_HealthSafetySubmitReviewV2Project.V2_TC_11A_01_EditMRCReviewPreliminary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11A_02_AddMRCCommentReviewPreliminary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Add MRC Comment Review Functionality");
		try {
			rc.SignOut();
			login.Login();
			v2project.SearchV2ProjectById(SheetName, rowNum);	
			performance.clickOnReviewTab();
			performance.verifyAddMRCCommentReview(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11A_00_HealthSafetySubmitReviewV2Project.V2_TC_11A_02_AddMRCCommentReviewPreliminary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11A_03_ReSubmitReviewPreliminary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Review ReSubmit Functionality");
		try {
			performance.verifyReSubmitReview(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.V2Project.V2_TC_11A_00_HealthSafetySubmitReviewV2Project.V2_TC_11A_03_ReSubmitReviewPreliminary" })
	@Parameters({ "SheetName","rowNum" })
	public void V2_TC_11A_04_HealthSafetyCompleteReviewV2Project(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"V2Project Review Submit Functionality");
		try {
			login.AdminLogin();
			v2project.hsrOptCompleteReviewV2Project(SheetName, rowNum);
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
