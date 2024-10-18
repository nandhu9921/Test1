package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_20B_ReviewEquityPortfolioTest extends BaseClass {

	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_20A_EquityDocumentTest.Portfolio_TC_20A_02_FeatureUploadDocumentInDocument"})
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_20B_00_WERSubmitReviewPortfolio(String SheetName, int rowNum) throws IOException {

	    TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Submitting Review for WER Opt");

		try {
			portfolio.PortfolioOptSubmitReviewDocument(SheetName, rowNum,"PortfolioOptScorecardWer");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_00_WERSubmitReviewPortfolio"})
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_20B_01_WERCompletetReviewPortfolio(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName, "Completing Review for WER Opt");

		try {
			login.AdminLogin();
			portfolio.AdminEditReviewMrc(SheetName, rowNum);
			portfolio.ReSubmitReview();
			portfolio.AdminCompleteReviewDocument(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);	
			portfolio.InitiateRound2Review(SheetName, rowNum);
			login.AdminLogin();
			portfolio.AdminCompleteRound2Review(SheetName, rowNum);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_01_WERCompletetReviewPortfolio" })
	@Parameters({"Commodity" })
	public void Portfolio_TC_20B_02_AdminImportDocumentRulingsV2Review(String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Admin Import Document Rulings Functionality");

		try {
			portfolio.AdminImportDocumentRulingsV2Review(Commodity);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_02_AdminImportDocumentRulingsV2Review" })
	@Parameters({"Commodity" })
	public void Portfolio_TC_20B_03_AdminImportAchievementsHsrReview(String Commodity) throws IOException {


		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Admin Import Achievements Functionality");
		try {
			portfolio.AdminImportAchievementsHsrReview(Commodity);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_03_AdminImportAchievementsHsrReview" })
	@Parameters({"Commodity" })
	public void Portfolio_TC_20B_04_AdminImportLocationScoresWprReview(String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Review Import Location Scores Functionality");
		try {
			portfolio.AdminImportLocationScoresWprReview(Commodity);
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_04_AdminImportLocationScoresWprReview" })
	@Parameters({ "Commodity" })
	public void Portfolio_TC_20B_05_AdminImportResultQCWerReview(String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Import Compare Current Score Functionality");

		try {
			portfolio.AdminImportResultQCWerReviewWerReview(Commodity,"WELL Equity Rating", "Year 1, Review Cycle #4 Round 2");
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_05_AdminImportResultQCWerReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_20B_06_AdminUploadReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Review Upload document Functionality");
		try {
		portfolio.AdminReviewUpload(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_06_AdminUploadReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_20B_07_ValidAdminReviewUpload(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"WELL At Scale Valid Review Upload Document in Import logs Functionality");

		try {
			portfolio.AdminReviewImportUploadedFile(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_07_ValidAdminReviewUpload" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_20B_08_DeleteAdminReviewUpload(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Delete Upload review document Functionality");

		try {
			portfolio.DeleteAdminReviewUpload(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_08_DeleteAdminReviewUpload"})
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_TC_20B_09_ValidAddLocation(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validation Location Count Functionality");

			try {
				rc.SignOut();
				login.Login();
			    portfolio.SearchPortfolioById(SheetName, rowNum);
			    portfolio.ValidAddLocation(SheetName, rowNum,"6");
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
		
		@Test(dependsOnMethods = {"com.Well.testcases.Portfolio.Portfolio_TC_20B_ReviewEquityPortfolioTest.Portfolio_TC_20B_09_ValidAddLocation"})
		@Parameters({ "SheetName", "rowNum" })
		public void Portfolio_TC_20B_10_ValidateWERSectionInAdminFields(String SheetName, int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName, "Validate WELL Equity Rating Text and Section");

			try {
				login.AdminLogin();
			    portfolio.AdminSearch(SheetName, rowNum);
			    portfolio.ValidateWERSection();
				
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
}