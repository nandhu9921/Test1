package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;


public class Performance_TC_21_CloseProjectTest extends BaseClass {

	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_11A_ValidateProjectAccessTeamTest.Performance_TC_11A_02_ValidateChangeAdminstrator" })
	@Parameters({ "SheetName","rowNum","Commodity"})
	public void Performance_21_00_AdminImportLogs(String SheetName,int rowNum,String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Admin Import Logs Functionality");

		try {
			login.AdminLogin();
			performance.AdminWprSearch(SheetName, rowNum);
			if (!TestNGTestName.contains("WPR-Renewal")) {
			performance.clickOnReviewTab();
			performance.WPRPreliminaryReviewViewBtn();
			portfolio.AdminImportResult();
			portfolio.AdminReviewimportLogs(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_21_CloseProjectTest.Performance_21_00_AdminImportLogs" })
	@Parameters({ "SheetName","rowNum","Commodity"})
	public void Performance_21_01_AdminReviewimportHistory(String SheetName,int rowNum,String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Review Import History Functionality");

		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
				portfolio.AdminReviewimportHistory(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_21_CloseProjectTest.Performance_21_01_AdminReviewimportHistory" })
	@Parameters({ "Commodity"})
	public void Performance_TC_21_02_AdminImportLocationPublishReview(String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Admin Import Location Publish Review Functionality");

		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			performance.WPRPreliminaryReviewViewBtn();
			portfolio.AdminImportLocationPulishReview(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	} 
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_21_CloseProjectTest.Performance_TC_21_02_AdminImportLocationPublishReview" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_21_03_CloseProject(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Close project functionality");
		try {
			
			rc.ValidateCloseProject(SheetName, rowNum);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
