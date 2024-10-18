package com.Well.testcases.Performance;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Performance_TC_09_ReviewTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_08_DocumentTest.Performance_TC_08_10_ValidateScorecardPagination" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_00_SubmitPreliminaryReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
		performance.SubmitWPRReview(SheetName, rowNum, "Preliminary Performance Rating Review");
		
		if (TestNGTestName.contains("Main")) {	
		performance.SearchPerformanceProjectId(SheetName, rowNum);	
		performance.SearchPerformanceFilterByStatus(SheetName, rowNum, "Under Review");
		performance.ClickPerformanceProjectId(SheetName, rowNum);	
		login.AdminLogin();
		performance.AdminSearchPerformanceFilterByStatus(SheetName, rowNum, "Under Review");
		}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_00_SubmitPreliminaryReview" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_09_01_EditMRCReviewPreliminary(String SheetName,int rowNum, String API) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Edit MRC Review");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			login.AdminLogin();	
			performance.AdminWprSearch(SheetName, rowNum);
			performance.clickOnReviewTab();
			performance.verifyEditMRCReview(SheetName, rowNum);
			}	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_01_EditMRCReviewPreliminary" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_02_AddMRCCommentReviewPreliminary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Add MRC Comment Review Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			rc.SignOut();
			login.Login();
			performance.SearchPerformanceByID(SheetName,rowNum);
			performance.clickOnReviewTab();
			performance.verifyAddMRCCommentReview(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}  
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_02_AddMRCCommentReviewPreliminary" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_03_ValidateScorecardMRCFromUnderReviewToReadyForReview(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Scorecard MRC from UnderReview to Ready for Review Functionality");

		try {			
			if (!TestNGTestName.contains("WPR-Renewal")) {
			rc.clickScorecard();
			portfolio.ValidateMRCFromUnderReviewToReadyForReviewInScorecard("Meet Thresholds for Particulate Matter", SheetName, rowNum, Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_03_ValidateScorecardMRCFromUnderReviewToReadyForReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_04_ValidateDocumentsLibMRCFromUnderReviewToReadyForReview(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Library MRC from UnderReview to Ready for Review Functionality");

		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			rc.clickDocument();
			portfolio.ValidateMRCFromUnderReviewToReadyForReviewInDocumentLib(SheetName, rowNum, Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_04_ValidateDocumentsLibMRCFromUnderReviewToReadyForReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_05_ReSubmitReviewPreliminary(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Review ReSubmit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			performance.verifyReSubmitReview(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_05_ReSubmitReviewPreliminary" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_06_CompletePreliminaryReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
		login.AdminLogin();
		performance.CompleteWPRReview(SheetName, rowNum, "Preliminary Performance Rating Review");		
	    if (TestNGTestName.contains("Main")) {	
		rc.SignOut();
		login.Login();
	    performance.SearchPerformanceProjectId(SheetName, rowNum);	
	    performance.SearchPerformanceFilterByStatus(SheetName, rowNum, "Achieved");
	    performance.ClickPerformanceProjectId(SheetName, rowNum);	
	    login.AdminLogin();
	    performance.AdminSearchPerformanceFilterByStatus(SheetName, rowNum, "Achieved");
	    performance.clickOnReviewTab();
	    performance.WPRPreliminaryReviewViewBtn();
	}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_06_CompletePreliminaryReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_07_AdminImportDocumentRulingsV2Review(String SheetName,int rowNum,String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			portfolio.AdminImportDocumentRulingsV2Review(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_07_AdminImportDocumentRulingsV2Review" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_08_AdminImportAchievementReview(String SheetName,int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			portfolio.AdminImportAchievementsHsrReview(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_08_AdminImportAchievementReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_09_AdminImportLocationScoresReview(String SheetName,int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			portfolio.AdminImportLocationScoresWprReview(Commodity);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_09_AdminImportLocationScoresReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Performance_TC_09_10_AdminImportResultQCWerReviewWerReview(String SheetName,int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			portfolio.AdminImportResultQCWerReviewWerReview(Commodity,"WELL Performance Rating","Round 1");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_10_AdminImportResultQCWerReviewWerReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_11_AdminUploadReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Review Upload document Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			performance.importReviewUpload();
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_11_AdminUploadReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_12_FinalSubmitReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			rc.SignOut();
			login.Login();
			performance.SearchPerformanceByID(SheetName,rowNum);
	    	performance.SubmitWPRReview(SheetName, rowNum,"Final Performance Rating Review");	
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_12_FinalSubmitReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_13_CompleteFinalReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Complete Performance Review Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			login.AdminLogin();
			performance.CompleteWPRReview(SheetName, rowNum,"Final Performance Rating Review");
			rc.SignOut();
			login.Login();
			performance.SearchPerformanceByID(SheetName,rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_13_CompleteFinalReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_14_CurativeSubmitReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Performance Review Submit Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
		login.AdminLogin();
		performance.AdminWprSearch(SheetName,rowNum);
		performance.ClickBilling();
		rc.Billing(SheetName, rowNum);
		performance.SubmitWPRReview(SheetName, rowNum,"Curative Action Review");	
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_14_CurativeSubmitReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Performance_TC_09_15_CompleteCurativeReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "Complete Performance Review Functionality");
		try {
			if (!TestNGTestName.contains("WPR-Renewal")) {
			performance.CompleteWPRReview(SheetName, rowNum,"Curative Action Review");
			rc.SignOut();
			login.Login();
			performance.SearchPerformanceByID(SheetName,rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_09_ReviewTest.Performance_TC_09_15_CompleteCurativeReview" })
	@Parameters({ "SheetName","rowNum"})
	public void Performance_TC_09_16_MarkAcheivedToReviewDocument(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Mark Acheived to Review Document");
		try {
			pfu.getProjectIdAndSetInIdColumnInExcel(SheetName, rowNum);
			pfu.ValidatingAcheivedReviewDocument(SheetName,rowNum);
	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}