package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_09_ReviewTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_08_DocumentTest.Portfolio_TC_08_02_FeatureUploadDocumentInDocument" })
	@Parameters({ "SheetName","rowNum","Commodity","ProjectType" })
	public void Portfolio_TC_09_00_UploadDocumentsInLocationAccount(String SheetName, int rowNum, String Commodity, String ProjectType) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			portfolio.UploadDocumentsInLocationAccount(SheetName, rowNum, Commodity, ProjectType);
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_00_UploadDocumentsInLocationAccount" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Portfolio_TC_09_01_SubmitRound1Review(String SheetName,int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			portfolio.SubmitReviewDocument(SheetName, rowNum);
			portfolio.deleteVaidateInAuthUser("PortfolioReviewListViewButton");
			login.AdminLogin();
			portfolio.AdminSearch(SheetName, rowNum);
			portfolio.deleteReview(SheetName, rowNum,"PortfolioReviewListViewButton",Commodity);
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);	
			portfolio.SubmitReviewDocument(SheetName, rowNum);
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_01_SubmitRound1Review" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_09_02_ValidateDocumentsStatusAsUnderReview(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			portfolio.clickDocument();
			portfolio.ValidateDocumentsStatusAsUnderReview("Under Review");
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_02_ValidateDocumentsStatusAsUnderReview" })
	@Parameters({ "SheetName","rowNum","Country", "ProjectType","Engagement_level","Upload","API", "Commodity","ImportLocation","ProjectName"})
	public void Portfolio_TC_09_03_ValidateLocationsExternalProjectAsUnderReview(String SheetName, int rowNum, String Country, String ProjectType, String Engagement_level,String Upload, String API, String Commodity, String ImportLocation, String ProjectName) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			
			String Type = "WELLCore";
			Boolean ProType;
			if(Type.equalsIgnoreCase("WELLCore")) {
				ProType =true;
			}
			else {
				ProType =false;
			}
			SheetName = "V2Project";
			commonAPI.StoreAnyProject(SheetName, rowNum, Country, ProjectType, Upload, API, "SingleAsset",ImportLocation, "AutomationV2UpsellV2project", ProType);
			commonAPI.SignAgreementProject(SheetName, rowNum,  "SingleAsset");
			commonAPI.RegisterProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			commonAPI.BillingProject(SheetName, rowNum, Country, ProjectType, "SingleAsset");
			login.AdminLogin();
			SheetName = "Portfolio";
			if (TestNGTestName.contains("Pilot")){
				SheetName = "Custom_Portfolio";
			}
			portfolio.AdminSearch(SheetName,rowNum);	
			portfolio.ValidateLocationsExternalProjectAsUnderReview(SheetName, rowNum, "This account is currently under review");
			
			} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
		@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_03_ValidateLocationsExternalProjectAsUnderReview" })
		@Parameters({ "SheetName","rowNum" })
		public void Portfolio_TC_09_04_VerifyLocationUpdatesRestricts(String SheetName,int rowNum) throws IOException {

			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

			StartTest(TestCaseName,"WELL At Scale Verify Location Updates Restricts Functionality");

			try {
				rc.SignOut();
				login.Login();
				portfolio.SearchPortfolioById(SheetName, rowNum);
				portfolio.VerifyLocationUpdatesRestricts(SheetName, rowNum);

			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_04_VerifyLocationUpdatesRestricts" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_09_05_EditRound1ReviewToMRC(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			login.AdminLogin();
			portfolio.AdminEditReviewMrc(SheetName, rowNum);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_05_EditRound1ReviewToMRC" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Portfolio_TC_09_06_ValidateScorecardMRCFromUnderReviewToReadyForReview(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Scorecard MRC from UnderReview to Ready for Review Functionality");

		try {
			rc.clickScorecard();
			portfolio.ValidateMRCFromUnderReviewToReadyForReviewInScorecard("Meet Thresholds for Particulate Matter", SheetName, rowNum, Commodity);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_06_ValidateScorecardMRCFromUnderReviewToReadyForReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Portfolio_TC_09_07_ValidateDocumentsLibMRCFromUnderReviewToReadyForReview(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"Validate Documents Library MRC from UnderReview to Ready for Review Functionality");

		try {
			rc.clickDocument();
			portfolio.ValidateMRCFromUnderReviewToReadyForReviewInDocumentLib(SheetName, rowNum, Commodity);

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_07_ValidateDocumentsLibMRCFromUnderReviewToReadyForReview" })
	@Parameters({ "SheetName","rowNum","Commodity" })
	public void Portfolio_TC_09_08_ResubmitReview(String SheetName, int rowNum, String Commodity) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"ReSubmit Review Functionality");

		try {
			portfolio.ReSubmitReview();

		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_08_ResubmitReview" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_09_09_CompleteRound1Review(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
		    portfolio.AdminCompleteReviewDocument(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);	
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_09_CompleteRound1Review" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_09_10_InitiateRound2Review(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
			portfolio.InitiateRound2Review(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_09_ReviewTest.Portfolio_TC_09_10_InitiateRound2Review" })
	@Parameters({ "SheetName","rowNum" })
	public void Portfolio_TC_09_11_AdminCompleteRound2Review(String SheetName,int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();

		StartTest(TestCaseName,"WELL At Scale Review Submit Functionality");

		try {
		    login.AdminLogin();
			portfolio.AdminCompleteRound2Review(SheetName, rowNum);
			rc.SignOut();
			login.Login();
			portfolio.SearchPortfolioById(SheetName, rowNum);	
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}