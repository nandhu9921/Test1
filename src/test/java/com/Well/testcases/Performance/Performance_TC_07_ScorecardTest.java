package com.Well.testcases.Performance;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Performance_TC_07_ScorecardTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_06_LocationTest.Performance_TC_06_Location" })
	@Parameters({"SheetName","rowNum","API" })
	public void Performance_TC_07_00_VerifyReviewErrorMessageByMinScorecardPurseYes(String SheetName, int rowNum, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Performance Scorecard Complete Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
			performance.VerifyReviewErrorMessageByMinScorecardPurseYes();			
			}
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_00_VerifyReviewErrorMessageByMinScorecardPurseYes" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_07_01_CompleteScorecard(String SheetName,int rowNum, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Performance Scorecard Complete Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
			performance.CompleteScorecardWprById(SheetName, rowNum);
			}
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_01_CompleteScorecard" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_07_02_clickMayBeAndValidWarningMessageInReview(String SheetName,int rowNum, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Performance Scorecard Complete Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
			performance.clickMayBeAndValidWarningMessageInReview();
			}
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_02_clickMayBeAndValidWarningMessageInReview" })
	@Parameters({ "SheetName","rowNum","Commodity","API" })
	public void Performance_TC_07_03_ValidWarningMessageInReviewForPendingDocuments(String SheetName,int rowNum, String Commodity, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Performance Scorecard Complete Functionality");
		try {			
			if (API.equalsIgnoreCase("false")) {
				performance.AssignLocationInScorecardDocumentAndValidWarningMessageInReviewForPendingDocuments("Innovation V", SheetName, rowNum, Commodity, AuditfileUpload ,false,false,false,false);
			}			
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_03_ValidWarningMessageInReviewForPendingDocuments" })
	@Parameters({ "SheetName","rowNum","Commodity","API" })
	public void Performance_TC_07_04_UploadDocumentScorecard(String SheetName,int rowNum, String Commodity, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Upload Document Scorecard Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
		        hsr.CommonBulkUploadScorecardDocument(21, SheetName, rowNum, Commodity, FeaturefileUpload ,false,false,false,false);
			}
			else {
				hsr.NavigateScorecard();
				commonAPI.storeRatingScorecardIdAPI(SheetName,rowNum,"wpr_scorecard_id");
				commonAPI.fillScorecardAPI(SheetName,rowNum, "ScorecardId");
				rc.CommonSingleUploadUpdateScorecardDocument("Ensure Adequate Ventilation","PA1", SheetName, rowNum, Commodity,FeaturefileUpload ,false,false,false,false);
				
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_04_UploadDocumentScorecard" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_07_05_ScoreCardCount(String SheetName,int rowNum, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Scorecard Count");
		try {
			if (API.equalsIgnoreCase("false")) {
			rc.VerifyScorecardPurseStatusCount(SheetName, "38","21","17", rowNum);
			rc.VerifyPaperIconCount(SheetName,rowNum,"21");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_05_ScoreCardCount" })
	@Parameters({ "SheetName","rowNum","API" })
	public void Performance_TC_07_06_ScoreCardSearchFilter(String SheetName,int rowNum, String API) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Search Scorecard Filter Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
			performance.searchFilterScoreCard("Meet Thresholds for Particulate Matter");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_06_ScoreCardSearchFilter" })
	@Parameters({ "SheetName","rowNum", "API","Commodity" })
	public void Performance_TC_07_07_ScoreCardOptionFilter(String SheetName,int rowNum, String API, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Scorecard Filter Options Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
				hsr.verifyScoreCardFilterRating(Commodity,"V2ProjectScorecardResponseFilter","V2ProjectScorecardYesFilter", "HsrScorecardPurseStatusYesValid","21","true");
				hsr.verifyScoreCardFilterRating(Commodity,"V2ProjectScorecardVerificationFilter","V2ProjectScorecardOnsitePhotographsFilter", "V2ProjectWPRPFeature","4","true");
				hsr.verifyScoreCardFilterRating(Commodity,"PortfolioScorecardDocumentScaleFilter","PortfolioIndividualFilter", "V2ProjectWPRPFeature","33","true");
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_07_ScoreCardOptionFilter" })
	@Parameters({ "SheetName","rowNum", "API","Commodity" })
	public void Performance_TC_07_08_ScoreCardDocumentCopyAndStoreOldDocumentIdInExcel(String SheetName,int rowNum, String API, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Scorecard Filter Options Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
				login.AdminLogin();
				performance.AdminWprSearch(SheetName, rowNum);
				rc.clickScorecard();
				equity.ScoreCardDocumentCopyAndStoreOldDocumentIdInExcel("Achieve Sound Isolation at Walls", SheetName, rowNum, Commodity, false, false, false, false);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Performance.Performance_TC_07_ScorecardTest.Performance_TC_07_08_ScoreCardDocumentCopyAndStoreOldDocumentIdInExcel" })
	@Parameters({ "SheetName","rowNum", "API","Commodity" })
	public void Performance_TC_07_09_PerformanceValidateOldDocumentId(String SheetName,int rowNum, String API, String Commodity) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Verifies Scorecard Filter Options Functionality");
		try {
			if (API.equalsIgnoreCase("false")) {
				equity.EquityValidateOldDocumentId("Assess and Maintain Drinking Water Quality", SheetName, rowNum, Commodity, FeaturefileUpload, false, false, false, false);
				rc.SignOut();
				login.Login();
				performance.SearchPerformanceByID(SheetName, rowNum);
			}
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
