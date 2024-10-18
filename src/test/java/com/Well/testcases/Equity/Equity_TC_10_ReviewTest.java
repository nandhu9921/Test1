package com.Well.testcases.Equity;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Well.Engine.BaseClass;

public class Equity_TC_10_ReviewTest extends BaseClass {

	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_09_DocumentTest.Equity_TC_09_07_AlternativeDocument" })
	@Parameters({ "SheetName","rowNum" })
	public void Equity_TC_10_00_PreliminarySubmitReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName, "Equity Review Submit Functionality");
		try {
			rc.clickOnTeamTab();
			rc.team(SheetName, rowNum);
			rc.SignOut();
			rc.teamLogin(SheetName, rowNum,"TeamMemberEmail");
			equity.SearchEquityByRegisterStatus(SheetName,rowNum);
			equity.NASubmitReviewAsTeam();
			rc.SignOut();
			login.Login();
			equity.SearchEquityByID(SheetName,rowNum);
			rc.clickOnTeamTab();
			rc.deleteAddedTeamMember(SheetName, rowNum);
			equity.WERSubmitReview(SheetName, rowNum, "Preliminary Equity Rating Review");	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_00_PreliminarySubmitReview" })
	@Parameters({ "SheetName","rowNum","ProjectType","Commodity" })
	public void Equity_TC_10_01_UnderReviewToolTipTextValidation(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Equity Validate Under Review Functionality");
		try {	
		rc.clickScorecard();
		performance.ValidateExpiringSoonStatusInScorecardAndDocumentLib("Create DEI Assessment and Action Plan",SheetName, rowNum, ProjectType, Commodity, SampleJpgfile, false,
			false, false, false, "Under Review");
		pfu.ValidatingUnderReviewDocument();
		rc.clickScorecard();
		rc.validateReviewInProgressDocUpload("Create DEI Assessment and Action Plan",Commodity);
		
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_01_UnderReviewToolTipTextValidation" })
	@Parameters({ "SheetName","rowNum" })
	public void Equity_TC_10_02_PreliminaryCompleteReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
        StartTest(TestCaseName, "Equity Completing Review Functionality");
		try {
			login.AdminLogin();
			equity.WERCompleteReview(SheetName, rowNum,"Preliminary Equity Rating Review");
			rc.SignOut();
			login.Login();
			equity.SearchEquityByID(SheetName,rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08_ScoreCardTest.Equity_TC_10_02_PreliminaryCompleteReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Equity_TC_10_03_FinalSubmitReview(String SheetName,int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        StartTest(TestCaseName, "Equity Review Submit Functionality");
			try {
				equity.WERSubmitReview(SheetName, rowNum, "Final Equity Rating Review");	
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}	
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_03_FinalSubmitReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Equity_TC_10_04_FinalCompleteReview(String SheetName,int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        StartTest(TestCaseName, "Equity Completing Review Functionality");
			try {
				login.AdminLogin();
				equity.WERCompleteReview(SheetName, rowNum,"Final Equity Rating Review");
				rc.SignOut();
				login.Login();
				equity.SearchEquityByID(SheetName,rowNum);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_08_ScoreCardTest.Equity_TC_10_04_FinalCompleteReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Equity_TC_10_05_CurativeSubmitReview(String SheetName,int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        StartTest(TestCaseName, "Equity Review Submit Functionality");
			try {
			    login.AdminLogin();
				equity.AdminSearchWer(SheetName,rowNum);
				performance.ClickBilling();
				rc.Billing(SheetName, rowNum);
				equity.WERSubmitReview(SheetName, rowNum,"Curative Action Review");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}	
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_05_CurativeSubmitReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Equity_TC_10_06_CurativeCompleteReview(String SheetName,int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        StartTest(TestCaseName, "Equity Completing Review Functionality");
			try {
				equity.WERCompleteReview(SheetName, rowNum, "Curative Action Review");	
				rc.SignOut();
				login.Login();
				equity.SearchEquityByID(SheetName,rowNum);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;         
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_06_CurativeCompleteReview" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_07_AchievingReviewDocument(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
	        StartTest(TestCaseName, "Equity Achieving Review Document");
			try {
			
				rc.ScorecardNavigation();
				rc.CommonSingleUploadScorecardDocument("Offer Sick Leave and Flexible Work", SheetName, rowNum, Commodity, AlternativeFileUpload, false, false, false, false);
				pfu.getProjectIdAndSetInIdColumnInExcel(SheetName, rowNum);
				pfu.ValidatingAcheivedReviewDocument(SheetName,rowNum);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;         
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_07_AchievingReviewDocument" })
		@Parameters({ "SheetName","rowNum", "ProjectType", "Commodity" })
		public void Equity_TC_10_08_ScoreCardPurseSearchFilterForFeatureDoc(String SheetName,int rowNum, String ProjectType, String Commodity) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verifies Scorecard Purse location filter Functionality");
			try {				
				rc.clickScorecard();
				rc.ScorecardPurseSearchFilter("Create DEI Assessment and Action Plan", "Achieved");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_08_ScoreCardPurseSearchFilterForFeatureDoc" })
		@Parameters({ "SheetName","rowNum" })
		public void Equity_TC_10_09_ScoreCardPurseSearchFilterForAlternativeDoc(String SheetName,int rowNum) throws IOException {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Verifies Scorecard Purse location filter Functionality");
			try {				
				rc.ScorecardPurseSearchFilter("Offer Sick Leave and Flexible Work", "Achieved");
							
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_09_ScoreCardPurseSearchFilterForAlternativeDoc" })
		@Parameters({ "SheetName","rowNum", "Commodity" })

		public void Equity_TC_10_10_ValidatePostReviewInScorecardFeature(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Equity Validate Post Review Functionality In Scorecard Feature");

			try {	
				equity.ValidatePostReviewInScorecardFeature(SheetName, rowNum, "Select Preferred Cleaning Products", Commodity);
				equity.ValidatePostReviewArchiveDocumentFunctionalityInScorecard(SheetName, rowNum, "Select Preferred Cleaning Products", Commodity);

			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;         
			}
		}		

		@Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_10_ValidatePostReviewInScorecardFeature" })
		@Parameters({ "SheetName","rowNum", "Commodity" })
		public void Equity_TC_10_11_ValidatePostReviewInScorecardAudit(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Equity Validate Post Review Functionality In Scorecard Audit");
			try {
				equity.ValidatePostReviewInScorecardAudit(SheetName, rowNum, "Provide Physical Activity Spaces", Commodity);
				equity.ValidatePostReviewArchiveDocumentFunctionalityInScorecard(SheetName, rowNum, "Provide Physical Activity Spaces", Commodity);
			
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	
	    @Test(dependsOnMethods = { "com.Well.testcases.Equity.Equity_TC_10_ReviewTest.Equity_TC_10_11_ValidatePostReviewInScorecardAudit" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_12_ValidatePostReviewInScorecardAlternative(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Equity Validate Post Review Functionality In Scorecard Alternative");
			try {
				equity.ValidatePostReviewInScorecardFeature(SheetName, rowNum, "Offer Sick Leave and Flexible Work", Commodity);
				equity.ValidatePostReviewArchiveDocumentFunctionalityInScorecard(SheetName, rowNum, "Offer Sick Leave and Flexible Work", Commodity);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_05_ReviewTest.Equity_TC_10_12_ValidatePostReviewInScorecardAlternative" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_13_MoveOnArchiveAndValidateArchiveInDocumentListForFeatureDoc(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Feature Post Review Functionality");
			try {				
				rc.clickDocument();
				equity.ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(SheetName, rowNum, Commodity, "Feature");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_05_ReviewTest.Equity_TC_10_13_MoveOnArchiveAndValidateArchiveInDocumentListForFeatureDoc" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_14_MoveOnArchiveAndValidateArchiveInDocumentListForAuditDoc(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Audit Post Review Functionality");
			try {				
				equity.ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(SheetName, rowNum, Commodity, "Audit");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_05_ReviewTest.Equity_TC_10_14_MoveOnArchiveAndValidateArchiveInDocumentListForAuditDoc" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_15_MoveOnArchiveAndValidateArchiveInDocumentListForAlternativeDoc(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Alternative Post Review Functionality");
			try {
				equity.ValidatePostReviewArchiveDocumentFunctionalityInDocumentsLibrary(SheetName, rowNum, Commodity, "Alternative");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_05_ReviewTest.Equity_TC_10_15_MoveOnArchiveAndValidateArchiveInDocumentListForAlternativeDoc" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_16_purseYesToMaybeValidFromScorecard(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Purse Yes to Maybe from Scorecard Functionality");
			try {
				rc.clickScorecard();
				equity.purseYesToMaybeValidFromScorecard(SheetName, rowNum, Commodity);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	    
	    @Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_CTC_05_ReviewTest.Equity_TC_10_16_purseYesToMaybeValidFromScorecard" })
		@Parameters({ "SheetName","rowNum","Commodity" })
		public void Equity_TC_10_17_purseYesToNoValidFromScorecard(String SheetName,int rowNum, String Commodity) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName,"Validate Purse Yes to No from Scorecard Functionality");
			try {
				equity.purseYesToNoValidFromScorecard(SheetName, rowNum, Commodity);
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
}
