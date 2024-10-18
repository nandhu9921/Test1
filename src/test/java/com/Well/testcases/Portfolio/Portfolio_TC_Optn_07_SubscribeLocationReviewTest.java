package com.Well.testcases.Portfolio;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Well.Engine.BaseClass;

public class Portfolio_TC_Optn_07_SubscribeLocationReviewTest extends BaseClass {
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_06_SubscribeLocationReplaceOptnTest.PortfolioSubscribeLocationOptnReplace_TC_06_02_AlternativeScorecardUpload" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_Optn_07_00_LocationAccountCompleteScorecard(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Account, Location Account and Upload Scorecard document WELL At Scale Project Functionality");
		try {
			login.AdminLogin();
			portfolio.adminNavigateAddedLocation(SheetName, rowNum);
			v2project.BuildScorecardV2ProjectById(SheetName, rowNum);	
			commonAPI.storeScorecardIdForLocationAccountAPI(SheetName,rowNum, "ScorecardId","scorecard_id");
			commonAPI.fillScorecardAPIWithPoints(SheetName,rowNum, "ScorecardId","100");	
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_07_SubscribeLocationReviewTest.Portfolio_TC_Optn_07_00_LocationAccountCompleteScorecard" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_Optn_07_01_LocationAccountSubmitReview(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Account, Location Account and Upload Scorecard document WELL At Scale Project Functionality");
		try {
			v2project.SubmitReviewV2Project(SheetName, rowNum,"Preliminary Precertification Review","ReviewViewButton");	
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_07_SubscribeLocationReviewTest.Portfolio_TC_Optn_07_01_LocationAccountSubmitReview" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_Optn_07_02_LocationAccountAdminCompleteReview(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Account, Location Account and Upload Scorecard document WELL At Scale Project Functionality");
		try {
			v2project.AdminCompleteReview(SheetName, rowNum, "Preliminary Precertification Review","ReviewViewButton");
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	
	@Test(dependsOnMethods = { "com.Well.testcases.Portfolio.Portfolio_TC_Optn_07_SubscribeLocationReviewTest.Portfolio_TC_Optn_07_02_LocationAccountAdminCompleteReview" })
	@Parameters({ "SheetName", "rowNum" })
	public void Portfolio_TC_Optn_07_03_LocationAccountSubmitAndCompleteOptnRatingReview(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"Register Portfolio Account, Location Account and Upload Scorecard document WELL At Scale Project Functionality");
		try {
			v2project.V2ProjectOpt(SheetName, rowNum, "EquityTab");
			String Id = commonAPI.storeprojectId(SheetName, rowNum);
			commonAPI.storeScorecardIdForLocationAccountOptnAPI(SheetName,rowNum, "ScorecardWerOptn","wer.scorecard_id", Id);
			commonAPI.fillScorecardForLocationAccountOptnAPI(SheetName,rowNum, "ScorecardWerOptn","ScorecardWerOptn");
			v2project.werSubmitReviewV2Project(SheetName, rowNum);
			v2project.werCompleteReview(SheetName, rowNum);
		
		} 
		catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	}

